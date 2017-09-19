package com.company;

import com.company.Utilities.FileUtilities;
import com.company.models.SentianceEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        File[] files = new File(args[0]).listFiles();
        //use as  HashMap<useridString, HashMap<eventDate, eventString>>
//        HashMap<String, HashMap<Date, String>> useridStringEventDateEventStringHashMap=new HashMap<>();
        int countSdkRestarted = 0;
        int countConfirmedStationeryStartEvent = 0;
        int countUnconfirmedStationeryStartEvent = 0;
        int countSdkToPending = 0;

        List<SentianceEvent> sdkRestartedList = new ArrayList<>();
        List<SentianceEvent> sdkConfirmedStationeryStartEvent = new ArrayList<>();
        List<SentianceEvent> sdkUnconfirmedStationeryStartEvent = new ArrayList<>();
        List<SentianceEvent> sdkSdkToPending = new ArrayList<>();

        List<Integer> countPerDir = new ArrayList<>();
        List<SentianceEvent> sentianceEventList = new ArrayList<>();
        try {
//            countSdkRestarted = FileUtilities.showFiles(files, "SdkStatusManager: SdkStatus startStatus changed to STARTED");
//            countConfirmedStationeryStartEvent = FileUtilities.showFiles(files, "Payload submission success: stationaryEvent|stationary_start");
//            countUnconfirmedStationeryStartEvent = FileUtilities.showFiles(files, "Payload evaluation unsuccessful: stationaryEvent|stationary_start");
//            countPerDir = FileUtilities.countFilesDirectory(files, "Sdk");
//            countSdkToPending = FileUtilities.showFiles(files, "SdkStatusManager: SdkStatus startStatus changed to PENDING");
            sdkRestartedList = FileUtilities.showFiles(files, "SdkStatusManager: SdkStatus startStatus changed to STARTED");
            sdkConfirmedStationeryStartEvent = FileUtilities.showFiles(files, "Payload submission success: stationaryEvent|stationary_start");
            sdkUnconfirmedStationeryStartEvent = FileUtilities.showFiles(files, "Payload evaluation unsuccessful: stationaryEvent|stationary_start");


        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("countSdkRestarted: " + countSdkRestarted);
//        System.out.println("countConfirmedStationeryStartEvent: " + countConfirmedStationeryStartEvent);
//        System.out.println("countUnconfirmedStationeryStartEvent: " + countUnconfirmedStationeryStartEvent);
//        for (Integer i : countPerDir) {
//            System.out.println("countPerDir: " + i);
//        }
//        System.out.println("countSdkToPending: " + countSdkToPending);

        printListContent(sdkRestartedList);
        printListContent(sdkConfirmedStationeryStartEvent);
        printListContent(sdkUnconfirmedStationeryStartEvent);

    }

    private static void printListContent(List<SentianceEvent> list) {
        for (SentianceEvent s : list) {
            System.out.println(s.getUserId() + ": " + s.getEvent());
        }
        System.out.println(list.size());
    }
}
