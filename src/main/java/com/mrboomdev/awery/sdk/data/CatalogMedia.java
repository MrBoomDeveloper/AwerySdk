package com.mrboomdev.awery.sdk.data;

import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogMedia {
    @NotNull
    public String globalId;
    public List<String> titles = new ArrayList<>();
    public Map<String, String> ids = new HashMap<>();
    public Map<String, String> authors = new HashMap<>();
    public String banner, description, country, ageRating, extra;
    public MediaType type;
    public ImageVersions poster = new ImageVersions();
    public Calendar releaseDate;
    public Integer duration, episodesCount, latestEpisode;
    public Float averageScore;
    public List<CatalogTag> tags;
    public List<String> genres;
    public MediaStatus status;
    @Json(ignore = true)
    public long visualId;

    /**
     * @param globalId The unique id of the media in the following format:
     * <p>{@code MANAGER_ID;;;EXTENSION_ID;;;ITEM_ID}</p>
     */
    public CatalogMedia(@NotNull String globalId) {
        this.globalId = globalId;
    }

    public CatalogMedia(String managerId, String extensionId, String mediaId) {
        this(managerId + ";;;" + extensionId + ";;;" + mediaId);
    }

    public String getManagerId() {
        return globalId.split(";;;")[0];
    }

    public String getExtensionId() {
        return globalId.split(";;;")[1];
    }

    public String getMediaId() {
        return globalId.split(";;;")[2];
    }

    @NotNull
    @Override
    public String toString() {
        var builder = new StringBuilder("{ ");

        builder.append("\"globalId\": \"")
                .append(globalId)
                .append("\"");

        if(!titles.isEmpty()) {
            builder.append(", \"titles\": [ ");

            var iterator = titles.iterator();

            while(iterator.hasNext()) {
                builder.append("\"")
                        .append(iterator.next())
                        .append("\"");

                if(iterator.hasNext()) {
                    builder.append(", ");
                }
            }

            builder.append(" ]");
        }

        if(!ids.isEmpty()) {
            builder.append(", \"ids\": { ");

            var iterator = ids.entrySet().iterator();

            while(iterator.hasNext()) {
                var entry = iterator.next();
                builder.append("\"")
                        .append(entry.getKey())
                        .append("\": \"")
                        .append(entry.getValue())
                        .append("\"");

                if(iterator.hasNext()) {
                    builder.append(", ");
                }
            }

            builder.append(" }");
        }

        if(!authors.isEmpty()) {
            builder.append(", \"authors\": { ");

            var iterator = authors.entrySet().iterator();

            while(iterator.hasNext()) {
                var entry = iterator.next();
                builder.append("\"")
                        .append(entry.getKey())
                        .append("\": \"")
                        .append(entry.getValue())
                        .append("\"");

                if(iterator.hasNext()) {
                    builder.append(", ");
                }
            }

            builder.append(" }");
        }

        if(banner != null) {
            builder.append(", \"banner\": \"")
                    .append(banner)
                    .append("\"");
        }

        builder.append(" }");
        return builder.toString();
    }

    public void setTitle(@NotNull String... titles) {
        this.titles = List.of(titles);
    }

    public String getTitle() {
        if(titles.isEmpty()) return null;
        return titles.get(0);
    }

    public void setTitles(Collection<String> titles) {
        if(titles == null) {
            this.titles = new ArrayList<>();
            return;
        }

        this.titles = List.copyOf(titles);
    }

    public String getBestBanner() {
        if(banner != null) return banner;
        return getBestPoster();
    }

    public void setId(String type, String id) {
        ids.put(type, id);
    }

    public String getId(String type) {
        return ids.get(type);
    }

    public String getBestPoster() {
        if(poster.extraLarge != null) return poster.extraLarge;
        if(poster.large != null) return poster.large;
        if(poster.medium != null) return poster.medium;
        return banner;
    }

    public void setPoster(String poster) {
        this.poster.extraLarge = poster;
        this.poster.large = poster;
        this.poster.medium = poster;
    }

    public enum MediaStatus {
        ONGOING, COMPLETED, COMING_SOON, PAUSED, CANCELLED, UNKNOWN
    }

    public enum MediaType {
        MOVIE, BOOK, TV, POST
    }

    public static class ImageVersions {
        public String extraLarge, large, medium;
    }
}