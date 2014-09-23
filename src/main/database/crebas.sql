create table User (
	userId SERIAL PRIMARY KEY,
	moodleId INT4 NOT NULL UNIQUE,
	username VARCHAR(200) NOT NULL UNIQUE,
	languageCode CHAR(2) NOT NULL,
	firstName VARCHAR(200) NOT NULL,
	lastName VARCHAR(200) NOT NULL,
	systemRole VARCHAR(200) NOT NULL
);

create table Translation (
	translationId SERIAL PRIMARY KEY
);

create table TranslationValue (
	translationValueId SERIAL PRIMARY KEY,
	translationId INT4 NOT NULL,
	languageCode CHAR(2) NOT NULL,
	value TEXT NOT NULL,
	UNIQUE(translationId, languageCode),
	FOREIGN KEY(translationId) REFERENCES Translation(translationId)
);

create table Room (
	roomId SERIAL PRIMARY KEY,
	name INT4 NOT NULL,
	imageUrl VARCHAR(200) NOT NULL,
	FOREIGN KEY(name) REFERENCES Translation(translationId)
);

create table Module (
	moduleId SERIAL PRIMARY KEY,
	code VARCHAR(200) NOT NULL,
	name INT4 NOT NULL,
	embedScript TEXT NOT NULL,
	description INT4 NOT NULL,
	pageContent INT4 NOT NULL,
	imageUrl VARCHAR(200) NOT NULL,
	roomId INT4 NOT NULL,
	FOREIGN KEY (name) REFERENCES Translation(translationId),
	FOREIGN KEY (description) REFERENCES Translation(translationId),
	FOREIGN KEY (pageContent) REFERENCES Translation(translationId),
	FOREIGN KEY (roomId) REFERENCES Room(roomId)
);

create table Page (
	pageId SERIAL PRIMARY KEY,
	name INT4 NOT NULL,
	content INT4 NOT NULL,
	urlSuffix VARCHAR(200) NOT NULL,
	FOREIGN KEY (name) REFERENCES Translation(translationId),
	FOREIGN KEY (content) REFERENCES Translation(translationId)
);

create table Course (
	courseId SERIAL PRIMARY KEY,
	name INT4 NOT NULL,
	description INT4 NOT NULL,
	FOREIGN KEY (description) REFERENCES Translation(translationId)
);

create table Task (
	taskId SERIAL PRIMARY KEY,
	moodleId INT4 NOT NULL,
	name VARCHAR(200) NOT NULL,
	courseId INT4 NOT NULL,
	definition INT4 NOT NULL,
	content INT4 NOT NULL,
	moduleId INT4 NOT NULL,
	templateUrl VARCHAR(200),
	FOREIGN KEY (courseId) REFERENCES Course(courseId),
	FOREIGN KEY (definition) REFERENCES Translation(translationId),
	FOREIGN KEY (content) REFERENCES Translation(translationId),
	FOREIGN KEY (moduleId) REFERENCES Module(moduleId)
);

create table CourseRole (
	courseRoleId SERIAL PRIMARY KEY,
	userId INT4 NOT NULL, 
	courseId INT4 NOT NULL, 
	role VARCHAR(200) NOT NULL,
	FOREIGN KEY (userId) REFERENCES User(userId), 
	FOREIGN KEY (courseId) REFERENCES Course(courseId)
);
	
create table TaskLog (
	taskLogId SERIAL PRIMARY KEY,
	taskId INT4 NOT NULL,
	userId INT4 NOT NULL,
	updatedTime TIMESTAMP NOT NULL,
	status VARCHAR(200),
	logName VARCHAR(200) NOT NULL,
	FOREIGN KEY (taskId) REFERENCES Task(taskId),
	FOREIGN KEY (userId) REFERENCES User(userId)
);