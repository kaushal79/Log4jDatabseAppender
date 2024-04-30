package com.crypto;

import java.io.File;
/**
 * A tester for the CryptoUtils class
 * @author www.codejava.net
 */
public class UtilsTest {
    public static void main(String[] args) {
        String key = "«ÒÙÏ˚>È*123ABCxyz@#$%^&!@#";
        //File inputFile = new File("//crypto/Test123.java");

        try {
            Utils.encrypt(key, inputFile);
            //Utils.decrypt(key, inputFile);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
