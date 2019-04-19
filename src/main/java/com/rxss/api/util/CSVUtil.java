package com.rxss.api.util;

import com.rxss.api.model.PharmacyInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static final String SEPARATOR = ",";
    private static String line = "";

    public static List<PharmacyInfo> readCSVFile(InputStream stream) throws Exception {
        List<PharmacyInfo> pharmacyMasterList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {

        br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] pharmacyLine = line.split(SEPARATOR);

                PharmacyInfo pharmacyInfo = new PharmacyInfo();
                pharmacyInfo.setName(formatStringValue(pharmacyLine[0]));
                pharmacyInfo.setAddress(formatStringValue(pharmacyLine[1]));
                pharmacyInfo.setCity(formatStringValue(pharmacyLine[2]));
                pharmacyInfo.setState(formatStringValue(pharmacyLine[3]));
                pharmacyInfo.setZipCode(Integer.parseInt(pharmacyLine[4]));
                pharmacyInfo.setLatitude(Double.parseDouble(pharmacyLine[5]));
                pharmacyInfo.setLongitude(Double.parseDouble(pharmacyLine[6]));
                pharmacyMasterList.add(pharmacyInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return pharmacyMasterList;
    }

    public static String formatStringValue(String stringWithQuotes) {
        // remove leading and trailing quotes and whitespaces
        return stringWithQuotes.replaceAll("^\"|\"$","").trim();
    }
}
