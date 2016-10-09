package xyz.enhorse;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         09/10/16
 */
public class ContentEntryTest {

    @Test
    public void title_latin() throws Exception {
        String original = "SimpleContentEntry";
        ContentEntry entry = new ContentEntry(original);

        assertEquals(original, entry.title());
    }


    @Test
    public void title_latin_symbols() throws Exception {
        String original = "simple-content;entry";
        ContentEntry entry = new ContentEntry(original);

        assertEquals(original, entry.title());
    }


    @Test
    public void title_latin_symbols_unicode() throws Exception {
        String original = "Simple*содежание_entry";
        ContentEntry entry = new ContentEntry(original);

        assertEquals(original, entry.title());
    }


    @Test
    public void link_latin_lowerCase() throws Exception {
        String original = "simple";
        ContentEntry entry = new ContentEntry(original);

        assertEquals(original, entry.link());
    }


    @Test
    public void link_latin_upperCase() throws Exception {
        String original = "SIMPLE";
        ContentEntry entry = new ContentEntry(original);

        assertEquals(original.toLowerCase(), entry.link());
    }


    @Test
    public void link_latin_mixedCase() throws Exception {
        String original = "SimpleEntry";
        ContentEntry entry = new ContentEntry(original);

        assertEquals(original.toLowerCase(), entry.link());
    }


    @Test
    public void link_symbols() throws Exception {
        String original = "simple;content*entry";
        ContentEntry entry = new ContentEntry(original);

        assertEquals("simplecontententry", entry.link());
    }


    @Test
    public void link_spaces() throws Exception {
        String original = "simple content entry";
        ContentEntry entry = new ContentEntry(original);

        assertEquals("simple-content-entry", entry.link());
    }


    @Test
    public void link_dashes() throws Exception {
        String original = "simple-content-entry";
        ContentEntry entry = new ContentEntry(original);

        assertEquals(original, entry.link());
    }


    @Test
    public void link_unicode() throws Exception {
        String original = "Содежание";
        ContentEntry entry = new ContentEntry(original);

        assertEquals(original, entry.link());
    }


    @Test
    public void link_latin_unicode_symbols_dashes_spaces() throws Exception {
        String original = "$Simple-Содержание entRy -";
        ContentEntry entry = new ContentEntry(original);

        assertEquals("simple-Содержание-entry--", entry.link());
    }


    @Test
    public void toString_format() throws Exception {
        String original = "SimpleContentEntry";
        ContentEntry entry = new ContentEntry(original);

        assertTrue(entry.toString().contains(original));
    }


    @Test
    public void equals_same() throws Exception {
        String original = "SimpleContentEntry";
        ContentEntry entry = new ContentEntry(original);

        assertEquals(entry, entry);
    }


    @Test
    public void equals_copy() throws Exception {
        String original = "SimpleContentEntry";
        ContentEntry originalEntry = new ContentEntry(original);
        ContentEntry copyEntry = new ContentEntry(original);

        assertTrue(originalEntry.equals(copyEntry));
        assertTrue(copyEntry.equals(originalEntry));
    }


    @Test
    public void equals_different() throws Exception {
        String original = "SimpleContentEntry";
        ContentEntry originalEntry = new ContentEntry(original);

        String another = "ComplexContentEntry";
        ContentEntry anotherEntry = new ContentEntry(another);

        assertFalse(originalEntry.equals(anotherEntry));
        assertFalse(anotherEntry.equals(originalEntry));
    }


    @Test
    public void hashCode_same() throws Exception {
        String original = "SimpleContentEntry";
        ContentEntry entry = new ContentEntry(original);

        assertEquals(entry.hashCode(), entry.hashCode());
    }


    @Test
    public void hashCode_copy() throws Exception {
        String original = "SimpleContentEntry";
        ContentEntry originalEntry = new ContentEntry(original);
        ContentEntry copyEntry = new ContentEntry(original);

        assertTrue(originalEntry.hashCode() == copyEntry.hashCode());
    }


    @Test
    public void hashCode_different() throws Exception {
        String original = "SimpleContentEntry";
        ContentEntry originalEntry = new ContentEntry(original);

        String another = "ComplexContentEntry";
        ContentEntry anotherEntry = new ContentEntry(another);

        assertFalse(originalEntry.hashCode() == anotherEntry.hashCode());
    }
}