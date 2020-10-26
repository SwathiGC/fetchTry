package com.forms.learn.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import com.forms.learn.core.config.PageJSONServletConfig;
import com.forms.learn.core.services.PageJSONServletService;

@Component
@Designate(ocd = PageJSONServletConfig.class)
public class PageJSONServletServiceImpl implements PageJSONServletService {

	
	String[] excludeProperties=null;
	String[] refrenceProperties=null;
	String[] renameProperties=null;
	long limit = 0;
	
	@Activate
	@Modified
	public void activate(PageJSONServletConfig config) {
		excludeProperties=config.exclude_properties();
		refrenceProperties = config.refrence_properties();
		renameProperties = config.rename_properties();
		limit= config.limit();
	}

	@Override
	public String[] getExcludedProperties() {
		return excludeProperties;
	}
	
	@Override
	public String[] getReferenceProperties() {
		return refrenceProperties;
	}

	@Override
	public String[] getRenameProperties() {
		return renameProperties;
	}

	@Override
	public long getLimit() {
		return limit;
	}	

}
