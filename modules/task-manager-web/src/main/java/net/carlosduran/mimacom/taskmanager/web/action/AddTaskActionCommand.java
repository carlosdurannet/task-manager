package net.carlosduran.mimacom.taskmanager.web.action;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import net.carlosduran.mimacom.taskmanager.sb.model.Task;
import net.carlosduran.mimacom.taskmanager.sb.service.TaskLocalServiceUtil;
import net.carlosduran.mimacom.taskmanager.web.constants.TaskManagerPortletKeys;
import net.carlosduran.mimacom.taskmanager.web.constants.TaskStatus;

@Component(
		immediate = true,
		property = {
			TaskManagerPortletKeys.TASKMANAGER_JAVAX_PORTLET_NAME,
			TaskManagerPortletKeys.MVC_COMMAND_NAME + "/taskManager/addTask"
		},
		service = MVCActionCommand.class
)
public class AddTaskActionCommand extends BaseMVCActionCommand {
	
	private static final Log _log = LogFactoryUtil.getLog(AddTaskActionCommand.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		var groupId = themeDisplay.getScopeGroupId();		
		var companyId = themeDisplay.getCompanyId();
		var userId = themeDisplay.getUserId();
		var screenName = themeDisplay.getUser().getScreenName();
		var now = new Date();
		
		var title = ParamUtil.getString(actionRequest, "title");
		var description = ParamUtil.getString(actionRequest, "description");		
		var taskId = CounterLocalServiceUtil.increment(Task.class.getName());
		var task = TaskLocalServiceUtil.createTask(taskId);
		
		task.setCompanyId(companyId);
		task.setGroupId(groupId);
		task.setUserId(userId);
		task.setUserName(screenName);
		task.setTitle(title);
		task.setDescription(description);
		task.setCreateDate(now);
		task.setModifiedDate(now);
		task.setStatus(TaskStatus.PENDING);
		
		try {
			TaskLocalServiceUtil.addTask(task);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, "failed-adding-task");
			_log.error(e);
		}

	}

}
