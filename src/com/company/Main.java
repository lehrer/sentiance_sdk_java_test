package com.company;

import com.company.Utilities.FileUtilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        File[] files = new File(args[0]).listFiles();
        int countSdkRestarted = 0;
        int countConfirmedStationeryStartEvent = 0;
        int countUnconfirmedStationeryStartEvent = 0;
        int countSdkToPending = 0;
        List<Integer> countPerDir = new ArrayList<>();
        try {
            countSdkRestarted = FileUtilities.showFiles(files, "SdkStatusManager: SdkStatus startStatus changed to STARTED");
            countConfirmedStationeryStartEvent = FileUtilities.showFiles(files, "Payload submission success: stationaryEvent|stationary_start");
            countUnconfirmedStationeryStartEvent = FileUtilities.showFiles(files, "Payload evaluation unsuccessful: stationaryEvent|stationary_start");
            countPerDir = FileUtilities.countFilesDirectory(files, "Sdk");
            countSdkToPending = FileUtilities.showFiles(files, "SdkStatusManager: SdkStatus startStatus changed to PENDING");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("countSdkRestarted: " + countSdkRestarted);
        System.out.println("countConfirmedStationeryStartEvent: " + countConfirmedStationeryStartEvent);
        System.out.println("countUnconfirmedStationeryStartEvent: " + countUnconfirmedStationeryStartEvent);
        for (Integer i : countPerDir) {
            System.out.println("countPerDir: " + i);
        }
        System.out.println("countSdkToPending: " + countSdkToPending);


        String inputString = "2017-06-23T22:57:47.846";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            Date inputDate = dateFormat.parse(inputString);
            System.out.println(inputDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
