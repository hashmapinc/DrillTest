package com.hashmapinc.tempus.witsml.DrillTest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashmapinc.tempus.witsml.DrillTest.model.caps.DtCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.logging.Logger;

@RestController
@ComponentScan(basePackageClasses = {DtCap.class})
public class CapabilitiesController {

    private static final Logger LOG = Logger.getLogger(CapabilitiesController.class.getName());
    private DtCap caps;

    @Autowired
    private void setCaps(DtCap caps){
        this.caps = caps;
    }

    /**
     * Get the
     */
    @RequestMapping(value = "/capabilities/v1", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCaps()
    {
        ObjectMapper mapper = new ObjectMapper();

        LOG.info("In Get well by UUID");
        StringWriter writer = new StringWriter();
        String jsonResult = "";
        try {
            jsonResult = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(caps);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }
}