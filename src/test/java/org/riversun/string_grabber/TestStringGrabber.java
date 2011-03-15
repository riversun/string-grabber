package org.riversun.string_grabber;

import static org.junit.Assert.*;

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
public class TestStringGrabber {

    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_toLowerCase() throws Exception {
        String srcString = "MyNameIsTom";
        // lower
        assertEquals("myNameIsTom", new StringGrabber(srcString).replaceFirstToLowerCase()
            .toString());

        assertEquals("mynameIsTom", new StringGrabber(srcString).replaceToLowerCase(0, 6).toString());
        assertEquals("mynameistom", new StringGrabber(srcString).toLowerCase().toString());
    }

    @Test
    public void test_toUpperCase() throws Exception {
        String srcString = "MyNameIsTom";

        assertEquals("MyNameIsTom", new StringGrabber(srcString).replaceFirstToUpperCase().toString());
        assertEquals("MYNAMEIsTom", new StringGrabber(srcString).replaceToUpperCase(0, 6).toString());
        assertEquals("MYNAMEISTOM", new StringGrabber(srcString).toUpperCase().toString());
    }

    @Test
    public void test_removeHead() throws Exception {
        String srcString = "MyNameIsTom";
        assertEquals("NameIsTom", new StringGrabber(srcString).removeHead(2).toString());
    }

    @Test
    public void test_removeTail() throws Exception {
        String srcString = "MyNameIsTom";
        assertEquals("MyNameIsTo", new StringGrabber(srcString).removeTail()
            .toString());

        assertEquals("MyNameIs", new StringGrabber(srcString).removeTail(3)
            .toString());
    }

    @Test
    public void test_removeChain() throws Exception {
        String srcString = "MyNameIsTom";
        assertEquals("Mynameistom", new StringGrabber(srcString).toLowerCase()
            .replaceFirstToUpperCase()
            .toString());
    }

    @Test
    public void test_left() throws Exception {
        String srcString = "MyNameIsTom";
        assertEquals("MyNa", new StringGrabber(srcString).left(4)
            .toString());
    }

    @Test
    public void test_right() throws Exception {
        String srcString = "MyNameIsTom";
        assertEquals("Tom", new StringGrabber(srcString).right(3)
            .toString());
    }

    @Test
    public void test_insertIntoHead() throws Exception {
        String srcString = "MyNameIsTom";
        assertEquals("Hello,MyNameIsTom", new StringGrabber(srcString)
            .insertIntoHead("Hello,")
            .toString());
    }

    @Test
    public void test_copy() throws Exception {
        String srcString = "MyNameIsTom";

        String expected = srcString;
        String actual = new StringGrabber(srcString).copy().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void test_replace() throws Exception {

        final String text = "He is a good person.";
        StringGrabber sg = new StringGrabber(text);
        sg.replace("He", "She");

        String expected = "She is a good person.";
        String actual = sg.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void test_replaceEnclosedIn() throws Exception {

        final String text = "[a] [b] [[c]] <d>";
        StringGrabber sg = new StringGrabber(text);

        sg.replaceEnclosedIn("[", "]", "test");

        assertEquals("[test] [test] [[test]] <d>", sg.toString());
    }

    @Test
    public void test_substring() throws Exception {

        final String text = "0123456789";

        assertEquals("0", new StringGrabber(text).substring(0, 0).toString());
        assertEquals("0123", new StringGrabber(text).substring(0, 3).toString());
        assertEquals("0", new StringGrabber(text).substring(-1, 0).toString());
        assertEquals("0123", new StringGrabber(text).substring(-1, 3).toString());
        assertEquals("0123456789", new StringGrabber(text).substring(0, 20).toString());

    }

    @Test
    public void test_getLastPartsSeparatedBy() throws Exception {

        final String text = "aa/bb/cc/dd";

        assertEquals("dd", new StringGrabber(text).getLastPartsSeparatedBy("/").toString());

    }

    @Test
    public void test_toNumbers() throws Exception {

        final double delta = 0.0000001;

        // to int
        assertEquals((int) 1, new StringGrabber("1").getLastPartsSeparatedBy("/").toInt(0));

        // to float
        assertEquals(1.2f, new StringGrabber("1.2").getLastPartsSeparatedBy("/").toFloat(0f), delta);
        assertEquals(1.2f, new StringGrabber("1.2f").getLastPartsSeparatedBy("/").toFloat(0f), delta);

        // to double
        assertEquals(1.256256d, new StringGrabber("1.256256").getLastPartsSeparatedBy("/").toDouble(0f), delta);
        assertEquals(1.256256d, new StringGrabber("1.256256d").getLastPartsSeparatedBy("/").toDouble(0f), delta);
    }

    @Test
    public void test_toBoolean() throws Exception {

        assertEquals(true, new StringGrabber("true").toBoolean(false));
    }

    @Test
    public void test_appendRepeat() throws Exception {
        assertEquals("Xaaaaa", new StringGrabber("X").appendRepeat("a", 5).toString());
    }

    @Test
    public void test_insertRepeat() throws Exception {
        assertEquals("aaaaaX", new StringGrabber("X").insertRepeat("a", 5).toString());
    }

    @Test
    public void test_carryHead() throws Exception {
        String srcString = "0123456789";
        assertEquals("012", new StringGrabber(srcString).carryHead(3).toString());

    }

    @Test
    public void test_carryTail() throws Exception {
        String srcString = "0123456789";
        assertEquals("789", new StringGrabber(srcString).carryTail(3).toString());

    }

    @Test
    public void test_isEmpty() throws Exception {
        assertTrue(new StringGrabber("").isEmpty());
    }

    @Test
    public void test_isNotEmpty() throws Exception {
        assertTrue(new StringGrabber("A").isNotEmpty());
    }

    @Test
    public void test_getStringEnclosedInFirst() throws Exception {
        String srcString = "[part1] and [part2] and [part3]";
        assertEquals("part1", new StringGrabber(srcString).getStringEnclosedInFirst("[", "]").toString());

    }

    @Test
    public void test_getStringEnclosedIn01() throws Exception {
        // different token
        String srcString = "[part1] and [part2] and [part3]";
        assertEquals("part1", new StringGrabber(srcString).getStringEnclosedIn("[", "]").get(0).toString());
        assertEquals("part2", new StringGrabber(srcString).getStringEnclosedIn("[", "]").get(1).toString());
        assertEquals("part3", new StringGrabber(srcString).getStringEnclosedIn("[", "]").get(2).toString());
    }

    @Test
    public void test_getStringEnclosedIn02() throws Exception {
        // the same token
        String srcString = "|part1|part2||part3||";
        assertEquals("part1", new StringGrabber(srcString).getStringEnclosedIn("|", "|").get(0).toString());
        assertEquals("part2", new StringGrabber(srcString).getStringEnclosedIn("|", "|").get(1).toString());
        assertEquals("part3", new StringGrabber(srcString).getStringEnclosedIn("||", "||").get(0).toString());

    }

    @Test
    public void test_Split01() throws Exception {
        // ordinary
        String srcString = "012\t3456\t789\t\t101112";
        assertEquals("012", new StringGrabber(srcString).split("\t").get(0).toString());
        assertEquals("3456", new StringGrabber(srcString).split("\t").get(1).toString());
        assertEquals("789", new StringGrabber(srcString).split("\t").get(2).toString());
        assertEquals("", new StringGrabber(srcString).split("\t").get(3).toString());
        assertEquals("101112", new StringGrabber(srcString).split("\t").get(4).toString());
    }

    @Test
    public void test_Split02() throws Exception {
        // index is over
        String srcString = "012\t3456";
        assertEquals("012", new StringGrabber(srcString).split("\t").get(0).toString());
        assertEquals("3456", new StringGrabber(srcString).split("\t").get(1).toString());

        // if the index is over, then returns empty string
        assertEquals("", new StringGrabber(srcString).split("\t").get(2).toString());
    }

    @Test
    public void test_toSgList() throws Exception {
        String srcString = "line0\nline1\nline2";
        assertEquals("line0", new StringGrabber(srcString).toSgList().get(0).toString());
        assertEquals("line1", new StringGrabber(srcString).toSgList().get(1).toString());
        assertEquals("line2", new StringGrabber(srcString).toSgList().get(2).toString());
    }

    @Test
    public void test_newLine() throws Exception {
        String srcString = "abcdef";
        assertEquals("abcdef\n", new StringGrabber(srcString).newLine().toString());
    }

    @Test
    public void test_length() throws Exception {
        final String text = "0123456789";
        StringGrabber sg = new StringGrabber(text);
        assertEquals(10, sg.length());

    }

}