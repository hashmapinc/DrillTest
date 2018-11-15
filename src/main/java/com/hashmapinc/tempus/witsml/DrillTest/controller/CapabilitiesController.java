package com.hashmapinc.tempus.witsml.DrillTest.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hashmapinc.tempus.witsml.DrillTest.model.Capabilities;
import com.hashmapinc.tempus.witsml.DrillTest.model.DtCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * Query a well by specified ID. Response returns detail information of the well match the specified ID.
     * UUID of the well.
     *
     * @return WITSML 2.0 representation of the well
     */
    @RequestMapping(value = "/capabilities/v1", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCaps()
    {
        ObjectMapper mapper = new ObjectMapper();

        LOG.info("In Get Well by UUID");
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