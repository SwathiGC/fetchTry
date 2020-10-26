<%--

  using service component.
keyService.setKey(10) ; 
String ff = keyService.getKey();

  

--%><%
%><%@include file="/libs/foundation/global.jsp"%><%
%><%@page session="false" %><%
%><%
	// TODO add you code here
%>
<%@include file="/libs/foundation/global.jsp"%>
<h1><%= properties.get("title", currentPage.getTitle()) %></h1>

<%@taglib prefix="sling" uri="https://sling.apache.org/taglibs/sling" %>
<% 
int vals=1;
 %>

<%= vals %>
<h1>vals</h1>

<sling:defineObjects />
<%

com.forms.learn.core.services.PageJSONServletService jsonService = sling.getService(com.forms.learn.core.services.PageJSONServletService.class);
String values[]=jsonService.getExcludedProperties();


%>
<%for ( int i=0; i<values.length;i++){ %>
         <font color = "green" size = "<%= values[i] %>">
            JSP Tutorial
         </font><br />
      <%}%>
<h2>This page invokes the AEM KeyService</h2>
