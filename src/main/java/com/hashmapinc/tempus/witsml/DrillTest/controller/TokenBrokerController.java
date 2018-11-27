package com.hashmapinc.tempus.witsml.DrillTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashmapinc.tempus.witsml.DrillTest.model.user.UserInfo;
import com.hashmapinc.tempus.witsml.DrillTest.model.well.WellInfo;
import com.hashmapinc.tempus.witsml.DrillTest.store.User;
import com.hashmapinc.tempus.witsml.DrillTest.store.UserRepository;
import com.hashmapinc.tempus.witsml.DrillTest.store.WellRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
public class TokenBrokerController {

    private static final Logger LOG = Logger.getLogger(TokenBrokerController.class.getName());

    private UserRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private void setUserRepo(UserRepository repo){
        this.repo = repo;
    }

    @ApiOperation(value = "Gets a JWT token for a username and pass", response = WellInfo.class)
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> getJwt(@RequestBody String userInfo) {
        LOG.info("In getJwt");

        UserInfo info;

        try {
            info = mapper.readValue(userInfo, UserInfo.class);
        } catch (IOException e) {
            return new ResponseEntity<>("Could not parse User Info", HttpStatus.BAD_REQUEST);
        }

        User foundUser = repo.findByuserName(info.getAccount());

        if (foundUser == null){
            return new ResponseEntity<>("{\n" +
                    "  \"message\": \"Invalid or missing client credentials.\"\n" +
                    "}", HttpStatus.NOT_FOUND);
        }

        String response = "{\n" +
                "  \"jwt\": \"" + foundUser.getToken() + "\"\n" +
                "}";

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
