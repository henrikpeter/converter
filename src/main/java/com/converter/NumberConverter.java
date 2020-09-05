package com.converter;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NumberConverter {

    private static final Logger logger = LoggerFactory.getLogger(NumberConverter.class);
    String jsonString;
    private static final String DOLLAR = "Dollar";
    private static final String CENT = "Cent";
    private static final String THOUSAND = "Thousand";

    public NumberConverter() {
        //default constructor
    }

    /**
     * Main converter method. converts a double value into a text string.
     * ex. 47.50 = FORTY SEVEN DOLLARS AND FIFTY CENTS
     *
     * @param number
     * @return String
     * @throws IllegalArgumentException
     */
    public String convertNumberToText(double number) throws IllegalArgumentException, IOException, ParseException {
        if (number < 0 || number > 999999.99) {
            throw new IllegalArgumentException("number must not be negative or higher than 999.999.99");
        }
        initJsonString();
        Map<String, String> dc = convertDoubleToStrings(number);
        String result = convertDollarsToText(dc.get(DOLLAR)) + " Dollars" + " and " + convertCentToText(dc.get(CENT)) + " Cent";
        return result.toUpperCase();
    }


    /**
     * loads data form the json file into a String
     */
    void initJsonString() throws IOException, ParseException {
        try {
            JSONParser parser = new JSONParser();
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("numbers.json")).getFile());
            String data = FileUtils.readFileToString(file, "UTF-8");
            JSONArray jsonArray = (JSONArray) parser.parse(data);
            jsonString = jsonArray.toJSONString();
        } catch (IOException | ParseException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    /**
     * splits the double value into two strings , dollars and cent
     *
     * @param numberToConvert
     * @return Map<String, String>
     */
    Map<String, String> convertDoubleToStrings(Double numberToConvert) {
        String[] array = numberToConvert.toString().replace(".", "/").split("/", 2);
        Map<String, String> dollarCent = new HashMap<>();
        dollarCent.put(DOLLAR, array[0]);
        dollarCent.put(CENT, array[1]);
        return dollarCent;
    }

    /**
     * converts the value one the left side of the dot to text.
     *
     * @param dollars
     * @return String
     * @throws IllegalArgumentException
     */
    String convertDollarsToText(String dollars) throws IllegalArgumentException {
        int dollarLength = dollars.length();
        switch (dollarLength) {
            case 1:
                //0 to 9
                return convertNumberToText(Integer.parseInt(dollars));
            case 2:
                //10 to 99
                return convertTwoDigitsToText(dollars);
            case 3:
                //100 to 999
                return convertThreeDigitsToText(dollars);
            case 4:
                //1000 to 9999
                return convertFourDigitsToText(dollars);
            case 5:
                //10000 to 99000
                return convertFiveDigitsToText(dollars);
            case 6:
                //100000 to 999999
                return convertSixDigitsToText(dollars);
            default:
                logger.error("Length of input number is Illegal");
                throw new IllegalArgumentException("Length of input number is Illegal");

        }

    }

    /**
     * converts the value of the right side of the dot to text
     *
     * @param cent
     * @return String
     */
    String convertCentToText(String cent) {
        int centLength = cent.length();
        if (centLength == 1) {
            int centAsInt = Integer.parseInt(cent) * 10;
            return convertNumberToText(centAsInt);
        } else {
            return convertTwoDigitsToText(cent);
        }
    }

    /**
     * converts two digits to string
     *
     * @param dollar
     * @return String
     */
    String convertTwoDigitsToText(String dollar) {
        int dollarAsInt = Integer.parseInt(dollar);
        int digitOne = Integer.parseInt(dollar.substring(0, 1));
        int digitTwo = Integer.parseInt(dollar.substring(1, 2));

        if (dollarAsInt > 9 && dollarAsInt < 21) {
            return convertNumberToText(dollarAsInt);
        }

        final int digitOneMultiplied = digitOne * 10;
        String digitOneMultipliedAsString = convertNumberToText(digitOneMultiplied);
        String digitTwoAsString = convertNumberToText(digitTwo);
        return digitOneMultipliedAsString + " " + digitTwoAsString;
    }

    /**
     * converts three digits to string
     *
     * @param dollar
     * @return String
     */
    String convertThreeDigitsToText(String dollar) {
        int digitOne = Integer.parseInt(dollar.substring(0, 1));
        int digitTwoAndThree = Integer.parseInt(dollar.substring(1, 3));
        String digitOneAsString = convertNumberToText(digitOne) + " hundred";
        if (digitTwoAndThree > 0) {
            return digitOneAsString + " and " + convertTwoDigitsToText(Integer.toString(digitTwoAndThree));
        }
        return digitOneAsString;

    }

    /**
     * converts four digits to string
     *
     * @param dollar
     * @return String
     */
    String convertFourDigitsToText(String dollar) {
        int digitOne = Integer.parseInt(dollar.substring(0, 1));
        int digitTwoTilFour = Integer.parseInt(dollar.substring(1, 4));
        String digitOneAsString = convertNumberToText(digitOne) + " " + THOUSAND;
        if (digitTwoTilFour > 0) {
            return digitOneAsString + " " + convertThreeDigitsToText(Integer.toString(digitTwoTilFour));
        }
        return digitOneAsString;
    }

    /**
     * converts five digits to string
     *
     * @param dollar
     * @return String
     */
    String convertFiveDigitsToText(String dollar) {
        String digitOneAndTwo = convertTwoDigitsToText(dollar.substring(0, 2)) + " " + THOUSAND;
        int digitThreeTilFive = Integer.parseInt(dollar.substring(2, 5));
        if (digitThreeTilFive == 0) {
            return digitOneAndTwo;
        } else {
            return digitOneAndTwo + " " + convertThreeDigitsToText(Integer.toString(digitThreeTilFive));
        }

    }

    /**
     * converts six digits to string
     *
     * @param dollar
     * @return String
     */
    String convertSixDigitsToText(String dollar) {
        String digitOneTilThree = convertThreeDigitsToText(dollar.substring(0, 3)) + " " + THOUSAND;
        int digitFourTilSix = Integer.parseInt(dollar.substring(3, 6));
        if (digitFourTilSix == 0) {
            return digitOneTilThree;
        } else {
            return digitOneTilThree + " " + convertThreeDigitsToText(Integer.toString(digitFourTilSix));
        }
    }

    /**
     * converts a number to any given text value from the json file.
     *
     * @param number
     * @return String
     */
    String convertNumberToText(int number) {
        try {
            List<Map<String, String>> dataList = JsonPath.parse(jsonString).read("$[?(@.number == " + number + ")]");
            return dataList.get(0).get("text");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
