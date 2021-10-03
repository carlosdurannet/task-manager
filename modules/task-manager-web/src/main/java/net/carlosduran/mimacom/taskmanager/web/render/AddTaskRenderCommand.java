package net.carlosduran.mimacom.taskmanager.web.render;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import net.carlosduran.mimacom.taskmanager.web.constants.TaskManagerPortletKeys;

@Component(
		immediate = true,
		property = {
			 TaskManagerPortletKeys.TASKMANAGER_JAVAX_PORTLET_NAME,
			 TaskManagerPortletKeys.MVC_COMMAND_NAME + "/taskManager/addTask"
		},
		service = MVCRenderCommand.class
)
public class AddTaskRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		return "/addTask.jsp";
	}

}
