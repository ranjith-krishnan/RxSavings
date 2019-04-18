package com.rxss.api.model;

import java.util.Comparator;

public class PharmacyDistanceComparator implements Comparator<PharmacyResponse> {
    @Override
    public int compare(PharmacyResponse o1, PharmacyResponse o2) {
        if ( o1.getDistance() < o2.getDistance()) return -1;
        if ( o1.getDistance() > o2.getDistance()) return 1;
        return 0;
    }
}
