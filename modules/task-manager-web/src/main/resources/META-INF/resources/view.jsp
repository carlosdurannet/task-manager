<%@page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
<%@page import="net.carlosduran.mimacom.taskmanager.sb.model.Task"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ include file="init.jsp" %>



<c:choose>
<c:when test="<%= SessionErrors.size(renderRequest) == 0 %>">

<%
PortletURL iteratorURL = renderResponse.createRenderURL();	
List<Task> pendingTasks = (List<Task>)renderRequest.getAttribute("pendingTasks");
List<Task> finishedTasks = (List<Task>)renderRequest.getAttribute("finishedTasks");
%>
<portlet:renderURL var="addTaskURL">
	<portlet:param name="mvcRenderCommandName" value="/taskManager/addTask"/>
</portlet:renderURL>


<div class="m-4">
    <div class="container">
    	<div class="row">
    		<div class="col">
				<div class="tools">
					<a class="btn btn-primary" href="${ addTaskURL }">
						<liferay-ui:message key="add.task"/>		
					</a>
				</div>    		
    		</div>
    	</div>
    	<div class="row mt-2">
    		<div class="col">
    			<h2 class="mb-4"><liferay-ui:message key="pending.tasks"/></h2>
				<div id="sc-main">			
					<liferay-ui:search-container delta="20" emptyResultsMessage="no.pending.tasks" iteratorURL="<%= iteratorURL %>" total="<%= pendingTasks.size() %>">
					    <liferay-ui:search-container-results  results="<%= ListUtil.subList(pendingTasks, searchContainer.getStart(), searchContainer.getEnd()) %>" />
					    <liferay-ui:search-container-row className="net.carlosduran.mimacom.taskmanager.sb.model.Task" keyProperty="taskId" modelVar="task">
					        <liferay-ui:search-container-column-text name="col.task.title" value="${ task.title }" />
					        <liferay-ui:search-container-column-text name="col.creation.date">
					        	<fmt:formatDate type = "both" value = "${ task.createDate }" />
					        </liferay-ui:search-container-column-text>
					        <liferay-ui:search-container-column-text name="col.task.operations">
					        	<portlet:actionURL var="deleteTaskURL" name="/taskManager/deleteTask">
					        		<portlet:param name="taskId" value="${ task.taskId }"/>
					        	</portlet:actionURL>
					        	<portlet:actionURL var="changeTaskStatusURL" name="/taskManager/changeTaskStatus">
					        		<portlet:param name="taskId" value="${ task.taskId }"/>
					        		<portlet:param name="status" value="2"/>
					        	</portlet:actionURL>				           	
					           	<a class="btn btn-primary" href="${ changeTaskStatusURL }">
					           		<liferay-ui:message key="action.finish"/>
					           	</a>					           
					           	<a class="btn btn-danger" href="${ deleteTaskURL }">
					           		<liferay-ui:message key="action.delete"/>
					           	</a>					           	
						    </liferay-ui:search-container-column-text>
					    </liferay-ui:search-container-row>		    
					    <liferay-ui:search-iterator  markupView="lexicon" />
					</liferay-ui:search-container>
				</div>
    		</div>
    	</div>
    	<div class="row mt-2">
    		<div class="col">
    			<h2 class="mb-4"><liferay-ui:message key="finished.tasks"/></h2>
				<div id="sc-main">			
					<liferay-ui:search-container delta="20" emptyResultsMessage="no.finished.tasks" iteratorURL="<%= iteratorURL %>" total="<%= finishedTasks.size() %>">
					    <liferay-ui:search-container-results  results="<%= ListUtil.subList(finishedTasks, searchContainer.getStart(), searchContainer.getEnd()) %>" />
					    <liferay-ui:search-container-row className="net.carlosduran.mimacom.taskmanager.sb.model.Task" keyProperty="taskId" modelVar="task">
					        <liferay-ui:search-container-column-text name="col.task.title" value="${ task.title }" />
					        <liferay-ui:search-container-column-text name="col.creation.date">
					        	<fmt:formatDate type = "both" value = "${ task.createDate }" />
					        </liferay-ui:search-container-column-text>
					        <liferay-ui:search-container-column-text name="col.task.operations">
					        	<portlet:actionURL var="deleteTaskURL" name="/taskManager/deleteTask">
					        		<portlet:param name="taskId" value="${ task.taskId }"/>
					        	</portlet:actionURL>
					        	<portlet:actionURL var="changeTaskStatusURL" name="/taskManager/changeTaskStatus">
					        		<portlet:param name="taskId" value="${ task.taskId }"/>
					        		<portlet:param name="status" value="1"/>
					        	</portlet:actionURL>				           	
					           	<a class="btn btn-primary" href="${ changeTaskStatusURL }">
					           		<liferay-ui:message key="action.restart"/>
					           	</a>					           
					           	<a class="btn btn-danger" href="${ deleteTaskURL }">
					           		<liferay-ui:message key="action.delete"/>
					           	</a>					           	
						    </liferay-ui:search-container-column-text>
					    </liferay-ui:search-container-row>		    
					    <liferay-ui:search-iterator  markupView="lexicon" />
					</liferay-ui:search-container>
				</div>
    		</div>
    	</div>
	</div>
</div>


</c:when>
<c:otherwise>
<liferay-ui:error key="no-logged-user" 
   message="required.loggerd.user" />	
</c:otherwise>
</c:choose>
