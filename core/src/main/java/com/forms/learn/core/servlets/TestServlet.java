package com.forms.learn.core.servlets;



import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
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
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/*import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.osgi.service.component.annotations.Component;*/
/*import javax.jcr.Node;
import javax.jcr.Session;
import javax.annotation.Resource;
import javax.jcr.Node;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.eclipse.jetty.server.Response;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.dam.api.*;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.forms.learn.core.models.ListPageDetail;*/

@Component(immediate = true, service = Servlet.class, property = { "sling.servlet.paths=" + "/bin/testing", })
public class TestServlet extends SlingSafeMethodsServlet {
	@Reference private transient ResourceResolverFactory resourceResolverFactory;
	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
		try { 
			  final Map <String, Object > param = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, (Object) "getResourceResolver"); 
			  ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(param); 
			  String path = resourceResolver.getResource("/apps/sling").getPath(); 
			  response.getWriter().write(path); 
			} catch (LoginException e) {
			  e.printStackTrace(); 
			  //response.getWriter().write(e.getMessage()); 
			} 
		/*
		 * @Reference private transient ResourceResolverFactory resolverFactory;
		 * 
		 * try { // /ResourceResolverFactory resolverFactory =null;
		 * response.getWriter().println("Test begining...");
		 * 
		 * final Map<String, Object> param = new HashMap<String, Object>();
		 * param.put(ResourceResolverFactory.SUBSERVICE, (Object) "writeTest");
		 * response.getWriter().print("Testing..."); ResourceResolver resourceResolver =
		 * resolverFactory.getServiceResourceResolver(param);
		 * 
		 * String path = resourceResolver.getResource("/content").getPath();
		 * 
		 * response.getWriter().write("for testing"); } catch (Exception e) {
		 * e.printStackTrace();
		 * 
		 * } finally {
		 * 
		 * }
		 */ catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		/* try { */
		/*
		 * need to check that /jcr:content has to be given or not. /* ModifiableValueMap
		 * properties = resource.adaptTo(ModifiableValueMap.class);
		 * properties.put("jcr:description", "Setting the description from Java.");
		 * properties.put("jcr:title", request.getParameter("tagName"));
		 */
		/*
		 * com.forms.learn.core.models.ChildTitlesModel childObj = resource
		 * .adaptTo(com.forms.learn.core.models.ChildTitlesModel.class);
		 * List<ListPageDetail> childTitles = childObj.getListOfChild();
		 */

		/*
		 * resourceResolver = request.getResourceResolver();
		 * 
		 * Resource resource =
		 * resourceResolver.getResource("/content/forms-data-poc/language-masters/en");
		 * response.getWriter().println("After resource :)");
		 * 
		 * String filename = request.getParameter("filename");
		 *  File file = new
		 * File(filename); 
		 * FileWriter myWriter = new FileWriter(file); if (resource !=
		 * null) { Page parentPage = resource.adaptTo(Page.class); if (parentPage !=
		 * null) { Iterator<Page> listChildPages = parentPage.listChildren(); while
		 * (listChildPages.hasNext()) { Page childPage = listChildPages.next(); //
		 * ListPageDetail detail = new ListPageDetail(); myWriter.write( "Title :" +
		 * childPage.getTitle() + " Description: " + childPage.getDescription());
		 * myWriter.write("");
		 * 
		 * } } } myWriter.write("Successfully written");
		 * 
		 * myWriter.close();
		 * 
		 * AssetManager manager = resourceResolver.adaptTo(AssetManager.class);
		 * InputStream is = new FileInputStream(file); Asset asset =
		 * manager.createAsset("/content/dam/swathi-image-test/" + filename, is,
		 * "text/plain", true);
		 * 
		 * response.getWriter().println("Before begining....");
		 */
//	            File[] files1 = new File("/apps").listFiles();
//	            response.getWriter().println(files1.toString());
//	            response.getWriter().println("before loop.."+files1.length);
//	           for(File fil:files1) {
//	        	   response.getWriter().println("inside loop..");
//	        	   if(fil.isDirectory()) {
//	        		  response.getWriter().println("File Nmae:"+fil.getPath());
//	        	   }
//	           }

		/*
		 * response.getWriter().println("Response from servlet :) ");
		 * resource.getResourceResolver().commit(); resourceResolver.close();
		 */

	}

}