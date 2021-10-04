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
import com.liferay.portal.kernel.util.ParamUtil;

import net.carlosduran.mimacom.taskmanager.sb.model.Task;
import net.carlosduran.mimacom.taskmanager.sb.service.TaskLocalServiceUtil;
import net.carlosduran.mimacom.taskmanager.web.constants.TaskManagerPortletKeys;

@Component(
		immediate = true,
		property = {
			TaskManagerPortletKeys.TASKMANAGER_JAVAX_PORTLET_NAME,
			TaskManagerPortletKeys.MVC_COMMAND_NAME + "/taskManager/editTask"
		},
		service = MVCActionCommand.class
)
public class EditTaskActionCommand extends BaseMVCActionCommand {
	
	private static final Log _log = LogFactoryUtil.getLog(EditTaskActionCommand.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		var now = new Date();		
		var title = ParamUtil.getString(actionRequest, "title");
		var description = ParamUtil.getString(actionRequest, "description");		
		var taskId = ParamUtil.getLong(actionRequest, "taskId");
		
		try {
			var task = TaskLocalServiceUtil.getTask(taskId);
			
			task.setTitle(title);
			task.setDescription(description);
			task.setModifiedDate(now);
			TaskLocalServiceUtil.updateTask(task);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, "failed-updating-task");
			_log.error(e);
		}

	}

}
