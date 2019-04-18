package com.rxss.api.controller;

import com.rxss.api.constants.SwaggerConstants;
import com.rxss.api.model.PharmacyResponse;
import com.rxss.api.service.PharmacyService;
import com.rxss.api.util.CSVUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = SwaggerConstants.PHARMACY_TAG)
public class PharmacyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PharmacyController.class);

    @Autowired
    private PharmacyService pharmacyService;

    @RequestMapping(path = "/pharmacyLocator", method = {RequestMethod.GET})
    @ApiOperation(SwaggerConstants.FIND_OPERATION)
    public PharmacyResponse getPharmacy(@RequestParam(value="latitude") Double latitude,
                                        @RequestParam(value="longitude") Double longitude) {
        LOGGER.info(CSVUtil.pharmacyMasterList.get(0).getName());

        return pharmacyService.getNearestPharmacy(latitude,longitude);
    }
}
