package org.riversun.string_grabber;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.riversun.string_grabber.StringCropper.StrPosition;

/**
 * 
 * Unit Tests for StringGrabber class
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class TestStringCropper {

    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_isBlank() throws Exception {
        StringCropper o = new StringCropper();
        assertTrue(o.isBlank(""));
        assertTrue(o.isBlank(null));
    }

    @Test
    public void test_isNotBlank() throws Exception {
        StringCropper o = new StringCropper();
        assertTrue(o.isNotBlank(" "));
        assertTrue(o.isNotBlank("abc123"));
    }

    @Test
    public void test_getRightOf() throws Exception {
        StringCropper o = new StringCropper();
        String actual = o.getRightOf("abc123", 3);
        String expected = "123";
        assertEquals(expected, actual);
    }

    @Test
    public void test_getLeftOf() throws Exception {
        StringCropper o = new StringCropper();
        String actual = o.getLeftOf("abc123", 3);
        String expected = "abc";
        assertEquals(expected, actual);
    }

    @Test
    public void test_removeHead() throws Exception {
        StringCropper o = new StringCropper();
        String actual = o.removeHead("abc123", 2);
        String expected = "c123";
        assertEquals(expected, actual);
    }

    @Test
    public void test_removeTail() throws Exception {
        StringCropper o = new StringCropper();
        String actual = o.removeTail("abc123", 2);
        String expected = "abc1";
        assertEquals(expected, actual);
    }

    @Test
    // test tailOf for single char
    public void test_tailOf_01() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "AAAABCDBCDBCDEEEEEEEEBCDBCDCAAAA";
        String searchStr = "A";

        int actual = o.tailOf(srcStr, searchStr);
        int expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    // test tailOf for multi char
    public void test_tailOf_02() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "AAAABCDBCDBCDEEEEEEEEBDCBDCBDCAAAA";
        String searchStr = "BCD";

        int actual = o.tailOf(srcStr, searchStr);
        int expected = 10;

        assertEquals(expected, actual);
    }

    @Test
    public void test_getAfterOf01() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "AAAABCDBCDEEEEBCD";
        String token = "BCD";

        String actual = o.getAfterOf(srcStr, token);
        String expected = "EEEEBCD";

        assertEquals(expected, actual);
    }

    @Test
    // Use tail mode
    public void test_getAfterOf02() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "AAAABCDBCDEEEEBCD";
        String token = "BCD";

        boolean useTailMode = true;
        String actual = o.getAfterOf(srcStr, token, useTailMode);
        String expected = "EEEEBCD";

        assertEquals(expected, actual);
    }

    @Test
    // Don't use tail mode
    public void test_getAfterOf03() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "AAAABCDBCDEEEEBCD";
        String token = "BCD";

        boolean useTailMode = false;
        String actual = o.getAfterOf(srcStr, token, useTailMode);
        String expected = "BCDEEEEBCD";

        assertEquals(expected, actual);
    }

    @Test
    public void test_getAfterOfWithDetails01() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "AAAABCDBCDEEEEBCD";
        String token = "BCD";

        StrPosition actual = o.getAfterOfWithDetails(srcStr, token);
        StrPosition expected = new StrPosition();
        expected.str = "EEEEBCD";
        expected.startIndex = 10;
        expected.endIndex = 16;

        assertEquals(expected.str, actual.str);
        assertEquals(expected.startIndex, actual.startIndex);
        assertEquals(expected.endIndex, actual.endIndex);
    }

    @Test
    // Use tail mode
    public void test_getAfterOfWithDetails02() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "AAAABCDBCDEEEEBCD";
        String token = "BCD";

        boolean useTailMode = true;
        StrPosition actual = o.getAfterOfWithDetails(srcStr, token, useTailMode);
        StrPosition expected = new StrPosition();
        expected.str = "EEEEBCD";
        expected.startIndex = 10;
        expected.endIndex = 16;

        assertEquals(expected.str, actual.str);
        assertEquals(expected.startIndex, actual.startIndex);
        assertEquals(expected.endIndex, actual.endIndex);
    }

    @Test
    // Don't use tail mode
    public void test_getAfterOfWithDetails03() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "AAAABCDBCDEEEEBCD";
        String token = "BCD";

        boolean useTailMode = false;
        StrPosition actual = o.getAfterOfWithDetails(srcStr, token, useTailMode);
        StrPosition expected = new StrPosition();

        expected.str = "BCDEEEEBCD";
        expected.startIndex = 7;
        expected.endIndex = 16;

        assertEquals(expected.str, actual.str);
        assertEquals(expected.startIndex, actual.startIndex);
        assertEquals(expected.endIndex, actual.endIndex);
    }

    @Test
    public void test_getBeforeOf() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "AAAABCDBCDEEEEBCD";
        String token = "EEEE";

        StrPosition actual = o.getBeforeOfWithDetails(srcStr, token);
        StrPosition expected = new StrPosition();

        expected.str = "AAAABCDBCD";
        expected.startIndex = 0;
        expected.endIndex = 9;

        assertEquals(expected.str, actual.str);
        assertEquals(expected.startIndex, actual.startIndex);
        assertEquals(expected.endIndex, actual.endIndex);
    }

    @Test
    public void test_getBeforeOfWithDetails() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "AAAABCDBCDEEEEBCD";
        String token = "EEEE";

        String actual = o.getBeforeOf(srcStr, token);
        String expected = "AAAABCDBCD";

        assertEquals(actual, expected);
    }

    @Test
    public void test_stringAt() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "0123456789";

        assertEquals(o.stringAt(srcStr, 5), "5");
        assertEquals(o.stringAt(srcStr, 10), "");
        assertEquals(o.stringAt(srcStr, -5), "");
    }

    @Test
    public void test_getStringEnclosedInFirstWithDetails() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "This is test [part1] and [[part2]] and [[[part3]]] .That's it.";
        {// no.1
            String startToken = "[";
            String endToken = "]";

            StrPosition actual = o.getStringEnclosedInFirstWithDetails(srcStr, startToken, endToken);
            StrPosition expected = new StrPosition();

            expected.str = "part1";
            expected.startIndex = 14;
            expected.endIndex = 18;

            assertEquals(expected.str, actual.str);
            assertEquals(expected.startIndex, actual.startIndex);
            assertEquals(expected.endIndex, actual.endIndex);
        }
        {// no.2
            String startToken = "[[";
            String endToken = "]]";

            StrPosition actual = o.getStringEnclosedInFirstWithDetails(srcStr, startToken, endToken);
            StrPosition expected = new StrPosition();

            expected.str = "part2";
            expected.startIndex = 27;
            expected.endIndex = 31;

            assertEquals(expected.str, actual.str);
            assertEquals(expected.startIndex, actual.startIndex);
            assertEquals(expected.endIndex, actual.endIndex);
        }
        {// no.3
            String startToken = "[[[";
            String endToken = "]]]";

            StrPosition actual = o.getStringEnclosedInFirstWithDetails(srcStr, startToken, endToken);
            StrPosition expected = new StrPosition();

            expected.str = "part3";
            expected.startIndex = 42;
            expected.endIndex = 46;

            assertEquals(expected.str, actual.str);
            assertEquals(expected.startIndex, actual.startIndex);
            assertEquals(expected.endIndex, actual.endIndex);
        }
    }

    @Test
    public void test_getStringEnclosedInFirst() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "This is test [part1] and [[part2]] and [[[part3]]] .That's it.";
        {// no.1
            String startToken = "[";
            String endToken = "]";

            String actual = o.getStringEnclosedInFirst(srcStr, startToken, endToken);
            String expected = "part1";

            assertEquals(expected, actual);

        }
    }

    @Test
    public void test_getStringEnclosedInWithDetails_01() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "This is test [part1] and [[part2]] and [[[part3]]] .That's it.";

        String startToken = "[";
        String endToken = "]";

        List<StrPosition> resultList = o.getStringEnclosedInWithDetails(srcStr, startToken, endToken);

        {// no.1

            StrPosition actual = resultList.get(0);
            StrPosition expected = new StrPosition();
            expected.str = "part1";
            expected.startIndex = 14;
            expected.endIndex = 18;
            assertEquals(expected.str, actual.str);
            assertEquals(expected.startIndex, actual.startIndex);
            assertEquals(expected.endIndex, actual.endIndex);
        }
        {// no.2

            StrPosition actual = resultList.get(1);
            StrPosition expected = new StrPosition();
            expected.str = "part2";
            expected.startIndex = 27;
            expected.endIndex = 31;
            assertEquals(expected.str, actual.str);
            assertEquals(expected.startIndex, actual.startIndex);
            assertEquals(expected.endIndex, actual.endIndex);
        }
        {// no.3

            StrPosition actual = resultList.get(2);
            StrPosition expected = new StrPosition();
            expected.str = "part3";
            expected.startIndex = 42;
            expected.endIndex = 46;
            assertEquals(expected.str, actual.str);
            assertEquals(expected.startIndex, actual.startIndex);
            assertEquals(expected.endIndex, actual.endIndex);
        }

    }

    @Test
    public void test_getStringEnclosedInWithDetails_02() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "This is test [part1] and [[part2]] and [[[part3]]] .That's it.";

        String startToken = "[[";
        String endToken = "]]";

        List<StrPosition> resultList = o.getStringEnclosedInWithDetails(srcStr, startToken, endToken);

        {// no.1

            StrPosition actual = resultList.get(0);
            StrPosition expected = new StrPosition();

            expected.str = "part2";
            expected.startIndex = 27;
            expected.endIndex = 31;
            assertEquals(expected.str, actual.str);
            assertEquals(expected.startIndex, actual.startIndex);
            assertEquals(expected.endIndex, actual.endIndex);

        }
        {// no.2

            StrPosition actual = resultList.get(1);
            StrPosition expected = new StrPosition();
            expected.str = "part3";
            expected.startIndex = 42;
            expected.endIndex = 46;
            assertEquals(expected.str, actual.str);
            assertEquals(expected.startIndex, actual.startIndex);
            assertEquals(expected.endIndex, actual.endIndex);
        }

    }

    @Test
    public void test_getStringEnclosedInWithDetails_03() throws Exception {

        StringCropper o = new StringCropper();

        String srcStr = "This is test [part1] and [[part2]] and [[[part3]]] .That's it.";

        String startToken = "[[[";
        String endToken = "]]]";

        List<StrPosition> resultList = o.getStringEnclosedInWithDetails(srcStr, startToken, endToken);

        {// no.1

            StrPosition actual = resultList.get(0);
            StrPosition expected = new StrPosition();
            expected.str = "part3";
            expected.startIndex = 42;
            expected.endIndex = 46;
            assertEquals(expected.str, actual.str);
            assertEquals(expected.startIndex, actual.startIndex);
            assertEquals(expected.endIndex, actual.endIndex);
        }

    }

    @Test
    public void test_getStringEnclosedIn() throws Exception {
        StringCropper o = new StringCropper();

        String srcStr = "This is test [part1] and [[part2]] and [[[part3]]] .That's it.";

        String startToken = "[";
        String endToken = "]";

        List<String> resultList = o.getStringEnclosedIn(srcStr, startToken, endToken);

        assertEquals("part1", resultList.get(0));
        assertEquals("part2", resultList.get(1));
        assertEquals("part3", resultList.get(2));
    }

    // ordinary
    @Test
    public void test_splitByIndex_01() throws Exception {
        StringCropper o = new StringCropper();

        // size is 10
        String srcStr = "abcdefghik";

        final int splitPoint1 = 2;
        final int splitPoint2 = 5;
        List<StrPosition> resultList = o.splitByIndexWithDetail(srcStr, splitPoint1, splitPoint2);

        assertEquals(3, resultList.size());

        StrPosition pos1 = resultList.get(0);

        assertEquals("abc", pos1.str);
        assertEquals(0, pos1.startIndex);
        assertEquals(2, pos1.endIndex);

        StrPosition pos2 = resultList.get(1);
        assertEquals("def", pos2.str);
        assertEquals(3, pos2.startIndex);
        assertEquals(5, pos2.endIndex);

        StrPosition pos3 = resultList.get(2);
        assertEquals("ghik", pos3.str);// rest of the str
        assertEquals(6, pos3.startIndex);
        assertEquals(9, pos3.endIndex);

    }

    // ordinary2
    @Test
    public void test_splitByIndex_02() throws Exception {
        StringCropper o = new StringCropper();

        // size is 10
        String srcStr = "abcdefghik";

        final int splitPoint1 = 2;
        final int splitPoint2 = 5;
        final int splitPoint3 = 9;// look!
        List<StrPosition> resultList = o.splitByIndexWithDetail(srcStr, new Integer[] { splitPoint1, splitPoint2, splitPoint3 });

        assertEquals(3, resultList.size());

        StrPosition pos1 = resultList.get(0);

        assertEquals("abc", pos1.str);
        assertEquals(0, pos1.startIndex);
        assertEquals(2, pos1.endIndex);

        StrPosition pos2 = resultList.get(1);
        assertEquals("def", pos2.str);
        assertEquals(3, pos2.startIndex);
        assertEquals(5, pos2.endIndex);

        StrPosition pos3 = resultList.get(2);
        assertEquals("ghik", pos3.str);// rest of the str
        assertEquals(6, pos3.startIndex);
        assertEquals(9, pos3.endIndex);

    }

    // index is outbound part1
    @Test
    public void test_splitByIndex_03() throws Exception {
        StringCropper o = new StringCropper();

        // size is 10
        String srcStr = "abcdefghik";

        final int splitPoint1 = 2;
        final int splitPoint2 = 5;
        final int splitPoint3 = 12;// look!
        final int splitPoint4 = 20;// look!
        List<StrPosition> resultList = o.splitByIndexWithDetail(srcStr, new Integer[] { splitPoint1, splitPoint2, splitPoint3, splitPoint4 });

        assertEquals(3, resultList.size());

        StrPosition pos1 = resultList.get(0);

        assertEquals("abc", pos1.str);
        assertEquals(0, pos1.startIndex);
        assertEquals(2, pos1.endIndex);

        StrPosition pos2 = resultList.get(1);
        assertEquals("def", pos2.str);
        assertEquals(3, pos2.startIndex);
        assertEquals(5, pos2.endIndex);

        StrPosition pos3 = resultList.get(2);
        assertEquals("ghik", pos3.str);// rest of the str
        assertEquals(6, pos3.startIndex);
        assertEquals(9, pos3.endIndex);

    }

    // index is outbound part2
    @Test
    public void test_splitByIndex_04() throws Exception {
        StringCropper o = new StringCropper();

        // size is 10
        String srcStr = "abcdefghik";

        final int splitPoint1 = -1;// look!
        final int splitPoint2 = 2;
        final int splitPoint3 = 5;
        List<StrPosition> resultList = o.splitByIndexWithDetail(srcStr, new Integer[] { splitPoint1, splitPoint2, splitPoint3 });

        assertEquals(3, resultList.size());

        StrPosition pos1 = resultList.get(0);

        assertEquals("abc", pos1.str);
        assertEquals(0, pos1.startIndex);
        assertEquals(2, pos1.endIndex);

        StrPosition pos2 = resultList.get(1);
        assertEquals("def", pos2.str);
        assertEquals(3, pos2.startIndex);
        assertEquals(5, pos2.endIndex);

        StrPosition pos3 = resultList.get(2);
        assertEquals("ghik", pos3.str);// rest of the str
        assertEquals(6, pos3.startIndex);
        assertEquals(9, pos3.endIndex);

    }

    // splitByIndex
    @Test
    public void test_splitByIndex_05() throws Exception {
        StringCropper o = new StringCropper();

        // size is 10
        String srcStr = "abcdefghik";

        final int splitPoint1 = 2;
        final int splitPoint2 = 5;
        List<String> resultList = o.splitByIndex(srcStr, splitPoint1, splitPoint2);

        assertEquals(3, resultList.size());

        assertEquals("abc", resultList.get(0));
        assertEquals("def", resultList.get(1));
        assertEquals("ghik", resultList.get(2));

    }
}
