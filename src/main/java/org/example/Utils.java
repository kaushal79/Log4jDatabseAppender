package com.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * A utility class that encrypts or decrypts a file.
 * @author www.codejava.net
 *
 */
public class Utils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static void encrypt(String key, File inputFile)
            throws Exception {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile);
    }

    public static void decrypt(String key, File inputFile)
            throws Exception {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile);
    }

    private static void doCrypto(int cipherMode, String key, File inputFile) throws Exception {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            String inputFileName = inputFile.getName();
            String cipherFileName = getNumericReferenceNumber(inputFileName);
            if(Cipher.ENCRYPT_MODE != cipherMode)
                cipherFileName = getStringReferenceNumber(inputFileName);


            System.out.println("File Name changed from " + inputFileName + " , to " + cipherFileName);
            System.out.println("Input File:" + inputFile.getAbsolutePath());

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            // File outputFile = new File(inputFile.getParent(), cipherFileName);
            // FileOutputStream outputStream = new FileOutputStream(outputFile);
            // outputStream.write(outputBytes);

            File currentDir = new File(".");
            File outputFile = new File(currentDir.getAbsolutePath(), cipherFileName);
            System.out.println("Output File: " + outputFile.getAbsolutePath());
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);


            inputStream.close();
            outputStream.close();
            // inputFile.delete();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                 | InvalidKeyException | BadPaddingException
                 | IllegalBlockSizeException | IOException ex) {
            throw new Exception("Error encrypting/decrypting file", ex);
        }
    }

    public static  Map<Character, String> map = new HashMap<>();
    public static  Map<String, Character> revmap = new HashMap<>();
    static
    {
        map.put('a', "01");
        map.put('b', "02");
        map.put('c', "03");
        map.put('d', "04");
        map.put('e', "05");
        map.put('f', "06");
        map.put('g', "07");
        map.put('h', "08");
        map.put('i', "09");
        map.put('j', "10");
        map.put('k', "11");
        map.put('l', "12");
        map.put('m', "13");
        map.put('n', "14");
        map.put('o', "15");
        map.put('p', "16");
        map.put('q', "17");
        map.put('r', "18");
        map.put('s', "19");
        map.put('t', "20");
        map.put('u', "21");
        map.put('v', "22");
        map.put('w', "23");
        map.put('x', "24");
        map.put('y', "25");
        map.put('z', "26");
        map.put('.', "27");

        for (Map.Entry<Character, String> entry : map.entrySet()) {
            revmap.put(entry.getValue(), entry.getKey());
        }
    }

    public static String getNumericReferenceNumber(String str) {

        String result = "";

        for (int i = 0; i < str.length(); i++) {

            char ch =  str.charAt(i);
            char lowerCase = ch;
            if (Character.isLetter(ch)) {
                lowerCase = Character.toLowerCase(ch);
            } else if (Character.isDigit(ch)) {
                lowerCase = '@';
            }

            result = map.containsKey(lowerCase) ? result.concat(map.get(lowerCase)) : result + lowerCase;

        }

        return result;
    }

    public static String getStringReferenceNumber(String str) {

        String result = "";

        for (int i = 0; i < str.length(); i++) {

            char ch =  str.charAt(i);
            if(Character.isDigit(ch)) {
                char nextch = str.charAt(i + 1);
                String key = Character.toString(ch) + Character.toString(nextch);
                System.out.println(key + ", " + i);
                result = revmap.containsKey(key) ? result.concat(Character.toString(revmap.get(key))) : result + key;
                i++;
            } else {
                result = result + Character.toString(ch);
            }

        }

        return result;
    }
}
