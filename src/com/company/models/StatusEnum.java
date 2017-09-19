package com.company.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gershonlehrer on 19/09/2017.
 */
public enum StatusEnum {
    SDK_START_EVENT("SdkStatusManager: SdkStatus startStatus changed to STARTED"),
    STATIONARY_START_EVENT("Payload submission success: stationaryEvent|stationary_start"),
    UNCONFIRMED_STATIONARY_STATE("Payload evaluation unsuccessful: stationaryEvent|stationary_start");

    private final static Map<String, StatusEnum> mMap = new HashMap<>();

    static {
        for (StatusEnum statusEnum : values())
            mMap.put(statusEnum.name, statusEnum);
    }

    private final String name;
//    //if set to true, it will apear in the list from which it can be navigated by clicking on it in the list with forms
//    //false if it can only be hown navigated to another form
//    private final boolean isDirectlyNavigatble;

    StatusEnum(String name) {
        this.name = name;
    }

    public static StatusEnum of(String name) {
        StatusEnum result = mMap.get(name);
        if (result == null) {
            throw new IllegalArgumentException("Invalid StatusEnum name: " + name);
        }
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
