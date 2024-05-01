package com.mrboomdev.awery.sdk.data;

import java.util.Calendar;
import java.util.List;

public class CatalogFilter {
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

	public void setValue(Object value) {
		this.value = value;
	}

	public String getStringValue() {
		return (String) value;
	}

	public Number getNumberValue() {
		if(value == null) return 0;
		return (Number) value;
	}

	public Integer getIntegerValue() {
		if(value == null) return 0;
		return (Integer) value;
	}

	public Boolean getToggleValue() {
		if(value == null) return false;
		return (Boolean) value;
	}

	public DisableableMode getDisablableValue() {
		if(value == null) return DisableableMode.UNCHECKED;
		return (DisableableMode) value;
	}

	public Calendar getDateValue() {
		return (Calendar) value;
	}

	public enum DisableableMode {
		UNCHECKED, CHECKED, DISABLED
	}

	public enum Type {
		STRING, NUMBER, INTEGER, TOGGLE, DISABLEABLE, DATE
	}
}