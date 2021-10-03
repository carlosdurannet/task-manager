package net.carlosduran.mimacom.taskmanager.web.action;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import net.carlosduran.mimacom.taskmanager.sb.service.TaskLocalServiceUtil;
import net.carlosduran.mimacom.taskmanager.web.constants.TaskManagerPortletKeys;

@Component(
		immediate = true,
		property = {
			TaskManagerPortletKeys.TASKMANAGER_JAVAX_PORTLET_NAME,
			TaskManagerPortletKeys.MVC_COMMAND_NAME + "/taskManager/changeTaskStatus"
		},
		service = MVCActionCommand.class
)
public class ChangeTaskStatusActionCommand extends BaseMVCActionCommand {
	
	private static final Log _log = LogFactoryUtil.getLog(ChangeTaskStatusActionCommand.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		var taskId = ParamUtil.getLong(actionRequest, "taskId");
		var status = ParamUtil.getInteger(actionRequest, "status");
		
		try {
			var task = TaskLocalServiceUtil.getTask(taskId);
			task.setStatus(status);
			task.setModifiedDate(new Date());
			TaskLocalServiceUtil.updateTask(task);
			
		} catch (Exception e) {
			SessionErrors.add(actionRequest, "failed-deleting-task");
			_log.error(e);
		}

	}

}
