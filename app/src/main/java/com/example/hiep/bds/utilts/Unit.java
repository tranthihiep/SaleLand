package com.example.hiep.bds.utilts;

import java.text.DecimalFormat;

public class Unit {
    public static String formatPrice(Long price){
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(price);
        return format;
    }


}
