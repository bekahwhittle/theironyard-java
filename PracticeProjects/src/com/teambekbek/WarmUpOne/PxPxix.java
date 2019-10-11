package com.teambekbek.WarmUpOne;

/**
 * Created by rdw1995 on 12/16/16.
 */
public class PxPxix {

    //x
//    Given a string name, e.g. "Bob", return a greeting of the form "Hello Bob!".
//
//    helloName("Bob") → "Hello Bob!"
//    helloName("Alice") → "Hello Alice!"
//    helloName("X") → "Hello X!"

    public String helloName(String name) {
        return "Hello" + "" + name + "!";
    }

// xi

//    Given a string, we'll say that the front is the first 3 chars of the string.
//    If the string length is less than 3, the front is whatever is there.
//    Return a new string which is 3 copies of the front.

//    front3("Java") → "JavJavJav"
//    front3("Chocolate") → "ChoChoCho"
//    front3("abc") → "abcabcabc"

    public String front3(String str){
        if(str.length() <= 3){
            return str + str + str;
        }
        else{
            String front = str.substring(0, 3);
            return front + front + front;
        }
    }

// xii
//    Given a string, take the last char and return a new string with the last char added at the front and back, so "cat" yields "tcatt".
//    The original string will be length 1 or more.
//    backAround("cat") → "tcatt"
//    backAround("Hello") → "oHelloo"
//    backAround("a") → "aaa"

    public String backAround(String str){
        String back = str.substring(str.length()-1);
        return back + str + back;
    }

    // xiii

//    Given two strings, a and b, return the result of putting them together in the order abba, e.g. "Hi" and "Bye" returns "HiByeByeHi".
//
//    makeAbba("Hi", "Bye") → "HiByeByeHi"
//    makeAbba("Yo", "Alice") → "YoAliceAliceYo"
//    makeAbba("What", "Up") → "WhatUpUpWhat"

    public String makeAbba(String a, String b) {
        return a + b + b + a;
    }
// xiv

//    Return true if the given non-negative number is a multiple of 3 or a multiple of 5.
//    Use the % "mod" operator -- see Introduction to Mod
//
//    or35(3) → true
//    or35(10) → true
//    or35(8) → false

    public boolean or35 (int n){
        if(n % 3 == 0 || n % 5 == 0){
            return true;
        }
        return false;
    }

// xv

//    Given a string, take the first 2 chars and return the string with the 2 chars added at both the front and back,
//    so "kitten" yields"kikittenki". If the string length is less than 2, use whatever chars are there.
//
//    front22("kitten") → "kikittenki"
//    front22("Ha") → "HaHaHa"
//    front22("abc") → "ababcab"

    public String front22 (String str) {
        if (str.length() == 1 || str.length() == 0){
            return str + str + str;
        }
        String front = str.substring(0, 1);
        return front + str + front;
    }

//xvi

//    Given a string, return true if the string starts with "hi" and false otherwise.
//
//    startHi("hi there") → true
//    startHi("hi") → true
//    startHi("hello hi") → false

    public boolean startHi(String str) {
       if(str.length() <= 2){
           return false;
       }
       if(str.substring(0, 2).equals("hi")){
           return true;
       }
       return false;
    }
// xvii


//    Given a string and a non-negative int n, return a larger string that is n copies of the original string.
//
//    stringTimes("Hi", 2) → "HiHi"
//    stringTimes("Hi", 3) → "HiHiHi"
//    stringTimes("Hi", 1) → "Hi"

    public String stringTimes(String str, int n) {
        String result = "";
        for(int i = 0; i < n; i++){
            result = result + str;
        }
        return result;
    }

// xviii

//    Given a string and a non-negative int n, we'll say that the front of the string is the first 3 chars, or whatever is there if the string is less than length 3. Return n copies of the front;
//
//    frontTimes("Chocolate", 2) → "ChoCho"
//    frontTimes("Chocolate", 3) → "ChoChoCho"
//    frontTimes("Abc", 3) → "AbcAbcAbc"

    public String frontTimes(String str, int n) {

        int frontLen = 3;
        if (frontLen > str.length()) {
            frontLen = str.length();
        }
        String front = str.substring(0, frontLen);

        String result = "";
        for (int i=0; i<n; i++) {
            result = result + front;
        }
        return result;
    }

// xix
//    Given an array of ints, return the number of 9's in the array.
//
//    arrayCount9([1, 2, 9]) → 1
//    arrayCount9([1, 9, 9]) → 2
//    arrayCount9([1, 9, 9, 3, 9]) → 3

    public int arrayCount9(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 9){
                count ++;
            }
        }
        return count;
    }

// xxx
//    When squirrels get together for a party, they like to have cigars.
//    A squirrel party is successful when the number of cigars is between 40 and 60, inclusive.
//    Unless it is the weekend, in which case there is no upper bound on the number of cigars.
//    Return true if the party with the given values is successful, or false otherwise.
//
//    cigarParty(30, false) → false
//    cigarParty(50, false) → true
//    cigarParty(70, true) → true

    public boolean cigarParty(int cigars, boolean isWeekend) {
        if (isWeekend = true){
            return true;
        }
        else {
            if ()
        }
        return false;
    }

}


