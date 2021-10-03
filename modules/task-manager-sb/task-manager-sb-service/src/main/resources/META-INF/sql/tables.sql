create table TaskMan_Task (
	uuid_ VARCHAR(75) null,
	taskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	title VARCHAR(255) null,
	description STRING null,
	status INTEGER,
	createDate DATE null,
	modifiedDate DATE null
);