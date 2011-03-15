package org.riversun.string_grabber;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * 
 * Unit Tests for StringGrabber class
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class TestStringGrabberList {
    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_add_get_size() throws Exception {

        StringGrabberList o = new StringGrabberList();

        // add
        o.add(new StringGrabber("test01"));
        o.add(new StringGrabber("test02"));

        // check size method
        assertEquals(2, o.size());

        // check get method
        assertEquals("test01", o.get(0).toString());
        assertEquals("test02", o.get(1).toString());

    }

    @Test
    public void test_get() throws Exception {

        StringGrabberList o = new StringGrabberList();

        // add
        o.add(new StringGrabber("test01"));
        o.add(new StringGrabber("test02"));

        // check return value is empty string when index is over
        assertEquals("", o.get(-1).toString());
        assertEquals("", o.get(2).toString());
        assertEquals("", o.get(3).toString());

    }

    @Test
    public void test_getFirst_getSecond_getLast() throws Exception {
        StringGrabberList o = new StringGrabberList();

        o.add(new StringGrabber("test01"));
        o.add(new StringGrabber("test02"));
        o.add(new StringGrabber("test03"));

        assertEquals("test01", o.getFirst().toString());
        assertEquals("test02", o.getSecond().toString());
        assertEquals("test03", o.getLast().toString());

    }

    @Test
    public void test_toArray() throws Exception {
        StringGrabberList o = new StringGrabberList();

        o.add(new StringGrabber("test01"));
        o.add(new StringGrabber("test02"));
        o.add(new StringGrabber("test03"));

        assertArrayEquals(new String[] { "test01", "test02", "test03" }, o.toArray());

    }

    @Test
    public void test_toList() throws Exception {
        StringGrabberList o = new StringGrabberList();

        o.add(new StringGrabber("test01"));
        o.add(new StringGrabber("test02"));
        o.add(new StringGrabber("test03"));

        final List<String> list = o.toList();

        assertEquals(3, list.size());
        assertEquals("test01", list.get(0));
        assertEquals("test02", list.get(1));
        assertEquals("test03", list.get(2));

    }
}
