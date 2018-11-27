package com.hashmapinc.tempus.witsml.DrillTest.controller;

import java.io.IOException;
import java.io.StringReader;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

@RestController
public class WellController {

    private static final Logger LOG = Logger.getLogger(WellController.class.getName());

    private WellRepository repo;

    @Autowired
    private void setWellRepo(WellRepository repo){
        this.repo = repo;
    }

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Query a well by specified ID. Response returns detail information of the well match the specified ID.
     * UUID of the well.
     *
     * @param uuid (required) UUID of the well.
     *
     * @return WITSML 2.0 representation of the well
     */
    @ApiOperation(value = "Gets a well by its UUID", response = WellInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved well"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "There was an internal server error trying to complete your request")
    }
    )
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
    @ApiOperation(value = "Query for wells", response = WellInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved well"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "There was an internal server error trying to complete your request")
    }
    )
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

            WellInfo info = createSingleWellInfo(w, includeData);
            try{
                return new ResponseEntity<>(serializeWell(info), HttpStatus.OK);
            }
            catch (JsonProcessingException ex){
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        // If Company Was not supplied and Name was not supplied
        if (company == null){

            Iterable<Well> wells = repo.findAll();

            if (wells == null){
                return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
            }

            WellInfo info = createMultiWellInfo(wells, includeData);

            try{
                return new ResponseEntity<>(serializeWell(info), HttpStatus.OK);
            }
            catch (JsonProcessingException ex){
                return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
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
    @RequestMapping(value = "/well/v2", method = RequestMethod.PUT, params = {"uuid", "contractId"})
    public ResponseEntity<String> upsertWell(@RequestParam String uuid,
                                           @RequestParam(required = false) int contractId,
                                           @RequestBody String payload)
    {
        try {
            WellInfo info = mapper.readValue(payload, WellInfo.class);
            Well w = new Well();
            com.hashmapinc.tempus.WitsmlObjects.v20.Well well = deserialize(info.getValue().get(0).getData());
            w.setName(well.getCitation().getTitle());
            w.setData(info.getValue().get(0).getData());
            w.setUuid(uuid);
            w.setTimeZone(well.getTimeZone());
            repo.save(w);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JAXBException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<String> serializeWell(@PathVariable int contractId, @PathVariable String uuid) {
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

    /**
     * Creates a single DrillTest WellInfo object with a single value.
     *
     * @param wellEntity The singular well entity returned from the repo
     * @param includeData Determines whether
     * @return
     */
    private WellInfo createSingleWellInfo(Well wellEntity, boolean includeData){
        List<Well> values = new ArrayList<>();
        values.add(wellEntity);
        return createMultiWellInfo((Iterable<Well>)values, includeData);
    }

    /**
     * Creates a single DrillTest WellInfo object with multiple values
     *
     * @param wellEntities The well entity objects returned from the repo to include in the WellInfo Object
     * @param includeData Determines whether to include the XML data in the return value
     * @return The WellInfo POJO that includes the returned wellEntities
     */
    private WellInfo createMultiWellInfo(Iterable<Well> wellEntities, boolean includeData){
        WellInfo info = new WellInfo();
        List<WellValue> values = new ArrayList<>();

        for (Well w : wellEntities) {
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

        return info;
    }

    /**
     * Serializes the WellInfo into a DrillTest Well Info Object
     *
     * @param wellInfoObj The WellInfo POJO that represents the DrillTest Well Info
     * @return The JSON-formatted string representation of the WellInfo Object
     * @throws JsonProcessingException Thrown if the object cannot be serialized
     */
    private String serializeWell(WellInfo wellInfoObj) throws JsonProcessingException {

        return mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(wellInfoObj);
    }

    /**
     * Deserializes a WITSML 2.0 object (make sure it has a upper case W for Well (i.e. Well)
     *
     * @param witsml The XML string representation of the Well object
     * @return The deserialized Well POJO
     *
     * @throws JAXBException Thrown if there is a parsing error
     */
    private static com.hashmapinc.tempus.WitsmlObjects.v20.Well deserialize(String witsml) throws JAXBException {
        StringReader witsmlReader = new StringReader(witsml);
        JAXBContext jaxbContext = JAXBContext.newInstance(com.hashmapinc.tempus.WitsmlObjects.v20.Well.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (com.hashmapinc.tempus.WitsmlObjects.v20.Well)JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(witsmlReader));
    }

}