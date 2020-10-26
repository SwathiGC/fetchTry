package com.forms.learn.core.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Property;
/*import javax.annotation.Resource;*/
import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.apache.sling.commons.json.JSONArray;

import org.apache.sling.commons.json.JSONObject;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;

//@SuppressWarnings("deprecation")
@SuppressWarnings("deprecation")
@Component(immediate = true, service = Servlet.class, property = { "sling.servlet.paths=" + "/bin/searchServlet", })
public class PageInfoServlet extends SlingSafeMethodsServlet {
	@Reference
	QueryBuilder queryBuilder;

	// @Reference ResourceResolverFactory resolverFactory;

	private ResourceResolver resourceResolver = null;
	private Resource resource;

	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
		Session session = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Type", "application/json");

			session = request.getResourceResolver().adaptTo(Session.class);
			Map map = new HashMap();
			map.put("type", "cq:Page");
			map.put("fulltext", request.getParameter("fulltext"));
			map.put("group.p.or", "true");
			map.put("group.1_path", "/content/we-gov");
			map.put("group.1_path", "/content/forms");

			Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
			SearchResult searchResult = query.getResult();
			resourceResolver = request.getResourceResolver();
			JSONArray arr = new JSONArray();

			for (Hit hit : searchResult.getHits()) {

				String path = hit.getPath();
				resource = resourceResolver.getResource(path);
				Node node = resource.adaptTo(Node.class);
				//node.getProperty(path+"/jcr:content").getBinary().getStream();
				JSONObject json = new JSONObject();
				Page page = resource.adaptTo(Page.class);
				json.put("jcr:primaryType", node.getPrimaryNodeType().toString());
				json.put("pageTitle", page.getPageTitle() != null ? page.getPageTitle() : "null");
				json.put("pagePath", null != page.getPath() ? page.getPath() : "null");
				json.put("pageDescription", page.getDescription() != null ? page.getDescription() : "null");
				arr.put(json);

			}

			response.getWriter().println(arr);
		} catch (Exception e) {
			// response.getWriter().println("In catch check for exception");
		} finally {
			resourceResolver.close();
			session.logout();
		}
	}

}
