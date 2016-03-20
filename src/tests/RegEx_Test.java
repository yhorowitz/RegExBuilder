package tests;

import regex.RegEx;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by Yitzi on 3/19/2016.
 */
public class RegEx_Test {
    RegEx regex;
    Pattern p;
    Matcher m;

    @Before
    public void setUp() {
        regex = new RegEx();
    }

    @Test
    public void digitReturnsTrueWithASingleDigitString() {
        String toMatch = "3";

        assertTrue(matchWithPattern("\\d", toMatch));
        assertTrue(matchWithRegExBuilder(RegEx.digit(), toMatch));
    }

    @Test
    public void digitReturnsFalseWithAMultipleDigitString() {
        String toMatch = "3542423";

        assertFalse(matchWithPattern("\\d", toMatch));
        assertFalse(matchWithRegExBuilder(RegEx.digit(), toMatch));
    }

    @Test
    public void digitReturnsFalseWithAnEmptyString() {
        String toMatch = "";

        assertFalse(matchWithPattern("\\d", toMatch));
        assertFalse(matchWithRegExBuilder(RegEx.digit(), toMatch));
    }

    @Test
    public void digitReturnsFalseWithASingleNonDigitCharacter() {
        String toMatch = "a";

        assertFalse(matchWithPattern("\\d", toMatch));
        assertFalse(matchWithRegExBuilder(RegEx.digit(), toMatch));
    }

    @Test
    public void digitReturnsFalseWithMultipleNonDigitCharacters() {
        String toMatch = "g^a";

        assertFalse(matchWithPattern("\\d", toMatch));
        assertFalse(matchWithRegExBuilder(RegEx.digit(), toMatch));
    }

    @Test
    public void digitReturnsFalseWithAMixOfDigitAndNonDigitCharactersStartingWithADigit() {
        String toMatch = "1gerwger[]";

        assertFalse(matchWithPattern("\\d", toMatch));
        assertFalse(matchWithRegExBuilder(RegEx.digit(), toMatch));
    }

    @Test
    public void digitReturnsFalseWithAMixOfDigitAndNonDigitCharactersStartingWithANonDigit() {
        String toMatch = "asdf1gerwger[]";

        assertFalse(matchWithPattern("\\d", toMatch));
        assertFalse(matchWithRegExBuilder(RegEx.digit(), toMatch));
    }


    private Boolean matchWithPattern(String regex, String s) {
        p = Pattern.compile(regex);
        m = p.matcher(s);
        return m.matches();
    }

    private Boolean matchWithRegExBuilder(RegEx regex, String s) {
        p = Pattern.compile(regex.toString());
        m = p.matcher(s);
        return m.matches();
    }
}
