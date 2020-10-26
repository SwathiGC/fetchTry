package com.forms.learn.core.services;

public interface PageJSONServletService {
	public String[] getExcludedProperties();
	public String[] getReferenceProperties();
	public String[] getRenameProperties();
	public long getLimit();
}
