package com.rest;


import com.converter.NumberConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.model.Number;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/service")
@Api(value = "converter service")
public class RestService {

    private static Logger logger = LoggerFactory.getLogger(RestService.class);
    private NumberConverter numberConverter;

    @GET
    @Path("/hello")
    @ApiOperation(value = "Hello World test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        return Response.status(Response.Status.OK).entity("hello").build();
    }


    @POST
    @Path("/convert")
    @ApiOperation(value = "converts a number to plain text")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "number is converted"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error") })
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response convertNumberToText(Number nb) {

        try {
            Double value = nb.getValue();
            numberConverter = new NumberConverter();
            String result = numberConverter.convertNumberToText(value);
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (IOException | ParseException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }


}