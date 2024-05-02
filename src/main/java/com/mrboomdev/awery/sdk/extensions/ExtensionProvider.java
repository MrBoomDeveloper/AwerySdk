package com.mrboomdev.awery.sdk.extensions;

import com.mrboomdev.awery.sdk.data.*;
import com.mrboomdev.awery.sdk.data.settings.SettingsItem;
import com.mrboomdev.awery.sdk.requests.CatalogTrackingRequest;
import com.mrboomdev.awery.sdk.requests.ReadMediaCommentsRequest;
import com.mrboomdev.awery.sdk.util.Context;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Base class for all extension providers.
 * @author MrBoomDev
 */
@SuppressWarnings("unused")
public abstract class ExtensionProvider implements Comparable<ExtensionProvider> {
    public static final int FEATURE_TAGS_SEARCH = 1;
    public static final int FEATURE_LOGIN = 2;
    public static final int FEATURE_MEDIA_WATCH = 3;
    public static final int FEATURE_MEDIA_READ = 4;
    public static final int FEATURE_MEDIA_COMMENTS = 5;
    public static final int FEATURE_TRACK = 6;
    public static final int FEATURE_COMMENTS_SORT = 7;
    public static final int FEATURE_COMMENTS_REPORT = 8;
    public static final int FEATURE_COMMENTS_VOTE = 9;
    public static final int FEATURE_MEDIA_REPORT = 10;
    public static final int FEATURE_MEDIA_SEARCH = 11;
    private final ExtensionsManager manager;
    private final Extension extension;

    public ExtensionProvider(ExtensionsManager manager, Extension extension) {
        this.manager = manager;
        this.extension = extension;
    }

    public Extension getExtension() {
        return extension;
    }

    public ExtensionsManager getManager() {
        return manager;
    }

    @Override
    public int compareTo(@NotNull ExtensionProvider o) {
        if(getName().equals(o.getName())) {
            return getLang().compareToIgnoreCase(o.getLang());
        }

        return getName().compareToIgnoreCase(o.getName());
    }

    public void searchMedia(
            Context context,
            List<CatalogFilter> filters,
            @NotNull ResponseCallback<CatalogSearchResults<? extends CatalogMedia>> callback
    ) {
        callback.onFailure(new UnsupportedOperationException("Search not implemented!"));
    }

    /**
     * @param context The Android context
     * @param callback Will be run on success or failure
     * @author MrBoomDev
     */
    public void getSettings(Context context, @NotNull ResponseCallback<SettingsItem> callback) {
        callback.onFailure(new UnsupportedOperationException("Settings not implemented!"));
    }

    public void readMediaComments(ReadMediaCommentsRequest request, @NotNull ResponseCallback<CatalogComment> callback) {
        callback.onFailure(new UnsupportedOperationException("Comments reading aren't implemented!"));
    }

    public void postMediaComment(CatalogComment parent, CatalogComment comment, @NotNull ResponseCallback<CatalogComment> callback) {
        callback.onFailure(new UnsupportedOperationException("Comments posting aren't implemented!"));
    }

    public void voteComment(CatalogComment comment, @NotNull ResponseCallback<Boolean> callback) {
        callback.onFailure(new UnsupportedOperationException("Comments voting aren't implemented!"));
    }

    public void trackMedia(
            CatalogMedia media,
            @Nullable CatalogTrackingRequest options,
            @NotNull ResponseCallback<CatalogTrackingRequest> callback
    ) {
        callback.onFailure(new UnsupportedOperationException("Comments voting aren't implemented!"));
    }

    public void searchTags(@NotNull ResponseCallback<List<CatalogTag>> callback) {
        callback.onFailure(new UnsupportedOperationException("Comments voting aren't implemented!"));
    }

    public void getEpisodes(int page, CatalogMedia media, @NotNull ResponseCallback<List<? extends CatalogEpisode>> callback) {
        callback.onFailure(new UnsupportedOperationException("Episodes not implemented!"));
    }

    public void getVideos(CatalogEpisode episode, @NotNull ResponseCallback<List<CatalogVideo>> callback) {
        callback.onFailure(new UnsupportedOperationException("Videos not implemented!"));
    }

    /**
     * @return A collection of constants representing features supported by the extension
     * @author MrBoomDev
     */
    public abstract Collection<Integer> getFeatures();

    /**
     * @param feature A constant from {@link ExtensionProvider} representing the feature.
     * @return Whether the extension supports the feature
     * @author MrBoomDev
     */
    public boolean hasFeature(int feature) {
        return getFeatures().contains(feature);
    }

    /**
     * @return A human-readable name of the extension
     * @author MrBoomDev
     */
    public String getName() {
        return getId();
    }

    public abstract String getId();

    /**
     * Note: The returned value can be an array of format: "en;ru;jp"
     * @return The language of the extension
     * @author MrBoomDev
     */
    @Nullable
    public String getLang() {
        return null;
    }

    public void getCatalogCategories(@NotNull ResponseCallback<Map<String, CatalogList>> callback) {
        callback.onFailure(new UnsupportedOperationException("Categories not implemented!"));
    }

    @NotNull
    @Override
    public String toString() {
        return getName();
    }

    public interface ResponseCallback<T> {
        void onSuccess(T t);
        void onFailure(Throwable e);
    }
}