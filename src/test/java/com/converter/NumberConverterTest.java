package com.converter;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

public class NumberConverterTest {

    private NumberConverter numberConverter;

    private static final String DOLLAR = "Dollar";
    private static final String CENT = "Cent";

    @Before
    public void setUp() throws IOException, ParseException {
        numberConverter = spy(new NumberConverter());
        numberConverter.initJsonString();
    }

    @Test
    public void convertNumberToText() throws IOException, ParseException {
        //run test
        String result = numberConverter.convertNumberToText(47.50);
        //check code
        assertEquals("FORTY SEVEN DOLLARS AND FIFTY CENT",result);
    }

    @Test
    public void initJsonString() throws IOException, ParseException {
        //run test
        numberConverter.initJsonString();
        //check code
        assertTrue(numberConverter.jsonString.contains("number"));
    }

    @Test
    public void convertDoubleToStrings() {
        //run test
        Map<String, String> map = numberConverter.convertDoubleToStrings(47.55);
        //check code
        assertEquals("47",map.get(DOLLAR));
        assertEquals("55",map.get(CENT));
    }

    @Test
    public void convertDollarsToText() {
        //setup
        //run test
        String result = numberConverter.convertDollarsToText("47");
        //check code
        assertEquals("FORTY SEVEN",result.toUpperCase());
    }

    @Test
    public void convertCentToText() {
        //run test

        //check code
    }

    @Test
    public void convertTwoDigitsToText() {
        //run test
        //check code
    }

    @Test
    public void convertThreeDigitsToText() {
        //run test
        //check code
    }

    @Test
    public void convertFourDigitsToText() {
        //run test
        //check code
    }

    @Test
    public void convertFiveDigitsToText() {
        //run test
        //check code
    }

    @Test
    public void convertSixDigitsToText() {
        //run test
        //check code
    }

    @Test
    public void testConvertNumberToText() {
        //run test
        //check code
    }
}