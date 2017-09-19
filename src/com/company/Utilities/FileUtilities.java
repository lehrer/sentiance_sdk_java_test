package com.company.Utilities;

import com.company.models.SentianceEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gershonlehrer on 19/09/2017.
 */
public class FileUtilities {
    private static String mCurrentUser;

//    public static int showFiles(File[] files, String stringToFind) throws IOException {
//        int count = 0;
//        for (File file : files) {
//            if (file.isDirectory()) {
//                System.out.println("Directory: " + file.getName());
//                count += showFiles(file.listFiles(), stringToFind); // Calls same method again.
//            } else {
//                System.out.println("File: " + file.getName());
//
//                count += countStringOccurencesInFile(file, stringToFind);
//            }
//        }
//        return count;
//    }

    public static List<SentianceEvent> showFiles(File[] files, String stringToFind) throws IOException {
        return showFiles(files, "", stringToFind);
    }

    public static List<SentianceEvent> showFiles(File[] files, String user, String stringToFind) throws IOException {
        List<SentianceEvent> sentianceEventList = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                mCurrentUser = file.getName();
                sentianceEventList.addAll(showFiles(file.listFiles(), mCurrentUser, stringToFind)); // Calls same method again.
            } else {
                System.out.println("File: " + file.getName());
                sentianceEventList.addAll(getMatchingEventsAsList(file, mCurrentUser, stringToFind));
            }
        }
        return sentianceEventList;
    }

//    public static List<SentianceEvent> countFilesDirectory(File[] files, String stringToFind) throws IOException {
//        List<Integer> integerList = new ArrayList<>();
//        List<SentianceEvent> sentianceEventList = new ArrayList<>();
//        String currentUser="";
//        for (File file : files) {
//            if (file.isDirectory()) {
//                System.out.println("Directory: " + file.getName());
//                if (file.getName().toLowerCase().startsWith("user")){
//                    currentUser=file.getName().toLowerCase();
//                }
//                integerList.add(showFiles(file.listFiles(), stringToFind));
//            } else {
//                System.out.println("File: " + file.getName());
//                if (integerList.size() > 0) {
//                    int position = integerList.size() - 1;
//                    int currentValue = integerList.get(position);
//                    integerList.set(position, currentValue + countStringOccurencesInFile(file, stringToFind));
//                } else {
//                    sentianceEventList.addAll(getMatchingEventsAsList(file, currentUser,stringToFind));
//                }
//            }
//        }
//        return sentianceEventList;
//    }

//    public static List<SentianceEvent> countFilesDirectory(File[] files,String user, String stringToFind) throws IOException {
//        List<SentianceEvent> sentianceEventList = new ArrayList<>();
//        String currentUser="";
//        for (File file : files) {
//            if (file.isDirectory()) {
//                System.out.println("Directory: " + file.getName());
//                if (file.getName().toLowerCase().startsWith("user")){
//                    currentUser=file.getName().toLowerCase();
//                }
//                integerList.add(showFiles(file.listFiles(), stringToFind));
//            } else {
//                System.out.println("File: " + file.getName());
//
//                if (integerList.size() > 0) {
//                    int position = integerList.size() - 1;
//                    int currentValue = integerList.get(position);
//                    integerList.set(position, currentValue + getMatchingEventsAsList(file,user, stringToFind));
//                } else {
//                    integerList.add(getMatchingEventsAsList(file, user,stringToFind));
//                }
//            }
//        }
//        return sentianceEventList;
//    }

    public static int countStringOccurencesInFile(File file, String stringToFind) throws IOException {
        int count = 0;
        //use as  HashMap<useridString, HashMap<eventDate, eventString>>
        HashMap<String, HashMap<Date, String>> stringHashMapHashMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
//                sb.append(line);
//                sb.append(System.lineSeparator());
                if (line.toLowerCase().contains(stringToFind.toLowerCase()))
                    count++;
                Date date = convertStringToDate(line.substring(0, 23));
                line = br.readLine();

            }
//            String everything = sb.toString();
        }
        return count;
    }

    public static List<SentianceEvent> getMatchingEventsAsList(File file, String userID, String stringToFind) throws IOException {
        int count = 0;
        List<SentianceEvent> sentianceEventList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                if (line.toLowerCase().contains(stringToFind.toLowerCase())) {
                    Date date = convertStringToDate(line.substring(0, 23));
                    SentianceEvent sentianceEvent = new SentianceEvent(userID, date, stringToFind);
                    sentianceEventList.add(sentianceEvent);
                }
                count++;
                line = br.readLine();

            }
        }
        return sentianceEventList;
    }

    public static Date convertStringToDate(String date) {
        String inputString = "2017-06-23T22:57:47.846";
        Date inputDate = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            inputDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inputDate;
    }
}
