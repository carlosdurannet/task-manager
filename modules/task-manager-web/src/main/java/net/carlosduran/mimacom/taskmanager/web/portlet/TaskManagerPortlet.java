package net.carlosduran.mimacom.taskmanager.web.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import net.carlosduran.mimacom.taskmanager.sb.service.TaskLocalServiceUtil;
import net.carlosduran.mimacom.taskmanager.web.constants.TaskManagerPortletKeys;

/**
 * @author Carlos
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.mimacom",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=display.name",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TaskManagerPortletKeys.TASKMANAGER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TaskManagerPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				
		if(themeDisplay.getUser().isDefaultUser()) {
			SessionErrors.add(renderRequest, "no-logged-user");
		}
		
		var pendingTasks = TaskLocalServiceUtil.getPendingTasks(themeDisplay.getUserId());
		var finishedTasks = TaskLocalServiceUtil.getFinishedTasks(themeDisplay.getUserId());
		renderRequest.setAttribute("pendingTasks", pendingTasks);
		renderRequest.setAttribute("finishedTasks", finishedTasks);
		
		super.doView(renderRequest, renderResponse);
	}
	
	
	
}