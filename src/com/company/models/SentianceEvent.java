package com.company.models;

import java.util.Calendar;

/**
 * Created by gershonlehrer on 19/09/2017.
 */
public class SentianceEvent {
    private String mUserId;
    private Calendar mDateOfEvent;
    private StatusEnum mEvent;

    public SentianceEvent(String userId, Calendar dateOfEvent, StatusEnum event) {
        mUserId = userId;
        mDateOfEvent = dateOfEvent;
        mEvent = event;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public Calendar getDateOfEvent() {
        return mDateOfEvent;
    }

    public void setDateOfEvent(Calendar dateOfEvent) {
        mDateOfEvent = dateOfEvent;
    }

    public StatusEnum getEvent() {
        return mEvent;
    }

    public void setEvent(StatusEnum event) {
        mEvent = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SentianceEvent that = (SentianceEvent) o;

        if (mUserId != null ? !mUserId.equals(that.mUserId) : that.mUserId != null) return false;
        if (mDateOfEvent != null ? !mDateOfEvent.equals(that.mDateOfEvent) : that.mDateOfEvent != null) return false;
        return mEvent == that.mEvent;
    }

    @Override
    public int hashCode() {
        int result = mUserId != null ? mUserId.hashCode() : 0;
        result = 31 * result + (mDateOfEvent != null ? mDateOfEvent.hashCode() : 0);
        result = 31 * result + (mEvent != null ? mEvent.hashCode() : 0);
        return result;
    }
}
