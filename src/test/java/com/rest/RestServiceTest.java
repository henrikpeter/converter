package com.rest;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import com.model.Number;
import org.mockito.Matchers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestServiceTest {

    private RestService restService;

    @Before
    public void setUp() {
        restService = mock(RestService.class);
    }

    @Test
    public void testHello() {
        when(restService.hello()).thenCallRealMethod();
        Response response = restService.hello();
        assertEquals(200,response.getStatus());
    }


    @Test
    public void testConvertNumberToText() {
        //setup
        Number number = new Number();
        number.setValue(47.50);
        when(restService.convertNumberToText(Matchers.<Number>any())).thenCallRealMethod();
        Response response = restService.convertNumberToText(number);
        assertEquals(200,response.getStatus());
    }

    @Test
    public void testConvertNumberToTextWithError(){
        //setup
        Number number = new Number();
        number.setValue(4755555555.50);
        when(restService.convertNumberToText(Matchers.<Number>any())).thenCallRealMethod();
        Response response = restService.convertNumberToText(number);
        assertEquals(400,response.getStatus());
    }
}