package com.company.Utilities;

import com.company.models.SentianceEvent;
import com.company.models.StatusEnum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gershonlehrer on 19/09/2017.
 */
public class FileUtilities {
    private static String mCurrentUser;

    public static Map<Calendar, List<SentianceEvent>> showFiles(File[] files, String stringToFind) throws IOException {
        return showFiles(files, "", stringToFind);
    }

    public static Map<Calendar, List<SentianceEvent>> showFiles(File[] files, String user, String stringToFind) throws IOException {
        List<SentianceEvent> sentianceEventList = new ArrayList<>();
        Map<Calendar, List<SentianceEvent>> listMap = new HashMap<>();
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                if (file.getName().startsWith("user"))
                    mCurrentUser = file.getName();
                for (Map.Entry<Calendar, List<SentianceEvent>> e : showFiles(file.listFiles(), mCurrentUser, stringToFind).entrySet()) {
                    if (!listMap.containsKey(e.getKey()))
                        listMap.put(e.getKey(), e.getValue());
                }
            } else {
                System.out.println("File: " + file.getName());
//                sentianceEventList.addAll(getMatchingEventsAsList(file, mCurrentUser, stringToFind));
                for (Map.Entry<Calendar, List<SentianceEvent>> e : getMatchingEventsAsList(file, mCurrentUser, stringToFind).entrySet()) {
                    if (!listMap.containsKey(e.getKey()))
                        listMap.put(e.getKey(), e.getValue());
                }
            }
        }
        return listMap;
    }


    public static Map<Calendar, List<SentianceEvent>> getMatchingEventsAsList(File file, String userID, String stringToFind) throws IOException {
        int count = 0;
        List<SentianceEvent> sentianceEventList = new ArrayList<>();
        Map<Calendar, List<SentianceEvent>> listMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                if (line.toLowerCase().contains(stringToFind.toLowerCase())) {
                    Calendar date = convertStringToDate(line.substring(0, 23));
                    SentianceEvent sentianceEvent = new SentianceEvent(userID, date, StatusEnum.of(stringToFind));
                    sentianceEventList = listMap.getOrDefault(sentianceEvent.getDateOfEvent(), new ArrayList<SentianceEvent>());
                    sentianceEventList.add(sentianceEvent);
                    listMap.put(sentianceEvent.getDateOfEvent(), sentianceEventList);
                }
                count++;
                line = br.readLine();

            }
        }
        return listMap;
    }

    public static Calendar convertStringToDate(String date) {
//        String inputString = "2017-06-23T22:57:47.846";
        Calendar inputDate = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            inputDate = DateUtilities.convertDateToCalendar(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inputDate;
    }


    public static Map<Calendar, Double> getAvgSdkRestartsPerDay(File[] files) throws IOException {
        Map<Calendar, List<SentianceEvent>> sdkRestartedList = FileUtilities.showFiles(files, StatusEnum.SDK_START_EVENT.toString());//"SdkStatusManager: SdkStatus startStatus changed to STARTED");
        Map<Calendar, Integer> totalRestartsPerDayMap = new HashMap<>();
        Map<Calendar, Double> avgRestartsPerDay = new HashMap<>();
        int currentValue = 0;
        for (Map.Entry<Calendar, List<SentianceEvent>> entry : sdkRestartedList.entrySet()) {
            int size = entry.getValue().size();
            for (SentianceEvent sentianceEvent : entry.getValue()) {
                //System.out.println(entry.getKey() + "/" + sentianceEvent.getEvent());
                Calendar cal = sentianceEvent.getDateOfEvent();
                currentValue = totalRestartsPerDayMap.getOrDefault(cal, 0);
                totalRestartsPerDayMap.put(cal, currentValue + 1);
            }

        }

        for (Map.Entry<Calendar, List<SentianceEvent>> entry : sdkRestartedList.entrySet()) {
            int size = entry.getValue().size();
            Calendar cal = entry.getKey();
            int count = totalRestartsPerDayMap.get(cal);
            double avg = size / count;
            avgRestartsPerDay.put(DateUtilities.createCalendarObject(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)), avg);
        }

        return avgRestartsPerDay;
    }
}

