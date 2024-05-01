package com.mrboomdev.awery.sdk.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CatalogVideo {
    private final Map<String, String> headers;
    private final String url, title;
    private List<CatalogSubtitle> subtitles;

    public CatalogVideo(String title, String url, Map<String, String> headers, List<CatalogSubtitle> subtitles) {
        this.title = title;
        this.url = url;
        this.headers = headers;
        this.subtitles = subtitles;
    }

    public CatalogVideo(String title, String url, Map<String, String> headers) {
        this.title = title;
        this.url = url;
        this.headers = headers;
    }

    public void setSubtitles(List<CatalogSubtitle> subtitles) {
        this.subtitles = subtitles;
    }

    public List<CatalogSubtitle> getSubtitles() {
        return subtitles;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}