/*  string-grabber Easy to manipulate String
 *
 *  Copyright (c) 2006- Tom Misawa, riversun.org@gmail.com
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a
 *  copy of this software and associated documentation files (the "Software"),
 *  to deal in the Software without restriction, including without limitation
 *  the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *  and/or sell copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 *  DEALINGS IN THE SOFTWARE.
 *  
 */
package org.riversun.string_grabber;

import java.util.ArrayList;
import java.util.List;

/**
 * Crop and Cut out the string in a variety of means
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class StringCropper {

    /**
     * returns all of discovery that enclosed in specific tokens found in the
     * source string
     * 
     * @param srcStr
     * @param startToken
     * @param endToken
     * @return
     */
    public List<String> getStringEnclosedIn(String srcStr, String startToken, String endToken) {
        List<StrPosition> resultList = getStringEnclosedInWithDetails(srcStr, startToken, endToken);
        List<String> retList = new ArrayList<String>();
        for (StrPosition sp : resultList) {
            retList.add(sp.str);
        }
        return retList;
    }

    // enable blank string as found string.
    private static final boolean handlingBlankStrEnabled = true;

    /**
     * 
     * returns all of discovery that enclosed in specific tokens found in the
     * source string with position-detail-info.
     * 
     * @param srcStr
     * @param startToken
     * @param endToken
     * @return
     */
    public List<StrPosition> getStringEnclosedInWithDetails(String srcStr, String startToken, String endToken) {

        final List<StrPosition> retList = new ArrayList<StrPosition>();

        boolean searchInProgress = true;

        int cursor = 0;

        String workStr = srcStr;

        do {

            final StrPosition discoveryStr = getStringEnclosedInFirstWithDetails(workStr, startToken, endToken);

            if (discoveryStr == null) {
                break;
            }

            final String discoveredStr = discoveryStr.str;
            final int startIdx = discoveryStr.startIndex;
            int endIdx = discoveryStr.startIndex + discoveredStr.length() - 1;

            if (handlingBlankStrEnabled && "".equals(discoveredStr)) {
                endIdx = startIdx;
            }

            if (discoveredStr == null) {
                searchInProgress = false;
            }
            else if (!handlingBlankStrEnabled && isBlank(discoveredStr)) {

                searchInProgress = false;

            } else {

                final StrPosition currentDiscovery = new StrPosition();

                currentDiscovery.str = discoveredStr;
                currentDiscovery.startIndex = startIdx + cursor;
                currentDiscovery.endIndex = endIdx + cursor;

                retList.add(currentDiscovery);
            }

            workStr = removeHead(workStr, startIdx);

            cursor += startIdx;

            if (workStr.indexOf(startToken) < 0) {
                searchInProgress = false;
            }

        } while (searchInProgress);

        return retList;
    }

    /**
     * returns the first one that enclosed in specific tokens found in the
     * source string.
     * 
     * @param srcStr
     * @param startToken
     * @param endToken
     * @return
     */
    public String getStringEnclosedInFirst(String srcStr, String startToken, String endToken) {
        return getStringEnclosedInFirstWithDetails(srcStr, startToken, endToken).str;
    }

    /**
     * 
     * returns the first one that enclosed in specific tokens found in the
     * source string with position-detail-info
     * 
     * @param srcStr
     * @param startToken
     * @param endToken
     * @return
     */
    public StrPosition getStringEnclosedInFirstWithDetails(String srcStr, String startToken, String endToken) {

        final StrPosition result = new StrPosition();

        final boolean useTailMode = true;

        {
            StrPosition stringAfterToken;

            stringAfterToken = getAfterOfWithDetails(srcStr, startToken);

            StrPosition stringBeforeToken = getBeforeOfWithDetails(stringAfterToken.str, endToken);

            if (stringBeforeToken == null) {
                return null;
            }

            int startIndex = stringAfterToken.startIndex;
            int endIndex = startIndex + stringBeforeToken.str.length() - 1;

            if (handlingBlankStrEnabled && "".equals(stringBeforeToken.str)) {
                endIndex = startIndex;
            }

            result.str = stringBeforeToken.str;
            result.startIndex = startIndex;
            result.endIndex = endIndex;

        }

        if (useTailMode) {

            int tailOfIdx = tailOf(result.str, startToken);

            if (tailOfIdx >= 0) {
                result.str = removeHead(result.str, tailOfIdx + startToken.length());
                result.startIndex += tailOfIdx + 1;

                if (handlingBlankStrEnabled && "".equals(result.str)) {

                    result.endIndex = result.startIndex;
                } else {
                    result.endIndex = result.startIndex + result.str.length() - 1;
                }
            }
        }

        return result;
    }

    /**
     * returns string at index
     * 
     * @param srcStr
     * @param index
     * @return
     */
    public String stringAt(String srcStr, int index) {

        if (0 <= index && index < srcStr.length()) {
            return String.valueOf(srcStr.charAt(index));
        } else {
            return "";
        }

    }

    /**
     * returns the string that is cropped before token with position-detail-info<br>
     * 
     * @param srcStr
     * @param token
     * @return
     */
    public String getBeforeOf(String srcStr, String token) {
        return getBeforeOfWithDetails(srcStr, token).str;
    }

    /**
     * returns the string that is cropped before token with position-detail-info<br>
     * 
     * @param srcStr
     * @param token
     * @return
     */
    public StrPosition getBeforeOfWithDetails(String srcStr, String token) {

        final StrPosition retVal = new StrPosition();

        if (isBlank(srcStr) || isBlank(token)) {
            return retVal;
        }

        int tokenStartIndex = srcStr.indexOf(token);
        if (tokenStartIndex < 0) {
            return null;
        }

        String beforeTokenStr = getLeftOf(srcStr, tokenStartIndex);

        retVal.startIndex = 0;
        retVal.endIndex = tokenStartIndex - 1;
        retVal.str = beforeTokenStr;

        return retVal;

    }

    /**
     * returns the string that is cropped after token with position-detail-info<br>
     * <br>
     * Method Example1 tail mode=true,<br>
     * <br>
     * getAfterOf("AAABCDBCDEEE","BCD",true) returns "EEE" <br>
     * <br>
     * Method Example2 tail mode=true,<br>
     * getAfterOf("AAABCDBCDEEE","BCD",true) returns "BCDEEE"
     * 
     * @param srcStr
     * @param token
     * @param useTailSearch
     * @return
     */
    public String getAfterOf(String srcStr, String token, boolean useTailSearch) {
        return getAfterOfWithDetails(srcStr, token, useTailSearch).str;
    }

    /**
     * returns the string that is cropped after token<br>
     * <br>
     * Method Example,<br>
     * getAfterOf("AAABCDBCDEEE","BCD") returns"EEE"
     * 
     * @param srcStr
     * @param token
     * @return
     */
    public String getAfterOf(String srcStr, String token) {
        return getAfterOfWithDetails(srcStr, token).str;
    }

    /**
     * returns the string that is cropped after token with position-detail-info<br>
     * <br>
     * Method Example,<br>
     * <br>
     * getAfterOfWithDetails("AAABCDBCDEEE","BCD") returns str=EEE,
     * startIndex=9, endIndex=11<br>
     * 
     * @param srcStr
     * @param token
     * @return
     */
    public StrPosition getAfterOfWithDetails(String srcStr, String token) {
        return getAfterOfWithDetails(srcStr, token, true);
    }

    /**
     * returns the string that is cropped after token with position-detail-info<br>
     * <br>
     * Method Example1 tail mode=true,<br>
     * <br>
     * getAfterOfWithDetails("AAABCDBCDEEE","BCD",true) returns str=EEE,
     * startIndex=9, endIndex=11<br>
     * <br>
     * <br>
     * Method Example2 tail mode=true,<br>
     * getAfterOfWithDetails("AAABCDBCDEEE","BCD",true) returns str=BCDEEE,
     * startIndex=6, endIndex=11
     * 
     * 
     * @param srcStr
     * @param token
     * @param useTailSearch
     *            is use tail mode<br>
     *            {@link #tailOf(String, String)}
     * @return
     */
    public StrPosition getAfterOfWithDetails(String srcStr, String token, boolean useTailSearch) {

        final StrPosition retVal = new StrPosition();

        if (isBlank(srcStr) || isBlank(token)) {
            return retVal;
        }

        final int tokenStartIndex;

        if (useTailSearch) {
            tokenStartIndex = tailOf(srcStr, token);
        } else {
            tokenStartIndex = srcStr.indexOf(token);
        }

        if (tokenStartIndex < 0) {
            return retVal;
        }

        int tokenEndIndex = tokenStartIndex + token.length() - 1;

        int removeHeadCount = tokenEndIndex + 1;

        String afterTokenStr = removeHead(srcStr, removeHeadCount);

        retVal.str = afterTokenStr;
        retVal.startIndex = tokenEndIndex + 1;
        retVal.endIndex = srcStr.length() - 1;

        return retVal;
    }

    /**
     * Returns the index within this string of finally found value in the first
     * discovered string sequence<br>
     * <br>
     * Method Example,<br>
     * tailOf("ABABsampletextABAB","AB") returns 2.<br>
     * <br>
     * (You know, String#indexOf returns 0.)<br>
     * 
     * @param srcStr
     * @param searchStr
     * @return
     */
    public int tailOf(String srcStr, String searchStr) {

        if (isBlank(srcStr) || isBlank(searchStr)) {
            return -1;
        }

        int cursor = -1;

        boolean loop = true;

        boolean firstTokenAlreadyFound = false;
        do {
            int idx = srcStr.indexOf(searchStr);

            if (idx >= 0) {

                if (firstTokenAlreadyFound && idx >= searchStr.length()) {
                    loop = false;
                    break;
                }

                cursor++;

                if (idx == 0 && firstTokenAlreadyFound == false) {
                    firstTokenAlreadyFound = true;
                }

                srcStr = removeHead(srcStr, 1);

            } else {

                loop = false;

            }

        } while (loop);

        return cursor;
    }

    /**
     * Return the rest of the string that is cropped the number of chars from
     * the end of string
     * 
     * @param srcStr
     * @param charCount
     * @return
     */
    public String removeTail(String srcStr, int charCount) {
        return getLeftOf(srcStr, srcStr.length() - charCount);
    }

    /**
     * Return the rest of the string that is cropped the number of chars from
     * the beginning
     * 
     * @param srcStr
     * @param charCount
     * @return
     */
    public String removeHead(String srcStr, int charCount) {
        return getRightOf(srcStr, srcStr.length() - charCount);
    }

    /**
     * Returns the number of characters specified from left
     * 
     * @param srcStr
     * @param charCount
     * @return
     */
    public String getLeftOf(String srcStr, int charCount) {

        String retValue = "";

        if (isNotBlank(srcStr)) {

            int length = srcStr.length();

            if (charCount < length) {
                retValue = srcStr.substring(0, charCount);
            } else {
                retValue = srcStr;
            }
        }

        return retValue;
    }

    /**
     * Returns the number of characters specified from right
     * 
     * @param srcStr
     * @param charCount
     * @return
     */
    public String getRightOf(String srcStr, int charCount) {

        String retVal = "";

        if (isNotBlank(srcStr)) {

            int length = srcStr.length();

            if (charCount < length) {
                retVal = srcStr.substring(length - charCount, length);
            } else {
                retVal = srcStr;
            }
        } else {

        }

        return retVal;
    }

    /**
     * split string by index[indexes] <br>
     * <br>
     * Example<br>
     * <br>
     * #splitByIndex("abcdefghi",2,5) returns<br>
     * StrPosition [str=abc, startIndex=0, endIndex=2]<br>
     * StrPosition [str=def, startIndex=3, endIndex=5]<br>
     * StrPosition [str=ghi, startIndex=6, endIndex=8]<br>
     * <br>
     * 
     * @param srcStr
     * @param splitPoint
     * @return
     */
    public List<String> splitByIndex(String srcStr, Integer... splitPoint) {
        final List<StrPosition> strPosList = splitByIndexWithDetail(srcStr, splitPoint);
        final List<String> resultList = new ArrayList<String>();
        for (StrPosition strPos : strPosList) {
            resultList.add(strPos.str);
        }
        return resultList;

    }

    /**
     * split string by index[indexes] <br>
     * <br>
     * Example<br>
     * <br>
     * #splitByIndex("abcdefghi",2,5) returns<br>
     * StrPosition [str=abc, startIndex=0, endIndex=2]<br>
     * StrPosition [str=def, startIndex=3, endIndex=5]<br>
     * StrPosition [str=ghi, startIndex=6, endIndex=8]<br>
     * <br>
     * 
     * @param srcStr
     * @param splitPoint
     * @return
     */
    public List<StrPosition> splitByIndexWithDetail(String srcStr, Integer... splitPoint) {

        final List<StrPosition> resultList = new ArrayList<StrPosition>();
        if (splitPoint == null || splitPoint.length == 0) {
            return resultList;
        }

        int cursor = 0;

        int cutStartIndex = 0, cutEndIndex = 0;

        final int lastCharIndex = srcStr.length() - 1;

        for (int i = 0; i < splitPoint.length; i++) {

            cutStartIndex = cursor;
            cutEndIndex = splitPoint[i];

            if (cutEndIndex < 0) {
                continue;
            }

            if (cutStartIndex > lastCharIndex) {
                break;
            }

            if (cutEndIndex > lastCharIndex) {
                cutEndIndex = lastCharIndex;
            }

            final String blockStr = substring(srcStr, cutStartIndex, cutEndIndex);

            final StrPosition strPos = new StrPosition();
            strPos.str = blockStr;
            strPos.startIndex = cutStartIndex;
            strPos.endIndex = cutEndIndex;

            resultList.add(strPos);

            cursor = cutEndIndex + 1;

        }

        if (cutEndIndex < lastCharIndex) {
            // if the cursor did not go to the last, add the rest of string.
            cutEndIndex = lastCharIndex;
            cutStartIndex = cursor;
            String lastBlockStr = substring(srcStr, cutStartIndex, cutEndIndex);

            final StrPosition strPos = new StrPosition();
            strPos.str = lastBlockStr;
            strPos.startIndex = cutStartIndex;
            strPos.endIndex = cutEndIndex;
            resultList.add(strPos);

        }

        return resultList;
    }

    private String substring(String srcStr, int startIdx, int endIndex) {
        return srcStr.substring(startIdx, endIndex + 1);
    }

    /**
     * return string isn't null or empty("") char
     * 
     * @param str
     * @return
     */
    public boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * return is string null or empty("") char
     * 
     * @param str
     * @return
     */
    public boolean isBlank(String str) {
        return str == null || "".equals(str);
    }

    /**
     * property holder class called position-detail-info contains string and
     * it's position index.
     */
    public static class StrPosition {

        public String str = "";
        public int startIndex = -1;
        public int endIndex = -1;

        @Override
        public String toString() {
            return "StrPosition [str=" + str + ", startIndex=" + startIndex + ", endIndex=" + endIndex + "]";
        }

    }
}
