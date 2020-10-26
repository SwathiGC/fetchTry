package com.forms.learn.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import java.util.Optional;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = Resource.class)
public class ChildTitlesModel {

	@SlingObject
	private Resource resource;

	@SlingObject
	private ResourceResolver resourceResolver;

	private List<ListPageDetail> listOfChild = new ArrayList<ListPageDetail>();
	public String getVal() {
		return val;
	}

	private String val = "Testing......";
	
	      
	@PostConstruct
    public void init() {
//	     PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
//	        String currentPagePath = Optional.ofNullable(pageManager)
//	                .map(pm -> pm.getContainingPage(currentResource))
//	                .map(Page::getPath).orElse("");
		listOfChild=getChildPagesTitle();
		
	}
		private List<ListPageDetail> getChildPagesTitle(){
			//Resource r1=resource.getChild("forms-data-poc/components/helloworld");
			
			Page page = resource.adaptTo(Page.class);
			if(null!= page) 
			{
				Iterator<Page> childPageList=page.listChildren();
				
				while(childPageList.hasNext())
				{
					Page currentChild = (Page) childPageList.next();
					//String currentChildTitle=currentChild.getTitle();
					ListPageDetail currentChildPage=new ListPageDetail();
					currentChildPage.setTitle(currentChild.getTitle());
					currentChildPage.setDescription(currentChild.getDescription());
					listOfChild.add(currentChildPage);
				}
				
			}
			return listOfChild;
		}
				
				
		
		
	public List<ListPageDetail> getListOfChild() {
		return listOfChild;
	}
  }
	
	
	
	
	


