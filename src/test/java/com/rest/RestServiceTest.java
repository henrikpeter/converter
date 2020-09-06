package com.rest;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestServiceTest {

    private RestService restService;

    @Before
    public void setUp() {
        restService = mock(RestService.class);
    }

//    @Test
//    public void testHello() {
//        when(restService.hello()).thenCallRealMethod();
//        Response rep = restService.hello();
//        assertEquals(200,rep.getStatus());
//    }

    //
//    @Test
//    public void testConvertNumberToText() {
//        //setup
//        Number number = new Number();
//        number.setValue(47.50);
//        Response response = restService.convertNumberToText(number);
//        assertEquals(200,response.getStatus());
//    }
//
//    @Test
//    public void testConvertNumberToTextWithError(){
//        //setup
//        Number number = new Number();
//        number.setValue(4755555555.50);
//        Response response = restService.convertNumberToText(number);
//        assertEquals(400,response.getStatus());
//    }
}