/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.forms.learn.core.schedulers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;

/**
 * A simple demo for cron-job like tasks that get executed regularly. It also
 * demonstrates how property values can be set. Users can set the property
 * values in /system/console/configMgr
 */
@Designate(ocd = SimpleScheduledTask.Config.class)
@Component(service = Runnable.class)
public class SimpleScheduledTask implements Runnable {

	@ObjectClassDefinition(name = "A scheduled task", description = "Simple demo for cron-job like task with properties")
	public static @interface Config {
		@AttributeDefinition(name = "Cron-job expression")
		String scheduler_expression() default "* * * * * ?";
// default "*/30 * * * * ?"
		@AttributeDefinition(name = "Concurrent task", description = "Whether or not to schedule this task concurrently")
		boolean scheduler_concurrent() default false;

		@AttributeDefinition(name = "A parameter", description = "Can be configured in /system/console/configMgr")
		String myParameter() default "xyz";
		
		@AttributeDefinition(name="If checked execute",description="Inorder to schedule please enable this")
		boolean runValue() default false;
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String myParameter;
	private boolean runValue;
	// ResourceResolver resolver = null;
	@Reference private transient ResourceResolverFactory resourceResolverFactory;

	//SlingHttpServletRequest request;

	@Override
	public void run() {
		 {
			 logger.debug("The run value is: "+runValue);
			if(runValue) {
				
				try { 
					  final Map <String, Object > param = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, (Object) "writeTest"); 
					  ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(param); 
					/* response.getWriter().println(resourceResolver); */
					 // String path = resourceResolver.getResource("/content").getPath(); 
					  String filename = "trial.txt";
					  File file = new File(filename);
						 FileWriter myWriter = new FileWriter(file);
						  myWriter.write("Files in Java might be tricky, but it is fun enough!");
						  myWriter.close();
						  
						  AssetManager manager = resourceResolver.adaptTo(AssetManager.class); 
						  InputStream is = new FileInputStream(file); 
						  Asset asset = manager.createAsset("/content/dam/swathi-image-test/"+filename, is,
						  "text/plain", true); 
						  logger.debug("SimpleScheduledTask "+resourceResolver);
						  resourceResolver.commit();
						  resourceResolver.close();
						 
						 System.out.println("Last of run"); 
					} catch (LoginException | IOException e) {
					  e.printStackTrace(); 
					 
					} 
			}
		}
		
		/*
		 * try {
		 * 
		 * HashMap<String, Object> param = new HashMap();
		 * param.put(ResourceResolverFactory.SUBSERVICE, (Object) "writeTest");
		 * 
		 * 
		 * 
		 * logger.debug("Before  resolver..."+resolverFactory.getServiceResourceResolver
		 * (param));
		 * 
		 * ResourceResolver resolver =
		 * resolverFactory.getServiceResourceResolver(param); //
		 * https://experienceleaguecommunities.adobe.com/t5/adobe-experience-manager/
		 * difference-between-getresourceresolver-param-and/td-p/183403 String filename
		 * = "trial.txt"; File file = new File(filename); FileWriter myWriter = new
		 * FileWriter(file);
		 * myWriter.write("Files in Java might be tricky, but it is fun enough!");
		 * myWriter.close();
		 * 
		 * AssetManager manager = resolver.adaptTo(AssetManager.class); InputStream is =
		 * new FileInputStream(file); Asset asset =
		 * manager.createAsset("/content/dam/swathi-image-test" + filename, is,
		 * "text/plain", true); resolver.close(); System.out.println("Last of run"); }
		 * catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
		 * }
		 */

		logger.debug("SimpleScheduledTask is now running, passed Parameter='{}'", myParameter);

	}

	@Activate
	protected void activate(final Config config) {
		System.out.println("From simple Scheduled Task: " + config.myParameter());
		System.out.print("Inside activate metthod");
//		if()
		myParameter = config.myParameter();
		runValue=config.runValue();
		CustomScheduler cust=new CustomScheduler();
		
		
	}

}
