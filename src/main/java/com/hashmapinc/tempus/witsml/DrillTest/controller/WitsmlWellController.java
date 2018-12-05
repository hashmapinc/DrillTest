package com.hashmapinc.tempus.witsml.DrillTest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hashmapinc.tempus.WitsmlObjects.Util.WitsmlMarshal;
import com.hashmapinc.tempus.WitsmlObjects.v1311.ObjWell;
import com.hashmapinc.tempus.witsml.DrillTest.store.User;
import com.hashmapinc.tempus.witsml.DrillTest.store.UserRepository;
import com.hashmapinc.tempus.witsml.DrillTest.store.WitsmlWell;
import com.hashmapinc.tempus.witsml.DrillTest.store.WitsmlWellRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class WitsmlWellController {

    private static final Logger LOG = Logger.getLogger(WitsmlWellController.class.getName());

    private WitsmlWellRepository repo;
    private UserRepository userRepo;

    @Autowired
    private void setWitsmlWellRepo(WitsmlWellRepository repo){
        this.repo = repo;
    }

    @Autowired
    private void setUserRepo(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    /**
     * Query a well by specified ID. Responds with a 1.4.1.1 formatted string
     *
     * @param uid (required) UID of the well.
     *
     * @return WITSML 1.4.1.1 representation of the well
     */
    @ApiOperation(value = "Gets a well by its UID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved well"),
            @ApiResponse(code = 401, message = "Unauthorized JWT token."),
            @ApiResponse(code = 403, message = "No Permission to access the resource."),
            @ApiResponse(code = 404, message = "The well you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Unexpected error happens on server.")
    }
    )
    @RequestMapping(value = "/witsml/wells/{uid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getWellByUUID(@PathVariable String uid, @RequestHeader("Authorization") String auth)
    {
        LOG.info("In Get witsml well by UID");

        if (!authorizeUser(auth))
            return new ResponseEntity<>("Unauthorized JWT token", HttpStatus.UNAUTHORIZED);

        LOG.info("User attempting access to well with uid " + uid);

        WitsmlWell w = repo.findByUid(uid);

        if (w == null){
            return new ResponseEntity<>("No Well with submitted uid found", HttpStatus.NOT_FOUND);
        }

        String wellData = w.getData();

        return new ResponseEntity<>(wellData, HttpStatus.OK);
    }

    /**
     * Query a well by specified ID. Responds with a 1.4.1.1 formatted string
     *
     * @param uid (required) UID of the well.
     *
     * @return WITSML 1.4.1.1 representation of the well
     */
    @ApiOperation(value = "Upsert a well by its UID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added/updated well"),
            @ApiResponse(code = 401, message = "Unauthorized JWT token."),
            @ApiResponse(code = 403, message = "No Permission to access the resource."),
            @ApiResponse(code = 404, message = "The well you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Unexpected error happens on server.")
    }
    )
    @RequestMapping(value = "/witsml/wells/{uid}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<String> putWellByUID(@PathVariable String uid, @RequestHeader("Authorization") String auth,
                                               @RequestBody String payload)
    {
        LOG.info("In Put witsml well by UID");

        if (!authorizeUser(auth))
            return new ResponseEntity<>("Unauthorized JWT token", HttpStatus.UNAUTHORIZED);

        LOG.info("User attempting put to well with uid " + uid);

        WitsmlWell foundWell = repo.findByUid(uid);
        if (foundWell == null)
        {
            foundWell = new WitsmlWell();
            foundWell.setUid(uid);
        }
        else {
            foundWell.setData(payload);

        }
        repo.save(foundWell);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    /**
     * Create a well by specified ID. Responds with a 1.4.1.1 formatted string of the updated well
     *
     * @return WITSML 1.4.1.1 representation of the updated well
     */
    @ApiOperation(value = "Deletes a well by its UID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted well"),
            @ApiResponse(code = 401, message = "Unauthorized JWT token."),
            @ApiResponse(code = 403, message = "No Permission to access the resource."),
            @ApiResponse(code = 404, message = "The well you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Unexpected error happens on server.")
    }
    )
    @RequestMapping(value = "/witsml/wells/{uid}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> deleteWellByUid(@PathVariable String uid, @RequestHeader("Authorization") String auth)
    {
        LOG.info("In DELETE witsml well");

        if (!authorizeUser(auth))
            return new ResponseEntity<>("Unauthorized JWT token", HttpStatus.UNAUTHORIZED);

        LOG.info("User attempting delete to well with uid " + uid);

        WitsmlWell well = repo.findByUid(uid);
        if (well == null) {
            return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
        }
        repo.delete(well);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * Create a well by specified ID. Responds with a 1.4.1.1 formatted string of the updated well
     *
     * @return WITSML 1.4.1.1 representation of the updated well
     */
    @ApiOperation(value = "Creates a well by its UID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved well"),
            @ApiResponse(code = 401, message = "Unauthorized JWT token."),
            @ApiResponse(code = 403, message = "No Permission to access the resource."),
            @ApiResponse(code = 404, message = "The well you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Unexpected error happens on server.")
    }
    )
    @RequestMapping(value = "/witsml/wells/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> postWellByUID(@RequestHeader("Authorization") String auth, @RequestBody String payload)
    {
        LOG.info("In POST witsml well");

        ObjWell well;
        try {
            well = WitsmlMarshal.deserializeFromJSON(payload, ObjWell.class);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!authorizeUser(auth))
            return new ResponseEntity<>("Unauthorized JWT token", HttpStatus.UNAUTHORIZED);

        LOG.info("User attempting save to well with uid " + well.getUid());

        WitsmlWell newWell = new WitsmlWell();
        if ("".equals(well.getUid())){
            well.setUid(UUID.randomUUID().toString());
        }
        newWell.setUid(well.getUid());
        newWell.setData(payload);

        repo.save(newWell);
        String returnJson;
        try {
            returnJson = WitsmlMarshal.serializeToJSON(well);
        } catch (JsonProcessingException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(returnJson, HttpStatus.OK);
    }

    private boolean authorizeUser(String jwt)
    {
        if (jwt == null){
            return false;
        }

        if ("".equals(jwt)){
            return false;
        }

        User foundUser = userRepo.findByToken(jwt);
        if (foundUser == null){
            return false;
        }

        return true;
    }
}
