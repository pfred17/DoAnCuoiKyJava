package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormat {
    public String formatDonGia(double donGia) {
        Locale locale = new Locale("en", "EN");
        String pattern = "###,###.##";
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat
                .getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);
        return decimalFormat.format(donGia);
    }
}
