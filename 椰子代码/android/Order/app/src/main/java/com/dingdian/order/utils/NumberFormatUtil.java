package com.dingdian.order.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberFormatUtil {

    public static final String _8LengthPattern = "0.00000000";
    public static final String _4LengthPattern = "0.0000";
    public static final String _2LengthPattern = "0.00";

    public static String format(String pattern, double input) {
        DecimalFormat df = new DecimalFormat(pattern);
        df.setRoundingMode(RoundingMode.DOWN);
        String result = df.format(BigDecimal.valueOf(input));
        return result;
    }

    public static String format(String pattern, BigDecimal input) {
        DecimalFormat df = new DecimalFormat(pattern);
        df.setRoundingMode(RoundingMode.DOWN);
        String result = df.format(input);
        return result;
    }

    public static String format(double input) {
        return format(_2LengthPattern,input);
    }
}
