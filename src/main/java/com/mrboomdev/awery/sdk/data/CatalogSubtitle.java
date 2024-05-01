package com.mrboomdev.awery.sdk.data;

import org.jetbrains.annotations.Contract;

public class CatalogSubtitle {
    private final String title, url;

    public CatalogSubtitle(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}