create index IX_89D58555 on TaskMan_Task (userId, status);
create index IX_BE9A10E9 on TaskMan_Task (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2E13BDAB on TaskMan_Task (uuid_[$COLUMN_LENGTH:75$], groupId);