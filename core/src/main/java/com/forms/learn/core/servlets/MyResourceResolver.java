package com.forms.learn.core.servlets;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Activate;


@Component(immediate = true, service = Servlet.class, property = { "sling.servlet.paths=" + "/bin/learning", })
public class MyResourceResolver extends SlingSafeMethodsServlet { 
 
@Reference ResourceResolverFactory resourceResolverFactory; 
 
public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException { 
try { 
  final Map <String, Object > param = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, (Object) "writeTest"); 
  ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(param); 
  response.getWriter().println(resourceResolver);
  String path = resourceResolver.getResource("/content").getPath(); 
  response.getWriter().write("removed refernce annotation.. "+path); 
} catch (LoginException e) {
  e.printStackTrace(); 
  response.getWriter().write(e.getMessage()); 
} 
}
}