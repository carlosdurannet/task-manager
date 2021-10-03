/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package net.carlosduran.mimacom.taskmanager.sb.service.impl;

import com.liferay.portal.aop.AopService;

import net.carlosduran.mimacom.taskmanager.sb.model.Task;
import net.carlosduran.mimacom.taskmanager.sb.service.base.TaskLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the task local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>net.carlosduran.mimacom.taskmanager.sb.service.TaskLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Carlos Durán
 * @see TaskLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=net.carlosduran.mimacom.taskmanager.sb.model.Task",
	service = AopService.class
)
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

	public List<Task> getPendingTasks(long userId){
		return taskPersistence.findByuserIdAndStatus(userId, 1);
	}
	
	public List<Task> getFinishedTasks(long userId){
		return taskPersistence.findByuserIdAndStatus(userId, 2);
	}
}