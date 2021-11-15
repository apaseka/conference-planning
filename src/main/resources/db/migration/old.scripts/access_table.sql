create table t_access
(
	id bigint not null
		constraint t_access_pkey
			primary key,
	object_code bigint not null,
	action_code bigint not null,
	url varchar(100)
);

alter table t_access owner to bpm_admin;

