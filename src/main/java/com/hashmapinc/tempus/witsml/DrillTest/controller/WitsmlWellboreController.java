package com.hashmapinc.tempus.witsml.DrillTest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hashmapinc.tempus.WitsmlObjects.Util.WitsmlMarshal;
import com.hashmapinc.tempus.WitsmlObjects.v1411.ObjWellbore;
import com.hashmapinc.tempus.witsml.DrillTest.store.*;
import io.swagger.annotations.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class WitsmlWellboreController {

    private static final Logger LOG = Logger.getLogger(WitsmlWellboreController.class.getName());

    private WitsmlWellboreRepository repo;
    private UserRepository userRepo;

    @Autowired
    private void setWitsmlWellboreRepo(WitsmlWellboreRepository repo){
        this.repo = repo;
    }

    @Autowired
    private void setUserRepo(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    /**
     * Query a well by specified ID. Responds with a 1.4.1.1 formatted string
     *
     * @param uid (required) UID of the wellbore.
     *
     * @return WITSML 1.4.1.1 representation of the wellbore
     */
    @ApiOperation(value = "Gets a wellbore by its UID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ObjWellbore.class),
            @ApiResponse(code = 401, message = "Unauthenticated JWT token."),
            @ApiResponse(code = 403, message = "No Permission to access the resource."),
            @ApiResponse(code = 404, message = "Wellbore not found."),
            @ApiResponse(code = 500, message = "Unexpected error happens on server.")
    }
    )
    @RequestMapping(value = "/democore/wellbore/v1/witsml/wellbores/{uid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getWellboreByUUID(@PathVariable String uid,
                                                    @RequestHeader("Authorization") String auth,
                                                    @RequestParam("uidWell") String uidWell)
    {
        LOG.info("In Get witsml wellbore by UID");

        if (!authorizeUser(auth))
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);

        LOG.info("User attempting access to wellbore with uid " + uid);

        WitsmlWellbore w = repo.findByUid(uid, uidWell);

        if (w == null){
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

        String wellboreData = w.getData();

        return new ResponseEntity<>(wellboreData, HttpStatus.OK);
    }

    /**
     * Query a wellbore by specified ID. Responds with a 1.4.1.1 formatted string
     *
     * @param uid (required) UID of the well.
     *
     * @return WITSML 1.4.1.1 representation of the wellbore
     */
    @ApiOperation(value = "Upsert a wellbore by its UID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ObjWellbore.class),
            @ApiResponse(code = 204, message = "NoContent"),
            @ApiResponse(code = 400, message = "Invalid request content."),
            @ApiResponse(code = 401, message = "Unauthorized JWT token."),
            @ApiResponse(code = 403, message = "No Permission to access the resource."),
            @ApiResponse(code = 404, message = "Wellbore not found"),
            @ApiResponse(code = 500, message = "Unexpected error happened on server.")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "payload",
                    dataTypeClass = ObjWellbore.class,
                    required = true
            )
    })
    @RequestMapping(value = "/democore/wellbore/v1/witsml/wellbores/{uid}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<String> putWellboreByUID(@PathVariable String uid,
                                                   @RequestHeader("Authorization") String auth,
                                                   @RequestBody String payload,
                                                   @RequestParam("uidWell") String uidWell)
    {
        LOG.info("In Put witsml wellbore by UID");

        if (!authorizeUser(auth))
            return new ResponseEntity<>("Unauthorized JWT token", HttpStatus.UNAUTHORIZED);

        LOG.info("User attempting put to wellbore with uid " + uid);

        WitsmlWellbore foundWellbore = repo.findByUid(uid, uidWell);
        if (foundWellbore == null)
        {
            foundWellbore = new WitsmlWellbore();
            foundWellbore.setUid(uid);
            foundWellbore.setWelluid(uidWell);
            foundWellbore.setData(payload);
        }
        else {
            try {
                JSONObject newWellbore = new JSONObject(payload);
                JSONObject oldWellbore = new JSONObject(foundWellbore.getData());
                JSONObject mergedWellbore = Util.merge(oldWellbore, newWellbore);
                foundWellbore.setData(mergedWellbore.toString());
            }catch (Exception ex){
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            }
        }
        repo.save(foundWellbore);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    /**
     * Create a wellbore by specified ID. Responds with a 1.4.1.1 formatted string of the updated well
     *
     * @return WITSML 1.4.1.1 representation of the updated wellbore
     */
    @ApiOperation(value = "Deletes a wellbore by its UID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted wellbore"),
            @ApiResponse(code = 401, message = "Unauthorized JWT token."),
            @ApiResponse(code = 403, message = "No Permission to access the resource."),
            @ApiResponse(code = 404, message = "The wellbore you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Unexpected error happens on server.")
    }
    )
    @RequestMapping(value = "democore/wellbore/v1/witsml/wellbores/{uid}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> deleteWellByUid(@PathVariable String uid,
                                                  @RequestHeader("Authorization") String auth,
                                                  @RequestParam("uidWell") String wellUid)
    {
        LOG.info("In DELETE witsml wellbore");

        if (!authorizeUser(auth))
            return new ResponseEntity<>("Unauthorized JWT token", HttpStatus.UNAUTHORIZED);

        LOG.info("User attempting delete to wellbore with uid " + uid);

        WitsmlWellbore wellbore = repo.findByUid(uid, wellUid);
        if (wellbore == null) {
            return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
        }

        repo.delete(wellbore);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * Create a wellbore by specified ID. Responds with a 1.4.1.1 formatted string of the updated well
     * Note this method will assign a new UID no matter if one was provided or not
     *
     * @return WITSML 1.4.1.1 representation of the updated well
     */
    @ApiOperation(value = "Creates a wellbore by its UID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = ObjWellbore.class, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized JWT token."),
            @ApiResponse(code = 403, message = "No Permission to access the resource."),
            @ApiResponse(code = 404, message = "The wellbore you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Unexpected error happens on server.")
    }
    )
    @RequestMapping(value = "/democore/wellbore/v1/witsml/wellbores", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> postWellByUID(@RequestHeader("Authorization") String auth,
                                                @RequestBody String payload,
                                                @RequestParam("uidWell") String uidWell)
    {
        LOG.info("In POST witsml wellbore");

        ObjWellbore wellbore;

        try {
            wellbore = WitsmlMarshal.deserializeFromJSON(payload, ObjWellbore.class);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!authorizeUser(auth))
            return new ResponseEntity<>("Unauthorized JWT token", HttpStatus.UNAUTHORIZED);

        LOG.info("User attempting save to wellbore with uid " + wellbore.getUid());

        WitsmlWellbore newWellbore = new WitsmlWellbore();

        //Create a new UID, because apparently that's what we do now.
        wellbore.setUid(UUID.randomUUID().toString());

        newWellbore.setUid(wellbore.getUid());
        newWellbore.setWelluid(wellbore.getParentUid());
        newWellbore.setData(payload);

        // ensure the wellbore doesn't already exist
        if (null != repo.findByUid(wellbore.getUid(), wellbore.getParentUid()))
            return new ResponseEntity<>("UID:<" + wellbore.getUid() + "> already exists", HttpStatus.CONFLICT);
        
        // wellbore uid is valid. Continue with save
        repo.save(newWellbore);

        String returnJson;
        try {
            returnJson = WitsmlMarshal.serializeToJSON(wellbore);
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

        String subJwt = jwt.substring(jwt.indexOf(" ") + 1);
        User foundUser = userRepo.findByToken(subJwt);
        if (foundUser == null){
            return false;
        }

        return true;
    }
}
