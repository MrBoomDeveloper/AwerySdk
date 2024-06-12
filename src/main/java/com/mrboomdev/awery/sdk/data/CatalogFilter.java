package com.mrboomdev.awery.sdk.data;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class CatalogFilter implements Serializable {
	@Serial
	private static final long serialVersionUID = 1;
	public static final String FILTER_PAGE = "__AWERY_FILTER_PAGE__";
	public static final String FILTER_QUERY = "__AWERY_FILTER_QUERY__";
	public static final String FILTER_FEED = "__AWERY_FILTER_FEED__";
	private final Type type;
	private final String id;
	private String title;
	private Object value;

	public CatalogFilter(Type type, String id, String title, Object value) {
		this.type = type;
		this.id = id;
		this.title = title;
		this.value = value;
	}

	public CatalogFilter(Type type, String id, Object value) {
		this.type = type;
		this.id = id;
		this.value = value;
	}

	public CatalogFilter(Type type, String id) {
		this.type = type;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object getValue() {
		return value;
	}

	public String getId() {
		return id;
	}

	public Type getType() {
		return type;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isEmpty() {
		return value == null;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public void setValue(DisableableMode value) {
		this.value = value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public void setValue(@NotNull Calendar value) {
		this.value = value.getTimeInMillis();
	}

	public void setValue(float value) {
		this.value = value;
	}

	public void clearValue() {
		this.value = null;
	}

	public String getStringValue() {
		return (String) getValue();
	}

	public float getFloatValue() {
		if(getValue() == null) return 0f;
		return (Float) getValue();
	}

	public int getIntegerValue() {
		if(getValue() == null) return 0;
		return (Integer) getValue();
	}

	public boolean getBooleanValue() {
		if(getValue() == null) return false;
		return (Boolean) getValue();
	}

	public DisableableMode getDisablableMode() {
		if(getValue() == null) return DisableableMode.UNCHECKED;
		return (DisableableMode) getValue();
	}

	public Calendar getDateValue() {
		var cal = Calendar.getInstance();
		cal.setTimeInMillis((long) getValue());
		return cal;
	}

	@SuppressWarnings("unchecked")
	public List<CatalogFilter> getItems() {
		if(getValue() == null) return null;
		return (List<CatalogFilter>) getValue();
	}

	public void setValue(List<CatalogFilter> filters) {
		this.value = filters;
	}

	public enum DisableableMode {
		UNCHECKED, CHECKED, DISABLED
	}

	public enum Type {
		STRING, FLOAT, INTEGER, BOOLEAN, DISABLEABLE, DATE, NESTED_FILTERS
	}
}