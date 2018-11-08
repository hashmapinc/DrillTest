package com.hashmapinc.tempus.witsml.DrillTest.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wellbore/v1")
public class WellboreController {

    /**
     * Create a new wellbore or update an existing wellbore with a witsml2.0 object. Response returns the
     * information of the wellbore created or updated.
     *
     * @param id UUID of the wellbore
     *
     * @return id, name, and WellId
     */
    @RequestMapping(value = "wellbore/v1/wellbores/{id}", method = RequestMethod.PUT, produces = "application/json", params = {"id"})
    public ResponseEntity<String> upsertWellbore(@PathVariable String id)
    {
        String wellbore="{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"name\": \"string\",\n" +
                "  \"wellId\": \"string\"\n" +
                "}";
        return new ResponseEntity<>(wellbore, HttpStatus.OK);
    }

    /**
     * Create a new wellbore with an existing well ID and wellbore name. Response returns the information of
     * the wellbore created.
     *
     * @return id, name, and WellId
     */
    @RequestMapping(value = "wellbore/v1/wellbores", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createWellbore(@RequestBody String request)
    {
        String wellbore="{\n" +
                "  \"id\": \"" + UUID.randomUUID() + "\",\n" +
                "  \"name\": \"string\",\n" +
                "  \"wellId\": \"string\"\n" +
                "}";
        return new ResponseEntity<>(wellbore, HttpStatus.CREATED);
    }

    /**
     * Delete an existing wellbore matching the specified ID. Response indicates delete succeed or not.
     *
     * @param id UUID of the wellbore
     *
     * @return void
     */
    @RequestMapping(value = "wellbore/v1/wellbores/{id}", method = RequestMethod.DELETE, params = {"id"})
    public ResponseEntity<Void> deleteWellbore(@RequestParam String id)
    {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Query wellbore by wellbore ID, Response returns detail information of the wellbore with specified wellbore ID.
     *
     * @param id UUID of the wellbore
     * @param contentType Only Witsml2.0 is accepted now.
     *
     * @return Witsml 2.0 wellbore
     */
    @RequestMapping(value = "wellbore/v1/wellbores/{id}", method = RequestMethod.GET, params = {"id", "contentType"})
    public ResponseEntity<String> getWellboreById(@PathVariable String id, @RequestParam String contentType)
    {
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    /**
     * Get all wellbores belong to the specified well. Response returns a list of wellbore informations.
     *
     * @param wellId Uuid of well to be queried to get all the wellbores belongs to it.
     *
     * @return Witsml 2.0 wellbore
     */
    @RequestMapping(value = "wellbore/v1/wellbores", method = RequestMethod.GET, params = {"wellId"})
    public ResponseEntity<String> getWellboresByWell(@RequestParam String wellId)
    {
        String wellbores = "{\n" +
                "  \"value\": [\n" +
                "    {\n" +
                "      \"id\": \"string\",\n" +
                "      \"name\": \"string\",\n" +
                "      \"wellId\": \"string\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        return new ResponseEntity<>(wellbores, HttpStatus.NO_CONTENT);
    }
}