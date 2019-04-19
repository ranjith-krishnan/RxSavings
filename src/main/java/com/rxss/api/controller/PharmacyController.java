package com.rxss.api.controller;

import com.rxss.api.constants.SwaggerConstants;
import com.rxss.api.model.PharmacyResponse;
import com.rxss.api.service.PharmacyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = SwaggerConstants.PHARMACY_TAG)
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @RequestMapping(path = "/pharmacyLocator", method = {RequestMethod.GET})
    @ApiOperation(SwaggerConstants.FIND_OPERATION)
    public ResponseEntity<PharmacyResponse> getPharmacy(
             @RequestParam(value = "latitude") @ApiParam(value = SwaggerConstants.LATITUDE) Double latitude,
             @RequestParam(value = "longitude") @ApiParam(value = SwaggerConstants.LONGITUDE) Double longitude) {

        if(null == latitude) {
            throw new IllegalArgumentException("Error: Latitude is invalid or not supplied");
        }
        if(null == longitude) {
            throw new IllegalArgumentException("Error: Longitude is invalid or not supplied");
        }

        return new ResponseEntity<>(pharmacyService.getNearestPharmacy(latitude, longitude), HttpStatus.OK );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<String> exceptionHandlerIllegalArgumentException(final IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<String> exceptionHandlerIllegalArgumentException(final NumberFormatException e) {
        return new ResponseEntity<>("Error: Invalid Number Format Exception " + e.getMessage(), HttpStatus.BAD_REQUEST );
    }
}
