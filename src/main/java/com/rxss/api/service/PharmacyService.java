package com.rxss.api.service;

import com.rxss.api.model.PharmacyDistanceComparator;
import com.rxss.api.model.PharmacyInfo;
import com.rxss.api.model.PharmacyResponse;
import com.rxss.api.util.CSVUtil;
import com.rxss.api.util.DistanceCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PharmacyService {

    public PharmacyResponse getNearestPharmacy(Double latitude, Double longitude) {
        List<PharmacyResponse> pharmacyResponseList;
        pharmacyResponseList = calculateDistances(latitude, longitude);

        Collections.sort(pharmacyResponseList, new PharmacyDistanceComparator());
        return pharmacyResponseList.get(0);
    }

    private List<PharmacyResponse> calculateDistances(Double latitude, Double longitude) {
        List<PharmacyResponse> pharmacyResponseList = new ArrayList<>();
        for(PharmacyInfo pharmacyInfo : CSVUtil.pharmacyMasterList) {
            PharmacyResponse pharmacyResponse = new PharmacyResponse(pharmacyInfo.getName(), pharmacyInfo.getAddress(),
                    DistanceCalculator.distance(latitude, longitude, pharmacyInfo.getLatitude(), pharmacyInfo.getLongitude()));
            pharmacyResponseList.add(pharmacyResponse);
        }
        return pharmacyResponseList;
    }
}
