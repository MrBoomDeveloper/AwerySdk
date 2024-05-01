package com.mrboomdev.awery.sdk.requests;

import com.mrboomdev.awery.sdk.data.CatalogList;

import java.util.Calendar;
import java.util.List;

public class CatalogTrackingRequest {
    public static final int FEATURE_PRIVATE = 1;
    public static final int FEATURE_LISTS = 2;
    public static final int FEATURE_LIST_CREATE = 4;
    public static final int FEATURE_DATE_START = 16;
    public static final int FEATURE_DATE_END = 32;
    public static final int FEATURE_PROGRESS = 64;
    public static final int FEATURE_SCORE = 128;
    private final int flags;
    public List<CatalogList> lists;
    public List<String> currentLists;
    public Calendar startDate, endDate;
    public boolean isPrivate;
    public float progress, score;
    public String id;

    public CatalogTrackingRequest(int flags) {
        this.flags = flags;
    }

    public boolean hasFeatures(int flags) {
        return (this.flags & flags) == flags;
    }
}