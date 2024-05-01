package com.mrboomdev.awery.sdk.data;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CatalogEpisode implements Comparable<CatalogEpisode> {
    private final String title, banner, description, url;
    private final float number;
    private final long releaseDate;
    private List<CatalogVideo> videos;
    private long id;

    public CatalogEpisode(String title, String url, String banner, String description, long releaseDate, float number) {
        this.title = title;
        this.url = url;
        this.number = number;
        this.banner = banner;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public void setVideos(List<CatalogVideo> videos) {
        this.videos = videos;
    }

    public List<CatalogVideo> getVideos() {
        return videos;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public String getBanner() {
        return banner;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public float getNumber() {
        return number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int compareTo(@NotNull CatalogEpisode o) {
        if(o.getNumber() != getNumber()) {
            return Float.compare(o.getNumber(), getNumber());
        }

        return o.getTitle().compareToIgnoreCase(getTitle());
    }
}