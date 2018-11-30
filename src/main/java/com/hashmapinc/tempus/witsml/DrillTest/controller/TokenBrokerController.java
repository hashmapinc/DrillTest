package com.hashmapinc.tempus.witsml.DrillTest.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashmapinc.tempus.witsml.DrillTest.model.user.UserInfo;
import com.hashmapinc.tempus.witsml.DrillTest.model.well.WellInfo;
import com.hashmapinc.tempus.witsml.DrillTest.store.User;
import com.hashmapinc.tempus.witsml.DrillTest.store.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDate;
import java.util.Date;
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

    @ApiOperation(value = "Gets a JWT token for a username and pass", response = UserInfo.class)
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> getJwt(@RequestBody String userInfo, @RequestHeader("Ocp-Apim-Subscription-Key") String apiKey) {
        LOG.info("In getJwt");

        if (apiKey == null){
            return new ResponseEntity<>("Authorization has been denied for this request due to invalid or missing API key.", HttpStatus.UNAUTHORIZED);
        }

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
                    "}", HttpStatus.BAD_REQUEST);
        }

        if (!foundUser.getPassword().equals(info.getPassword())) {
            return new ResponseEntity<>("{\n" +
                    "  \"message\": \"Invalid or missing client credentials.\"\n" +
                    "}", HttpStatus.BAD_REQUEST);
        }

        String token = GetToken(info.getAccount());

        if (token == null){
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        foundUser.setToken(token);

        repo.save(foundUser);

        String response = "{\n" +
                "  \"jwt\": \"" + foundUser.getToken() + "\"\n" +
                "}";

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String GetToken(String userName) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("DrillTest")
                    .withSubject(userName)
                    .withIssuedAt(new Date())
                    .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            return null;
        }
    }
}
