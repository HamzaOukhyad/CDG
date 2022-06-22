package ma.cdgcapital.saham.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonDate {

  public static String toStringFormat(String formatDate, Date date) {
    String reponse = "";
    try {
      if (date != null && formatDate != null && !formatDate.equals("")) {
        SimpleDateFormat sDF = new SimpleDateFormat(formatDate);
        reponse = sDF.format(date);
      }
    } catch (Throwable throwable) {
    }
    return reponse;
  }
}
