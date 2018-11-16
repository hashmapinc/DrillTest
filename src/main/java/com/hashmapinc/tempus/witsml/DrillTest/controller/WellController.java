package com.hashmapinc.tempus.witsml.DrillTest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashmapinc.tempus.witsml.DrillTest.model.well.WellInfo;
import com.hashmapinc.tempus.witsml.DrillTest.store.Well;
import com.hashmapinc.tempus.witsml.DrillTest.model.well.WellValue;
import com.hashmapinc.tempus.witsml.DrillTest.store.WellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class WellController {

    private static final Logger LOG = Logger.getLogger(WellController.class.getName());

    @Autowired
    private WellRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Query a well by specified ID. Response returns detail information of the well match the specified ID.
     * UUID of the well.
     *
     * @param uuid (required) UUID of the well.
     *
     * @return WITSML 2.0 representation of the well
     */
    @RequestMapping(value = "/well/v2", method = RequestMethod.GET, produces = "application/json", params = { "uuid" })
    public ResponseEntity<String> getWellByUUID(@RequestParam String uuid)
    {
        LOG.info("In Get well by UUID");
        Well w = repo.findByUUid(uuid);

        if (w == null){
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

        String wellData = w.getData();
        WellInfo info = new WellInfo();
        WellValue value = new WellValue();
        value.setData(wellData);
        value.setContentType("application/x-witsml+xml;version=2.0;type=Well");
        value.setStreamingState(w.getStreamingState());
        List<WellValue> values = new ArrayList<>();
        values.add(value);
        info.setValue(values);

        String jsonResult = "";
        try {
            jsonResult = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(info);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    /**
     * Query a well by specified ID. Response returns detail information of the well match the specified ID.
     * UUID of the well.
     *
     * @param company (optional) Name of the company that the well belongs to. If no company name specified,
     *                all wells will be returned.
     * @param name    (optional) Name of the well
     * @param streamingState (optional) [Ready|Streaming|Disable|Idle|(Any)] Streaming state of the well.
     * @param liveState (optional) [Operative|Connected|Disconnected|Ghost|Zombie|SuperStateAny|SuperStateDead|
     *                  (SuperStateAlive)] Live state of the well.
     * @param includeData (optional) Set if the well data is contained in response body.
     *
     * @return WITSML 2.0 representation of the well
     */
    @RequestMapping(value = "/well/v2", method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<String> queryForWells(@RequestParam(required = false) String company,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String streamingState,
                                                @RequestParam(required = false) String liveState,
                                                @RequestParam(required = false) boolean includeData) {
        // If Name was Supplied
        if (name != null){
            Well w = repo.findByName(name);
            if (w == null){
                return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
            }

            WellInfo info = new WellInfo();
            WellValue value = new WellValue();
            value.setCompany(w.getCompany());
            value.setName(w.getName());
            value.setTimeZone(w.getTimeZone());

            if (includeData){
                value.setData(w.getData());
            }

            List<WellValue> values = new ArrayList<>();
            values.add(value);
            info.setValue(values);

            String jsonResult = "";
            try {
                jsonResult = mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(info);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }

        // If Company Was not supplied
        if (company == null){
            WellInfo info = new WellInfo();
            List<WellValue> values = new ArrayList<>();

            for (Well w : repo.findAll()) {
                WellValue value = new WellValue();
                value.setCompany(w.getCompany());
                value.setName(w.getName());
                value.setTimeZone(w.getTimeZone());
                value.setId(w.getUuid());

                if (includeData){
                    value.setData(w.getData());
                }
                values.add(value);
            }

            info.setValue(values);

            String jsonResult = "";
            try {
                jsonResult = mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(info);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    /**
     * Create or update a well using WITSML object. well is being created if it doesn't exist, otherwise it gets
     * updated. Response indicates create or update succeed or not.
     *
     * @param uuid (required) Uuid of the well to be created or updated.
     * @param contractId (required) Format - int64. contract ID to which the well belongs.
     *
     * @return void
     */
    @RequestMapping(value = "/well/v2", method = RequestMethod.PUT, params = {"uuid","contractId"})
    public ResponseEntity<Void> upsertWell(@RequestParam String uuid,
                                           @RequestParam int contractId)
    {
        //TODO well add to data model
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{contractId}").build().toUri();//buildAndExpand(contract.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Delete an existing well with specified ID. This API will delete a well and trigger the deletion
     * of related data including growing, streaming and context objects. Response indicates delete succeed or not.
     *
     * @param uuid (required) Uuid of the well to be deleted
     *
     * @return void
     */
    @RequestMapping(value = "/well/v2", method = RequestMethod.DELETE, params = {"uuid"})
    public ResponseEntity<Void> deleteWell(@RequestParam String uuid)
    {
        //TODO well add to data model
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{contractId}").build().toUri();//buildAndExpand(contract.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Create a new well with contract ID, well name, company and timezone.
     * Use this API when there is no proposed ID for the well.
     * Response returns detail information of the well created.
     *
     * @param contractId Format - int64. Contract ID to which the well belongs.
     */
    @RequestMapping(path = "/well/v2", method = RequestMethod.POST, produces = "application/json", params = {"contractId"})
    public ResponseEntity<String> createWellByContract(@PathVariable int contractId) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    /**
     * Create a new well with a specified ID.
     * Use this API when there is a proposed ID for the well.
     * Response returns detail information of the well created.
     *
     * @param contractId (required) Format - int64. Contract ID to which the well belongs.
     * @param uuid (required) Uuid of the well to be created.
     */
    @RequestMapping(path = "/well/v2", method = RequestMethod.POST, produces = "application/json", params = {"contractId","uuid"})
    public ResponseEntity<String> createWell(@PathVariable int contractId, @PathVariable String uuid) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    /**
     * Configure well with TTL, TTG, and archivable properties by ID.
     * Response returns the latest configuration data of the well.
     *
     * @param uuid (required) Uuid of the well
     *
     * @return TTL configuration
     */
    @RequestMapping(value = "/well/v2", method = RequestMethod.PUT, produces = "application/json",  params = {"uuid"})
    public ResponseEntity<String> configureWellByID(@RequestParam String uuid)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    /**
     * Get well configuration of TTL, TTG, and archivable properties by ID.
     * Response returns the latest configuration data of the well.
     *
     * @param uuid (required) Uuid of the well
     *
     * @return TTL configuration
     */
    @RequestMapping(value = "/well/v2/{uuid}/configuration", method = RequestMethod.GET, produces = "application/json", params = "uuid")
    public ResponseEntity<String> getWellConfiguration(@PathVariable String uuid)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    /**
     * Set a well to connect or disconnect state. Response indicates operation succeed or not.
     *
     * @param uuid (required) Uuid of the well to be operated
     */
    @RequestMapping(value = "/well/v2", method = RequestMethod.POST, params = {"uuid"})
    public void setConnectionState(@RequestParam String uuid, @RequestBody String payload)
    {
        LOG.info(payload);
    }

}