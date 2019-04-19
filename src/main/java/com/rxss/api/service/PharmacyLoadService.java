package com.rxss.api.service;

import com.rxss.api.model.PharmacyInfo;
import com.rxss.api.model.PharmacyInfoRepository;
import com.rxss.api.util.CSVUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyLoadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PharmacyLoadService.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private PharmacyInfoRepository pharmacyInfoRepository;

    public PharmacyLoadService() {}

    @PostConstruct
    public void init() {
        Resource resource = resourceLoader.getResource("classpath:pharmacies.csv");
        List<PharmacyInfo> pharmacyInfos = new ArrayList<>();
        LOGGER.info("Reading from array");
        try {
            pharmacyInfos = CSVUtil.readCSVFile(resource.getInputStream());
        } catch (Exception ex) {
            LOGGER.error("Resource not found");
            ex.printStackTrace();
        }
        for(PharmacyInfo pharmacyInfo : pharmacyInfos) {
            pharmacyInfoRepository.save(pharmacyInfo);
        }
        LOGGER.info("Loaded data to repository");
    }
}
