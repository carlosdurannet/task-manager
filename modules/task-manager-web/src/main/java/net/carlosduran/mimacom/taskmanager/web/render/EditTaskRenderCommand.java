package net.carlosduran.mimacom.taskmanager.web.render;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import net.carlosduran.mimacom.taskmanager.sb.service.TaskLocalServiceUtil;
import net.carlosduran.mimacom.taskmanager.web.constants.TaskManagerPortletKeys;

@Component(
		immediate = true,
		property = {
			 TaskManagerPortletKeys.TASKMANAGER_JAVAX_PORTLET_NAME,
			 TaskManagerPortletKeys.MVC_COMMAND_NAME + "/taskManager/editTask"
		},
		service = MVCRenderCommand.class
)
public class EditTaskRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		var taskId = ParamUtil.getLong(renderRequest, "taskId");
		try {
			var task = TaskLocalServiceUtil.getTask(taskId);
			var backURL = ParamUtil.getString(renderRequest, "backURL");
			renderRequest.setAttribute("task", task);
			renderRequest.setAttribute("backURL", backURL);
			return "/editTask.jsp";
		} catch (PortalException e) {
			SessionErrors.add(renderRequest, "error-getting-task");
		}		
		
		return "/view.jsp";
		
	}

}
