package com.company.stringUtils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StringUtils {
    public static int countOccurance(char c, String s) {
        int counter = 0;
        for (char ch : s.toCharArray()) {
            if (ch == c) counter++;
        }
        return counter;
    }

    public static int getIndexOfCharNotInsidePairOfTwoChars(String haystack, char needle, char c1, char c2) {
        int opened = 0, closed = 0;
        for (int i = haystack.length() - 1; i >= 0; i--) {
            char curChar = haystack.charAt(i);

            if (curChar == needle && opened == closed) return i;
            if (curChar == c1) opened++;
            if (curChar == c2) closed++;
        }
        return -1;
    }

    public static String unwrap(String s, char start, char end) {
        int startI = s.charAt(0) == start ? 1 : 0;
        if (startI == 0) return s;

        int normalEnd = s.length();
        int endI = s.charAt(normalEnd - 1) == end ? normalEnd - 1 : normalEnd;
        return s.substring(startI, endI);
    }
}
