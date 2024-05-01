package com.mrboomdev.awery.sdk.data.settings;

import com.mrboomdev.awery.sdk.PlatformApi;
import com.mrboomdev.awery.sdk.util.Context;
import com.squareup.moshi.Json;
import com.squareup.moshi.ToJson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SettingsItem {
    public static final SettingsItem INVALID_SETTING = new Builder(SettingsItemType.BOOLEAN)
            .setTitle("Invalid!")
            .setBooleanValue(false)
            .build();

    public static final String SEPARATOR = "_";
    private String key, title, description, icon, behaviour;
    protected SettingsItemType type;
    private String parentKey;
    @Json(name = "tint_icon")
    private Boolean tintIcon;
    @Json(name = "show_if")
    private String showIf;
    private boolean restart;
    private List<SettingsItem> items;
    @Json(name = "header_items")
    private List<SettingsItem> headerItems;
    @Json(ignore = true)
    private SettingsItem parent;
    @Json(name = "icon_size")
    private Float iconSize;
    @Json(name = "boolean_value")
    private Boolean booleanValue;
    @Json(name = "int_value")
    private Integer intValue;
    @Json(name = "string_set_value")
    private Set<String> stringSetValue;
    private Float from, to;
    @Json(name = "string_value")
    private String stringValue;

    public SettingsItem(@NotNull SettingsItem item) {
        copyFrom(item);
    }

    protected SettingsItem() {}

    protected void copyFrom(@NotNull SettingsItem item) {
        this.key = item.key;
        this.type = item.type;
        this.items = item.items;

        this.booleanValue = item.booleanValue;
        this.stringValue = item.stringValue;

        this.intValue = item.intValue;
        this.from = item.from;
        this.to = item.to;

        this.behaviour = item.behaviour;
        this.restart = item.restart;
        this.showIf = item.showIf;

        this.icon = item.icon;
        this.tintIcon = item.tintIcon;
        this.iconSize = item.iconSize;

        this.title = item.title;
        this.description = item.description;

        this.parentKey = item.parentKey;
        this.parent = item.parent;
    }

    public void setAsParentForChildren() {
        if(items == null) return;

        for(var item : items) {
            item.setParent(this);
            item.setAsParentForChildren();
        }
    }

    public boolean isFromToAvailable() {
        return from != null && to != null;
    }

    public Float getFrom() {
        return from;
    }

    public Float getTo() {
        return to;
    }

    public float getIconSize() {
        return iconSize == null ? 1 : iconSize;
    }

    public boolean tintIcon() {
        return tintIcon == null || tintIcon;
    }

    public boolean isVisible() {
        if(showIf != null) {
            var platformApi = PlatformApi.getInstance();
            var requirements = showIf.split(",");

            for(var requirement : requirements) {
                if(!platformApi.isRequirementMet(requirement)) return false;
            }
        }

        return true;
    }

    public void setItems(Collection<SettingsItem> items) {
        this.items = List.copyOf(items);
    }

    public String getBehaviour() {
        return behaviour;
    }

    public boolean isRestartRequired() {
        return restart;
    }

    public String getTitle(@NotNull Context context) {
        var got = context.resolveString(title);
        if(got != null) return got;
        if(title != null) return title;

        return getKey();
    }

    public String getDescription(Context context) {
        if(description == null) return null;

        var got = context.resolveString(description);
        if(got != null) return got;

        return description;
    }

    public Boolean getBooleanValue() {
        return booleanValue != null && booleanValue;
    }

    public List<SettingsItem> getHeaderItems() {
        return headerItems;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(int value) {
        intValue = value;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Set<String> getStringSetValue() {
        return stringSetValue;
    }

    public void onClick(Context context) {}

    public void setStringValue(String value) {
        stringValue = value;
    }

    public void setBooleanValue(boolean value) {
        booleanValue = value;
    }

    public void setParent(SettingsItem parent) {
        this.parent = parent;
    }

    public SettingsItem getParent() {
        return parent;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public String getFullKey() {
        if(!hasParent()) {
            if(parentKey != null) {
                return parentKey + "_" + key;
            }

            return key;
        }

        return parent.getFullKey() + SEPARATOR + key;
    }

    public String getKey() {
        return key;
    }

    public SettingsItemType getType() {
        return type;
    }

    public List<SettingsItem> getItems() {
        return items;
    }

    private @Nullable SettingsItem findDirect(String key) {
        for(var item : items) {
            if(item.getKey().equals(key)) {
                return item;
            }
        }

        return null;
    }

    public SettingsItem find(@NotNull String query) {
        return switch(type) {
            case BOOLEAN, INT, STRING, COLOR, SELECT, SELECT_INT, MULTISELECT -> query.equals(getFullKey()) ? this : null;

            case SCREEN, SCREEN_BOOLEAN -> {
                if(query.equals(getFullKey())) {
                    yield this;
                }

                if(items == null) {
                    yield null;
                }

                for(var item : items) {
                    var found = item.find(query);

                    if(found != null) {
                        yield found;
                    }
                }

                yield null;
            }

            case ACTION -> null;
        };
    }

    public static class Builder {
        private final SettingsItem item = new SettingsItem();

        public Builder(SettingsItemType type) {
            item.type = type;
        }

        public Builder setKey(String key) {
            item.key = key;
            return this;
        }

        public Builder setBooleanValue(boolean value) {
            item.booleanValue = value;
            return this;
        }

        public Builder setTitle(String title) {
            item.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            item.description = description;
            return this;
        }

        public Builder setIcon(String icon) {
            item.icon = icon;
            return this;
        }

        public Builder setStringSetValue(Set<String> values) {
            item.stringSetValue = values;
            return this;
        }

        public Builder setRestartRequired(boolean restart) {
            item.restart = restart;
            return this;
        }

        public Builder setBehaviour(String behaviour) {
            item.behaviour = behaviour;
            return this;
        }

        public Builder setIconSize(float size) {
            item.iconSize = size;
            return this;
        }

        public Builder setTintIcon(boolean tint) {
            item.tintIcon = tint;
            return this;
        }

        public Builder setItems(Collection<? extends SettingsItem> items) {
            item.items = new ArrayList<>(items);
            return this;
        }

        public Builder setParent(SettingsItem parent) {
            item.parent = parent;
            return this;
        }

        public SettingsItem build() {
            return item;
        }
    }

    @SuppressWarnings("unused")
    public static class Adapter {

        @ToJson
        public SettingsItem toJson(SettingsItem item) {
            var newItem = new SettingsItem(item);

            if(item.getParent() != null) {
                newItem.parentKey = item.getParent().getFullKey();
            }

            return newItem;
        }
    }
}