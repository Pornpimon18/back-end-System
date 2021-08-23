package com.thailife.tax.utils;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class IdGenerator {

    public static synchronized String getIdExtact(String site, String dataType) {
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
        DATE_TIME_FORMAT.setTimeZone(TimeZone.getDefault());
        Random rand = new Random();
        int value3 = rand.nextInt(9999);
        DecimalFormat df3 = new DecimalFormat("0000");
        if (dataType.equals("DOM")) {
            dataType = "1";
        } else if (dataType.equals("CROSS")) {
            dataType = "2";
        } else {
            dataType = "4";
        }
        String id = site + DATE_TIME_FORMAT.format(new Date()) + dataType + df3.format(value3);
        return id;
    }

    public static synchronized String getId() {
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MMddHHmmssSSS");
        DATE_TIME_FORMAT.setTimeZone(TimeZone.getDefault());
        SecureRandom rand = new SecureRandom();
        int value = rand.nextInt(99);
        DecimalFormat df = new DecimalFormat("00");
        String id = DATE_TIME_FORMAT.format(new Date()) + df.format(value);
        return  id;
    }

    public static synchronized String getId(String site, String dataTypeCode, int seq) {
        return getId(site, dataTypeCode, new Date(), seq);
    }

    public static synchronized String getId(String site, String dataTypeCode, Date refDate, int seq) {
       
        String digitDate = null;
        String digitRandom = null;
        String digitSeq = null;

        digitDate = getDigitDate(refDate);
        digitRandom = getDigitRandom();
        digitSeq = getDigitSeq(seq);

        return  digitDate + digitRandom + digitSeq;
    }

    private static String getDigitSeq(int seq) {
        int value = seq % 100;
        return String.format("%02d", value);
    }

    private static String getDigitRandom() {
        Random rand = new Random();
        int value = rand.nextInt(999);
        return String.format("%04d", value);
    }

    public static String dateTime() {
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return DATE_TIME_FORMAT.format(new Date());
    }

    private static String getDigitDate(Date refDate) {
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyMMddHHmmss");
        return DATE_TIME_FORMAT.format(refDate);
    }

    private static String getDigitDate() {
        return getDigitDate(new Date());
    }

}
