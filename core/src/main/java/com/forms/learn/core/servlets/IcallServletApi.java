package com.forms.learn.core.servlets;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.Servlet;
import org.osgi.service.component.annotations.Reference;

import com.forms.learn.core.services.IcallServiceApi;

@Component(
	immediate = true,
	service  = Servlet.class,
	property = {
		"sling.servlet.paths="+ "/bin/getJson"
	}
	)
public class IcallServletApi extends SlingSafeMethodsServlet {

	

	@Reference
	IcallServiceApi apiService;

	@Override
	protected void doGet(final SlingHttpServletRequest req,
		final SlingHttpServletResponse response)  {
		try{

			response.getWriter().println(apiService.myGetRequest());
		}catch(Exception e){
		//response.getWriter().println("In catch check for exception");
	}
	}
}