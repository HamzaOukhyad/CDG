package ma.cdgcapital.saham.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import ma.cdgcapital.saham.model.Compte;
import ma.cdgcapital.saham.model.Operation;
import ma.cdgcapital.saham.model.Position;
import ma.cdgcapital.saham.model.XlsColumn;
import ma.cdgcapital.saham.model.provider.CompteTitreProvider;
import ma.cdgcapital.saham.utils.CommonDate;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelFileExporter {

  private static CompteTitreProvider compteTitreProvider;

  @Autowired
  public ExcelFileExporter(CompteTitreProvider compteTitreProvider) {
    this.compteTitreProvider = compteTitreProvider;
  }

  private static final List<XlsColumn> parentColumns =
      Arrays.asList(
          new XlsColumn("Date", 2), new XlsColumn("Opération", 2), new XlsColumn("Valeur", 6));
  private static final List<XlsColumn> childColumns =
      Arrays.asList(
          new XlsColumn("Traitement", 1),
          new XlsColumn("Opération", 1),
          new XlsColumn("N° transaction", 1),
          new XlsColumn("Libellé", 1),
          new XlsColumn("Code valeur", 1),
          new XlsColumn("Désignation valeur", 1),
          new XlsColumn("Quantité", 1),
          new XlsColumn("Prix unitaire", 1),
          new XlsColumn("Montant Brut", 1),
          new XlsColumn("Montant Net", 1));

  private static final List<XlsColumn> positionColumns =
      Arrays.asList(
          new XlsColumn("DATE EXTRATION", 1),
          new XlsColumn("IDCLIENT", 1),
          new XlsColumn("CLIENT LIBELLE", 1),
          new XlsColumn("COMPTE TITRE", 1),
          new XlsColumn("COMPTES ESPECES", 1),
          new XlsColumn("ISIN", 1),
          new XlsColumn("ISIN DESCRIPTION", 1),
          new XlsColumn("QUANTITE", 1),
          new XlsColumn("PRIX UNITAIRE", 1),
          new XlsColumn("VALORISATION POSITION", 1),
          new XlsColumn("NATURE POSITION", 1));

  private static final List<XlsColumn> pos =
	      Arrays.asList(
	          );

  
  public static HSSFFont generateFont(
      HSSFWorkbook wb, short bold, short color, String fontName, short fontHeight) {
    HSSFFont font = wb.createFont();
    font.setBoldweight(bold);
    font.setColor(color);
    font.setFontName(fontName);
    font.setFontHeightInPoints(fontHeight);
    return font;
  }

  public static HSSFCellStyle cellStyle(HSSFWorkbook wb, HSSFFont font, short background) {
    HSSFCellStyle cellStyle = wb.createCellStyle();
    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    cellStyle.setFont(font);
    cellStyle.setFillForegroundColor(background);
    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
    return cellStyle;
  }

  public static void createRow(
      HSSFSheet sheet,
      String cellValue,
      HSSFCellStyle cellStyle,
      int rowFrom,
      int rowTo,
      int colFrom,
      int colTo,
      List<XlsColumn> columns) {
    HSSFRow row = sheet.createRow(rowFrom);
    if (columns.isEmpty()) {
      sheet.addMergedRegion(new Region(rowFrom, (short) colFrom, rowTo, (short) colTo));
      HSSFCell cell = row.createCell((short) colFrom);
      cell.setCellValue(cellValue);
      cell.setCellStyle(cellStyle);

    } else {
      int beginCell = colFrom;
      for (XlsColumn column : columns) {
        HSSFCell cell = row.createCell((short) beginCell);
        sheet.addMergedRegion(
            new Region(
                (short) rowFrom,
                (short) beginCell,
                (short) rowTo,
                (short) (column.getMergedNumber() + beginCell - 1)));
        cell.setCellValue(column.getName());
        cell.setCellStyle(cellStyle);
        beginCell = (short) (beginCell + column.getMergedNumber());
      }
    }
  }

  public static String todayAmountTwo() {
    Date date = new Date();
    date = DateUtils.addDays(date, -2);
    return CommonDate.toStringFormat("dd/MM/yyyy", date);
  }

  public static void generatePositionQuotidienneComptesTitres() throws IOException {

    List<Position> positions = compteTitreProvider.getPositions("1410000199509");
    Compte compte = compteTitreProvider.getCompteTitre("1410000199509");
    
   
    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet("Feuil1");

    HSSFFont fontTitle =
        generateFont(
            wb, HSSFFont.BOLDWEIGHT_BOLD, IndexedColors.WHITE.getIndex(), "Calibri", (short) 11);
    createRow(
        sheet,
        null,
        cellStyle(wb, fontTitle, IndexedColors.DARK_BLUE.index),
        1,
        1,
        1,
        1,
        positionColumns);
    sheet.setDisplayGridlines(false);
    for (int i = 0; i < positionColumns.size(); i++) {
      sheet.autoSizeColumn((short) i);
    }

    int rowNum = 2;
    for (int i = 0; i < positions.size(); i++) {
      sheet.autoSizeColumn((short) i);
      HSSFRow row = sheet.createRow(rowNum++);
      row.createCell((short) 1).setCellValue(todayAmountTwo());
      row.createCell((short) 2).setCellValue(compte.getIdClientTitre());
      row.createCell((short) 3).setCellValue(compte.getIntitule());
      row.createCell((short) 4).setCellValue(positions.get(i).getNumeroCompte());
      row.createCell((short) 5).setCellValue(compte.getNumeroCompteEspeceAttache());
      row.createCell((short) 6).setCellValue(positions.get(i).getCodeIsin());
      row.createCell((short) 7).setCellValue(positions.get(i).getDescriptionTitre());
      row.createCell((short) 8).setCellValue(positions.get(i).getQuantite());
      row.createCell((short) 11).setCellValue("Settled");
    }

    FileOutputStream out = new FileOutputStream("feuille 1.xls");
    wb.write(out);
    out.close();
  }

  public static void generateReleveChronoOperationsTitres() throws IOException {
	  Operation operation = compteTitreProvider.getReleveChronologiques("1410000199509");
	  
	  
	  HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("SMC");

	    HSSFFont fontTitle =
	        generateFont(
	            wb, HSSFFont.BOLDWEIGHT_BOLD, IndexedColors.WHITE.getIndex(), "Calibri", (short) 11);
	    
	    HSSFFont fontTitle1 =
		        generateFont(
		            wb, HSSFFont.BOLDWEIGHT_BOLD, IndexedColors.BLUE.getIndex(), "Calibri", (short) 11);
	    createRow(
	        sheet,
	       null,
	        cellStyle(wb, fontTitle, IndexedColors.GREY_50_PERCENT.index),
	        3,
	        3,
	        2,
	        14,
	        parentColumns);
	  
	    
	    createRow(
		        sheet,
		        null,
		        cellStyle(wb, fontTitle, IndexedColors.GREY_50_PERCENT.index),
		        4,
		        4,
		        2,
		        14,
		        childColumns);
	
	    
		    createRow(
			        sheet,
			        "Relevé chronologique des opérations Titres",
			        cellStyle(wb, fontTitle1, IndexedColors.WHITE.index),
			        0,
			        0,
			        2,
			        9,
			        pos);
			
		    

			    createRow(
				        sheet,
				        "Du : 01/01/2018 Au  11/06/2021  SMC",
				        cellStyle(wb, fontTitle1, IndexedColors.WHITE.index),
				        1,
				        2,
				        1,
				        9,
				        pos);
			sheet.setDisplayGridlines(false);
				    
			System.out.print(operation.getContent().size());
			
			
			 int rowNum = 5;
			    for (int i = 0; i < operation.getContent().size(); i++) {
			      sheet.autoSizeColumn((short) i);
			      HSSFRow row = sheet.createRow(rowNum++);
			      row.createCell((short) 2).setCellValue(todayAmountTwo());
			      row.createCell((short) 3).setCellValue(operation.getContent().get(i).getDateOperation());
			      row.createCell((short) 4).setCellValue(operation.getContent().get(i).getReferenceCro());
			      row.createCell((short) 5).setCellValue(operation.getContent().get(i).getLibelle());
			      row.createCell((short) 6).setCellValue(operation.getContent().get(i).getCodeIsin());
			      row.createCell((short) 7).setCellValue(operation.getContent().get(i).getDescriptionTitre());
			      row.createCell((short) 8).setCellValue(operation.getContent().get(i).getQuantite());
			      row.createCell((short) 10).setCellValue(operation.getContent().get(i).getMontantBrut());
			      row.createCell((short) 11).setCellValue(operation.getContent().get(i).getMontantNet());
			      
			    }

	    FileOutputStream out = new FileOutputStream("SMC.xls");
	    wb.write(out);
	    out.close();
	    
  }
}
