package com.mrboomdev.awery.sdk.util;

import com.mrboomdev.awery.sdk.util.exceptions.InvalidSyntaxException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FancyVersionTest {

    @Test
    void testValidation() {
        assertThrows(InvalidSyntaxException.class, () -> new FancyVersion("1..0.0"));
        assertThrows(InvalidSyntaxException.class, () -> new FancyVersion("v.", "v1.0.0.0"));

        assertNotEquals(new FancyVersion("1.4.2-BETA"), new FancyVersion("1.4.2-ALPHA"));
        assertTrue(new FancyVersion("1.4.2-BETA").equalsNumbers(new FancyVersion("1.4.2-ALPHA")));
    }

    @Test
    void testComparison() {
        assertEquals(0, FancyVersion.compare("1.0.0", "1.0.0"));
        assertEquals(-1, FancyVersion.compare("1.0.0", "1.0.1"));
        assertEquals(1, FancyVersion.compare("1.0.1", "1.0.0"));
    }

    @Test
    void testPrefixParsing() {
        assertDoesNotThrow(() -> FancyVersion.isValid("v", "v1.0.0"));
        assertEquals(1, new FancyVersion("v", "v1.0.0").getNumberPart(0));
        assertEquals(0, new FancyVersion("v", "v1.0.0").getNumberPart(1));
    }

    @Test
    void testSuffixParsing() {
        assertDoesNotThrow(() -> FancyVersion.isValid("v", "v1.0.0-DEBUG"));
        assertEquals(6, new FancyVersion("v", "v1.0.6-DEBUG").getNumberPart(2), 6);
        assertEquals("-DEBUG", new FancyVersion("v", "v1.0.0-DEBUG").getSuffix());
    }
}
