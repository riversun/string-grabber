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

import org.riversun.string_grabber.StringCropper.StrPosition;

/**
 * String wrapper class easy to manipulate String <br>
 * 
 * To operate followings easily<br>
 * Partial cutout of string<br>
 * binding<br>
 * add<br>
 * search<br>
 * conversion<br>
 * replace<br>
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 * 
 */
public class StringGrabber {

	private StringBuilder sb;

	private StringCropper cropper;

	public StringGrabber() {
		this("");
	}

	public StringGrabber(String str) {
		set(str);
	}

	public StringGrabber(StringGrabber sg) {
		set(sg.sb.toString());
	}

	/**
	 * new line char(s)
	 */
	private static String sNewLine = "\n";

	/**
	 * set new line char(s) for StringGrabber
	 * 
	 * @param newLine
	 */
	public static void setNewLine(String newLine) {
		sNewLine = newLine;
	}

	public StringGrabber copy() {
		return new StringGrabber(StringGrabber.this);
	}

	/**
	 * append string
	 * 
	 * @param str
	 * @return
	 */
	public StringGrabber append(String str) {
		sb.append(str);
		return StringGrabber.this;
	}

	/**
	 * set new source of string to this class
	 * 
	 * @param str
	 * @return
	 */
	public StringGrabber set(String str) {
		if (str == null)
		{
			str = "";
		}
		return set(new StringBuilder(str));
	}

	/**
	 * set new source of stringbuilder to this class
	 * 
	 * @param stringBuilder
	 * @return
	 */
	public StringGrabber set(StringBuilder stringBuilder) {
		if (stringBuilder == null) {
			stringBuilder = new StringBuilder();
		}
		sb = stringBuilder;
		return StringGrabber.this;
	}

	/**
	 * remove last char
	 * 
	 * @see StringGrabber#carryTail()
	 * @return
	 */
	public StringGrabber removeTail() {
		removeTail(1);
		return StringGrabber.this;
	}

	/**
	 * remove N chars from head
	 * 
	 * @see StringGrabber#carryHead()
	 * @param cnt
	 *            count of chars
	 * @return
	 */
	public StringGrabber removeHead(int cnt) {
		try {
			sb.delete(0, cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StringGrabber.this;
	}

	/**
	 * remove N chars from tail
	 * 
	 * @see StringGrabber#carryTail()
	 * @param cnt
	 *            count of chars
	 * @return
	 */
	public StringGrabber removeTail(int cnt) {
		int leng = sb.length();
		try {
			sb.delete(leng - cnt, leng);
		} catch (Exception e) {
		}
		return StringGrabber.this;
	}

	/**
	 * remove consecutive chars placed at the head
	 * 
	 * @param charToRemove
	 */
	public StringGrabber removeHeadConsecutiveChars(char charToRemove) {
		boolean loop = true;

		if (sb.length() > 0) {
			while (loop) {
				if (sb.charAt(0) == charToRemove) {
					removeHead(1);
				} else {
					loop = false;
				}
			}
		}
		return StringGrabber.this;
	}

	/**
	 * remove consecutive chars placed at tail end.
	 * 
	 * @param charToRemove
	 */
	public StringGrabber removeTailConsecutiveChars(char charToRemove) {
		boolean loop = true;

		if (sb.length() > 0) {
			while (loop) {
				if (sb.charAt(sb.length() - 1) == charToRemove) {
					removeTail(1);
				} else {
					loop = false;
				}
			}
		}
		return StringGrabber.this;
	}

	/**
	 * remove consecutive chars placed at top and tail end.
	 * 
	 * @param charToRemove
	 */
	public StringGrabber removeHeadAndTailChars(char charToRemove) {
		removeHeadConsecutiveChars(charToRemove);
		removeTailConsecutiveChars(charToRemove);
		return StringGrabber.this;
	}

	/**
	 * conver all chars to lower case
	 * 
	 * @see StringGrabber#toUpperCase()
	 * @return
	 */
	public StringGrabber toLowerCase() {
		set(sb.toString().toLowerCase());
		return StringGrabber.this;
	}

	/**
	 * conver all chars to upper case
	 * 
	 * @see StringGrabber#toUpperCase()
	 * @return
	 */
	public StringGrabber toUpperCase() {
		set(sb.toString().toUpperCase());
		return StringGrabber.this;
	}

	/**
	 * convert first char to lower case
	 * 
	 * @return
	 */
	public StringGrabber replaceFirstToLowerCase() {
		return replaceToLowerCase(0);
	}

	/**
	 * convert string in the specified position to lower case
	 * 
	 * @param pos
	 * @return
	 */
	public StringGrabber replaceToLowerCase(int pos) {
		return replaceCaseTo(pos, pos + 1, ECase.LOWER);
	}

	/**
	 * convert string in the specified position(range) to lower case
	 * 
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	public StringGrabber replaceToLowerCase(int startPos, int endPos) {
		return replaceCaseTo(startPos, endPos, ECase.LOWER);
	}

	/**
	 * convert first char to upper case
	 * 
	 * @return
	 */
	public StringGrabber replaceFirstToUpperCase() {
		return replaceToUpperCase(0);
	}

	/**
	 * convert string in the specified position to upper case
	 * 
	 * @param pos
	 * @return
	 */
	public StringGrabber replaceToUpperCase(int pos) {
		return replaceCaseTo(pos, pos + 1, ECase.UPPER);
	}

	/**
	 * convert string in the specified position(range) to upper case
	 * 
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	public StringGrabber replaceToUpperCase(int startPos, int endPos) {
		return replaceCaseTo(startPos, endPos, ECase.UPPER);
	}

	/**
	 * Insert string into the first
	 * 
	 * @param str
	 * @return
	 */
	public StringGrabber insertIntoHead(String str) {

		if (str != null) {
			try {
				sb.insert(0, str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return StringGrabber.this;
	}

	/**
	 * convert string in the specified position(range) to specified case
	 * 
	 * @param sb
	 * 
	 * @param startPos
	 * 
	 * @param endPos
	 */
	private StringGrabber replaceCaseTo(int startPos, int endPos, ECase toCase) {

		StringBuilder sbRange = new StringBuilder();

		try {
			for (int i = startPos; i < endPos; i++) {
				sbRange.append(String.valueOf(sb.charAt(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		final String caseConvertedRangeStr;

		if (ECase.LOWER == toCase) {
			caseConvertedRangeStr = sbRange.toString().toLowerCase();
		} else {
			caseConvertedRangeStr = sbRange.toString().toUpperCase();
		}
		try {
			sb.replace(startPos, endPos, caseConvertedRangeStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StringGrabber.this;
	}

	/**
	 * returns a new string that is a substring of this string from left.
	 * 
	 * @param cnt
	 * @return
	 */
	public StringGrabber left(int charCount) {
		String str = getCropper().getLeftOf(sb.toString(), charCount);
		sb = new StringBuilder(str);
		return StringGrabber.this;

	}

	/**
	 * returns a new string that is a substring of this string from right.
	 * 
	 * @param charCount
	 * @return
	 */
	public StringGrabber right(int charCount) {

		String str = getCropper().getRightOf(sb.toString(), charCount);
		sb = new StringBuilder(str);
		return StringGrabber.this;
	}

	/**
	 * replace specified string
	 * 
	 * @param target
	 * @param replacement
	 * @return
	 */
	public StringGrabber replace(String target, String replacement) {
		sb = new StringBuilder(sb.toString().replace(target, replacement));
		return StringGrabber.this;
	}

	/**
	 * replace specified string enclosed in speficied token
	 * 
	 * @param startToken
	 * @param endToken
	 * @param replacement
	 * @return
	 */
	public StringGrabber replaceEnclosedIn(String startToken, String endToken, String replacement) {

		StringCropper cropper = getCropper();

		final List<StrPosition> stringEnclosedInWithDetails = cropper.getStringEnclosedInWithDetails(sb.toString(), startToken, endToken);

		final List<Integer> splitList = new ArrayList<Integer>();

		for (StrPosition sp : stringEnclosedInWithDetails) {

			int splitPointFirst = sp.startIndex - 1;
			int splitPointSecond = sp.endIndex;

			splitList.add(splitPointFirst);
			splitList.add(splitPointSecond);
		}

		final Integer[] splitIndexes = splitList.toArray(new Integer[] {});

		List<String> splitStringList = cropper.splitByIndex(sb.toString(), splitIndexes);

		final StringBuilder tempSb = new StringBuilder();

		final int strNum = splitStringList.size();

		boolean nextIsValue = false;

		int countOfReplacement = 0;

		for (int i = 0; i < strNum; i++) {

			String strPart = splitStringList.get(i);

			if (nextIsValue) {
				// position to add into replacement
				tempSb.append(replacement);

				countOfReplacement++;

				if (strPart.startsWith(endToken)) {
					// is string Blank
					tempSb.append(strPart);
				}

			} else {
				tempSb.append(strPart);
			}

			if (strPart.endsWith(startToken)) {
				nextIsValue = true;
			} else {
				nextIsValue = false;
			}

		}

		sb = tempSb;

		return StringGrabber.this;

	}

	/**
	 * returns substring specified with start index and endIndex
	 * 
	 * @param startIdx
	 * @param endIndex
	 * @return
	 */
	public StringGrabber substring(int startIdx, int endIndex) {
		if (startIdx < 0) {
			startIdx = 0;
		}
		if (endIndex > length() - 1) {
			endIndex = length() - 1;
		}

		sb = new StringBuilder(substringInternally(startIdx, endIndex));
		return StringGrabber.this;
	}

	private String substringInternally(int startIdx, int endIndex) {
		return sb.toString().substring(startIdx, endIndex + 1);
	}

	public int length() {
		return sb.length();
	}

	/**
	 * 
	 * returns the last part that was separated by the specified string <br>
	 * 
	 * @param separator
	 * @return
	 */
	public StringGrabber getLastPartsSeparatedBy(String separator) {
		final String str = sb.toString();
		final int idx = str.lastIndexOf(separator);
		removeHead(idx + 1);
		return StringGrabber.this;
	}

	/**
	 * returns int value ,when format error occurred ,returns default value
	 * 
	 * @param defaultValue
	 * @return
	 */
	public int toInt(int defaultValue) {
		try {
			return Integer.parseInt(sb.toString());
		} catch (java.lang.NumberFormatException e) {
			e.printStackTrace();
		}
		return defaultValue;
	}

	/**
	 * returns double value ,when format error occurred ,returns default value
	 * 
	 * @param defaultValue
	 * @return
	 */
	public double toDouble(double defaultValue) {
		try {
			return Double.parseDouble(sb.toString());
		} catch (java.lang.NumberFormatException e) {
		}
		return defaultValue;
	}

	/**
	 * returns float value ,when format error occurred ,returns default value
	 * 
	 * @param defaultValue
	 * @return
	 */
	public float toFloat(float defaultValue) {
		try {
			return Float.parseFloat(sb.toString());
		} catch (java.lang.NumberFormatException e) {
		}
		return defaultValue;
	}

	/**
	 * 
	 * returns boolean value ,when format error occurred ,returns default value
	 * 
	 * @param defaultValue
	 * @return
	 */
	public boolean toBoolean(boolean defaultValue) {
		try {
			return Boolean.parseBoolean(sb.toString());
		} catch (java.lang.NumberFormatException e) {
		}
		return defaultValue;
	}

	/**
	 * Append a string to be repeated
	 * 
	 * @see StringGrabber#insertRepeat(String);
	 * @param str
	 * @param repeatCount
	 * @return
	 */
	public StringGrabber appendRepeat(String str, int repeatCount) {
		for (int i = 0; i < repeatCount; i++) {
			if (str != null) {
				sb.append(str);
			}
		}
		return StringGrabber.this;
	}

	/**
	 * Append a string to be repeated into head
	 * 
	 * @see StringGrabber#insertRepeat(String);
	 * @param str
	 * @param repeatCount
	 * @return
	 */
	public StringGrabber insertRepeat(String str, int repeatCount) {
		for (int i = 0; i < repeatCount; i++) {
			insertIntoHead(str);
		}
		return StringGrabber.this;
	}

	/**
	 * To leave N character from head , cut off the rest
	 * 
	 * @see StringGrabber#removeHead()
	 * @param charCount
	 * @return
	 */
	public StringGrabber carryHead(int charCount) {
		removeTail(length() - charCount);
		return StringGrabber.this;
	}

	/**
	 * To leave the tail of the N character , cut off the rest
	 * 
	 * @see StringGrabber#removeTail()
	 * @param charCount
	 * @return
	 */
	public StringGrabber carryTail(int charCount) {
		removeHead(length() - charCount);
		return StringGrabber.this;
	}

	/**
	 * returns true if and only if length() is 0.
	 * 
	 * @see StringGrabber#isNotEmpty()
	 * @return
	 */
	public boolean isEmpty() {
		return sb.toString().isEmpty();
	}

	/**
	 * returns true if and only if length() is NOT 0.
	 * 
	 * @see StringGrabber#isEmpty()
	 * @return
	 */
	public boolean isNotEmpty() {
		return !sb.toString().isEmpty();
	}

	/**
	 * returns the first one that enclosed in specific tokens found in the
	 * source string.
	 * 
	 * 
	 * @param startToken
	 * @param endToken
	 * @return
	 */
	public StringGrabber getStringEnclosedInFirst(String startToken, String endToken) {
		return new StringGrabber(getCropper().getStringEnclosedInFirst(sb.toString(), startToken, endToken));
	}

	/**
	 * returns all of discovery that enclosed in specific tokens found in the
	 * source string
	 * 
	 * 
	 * @param startToken
	 * @param endToken
	 * @return
	 */
	public StringGrabberList getStringEnclosedIn(String startToken, String endToken) {
		return new StringGrabberList(getCropper().getStringEnclosedIn(sb.toString(), startToken, endToken));
	}

	/**
	 * Splits this string around matches of the given regular expression.
	 * 
	 * @param regexp
	 * @return
	 */
	public StringGrabberList split(String regexp) {

		final StringGrabberList retList = new StringGrabberList();

		final String string = sb.toString();

		String[] splitedStrings = string.split(regexp);

		for (String str : splitedStrings) {
			retList.add(new StringGrabber(str));
		}

		return retList;

	}

	/**
	 * return what was split by a newline code
	 * 
	 * @return
	 */
	public StringGrabberList toSgList() {
		return toSgList(sNewLine);
	}

	/**
	 * return what was split by the specified newline code
	 * 
	 * @return
	 */
	private StringGrabberList toSgList(String newLineChars) {
		return split(newLineChars);
	}

	/**
	 * append new line to the String
	 * 
	 * @return
	 */
	public StringGrabber newLine() {
		append(sNewLine);
		return StringGrabber.this;
	}

	/**
	 * Compares this object to the specified object.
	 * 
	 * @param sg
	 * @return
	 */
	public boolean equals(StringGrabber sg) {
		return sg.toString().equals(sb.toString());
	}

	private StringCropper getCropper() {
		if (cropper == null) {
			cropper = new StringCropper();
		}
		return cropper;
	}

	private enum ECase {
		UPPER, LOWER
	}

	@Override
	public String toString() {
		return sb.toString();
	}

}
