package com.asia.leadsgen.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asia.leadsgen.test.service.CampaignService;
import com.asia.leadsgen.test.util.DecryptTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.sql.SQLException;
import java.util.logging.Logger;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/test")
public class SimpleController {

    @Autowired
    DecryptTokenUtil decryptTokenUtil;

    @Autowired
    CampaignService campaignService;

    @GetMapping
    @Operation(summary = "Demo REST API Docs with OpenAPI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    public ResponseEntity<String> testApi(@Parameter(required = false) @PathParam("param") String paramString) throws SQLException {
        return new ResponseEntity<>(paramString, HttpStatus.OK);
    }

    private Logger logger = Logger.getLogger(SimpleController.class.getName());
}
