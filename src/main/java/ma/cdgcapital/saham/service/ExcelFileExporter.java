package ma.cdgcapital.saham.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import ma.cdgcapital.saham.model.XlsColumn;
import ma.cdgcapital.saham.model.provider.CompteTitreProvider;
import ma.cdgcapital.saham.utils.CommonDate;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelFileExporter {

  private final CompteTitreProvider compteTitreProvider;

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
          new XlsColumn("DATEEXTRATION", 1),
          new XlsColumn("IDCLIENT", 1),
          new XlsColumn("CLIENTLIBELLE", 1),
          new XlsColumn("COMPTETITRE", 1),
          new XlsColumn("COMPTESESPECES", 1),
          new XlsColumn("ISIN", 1),
          new XlsColumn("ISINDESCRIPTION", 1),
          new XlsColumn("QUANTITE", 1),
          new XlsColumn("PRIXUNITAIRE", 1),
          new XlsColumn("VALORISATIONPOSITION", 1),
          new XlsColumn("NATUREPOSITION", 1));

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

  public String todayAmountTwo() {
    Date date = new Date();
    date = DateUtils.addDays(date, -2);
    return CommonDate.toStringFormat("dd/MM/yyyy", date);
  }

  public static void generatePositionQuotidienneComptesTitres() {}

  public static void generateReleveChronoOperationsTitres() {}
}
