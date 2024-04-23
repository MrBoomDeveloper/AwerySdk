package com.mrboomdev.awery.sdk.data;

import java.util.Calendar;
import java.util.List;

public class CatalogFilter {
	private final Type type;
	private final String name;
	private Object value;

	public CatalogFilter(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getName() {
		return name;
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

	public Integer getNumberValue() {
		return (Integer) value;
	}

	public Boolean getToggleValue() {
		return (Boolean) value;
	}

	public DisableableMode getDisablableValue() {
		return (DisableableMode) value;
	}

	public Calendar getDateValue() {
		return (Calendar) value;
	}

	public enum DisableableMode {
		UNCHECKED, CHECKED, DISABLED
	}

	public enum Type {
		STRING, NUMBER, TOGGLE, DISABLEABLE, DATE
	}
}