<%@ include file="init.jsp" %>

<portlet:actionURL var="editTaskURL" name="/taskManager/editTask">
	<portlet:param name="taskId" value="${ task.taskId }" />
</portlet:actionURL>

<h2><liferay-ui:message key="edit.task" /></h2>

<aui:form action="${ editTaskURL }" method="post">
        <aui:input label="task.title" name="title" type="text" value="${ task.title }">
        	<aui:validator name="required" />
        </aui:input>
        <aui:input label="task.description" type="textarea" name="description" value="${ task.description }">
        	<aui:validator name="required" />
        </aui:input>

        <aui:button type="submit" value="update.task"/>
        <a href="${ backURL }" class="btn btn-secondary">
        	<liferay-ui:message key="close" />
        </a>
</aui:form>


