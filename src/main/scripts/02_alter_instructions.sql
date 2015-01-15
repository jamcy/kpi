--Alter instruction go here
--Each instruction should contain comment
--Comment format: <date added> <run on prod (yes/no)> <name of last prod backup, made before run>

--15.01.2015	no
alter table room add column description_i18n_id int not null default(0) references i18n(id);

--15.01.2015	no
alter table module add column embed_width int not null default(900);
alter table module add column embed_height int not null default(600);