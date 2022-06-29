package ma.cdgcapital.saham.service;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.cdgcapital.saham.model.Compte;
import ma.cdgcapital.saham.model.Operation;
import ma.cdgcapital.saham.model.Position;
import ma.cdgcapital.saham.model.XlsColumn;
import ma.cdgcapital.saham.model.provider.CompteTitreProvider;
import ma.cdgcapital.saham.utils.CommonDate;

@Service
public class ExcelFileExporter {

  private static CompteTitreProvider compteTitreProvider;

  @Autowired
  public ExcelFileExporter(CompteTitreProvider compteTitreProvider) {
    this.compteTitreProvider = compteTitreProvider;
  }

//  private static final List<XlsColumn> parentColumns =
//      Arrays.asList(
//          new XlsColumn("Date", 2), new XlsColumn("Opération", 2), new XlsColumn("Valeur", 6));
//  private static final List<XlsColumn> childColumns =
//      Arrays.asList(
//          new XlsColumn("Traitement", 1),
//          new XlsColumn("Opération", 1),
//          new XlsColumn("N° transaction", 1),
//          new XlsColumn("Libellé", 1),
//          new XlsColumn("Code valeur", 1),
//          new XlsColumn("Désignation valeur", 1),
//          new XlsColumn("Quantité", 1),
//          new XlsColumn("Prix unitaire", 2),
//          new XlsColumn("Montant Brut", 1),
//          new XlsColumn("Montant Net", 3));
//
//  private static final List<XlsColumn> positionColumns =
//      Arrays.asList(
//          new XlsColumn("DATE EXTRATION", 1),
//          new XlsColumn("ID CLIENT", 1),
//          new XlsColumn("CLIENT LIBELLE", 1),
//          new XlsColumn("COMPTE TITRE", 1),
//          new XlsColumn("COMPTES ESPECES", 1),
//          new XlsColumn("CODE ISIN", 1),
//          new XlsColumn("ISIN DESCRIPTION", 1),
//          new XlsColumn("QUANTITE", 1),
//          new XlsColumn("PRIX UNITAIRE", 1),
//          new XlsColumn("VALORISATION POSITION", 1),
//          new XlsColumn("NATURE POSITION", 1));
//  
//  private static final List<XlsColumn> positionLigne1 =
//	      Arrays.asList(
//	          new XlsColumn("Relevé chronologique des opérations Titres", 9));
//
//  private static final List<XlsColumn> positionLigne2 =
//	      Arrays.asList(
//	          new XlsColumn("Du : 01/01/2018 Au  11/06/2021  SMC", 9));
//
//  private static final List<XlsColumn> positionLigne3 =
//	      Arrays.asList(
//	          new XlsColumn("DATE", 2),
//	          new XlsColumn("OPERATION", 2),
//	          new XlsColumn("VALORISATION", 9));
//  public static HSSFFont generateFont(
//      HSSFWorkbook wb, String fontName, short fontHeight, short Color) {
//    HSSFFont font = wb.createFont();
//    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//    font.setColor(Color);
//    font.setFontName(fontName);
//    font.setFontHeightInPoints(fontHeight);
//    return font;
//  }
//  
//  
//  public static HSSFCellStyle cellStyle(HSSFWorkbook wb, HSSFFont font,short background) {
//    HSSFCellStyle cellStyle = wb.createCellStyle();
//    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//    cellStyle.setFont(font);
//    cellStyle.setFillForegroundColor(background);
//    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//    cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
//    return cellStyle;
//  }
//  
//  
//  
//  public static void createRow(
//      HSSFSheet sheet,
//      String cellValue,
//      HSSFCellStyle cellStyle,
//      int rowFrom,
//      int rowTo,
//      int colFrom,
//      int colTo,
//      List<XlsColumn> columns) {
//    HSSFRow row = sheet.createRow(rowFrom);
//    if (columns.isEmpty()) {
//      sheet.addMergedRegion(new Region(rowFrom, (short) colFrom, rowTo, (short) colTo));
//      HSSFCell cell = row.createCell((short) colFrom);
//      cell.setCellValue(cellValue);
//      cell.setCellStyle(cellStyle);
//   
//    } else {
//      int beginCell = colFrom;
//      for (XlsColumn column : columns) {
//        HSSFCell cell = row.createCell((short) beginCell);
//        sheet.addMergedRegion(
//            new Region(
//                (short) rowFrom,
//                (short) beginCell,
//                (short) rowTo,
//                (short) (column.getMergedNumber() + beginCell - 1)));
//        cell.setCellValue(column.getName());
//        cell.setCellStyle(cellStyle);
//        beginCell = (short) (beginCell + column.getMergedNumber());
//      }
//    }
//  }

  public static String todayAmountTwo() {
    Date date = new Date();
    date = DateUtils.addDays(date, -2);
    return CommonDate.toStringFormat("dd/MM/yyyy", date);
  }

  public static void generatePositionQuotidienneComptesTitres() throws IOException {
	
	  List<Position> l=new ArrayList<Position>();
	  
      l=compteTitreProvider.getPositions("1410000199509");
      
      
      
      Compte compte= compteTitreProvider.getCompteTitre("1410000199509");
      
	  //Blank workbook
      HSSFWorkbook workbook = new HSSFWorkbook(); 
       
      //Create a blank sheet
      HSSFSheet sheet = workbook.createSheet("feuille 1");
     
      sheet.setDisplayGridlines(false);
      
      Row row = sheet.createRow(1);
		
       
      HSSFFont font= workbook.createFont();
      font.setFontHeightInPoints((short)10);
      font.setFontName("Arial");
      font.setColor(IndexedColors.BLUE.getIndex());
      
      font.setItalic(true);
		// Define header cell style
      CellStyle headerCellStyle = workbook.createCellStyle();
      headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
      headerCellStyle.setFillForegroundColor((short) 4);    
      headerCellStyle.setFont(font);
      
      // Creating header cells 
      Cell cell = row.createCell(1);
      cell.setCellValue("Date relevé");
      cell.setCellStyle(headerCellStyle);
      
      cell = row.createCell(2);
      cell.setCellValue("ID Client");
      cell.setCellStyle(headerCellStyle);
      
      cell = row.createCell(3);
      cell.setCellValue("Client : libelle");
      cell.setCellStyle(headerCellStyle);
      
      cell = row.createCell(4);
      cell.setCellValue("Compte Titre");
      cell.setCellStyle(headerCellStyle);
      
      cell = row.createCell(5);
      cell.setCellValue("Comptes espèces");
      cell.setCellStyle(headerCellStyle);
      
      cell = row.createCell(6);
      cell.setCellValue("Code ISIN");
      cell.setCellStyle(headerCellStyle);
      
      cell = row.createCell(7);
      cell.setCellValue("Description Titre");
      cell.setCellStyle(headerCellStyle);
      
      cell = row.createCell(8);
      cell.setCellValue("Quantité");
      cell.setCellStyle(headerCellStyle);
      
      cell = row.createCell(9);
      cell.setCellValue("Prix unitaire");
      cell.setCellStyle(headerCellStyle);
      
      cell = row.createCell(10);
      cell.setCellValue("Valorisation Position");
      cell.setCellStyle(headerCellStyle);
      
      cell = row.createCell(11);
      cell.setCellValue("Nature position");
      cell.setCellStyle(headerCellStyle);
      
      for(int i = 1; i < l.size(); i++) {
      	Row dataRow = sheet.createRow(i + 1);
      	
      	dataRow.createCell(1).setCellValue(todayAmountTwo());
      	dataRow.createCell(2).setCellValue(compte.getIdClientTitre());
      	dataRow.createCell(3).setCellValue(compte.getIntitule());
      	dataRow.createCell(4).setCellValue(l.get(i).getNumeroCompte());
      	dataRow.createCell(5).setCellValue(compte.getNumeroCompteEspeceAttache());
      	dataRow.createCell(6).setCellValue(l.get(i).getCodeIsin());
      	dataRow.createCell(7).setCellValue(l.get(i).getDescriptionTitre());
      	dataRow.createCell(8).setCellValue(l.get(i).getQuantite());
      	dataRow.createCell(11).setCellValue("Settled");

      }
      
      
      FileOutputStream out = new FileOutputStream(new File("feuille 1.xls"));
      workbook.write(out);
      out.close();
      System.out.println("feuille 1.xls written successfully");
   }
      
      

  public static void generateReleveChronoOperationsTitres() throws IOException {
	  
	  Operation operation= compteTitreProvider.getReleveChronologiques("1410000199509");
	  
	  
	  //Blank workbook
      HSSFWorkbook workbook = new HSSFWorkbook(); 
       
      //Create a blank sheet
      HSSFSheet sheet = workbook.createSheet("SMC");
      
      Row row = sheet.createRow(0);
    
      sheet.addMergedRegion(new CellRangeAddress(0,0,1,9));  
      
      HSSFFont font= workbook.createFont();
      font.setFontHeightInPoints((short)10);
      font.setFontName("Arial");
      font.setColor(IndexedColors.BLUE.getIndex());
      font.setItalic(true);
      
      
      HSSFFont font1= workbook.createFont();
      font1.setFontHeightInPoints((short)10);
      font1.setFontName("Arial");
      font1.setColor(IndexedColors.BLACK.getIndex());
      font1.setItalic(true);
      
   // Define header cell style
      CellStyle headerCellStyle1 = workbook.createCellStyle();
      headerCellStyle1.setFillBackgroundColor((short) 4);
      headerCellStyle1.setAlignment(CellStyle.ALIGN_CENTER);
     
      headerCellStyle1.setFont(font1);
      
	// Define header cell style
      CellStyle headerCellStyle = workbook.createCellStyle();
      headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);   
      headerCellStyle.setFont(font);
      
      headerCellStyle.setFillBackgroundColor((short) 7);
      // Creating header cells 
      Cell cell = row.createCell(1);
      cell.setCellValue("Relevé chronologique des opérations Titres");
      cell.setCellStyle(headerCellStyle);
      
      // Creating header cells 
      Cell cell2 = row.createCell(10);
      cell2.setCellValue("");
      cell2.setCellStyle(headerCellStyle);
      sheet.addMergedRegion(new CellRangeAddress(0,0,10,13)); 
      
      Row row1 = sheet.createRow(1);
      sheet.setDisplayGridlines(false);
      
      // Creating header cells 
      Cell cell1 = row1.createCell(1);
      cell1.setCellValue("Du : 01/01/2018 Au  11/06/2021  SMC");
      cell1.setCellStyle(headerCellStyle);
      
      sheet.addMergedRegion(new CellRangeAddress(1,2,1,9)); 
      
      
      Row row2 = sheet.createRow(3);
      
      // Creating header cells 
      Cell cell22 = row2.createCell(2);
      cell22.setCellValue("Date");
      cell22.setCellStyle(headerCellStyle1);
      sheet.addMergedRegion(new CellRangeAddress(3,3,2,3)); 
   // Creating header cells 
      Cell cell222 = row2.createCell(4);
      cell222.setCellValue("Opération");
      cell222.setCellStyle(headerCellStyle1);
      
      sheet.addMergedRegion(new CellRangeAddress(3,3,4,5)); 

   // Creating header cells 
      Cell cell22a = row2.createCell(6);
      cell22a.setCellValue("Valeur");
      cell22a.setCellStyle(headerCellStyle1);
      
      sheet.addMergedRegion(new CellRangeAddress(3,3,6,14)); 
      
      
      Row row3 = sheet.createRow(4);
      
   // Creating header cells 
      Cell cell3 = row3.createCell(2);
      cell3.setCellValue("Traitement");
      cell3.setCellStyle(headerCellStyle1);
      
   // Creating header cells 
      Cell cell3a = row3.createCell(3);
      cell3a.setCellValue("Opération");
      cell3a.setCellStyle(headerCellStyle1);
      
      
   // Creating header cells 
      Cell cell3b = row3.createCell(4);
      cell3b.setCellValue("N° transaction");
      cell3b.setCellStyle(headerCellStyle1);
      
      
   // Creating header cells 
      Cell cell3c = row3.createCell(5);
      cell3c.setCellValue("Libellé");
      cell3c.setCellStyle(headerCellStyle1);
      
      
   // Creating header cells 
      Cell cell3d = row3.createCell(6);
      cell3d.setCellValue("Code valeur");
      cell3d.setCellStyle(headerCellStyle1);
      
           
   
   // Creating header cells 
      Cell cell3e = row3.createCell(7);
      cell3e.setCellValue("Désignation valeur");
      cell3e.setCellStyle(headerCellStyle1);
      
      
      
   // Creating header cells 
      Cell cell3f = row3.createCell(8);
      cell3f.setCellValue("Quantité");
      cell3f.setCellStyle(headerCellStyle1);
      
      
      
   // Creating header cells 
      Cell cell3g = row3.createCell(9);
      cell3g.setCellValue("Prix unitaire");
      cell3g.setCellStyle(headerCellStyle1);
      
      
      
   // Creating header cells 
      Cell cell3h = row3.createCell(10);
      cell3h.setCellValue("Montant Brut");
      cell3h.setCellStyle(headerCellStyle1);
      
      
   // Creating header cells 
      Cell cell3i = row3.createCell(11);
      cell3i.setCellValue("Montant Net");
      cell3i.setCellStyle(headerCellStyle1);
      
      sheet.setDisplayGridlines(false);
      
      for(int i = 0,j = 5; i < operation.getContent().size(); i++,j++) {
    	 
        	Row dataRow = sheet.createRow(j + 1);
          	dataRow.createCell(2).setCellValue(operation.getContent().get(i).getDateValeur());
          	dataRow.createCell(3).setCellValue(operation.getContent().get(i).getDateOperation());
          	dataRow.createCell(4).setCellValue(operation.getContent().get(i).getReferenceCro());
          	dataRow.createCell(5).setCellValue(operation.getContent().get(i).getLibelle());
          	dataRow.createCell(6).setCellValue(operation.getContent().get(i).getCodeIsin());
          	dataRow.createCell(7).setCellValue(operation.getContent().get(i).getDescriptionTitre());
          	dataRow.createCell(8).setCellValue(operation.getContent().get(i).getQuantite());
          	dataRow.createCell(9).setCellValue(operation.getContent().get(i).getPrix());
          	dataRow.createCell(10).setCellValue(operation.getContent().get(i).getMontantBrut());
          	dataRow.createCell(11).setCellValue(operation.getContent().get(i).getMontantNet());

        }
        
        
      
      FileOutputStream out = new FileOutputStream(new File("SMC.xls"));
      workbook.write(out);
      out.close();
      System.out.println("SMC.xls written successfully");
   }
  }

