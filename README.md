# Overview
'string-grabber' is a java library for manipulating and editing String object easily and quickly.

It is licensed under [MIT License](http://opensource.org/licenses/MIT).


#Downloads
## maven
- Add dependencies to maven pom.xml file.
```xml
<dependency>
  <groupId>org.riversun</groupId>
  <artifactId>string-grabber</artifactId>
  <version>0.1.0</version>
</dependency>
```

# methods
## StringGrabber#append
Simply append the text  
  
**want to do**
```
"abc" => "abcdef"
```
**source**
```java
import org.riversun.string_grabber.StringGrabber;

public class Example01 {

    public static void main(String[] args) {
        StringGrabber sg = new StringGrabber("My name is ");
        sg.append("tom.");
        System.out.println(sg.toString());
    }
}

```
**result**
```
My name is tom
```
## StringGrabber#insertIntoHead
 Insert string into the first
  
**want to do**
```
"abc" => "defabc"
```
**source**
```java
 StringGrabber sg = new StringGrabber("abc");
        sg.insertIntoHead("def");
```
**result**
```
defabc
```


## StringGrabber#newLine
append newline
  
**want to do**
```
abc  
=>  
abc  
def  

```
**source**
```java
 StringGrabber sg = new StringGrabber("");
        sg.append("line0");
        sg.newLine();
        sg.append("line1");
```
**result**
```
line0  
line1  
```
## StringGrabber#removeTail
Remove the last char from String
  
**want to do**
```
abcdef => abcde
```
**source**
```java
 StringGrabber sg = new StringGrabber("abcdef");
        sg.removeTail();
```
**result**
```
abcde
```

**source**
```java
 StringGrabber sg = new StringGrabber("abcdef");
        sg.removeTail(2);
```
**result**
```
abcd
```

## StringGrabber#removeHead
Remove char(s) from first
  
**want to do**
```
"abcdef" => "cdef"
```
**source**
```java
 StringGrabber sg = new StringGrabber("abcdef");
        sg.removeHead(2);
```
**result**
```
cdef
```

## StringGrabber#carryTail
To leave the tail of the N character , cut off the rest
  
**want to do**
```
"abcdef" => "ef"
```
**source**
```java
 StringGrabber sg = new StringGrabber("abcdef");
        sg.carryTail(2);
```
**result**
```
ef
```

## StringGrabber#carryHead
 To leave N character from head , cut off the rest
  
**want to do**
```
"abcdef" => "abc"
```
**source**
```java
 StringGrabber sg = new StringGrabber("abcdef");
        sg.carryHead(3);
```
**result**
```
abc
```

## StringGrabber#replaceFirstToLowerCase
convert first char to lower case
  
**want to do**
```
"ABC" => "aBC"
```
**source**
```java
  StringGrabber sg = new StringGrabber("ABCDEF");
        sg.replaceFirstToLowerCase();
```
**result**
```
aBCDEF

```
## StringGrabber#replaceToLowerCase
Convert string in the specified position to lower case
  
**want to do**
```
"ABCDEF" => "ABCdEF"
```
**source**
```java
 StringGrabber sg = new StringGrabber("ABCDEF");
        sg.replaceToLowerCase(3);
```
**result**
```
ABCdEF
```
**source**
```java
   StringGrabber sg = new StringGrabber("ABCDEF");
        sg.replaceToLowerCase(2,4);
```
**result**
```
ABcdEF

```

## StringGrabber#replace
replace specified string
  
**want to do**
```
"AAA is a pen.AAA is a penguin." => "BBB is a pen.BBB is a penguin."
```
**source**
```java
  StringGrabber sg = new StringGrabber("This is a pen.This is a penguin.This is a pencil.");
        sg.replace("This","That");
```
**result**
```
That is a pen.That is a penguin.That is a pencil.

```

## StringGrabber#replaceEnclosedIn
Replace specified string enclosed in speficied token
  
**want to do**
```
"<<A>><<B>><<C>>" => "<<X>><<X>><<X>>"
```
**source**
```java
  StringGrabber sg = new StringGrabber("<<sample1>><<sample2>><<sample3>>");
        sg.replaceEnclosedIn("<<",">>","REPLACE");
```
**result**
```
<<REPLACE>><<REPLACE>><<REPLACE>>

```
## StringGrabber#substring
returns substring specified with start index and endIndex
  
**want to do**
```
"0123456789" => "2345"
```
**source**
```java
 StringGrabber sg = new StringGrabber("0123456789");
        sg.substring(2,5);
```
**result**
```
●2345

```

## StringGrabber#getLastPartsSeparatedBy
 returns the last part that was separated by the specified string 
  
**want to do**
```
"a.b.c.d" => "d"
```
**source**
```java
  StringGrabber sg = new StringGrabber("http://github.com/riversun/test.html");
        sg.getLastPartsSeparatedBy("/");
```
**result**
```
test.html
```
## StringGrabber#toInt
 returns int value ,when format error occurred ,returns default value
  
**want to do**
```
"12345" => 12345
```
**source**
```java
  StringGrabber sg = new StringGrabber("12345");
        int value=sg.toInt(0);
```
**result**
```
12345
```
Also toFloat,toDouble,toBoolean is available.  

## StringGrabber#appendRepeat
Append a string to be repeated
  
**want to do**
```
"test" => "testAAAAAAAAA"
```
**source**
```java
  StringGrabber sg = new StringGrabber("test");
        sg.appendRepeat("A", 10);

```
**result**
```
testAAAAAAAAAA

```
## StringGrabber#insertRepeat
Append a string to be repeated into head
  
**want to do**
```
"test" => "AAAAAAAAAAtest
"
```
**source**
```java
 StringGrabber sg = new StringGrabber("test");
        sg.insertRepeat("A", 10);
```
**result**
```
AAAAAAAAAAtest

```
## StringGrabber#getStringEnclosedInFirst
returns the first one that enclosed in specific tokens found in the source string.
  
**want to do**
```
"part1part2" => "part1"
```
**source**
```java
  StringGrabber sg = new StringGrabber("<<part1>><<part2>>");
        final String startToken = "<<";
        final String endToken = ">>";
        String result = sg.getStringEnclosedInFirst(startToken, endToken).toString();
```
**result**
```
part1
```
## StringGrabber#getStringEnclosedIn
Returns all of discovery that enclosed in specific tokens found in the source string
  
**want to do**
```
part1part2
=>  
part1  
part2  
```
**source**
```java
 StringGrabber sg = new StringGrabber("<<part1>><<part2>>");
        final String startToken = "<<";
        final String endToken = ">>";
        StringGrabberList sgList = sg.getStringEnclosedIn(startToken, endToken);
        System.out.println(sgList.get(0).toString());
        System.out.println(sgList.get(1).toString());
```
**result**
```
part1
part2
```

## StringGrabber#split
Splits this string around matches of the given regular expression.
  
**want to do**
```
part1,part2,part3
=>
part1  
part2  
part3  
```
**source**
```java
 StringGrabber sg = new StringGrabber("part1,part2,part3");
        StringGrabberList sgList = sg.split(",");
        System.out.println(sgList.get(0).toString());
        System.out.println(sgList.get(1).toString());
        System.out.println(sgList.get(2).toString());
```
**result**
```
part1
part2
part3

```












