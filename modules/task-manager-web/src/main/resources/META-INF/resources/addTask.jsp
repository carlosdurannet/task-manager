<%@ include file="init.jsp" %>

<portlet:actionURL var="addTaskURL" name="/taskManager/addTask" />

<h2><liferay-ui:message key="add.new.task" /></h2>

<aui:form action="${ addTaskURL }" method="post">
        <aui:input label="task.title" name="title" type="text">
        	<aui:validator name="required" />
        </aui:input>
        <aui:input label="task.description" type="textarea" name="description">
        	<aui:validator name="required" />
        </aui:input>

        <aui:button type="submit" value="add.task" />
        <a href="${ backURL }" class="btn btn-secondary">
        	<liferay-ui:message key="cancel" />
        </a>
</aui:form>


