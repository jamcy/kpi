create table account (
	id serial primary key,
	moodle_id int not null unique,
	username varchar(200) not null unique,
	full_name varchar(200) not null,
	role varchar(200) not null
);

create table i18n (
	id serial primary key
);

create table i18n_value (
	id serial primary key,
	i18n_id int not null,
	language_code char(2) not null,
	content text not null,
	unique(i18n_id, language_code),
	foreign key(i18n_id) references i18n(id)
);

create table room (
	id serial primary key,
	name_i18n_id int not null,
	description_i18n_id int not null,
	image_url varchar(200),
	foreign key(name_i18n_id) references i18n(id),
	foreign key(description_i18n_id) references i18n(id)
);

create table module (
	id serial primary key,
	code varchar(200) not null,
	name_i18n_id int not null,
	embed_code text not null,
	embed_width int not null default(900),
	embed_height int not null default(600),
	description_i18n_id int not null,
	page_content_i18n_id int not null,
	image_url varchar(200) not null,
	room_id int not null,
	foreign key (name_i18n_id) references i18n(id),
	foreign key (description_i18n_id) references i18n(id),
	foreign key (page_content_i18n_id) references i18n(id),
	foreign key (room_id) references room(id)
);

create table page (
	id serial primary key,
	name_i18n_id int not null,
	content_i18n_id int not null,
	url_suffix varchar(200) not null,
	foreign key (name_i18n_id) references i18n(id),
	foreign key (content_i18n_id) references i18n(id)
);

create table course (
	id serial primary key,
	name_i18n_id int not null,
	description_i18n_id int not null,
	image_url varchar(200),
	foreign key (description_i18n_id) references i18n(id)
);

create table task (
	id serial primary key,
	moodle_id int not null,
	name_i18n_id int not null,
	course_id int not null,
	definition_i18n_id int not null,
	content_i18n_id int not null,
	module_id int not null,
	starting_template_url varchar(200),
	foreign key (course_id) references course(id),
	foreign key (definition_i18n_id) references i18n(id),
	foreign key (content_i18n_id) references i18n(id),
	foreign key (module_id) references module(id)
);

create table account_course (
	account_id int not null, 
	course_id int not null, 
	foreign key (account_id) references account(id), 
	foreign key (course_id) references course(id),
	primary key(course_id, account_id)
);
	
create table task_log (
	id serial primary key,
	task_id int not null,
	account_id int not null,
	last_updated timestamp,
	status varchar(200),
	code varchar(200) not null,
	foreign key (task_id) references task(id),
	foreign key (account_id) references account(id)
);