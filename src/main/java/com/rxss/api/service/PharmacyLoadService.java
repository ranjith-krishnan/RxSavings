package com.rxss.api.service;

import com.rxss.api.util.CSVUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PharmacyLoadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PharmacyLoadService.class);

    @Autowired
    private ResourceLoader resourceLoader;

    public PharmacyLoadService() {}

    @PostConstruct
    public void init() {
        Resource resource = resourceLoader.getResource("classpath:pharmacies.csv");
        LOGGER.debug("Reading from array");
        try {
            CSVUtil.readCSVFile(resource.getInputStream());
        } catch (Exception ex) {
            LOGGER.error("Resource not found");
            ex.printStackTrace();
        }
    }
}
