package com.forms.learn.core.schedulers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.apache.sling.api.resource.ResourceResolverFactory;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.forms.learn.core.config.SlingSchedulerConfiguration;
import com.forms.learn.core.schedulers.SimpleScheduledTask.Config;
//import com.forms.learn.core.services.ResourceResolverService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anirudh Sharma
 * 
 *         A Sling Scheduler demo using OSGi R6 annotations
 *
 */
@Component(immediate = true, service = CustomScheduler.class)
@Designate(ocd = SlingSchedulerConfiguration.class)
public class CustomScheduler implements Runnable {
	/*
	 * @Reference ResourceResolverService resourecResolverService;
	 */
	/**
	 * Logger
	 */
	/*
	 * private static final Logger log =
	 * LoggerFactory.getLogger(CustomScheduler.class);
	 * 
	 *//**
		 * Custom parameter that is to be read from the configuration
		 */
	/*
	 * private String customParameter;
	 * 
	 *//**
		 * Id of the scheduler based on its name
		 */
	/*
	 * private int schedulerId;
	 * 
	 *//**
		 * Scheduler instance injected
		 */
	/*
	 * @Reference private Scheduler scheduler;
	 * 
	 *//**
		 * Activate method to initialize stuff
		 * 
		 * @param config
		 */
	/*
	 * @Activate protected void activate(SlingSchedulerConfiguration config) {
	 * 
	 *//**
		 * Getting the scheduler id
		 */
	/*
	 * schedulerId = config.schdulerName().hashCode();
	 * 
	 *//**
		 * Getting the custom parameter
		 */
	/*
	 * customParameter = config.customParameter(); }
	 * 
	 *//**
		 * Modifies the scheduler id on modification
		 * 
		 * @param config
		 */
	/*
	 * @Modified protected void modified(SlingSchedulerConfiguration config) {
	 * 
	 *//**
		 * Removing the scheduler
		 */
	/*
	 * removeScheduler();
	 * 
	 *//**
		 * Updating the scheduler id
		 */
	/*
	 * schedulerId = config.schdulerName().hashCode();
	 * 
	 *//**
		 * Again adding the scheduler
		 */
	/*
	 * addScheduler(config); }
	 * 
	 *//**
		 * This method deactivates the scheduler and removes it
		 * 
		 * @param config
		 */
	/*
	 * @Deactivate protected void deactivate(SlingSchedulerConfiguration config) {
	 * 
	 *//**
		 * Removing the scheduler
		 */
	/*
	 * removeScheduler(); }
	 * 
	 *//**
		 * This method removes the scheduler
		 */
	/*
	 * private void removeScheduler() {
	 * 
	 * log.debug("Removing scheduler: {}", schedulerId);
	 * 
	 *//**
		 * Unscheduling/removing the scheduler
		 */
	/*
	 * scheduler.unschedule(String.valueOf(schedulerId)); }
	 * 
	 *//**
		 * This method adds the scheduler
		 * 
		 * @param config
		 */
	/*
	 * private void addScheduler(SlingSchedulerConfiguration config) {
	 * 
	 *//**
		 * Check if the scheduler is enabled
		 */
	/*
	 * if(config.enabled()) {
	 * 
	 *//**
		 * Scheduler option takes the cron expression as a parameter and run accordingly
		 */
	/*
	 * ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
	 * 
	 *//**
		 * Adding some parameters
		 */
	/*
	 * scheduleOptions.name(config.schdulerName());
	 * scheduleOptions.canRunConcurrently(false);
	 * 
	 *//**
		 * Scheduling the job
		 */
	/*
	 * scheduler.schedule(this, scheduleOptions);
	 * 
	 * log.debug("Scheduler added");
	 * 
	 * } else {
	 * 
	 * log.debug("Scheduler is disabled");
	 * 
	 * } }
	 * 
	 *//**
		 * Overridden run method to execute Job
		 *//*
			 * @Override public void run() {
			 * System.out.println("The custom parameter is: "+customParameter); log.
			 * debug("Custom Scheduler is now running using the passed custom paratmeter, customParameter= {}"
			 * , customParameter);
			 * 
			 * }
			 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

    private String myParameter;
	/*
	 * ResourceResolver resolver = null; ResourceResolverFactory resolverFactory;
	 */
	 

	    @Activate
	    protected void activate(final SlingSchedulerConfiguration config) throws Exception {
	    	
	    	
		myParameter = config.customParameter();
		/*
		 * HashMap param = new HashMap(); param.put(ResourceResolverFactory.SUBSERVICE,
		 * "readService"); // readService is my resolver =
		 * resolverFactory.getServiceResourceResolver(param); Resource resource =
		 * resolver.getResource("/content/dam/ab/holiday.json");
		 * 
		 * String filename = "test1.txt"; File file = new File(filename); FileWriter
		 * myWriter = new FileWriter(file);
		 * myWriter.write("Files in Java might be tricky, but it is fun enough!");
		 * myWriter.close();
		 * 
		 * AssetManager manager = resolver.adaptTo(AssetManager.class); InputStream is =
		 * new FileInputStream(file); Asset asset =
		 * manager.createAsset("/content/dam/swathi-image-test" + filename, is,
		 * "text/plain", true);
		 */
		// Asset asset = resource.adaptTo(Asset.class);
		System.out.println("From custom Scheduled Task: " + config.customParameter());
	    }
	    
	    
	    
	    @Override
	    public void run() {
	    	//String output=writeContent();
	    	
		/*
		 * try { HashMap param = new HashMap();
		 * param.put(ResourceResolverFactory.SUBSERVICE, "readService"); // readService
		 * is my resolver = resolverFactory.getServiceResourceResolver(param); Resource
		 * resource = resolver.getResource("/content/dam/ab/holiday.json");
		 * 
		 * String filename = "test1.txt"; File file = new File(filename); FileWriter
		 * myWriter = new FileWriter(file);
		 * myWriter.write("Files in Java might be tricky, but it is fun enough!");
		 * myWriter.close();
		 * 
		 * AssetManager manager = resolver.adaptTo(AssetManager.class); InputStream is =
		 * new FileInputStream(file); Asset asset =
		 * manager.createAsset("/content/dam/swathi-image-test" + filename, is,
		 * "text/plain", true); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (LoginException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
			

	        logger.debug("Custom-Scheduled is now running, passed Parameter='{}'", myParameter);
	    }
}