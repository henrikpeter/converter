package com.converter;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;

public class NumberConverterTest {

    private NumberConverter numberConverter;

    private static final String DOLLAR = "dollar";
    private static final String CENT = "cent";
    private static final String ERROR_TEXT_1 = "number must not be negative or higher than 999.999.99";
    private static final String ERROR_TEXT_2 = "integer value could not be found in json array.";

    @Before
    public void setUp() throws IOException, ParseException {
        numberConverter = spy(new NumberConverter());
        numberConverter.initJsonString();
    }

    @Test
    public void testConvertNumberToText() throws IOException, ParseException {
        //run test
        String result = numberConverter.convertNumberToText(47.50);
        //check code
        assertEquals("FORTY SEVEN DOLLARS AND FIFTY CENT", result);
    }

    @Test
    public void testConvertNumberToTextWithError() throws IllegalArgumentException {
        try {
            numberConverter.convertNumberToText(1000111.99);
            fail();
        } catch (Exception e) {
            //check code
            assertEquals(ERROR_TEXT_1, e.getMessage());
        }

    }

    @Test
    public void testInitJsonString() throws IOException, ParseException {
        //run test
        numberConverter.initJsonString();
        //check code
        assertTrue(numberConverter.jsonString.contains("number"));
    }

    @Test
    public void testConvertDoubleToStrings() {
        //run test
        Map<String, String> map = numberConverter.convertDoubleToStrings(47.55);
        //check code
        assertEquals("47", map.get(DOLLAR));
        assertEquals("55", map.get(CENT));
    }

    @Test
    public void testConvertDollarsToTextCaseOne() {
        //run test
        String result = numberConverter.convertDollarsToText("4");
        //check code
        assertEquals("four", result);
    }


    @Test
    public void testConvertDollarsToTextCaseTwo() {
        //run test
        String result = numberConverter.convertDollarsToText("47");
        //check code
        assertEquals("forty seven", result);
    }

    @Test
    public void testConvertDollarsToTextCaseThree() {
        //run test
        String result = numberConverter.convertDollarsToText("458");
        //check code
        assertEquals("four hundred and fifty eight", result);
    }


    @Test
    public void testConvertDollarsToTextCaseFour() {
        //run test
        String result = numberConverter.convertDollarsToText("4759");
        //check code
        assertEquals("four thousand seven hundred and fifty nine", result);
    }

    @Test
    public void testConvertDollarsToTextCaseFive() {
        //run test
        String result = numberConverter.convertDollarsToText("41111");
        //check code
        assertEquals("forty one thousand one hundred and eleven", result);
    }


    @Test
    public void testConvertDollarsToTextCaseSix() {
        //run test
        String result = numberConverter.convertDollarsToText("471459");
        //check code
        assertEquals("four hundred and seventy one thousand four hundred and fifty nine", result);
    }

    @Test
    public void testConvertDollarsToTextWithError() {
        //run test
        try {
            numberConverter.convertDollarsToText("1000111.99");
            fail();
        } catch (Exception e) {
            //check code
            assertEquals("Length of input number is Illegal", e.getMessage());
        }
    }


    @Test
    public void testConvertCentToText() {
        //run test
        String result = numberConverter.convertCentToText("15");
        //check code
        assertEquals("fifteen", result);
    }

    @Test
    public void testConvertTwoDigitsToText() {
        //run test
        String result = numberConverter.convertTwoDigitsToText("56");
        //check code
        assertEquals("fifty six", result);
    }

    @Test
    public void testConvertThreeDigitsToText() {
        //run test
        String result = numberConverter.convertThreeDigitsToText("159");
        //check code
        assertEquals("one hundred and fifty nine", result);
    }

    @Test
    public void testConvertFourDigitsToText() {
        //run test
        String result = numberConverter.convertFourDigitsToText("1414");
        //check code
        assertEquals("one thousand four hundred and fourteen", result);
    }

    @Test
    public void testConvertFiveDigitsToText() {
        //run test
        String result = numberConverter.convertFiveDigitsToText("15000");
        //check code
        assertEquals("fifteen thousand", result);
    }

    @Test
    public void testConvertSixDigitsToText() {
        //run test
        String result = numberConverter.convertSixDigitsToText("151698");
        //check code
        assertEquals("one hundred and fifty one thousand six hundred and ninety eight", result);
    }

    @Test
    public void testConvertIntToText() {
        //run test
        String result = numberConverter.convertIntToText(50);
        //check code
        assertEquals("fifty", result);
    }

    @Test
    public void testConvertIntToTextWithError() {
        //run test
        try {
            numberConverter.convertIntToText(58);
            fail();
        } catch (Exception e) {
            //check code
            assertEquals(ERROR_TEXT_2, e.getMessage());
        }
    }
}