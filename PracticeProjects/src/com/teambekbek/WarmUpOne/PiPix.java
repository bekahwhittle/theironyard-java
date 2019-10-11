package com.teambekbek.WarmUpOne;

import com.teambekbek.Main;

import java.util.Iterator;

/**
 * Created by rdw1995 on 12/16/16.
 */
public class PiPix {

    // i
//    We have two monkeys, a and b, and the parameters aSmile and bSmile indicate if each is smiling.
//    We are in trouble if they are both smiling or if neither of them is smiling.
//    Return true if we are in trouble.
//    monkeyTrouble(true, true) → true
//    monkeyTrouble(false, false) → true
//    monkeyTrouble(true, false) → false*/
    public boolean monkeyTrouble(boolean aSmile, boolean bSmile) {
        if ((aSmile && bSmile) || (!aSmile && !bSmile)) {
            return true;
        }
        return false;
    }


//ii

    //Given two int values, return their sum. Unless the two values are the same, then return double their sum.
//
//sumDouble(1, 2) → 3
//sumDouble(3, 2) → 5
//sumDouble(2, 2) → 8
//
    public int sumDouble(int a, int b) {
        int sum = a + b;
        if (a == b) {
            sum = sum * 2;
        }
        return sum;
    }

// iii

//Given an int n, return the absolute difference between n and 21, except return double the absolute difference if n is over 21.
//diff21(19) → 2
//diff21(10) → 11
//diff21(21) → 0


    public int diff21(int n) {
        if (n <= 21) {
            return 21 - n;
        } else {
            return (n - 21) * 2;
        }
    }

// iv

//Given an array of ints, return true if the sequence of numbers 1, 2, 3 appears in the array somewhere.
//
//array123([1, 1, 2, 3, 1]) → true
//array123([1, 1, 2, 4, 1]) → false
//array123([1, 1, 2, 1, 2, 3]) → true*/

    public boolean array123(int[] nums) {
        for (int i = 0; i < (nums.length - 2); i++) {
            if (nums[i] == 1 && nums[i + 1] == 2 && nums[i + 2] == 3) {
                return true;
            }
        }
        return false;
    }

// v

    // Given an int n, return true if it is within 10 of 100 or 200. Note: Math.abs(num) computes the absolute value of a number.
//
// nearHundred(93) → true
// nearHundred(90) → true
// nearHundred(89) → false
    public boolean nearHundred(int n) {
        return ((Math.abs(100 - n) <= 10) || (Math.abs(200 - n) <= 10));
    }

// vi

// Given 2 int values, return true if one is negative and one is positive.
// Except if the parameter "negative" is true, then return true only if both are negative.
//
// posNeg(1, -1, false) → true
// posNeg(-1, 1, false) → true
// posNeg(-4, -5, true) → true

    public boolean posNeg(int a, int b, boolean negative) {
        if (negative) {
            return (a < 0 && b < 0);
        } else {
            return ((a > 0 && b < 0) || (a < 0 && b > 0));
        }
    }

// vii

// Given a string, return a new string where "not " has been added to the front.
// However, if the string already begins with "not", return the string unchanged.
// Note: use .equals() to compare 2 strings.

// notString("candy") → "not candy"
// notString("x") → "not x"
// notString("not bad") → "not bad"

    public String notString(String str) {
       if(str.contains("not")){
           return str;
       }
       else {
           return "not " + str;
       }
    }

// Viii

//    Given a non-empty string and an int n, return a new string where the char at index n has been removed.
//    The value of n will be a valid index of a char in the original string
//    (i.e. n will be in the range 0..str.length()-1 inclusive).
//
//    missingChar("kitten", 1) → "ktten"
//    missingChar("kitten", 0) → "itten"
//    missingChar("kitten", 4) → "kittn"

    public String missingChar(String str, int n) {
        String front = str.substring(0, n);
        String back = str.substring(n + 1, str.length());
        return front + back;
    }


//ix

//    Given a string, return a new string where the first and last chars have been exchanged.
//
//    frontBack("code") → "eodc"
//    frontBack("a") → "a"
//    frontBack("ab") → "ba"

    public String frontBack(String str) {
        if (str.length() <= 1){
            return str;
        }
        String mid = str.substring(1, str.length()-1);
        return str.charAt(str.length()-1) + mid + str.charAt(0);
    }
}