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
 * List of StringGrabber
 */
public class StringGrabberList {

    private final List<StringGrabber> mSgList = new ArrayList<StringGrabber>();

    StringGrabberList() {

    }

    StringGrabberList(List<String> list) {

        for (String str : list) {
            add(new StringGrabber(str));
        }

    }

    void add(StringGrabber sg) {
        mSgList.add(sg);
    }

    /**
     * returns size of the list
     * 
     * @return
     */
    public int size() {
        return mSgList.size();
    }

    /**
     * clear all members of the list
     */
    public void clear() {
        mSgList.clear();
    }

    /**
     * returns a StringGrabber value specified by index <br>
     * even if index is less than 0 or index is beyond the size of list, safely
     * returns the empty string.
     * 
     * @param index
     * @return
     */
    public StringGrabber get(int index) {

        if (index < 0) {

            return new StringGrabber();

        } else if (index > size() - 1) {

            return new StringGrabber();

        } else {

            return mSgList.get(index);

        }
    }

    /**
     * returns the get first value
     * 
     * @return
     */
    public StringGrabber getFirst() {
        return get(0);
    }

    /**
     * returns the get second value
     * 
     * @return
     */
    public StringGrabber getSecond() {
        return get(1);
    }

    /**
     * returns the last value
     * 
     * @return
     */
    public StringGrabber getLast() {
        return get(size() - 1);
    }

    /**
     * returns values that converted to array of String
     * 
     * @return
     */
    public String[] toArray() {
        return toList().toArray(new String[] {});
    }

    /**
     * returns values that converted to List<String>
     * 
     * @return
     */
    public List<String> toList() {
        ArrayList<String> list = new ArrayList<String>();
        for (StringGrabber sg : mSgList) {
            list.add(sg.toString());
        }
        return list;

    }
}
