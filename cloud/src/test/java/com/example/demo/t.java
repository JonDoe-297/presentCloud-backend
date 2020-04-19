package com.example.demo;

public class t {
    public static void main(String[] args) {
        int n = 5;
        int c;
        for (c=0;n>0;++c){
            n &= (n-1);
        }
        System.out.println(c);
    }
}
