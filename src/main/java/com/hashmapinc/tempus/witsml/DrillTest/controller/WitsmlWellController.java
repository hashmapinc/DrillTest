package com.hashmapinc.tempus.witsml.DrillTest.controller;

import com.hashmapinc.tempus.witsml.DrillTest.model.well.WellInfo;
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
    @ApiOperation(value = "Gets a well by its UID", response = WellInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved well"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "There was an internal server error trying to complete your request")
    }
    )
    @RequestMapping(value = "/witsml/wells/{uid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getWellByUUID(@PathVariable String uid, @RequestHeader("Authorization") String auth)
    {
        LOG.info("In Get witsml well by UID");

        if (auth == null){
            return new ResponseEntity<>("Unauthorized JWT token.", HttpStatus.UNAUTHORIZED);
        }

        User foundUser = userRepo.findByToken(auth);
        if (foundUser == null){
            return new ResponseEntity<>("Unauthorized JWT token.", HttpStatus.UNAUTHORIZED);
        }

        LOG.info("User " + foundUser.getUserName() + " attempting access to well with uid " + uid);

        WitsmlWell w = repo.findByUid(uid);

        if (w == null){
            return new ResponseEntity<>("No Well with submitted uid found", HttpStatus.NOT_FOUND);
        }

        String wellData = w.getData();

        return new ResponseEntity<>(wellData, HttpStatus.OK);
    }
}
