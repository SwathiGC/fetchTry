
  package com.forms.learn.core.services.impl;
  
  import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.json.JSONObject;
import org.json.JSONException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.forms.learn.core.services.IcallQuery;


import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
 
@Component(immediate = true, service = IcallQuery.class) 
public class IcallQueryImpl implements IcallQuery {
  
  @Reference ResourceResolverFactory resolverFactory;
  
  
  @Reference
  Session session;
  
  private ResourceResolver resourceResolver = null;
  private Resource resource;
  
  @Reference QueryBuilder queryBuilder;
  
  @Override 
  public JSONArray myGetQuery() throws JSONException { 
  
  Map map = new HashMap();
  //map.put("fulltext",request.getParameter("fulltext"));
  map.put("group.p.or","true"); map.put("group.1_path", "/content/we-gov");
  map.put("group.1_path", "/content/forms"); Query query =
  queryBuilder.createQuery(PredicateGroup.create(map), session);
  
  
  Map param=new HashMap(); param.put(ResourceResolverFactory.SUBSERVICE,
  "writeTest"); 
  SearchResult searchResult = query.getResult();
  try {
	resourceResolver=resolverFactory.getResourceResolver(param);
} catch (LoginException e) {
	
} for(Hit hit :
  searchResult.getHits()) {
  
  String path;
try {
	path = hit.getPath();
	resource=resourceResolver.getResource(path);
	Node node=resource.adaptTo(Node.class);
	if("cq:Page".equals(node.getPrimaryNodeType())) {
		Page page=resource.adaptTo(Page.class);
		page.getTitle(); 
		page.getPath();
		page.getDescription();
	}
	
} catch (RepositoryException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}  }
  
  
  return null; }
  
  }
 