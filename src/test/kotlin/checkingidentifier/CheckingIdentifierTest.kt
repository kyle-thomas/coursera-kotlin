package checkingidentifier

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestSource() {
    @Test fun f() {
        assertEquals(true, isValidIdentifier("name"))   // true
        assertEquals(true, isValidIdentifier("_name"))  // true
        assertEquals(true, isValidIdentifier("_12"))    // true
        assertEquals(false, isValidIdentifier(""))       // false
        assertEquals(false, isValidIdentifier("012"))    // false
        assertEquals(false, isValidIdentifier("no$"))    // false
    }
}