package com.company;

import com.company.Utilities.DateUtilities;
import com.company.Utilities.FileUtilities;
import com.company.models.SentianceEvent;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        File[] files = new File(args[0]).listFiles();
        int countSdkRestarted = 0;
        int countConfirmedStationeryStartEvent = 0;
        int countUnconfirmedStationeryStartEvent = 0;
        int countSdkToPending = 0;

//        List<SentianceEvent> sdkRestartedList = new ArrayList<>();
//        List<SentianceEvent> sdkConfirmedStationeryStartEvent = new ArrayList<>();
//        List<SentianceEvent> sdkUnconfirmedStationeryStartEvent = new ArrayList<>();
//        List<SentianceEvent> sdkSdkToPending = new ArrayList<>();
        Map<Date, List<SentianceEvent>> sdkRestartedList = new HashMap<>();
        Map<Date, List<SentianceEvent>> sdkConfirmedStationeryStartEvent = new HashMap<>();
        Map<Date, List<SentianceEvent>> sdkUnconfirmedStationeryStartEvent = new HashMap<>();
        Map<Calendar, Double> avgRestartsPerDayMap = new HashMap<>();

        List<Integer> countPerDir = new ArrayList<>();
        List<SentianceEvent> sentianceEventList = new ArrayList<>();
        try {
//            sdkRestartedList = FileUtilities.showFiles(files, StatusEnum.SDK_START_EVENT.toString());//"SdkStatusManager: SdkStatus startStatus changed to STARTED");
//            sdkConfirmedStationeryStartEvent = FileUtilities.showFiles(files, StatusEnum.STATIONARY_START_EVENT.toString());//"Payload submission success: stationaryEvent|stationary_start");
//            sdkUnconfirmedStationeryStartEvent = FileUtilities.showFiles(files, StatusEnum.UNCONFIRMED_STATIONARY_STATE.toString());//"Payload evaluation unsuccessful: stationaryEvent|stationary_start");
            avgRestartsPerDayMap = FileUtilities.getAvgSdkRestartsPerDay(files);

            for (Map.Entry<Calendar, Double> entry : avgRestartsPerDayMap.entrySet()) {
                System.out.println(DateUtilities.convertCalendarToString(entry.getKey()) + " ; " + entry.getValue());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

//        printListContent(sdkRestartedList);
//        printListContent(sdkConfirmedStationeryStartEvent);
//        printListContent(sdkUnconfirmedStationeryStartEvent);



    }

    private static void printListContent(Map<Date, List<SentianceEvent>> map) {
        for (Map.Entry<Date, List<SentianceEvent>> entry : map.entrySet()) {
            for (SentianceEvent sentianceEvent : entry.getValue()) {
                System.out.println(entry.getKey() + "/" + sentianceEvent.getEvent());
            }
        }

        System.out.println(map.size());
    }


}
