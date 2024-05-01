package com.mrboomdev.awery.sdk.requests;

import com.mrboomdev.awery.sdk.data.CatalogComment;
import com.mrboomdev.awery.sdk.data.CatalogEpisode;
import com.mrboomdev.awery.sdk.data.CatalogMedia;

public class ReadMediaCommentsRequest {
    private CatalogMedia media;
    private CatalogEpisode episode;
    private CatalogComment parentComment;
    private String sortMode;
    private int page;

    public ReadMediaCommentsRequest setMedia(CatalogMedia media) {
        this.media = media;
        return this;
    }

    public ReadMediaCommentsRequest setEpisode(CatalogEpisode episode) {
        this.episode = episode;
        return this;
    }

    public ReadMediaCommentsRequest setParentComment(CatalogComment parentComment) {
        this.parentComment = parentComment;
        return this;
    }

    public ReadMediaCommentsRequest setSortMode(String sortMode) {
        this.sortMode = sortMode;
        return this;
    }

    public ReadMediaCommentsRequest setPage(int page) {
        this.page = page;
        return this;
    }

    public CatalogMedia getMedia() {
        return media;
    }

    public CatalogEpisode getEpisode() {
        return episode;
    }

    public CatalogComment getParentComment() {
        return parentComment;
    }

    public String getSortMode() {
        return sortMode;
    }

    public int getPage() {
        return page;
    }
}