package com.hashmapinc.tempus.witsml.DrillTest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class WellController {

    private static final Logger LOG = Logger.getLogger(WellController.class.getName());

    /**
     * Query a well by specified ID. Response returns detail information of the well match the specified ID.
     * UUID of the well.
     *
     * @param uuid (required) UUID of the well.
     *
     * @return WITSML 2.0 representation of the well
     */
    @RequestMapping(value = "/well/v2", method = RequestMethod.GET, produces = "application/json", params = {"uuid"})
    public ResponseEntity<String> getWellById(@RequestParam String uuid)
    {
        LOG.info("In Get Well by UUID");
        //TODO get well2 from model data by the uuid
        String well2="";
        return new ResponseEntity<String>(well2, HttpStatus.OK);
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
    @RequestMapping(value = "/well/v2", method = RequestMethod.GET, produces = "application/json", params = {"company","name","streamingState","liveState","includeData"})
    public ResponseEntity<ArrayList<String>> queryForWells(@RequestParam String company,
                                                           @RequestParam String name,
                                                           @RequestParam String streamingState,
                                                           @RequestParam String liveState,
                                                           @RequestParam boolean includeData) {
        //TODO get wells2 from model data by the company and name
        ArrayList<String> wells = new ArrayList<>(2);
        wells.add("");
        wells.add("");
        return new ResponseEntity<>(wells, HttpStatus.OK);//HTTP 200 response code
    }

    /**
     * Create or update a well using WITSML object. Well is being created if it doesn't exist, otherwise it gets
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