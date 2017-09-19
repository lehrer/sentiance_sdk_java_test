package com.company.Utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gershonlehrer on 19/09/2017.
 */
public class FileUtilities {
    public static int showFiles(File[] files, String stringToFind) throws IOException {
        int count = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                count += showFiles(file.listFiles(), stringToFind); // Calls same method again.
            } else {
                System.out.println("File: " + file.getName());
                count += countStringOccurencesInFile(file, stringToFind);
            }
        }
        return count;
    }

    public static List<Integer> countFilesDirectory(File[] files, String stringToFind) throws IOException {
        List<Integer> integerList = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                integerList.add(showFiles(file.listFiles(), stringToFind));
            } else {
                System.out.println("File: " + file.getName());
                if (integerList.size() > 0) {
                    int position = integerList.size() - 1;
                    int currentValue = integerList.get(position);
                    integerList.set(position, currentValue + countStringOccurencesInFile(file, stringToFind));
                } else {
                    integerList.add(countStringOccurencesInFile(file, stringToFind));
                }
            }
        }
        return integerList;
    }

    public static int countStringOccurencesInFile(File file, String stringToFind) throws IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
//                sb.append(line);
//                sb.append(System.lineSeparator());
                if (line.toLowerCase().contains(stringToFind.toLowerCase()))
                    count++;
                line = br.readLine();

            }
//            String everything = sb.toString();
        }
        return count;
    }
}
