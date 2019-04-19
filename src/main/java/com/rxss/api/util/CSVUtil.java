package com.rxss.api.util;

import com.rxss.api.model.PharmacyInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CSVUtil {

    public static final String SEPARATOR = ",";
    private static String line = "";
    public static List<PharmacyInfo> pharmacyMasterList;

    public static void readCSVFile(InputStream stream) {
        pharmacyMasterList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {

        br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] pharmacyLine = line.split(SEPARATOR);

                PharmacyInfo pharmacyInfo = new PharmacyInfo();
                System.out.println("Pharmacy [code= " + pharmacyLine[0] + " , name=" + pharmacyLine[1] + "]");
                pharmacyInfo.setUuid(UUID.randomUUID().toString());
                pharmacyInfo.setName(removeQuotes(pharmacyLine[0]));
                pharmacyInfo.setAddress(removeQuotes(pharmacyLine[1]));
                pharmacyInfo.setCity(removeQuotes(pharmacyLine[2]));
                pharmacyInfo.setState(removeQuotes(pharmacyLine[3]));
                pharmacyInfo.setZipCode(Integer.parseInt(pharmacyLine[4]));
                pharmacyInfo.setLatitude(Double.parseDouble(pharmacyLine[5]));
                pharmacyInfo.setLongitude(Double.parseDouble(pharmacyLine[6]));
                pharmacyMasterList.add(pharmacyInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String removeQuotes(String stringWithQuotes) {
        return stringWithQuotes.replaceAll("^\"|\"$","");
    }
}
