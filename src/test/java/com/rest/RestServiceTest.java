package com.rest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;
import com.model.Number;
import org.mockito.Matchers;

public class RestServiceTest {

    private RestService restService;

    @Before
    public void setUp(){
        restService = spy(new RestService());
    }

    @Test
    public void testHello() {
        Response response = restService.hello();
        assertEquals(200,response.getStatus());

    }

    @Test
    public void testConvertNumberToText() {
        //setup
        Number number = new Number();
        number.setValue(47.50);
        Response response = restService.convertNumberToText(number);
        assertEquals(200,response.getStatus());
    }

    @Test
    public void testConvertNumberToTextWithError(){
        //setup
        Number number = new Number();
        number.setValue(4755555555.50);
        Response response = restService.convertNumberToText(number);
        assertEquals(400,response.getStatus());
    }
}