CREATE TABLE IF NOT EXISTS revinfo
(
    rev      integer not null
        constraint revinfo_pkey
            primary key,
    revtstmp bigint
);

CREATE TABLE IF NOT EXISTS t_actions
(
    id          bigint not null
        constraint t_actions_pkey
            primary key,
    code        bigint not null
        constraint t_actions_unique
            unique,
    description varchar(160)
);

CREATE TABLE IF NOT EXISTS t_modules
(
    id          bigint not null
        constraint t_modules_pkey
            primary key,
    code        bigint not null
        constraint t_modules_unique
            unique,
    description varchar(160)
);

CREATE TABLE IF NOT EXISTS t_permission_objects
(
    id          bigint not null
        constraint t_permission_objects_pkey
            primary key,
    code        bigint not null
        constraint t_permission_object_code_unique
            unique,
    description varchar(160),
    module_code bigint not null
        constraint fk_permission_object_module
            references t_modules (code)
);

CREATE TABLE IF NOT EXISTS t_projects
(
    id          bigint not null
        constraint t_projects_pkey
            primary key,
    code        bigint not null
        constraint t_projects_unique
            unique,
    description varchar(160),
    is_active     boolean default true not null
);

CREATE TABLE IF NOT EXISTS t_permission_objects_available_actions
(
    is_action_value_exist boolean default false not null,
    reg_exp varchar(255),
    reg_exp_description varchar(255),
    p_code                bigint                not null
        constraint fk_permission_objects_available_actions_permission_object
            references t_permission_objects (code),
    a_code                bigint                not null
        constraint fk_permission_objects_available_actions_action
            references t_actions (code),
    constraint t_permission_objects_available_actions_pkey
        primary key (p_code, a_code)
);

CREATE TABLE IF NOT EXISTS t_modules_projects
(
    m_code bigint not null,
    p_code bigint not null,
    constraint t_modules_projects_pkey
        primary key (m_code, p_code)
);

CREATE TABLE IF NOT EXISTS t_roles
(
    id            bigint               not null
        constraint t_roles_pkey
            primary key,
    created_by    varchar(255),
    created_date  bigint,
    modified_by   varchar(255),
    modified_date bigint,
    version       integer,
    is_active     boolean default true not null,
    code          bigint               not null
        constraint t_roles_code_unique
            unique,
    description   varchar(128),
    is_fresh      boolean default true not null,
    name          varchar(64)          not null,
    deact_time    timestamp
);

CREATE TABLE IF NOT EXISTS t_roles_aud
(
    id            bigint  not null,
    rev           integer not null
        constraint fk_roles_rev
            references revinfo,
    revtype       smallint,
    created_by    varchar(255),
    created_date  bigint,
    modified_by   varchar(255),
    modified_date bigint,
    is_active     boolean default true,
    code          bigint,
    description   varchar(128),
    is_fresh      boolean default true,
    name          varchar(64),
    deact_time    timestamp,
    constraint t_roles_aud_pkey
        primary key (id, rev)
);

CREATE TABLE IF NOT EXISTS t_roles_projects
(
    r_code bigint not null,
    p_code bigint not null,
    constraint t_roles_projects_pkey
        primary key (r_code, p_code)
);

CREATE TABLE IF NOT EXISTS  t_roles_projects_aud
(
    rev     integer not null
        constraint fk_roles_projects_rev
            references revinfo,
    r_code  bigint  not null,
    p_code  bigint  not null,
    revtype smallint,
    constraint t_roles_projects_aud_pkey
        primary key (rev, r_code, p_code)
);

CREATE TABLE IF NOT EXISTS t_authority
(
    id                     bigint not null
        constraint t_authority_pkey
            primary key,
    created_by             varchar(255),
    created_date           bigint,
    modified_by            varchar(255),
    modified_date          bigint,
    version                integer,
    action_value           varchar(255),
    action_code            bigint not null
        constraint fk_authority_action
            references t_actions (code),
    permission_object_code bigint not null
        constraint fk_authority_permission_object
            references t_permission_objects (code),
    project_code           bigint not null
        constraint fk_authority_project
            references t_projects (code),
    role_code              bigint not null
        constraint fk_authority_role
            references t_roles (code)
);

CREATE TABLE IF NOT EXISTS t_authority_aud
(
    id                     bigint  not null,
    rev                    integer not null
        constraint fk_authority_rev
            references revinfo,
    revtype                smallint,
    created_by             varchar(255),
    created_date           bigint,
    modified_by            varchar(255),
    modified_date          bigint,
    action_value           varchar(255),
    action_code            bigint,
    permission_object_code bigint,
    project_code           bigint,
    role_code              bigint,
    constraint t_authority_aud_pkey
        primary key (id, rev)
);

CREATE TABLE IF NOT EXISTS t_sys_param
(
    id            bigint               not null
        constraint t_sys_param_pkey
            primary key,
    created_by    varchar(255),
    created_date  bigint,
    modified_by   varchar(255),
    modified_date bigint,
    version       integer,
    is_active     boolean default true not null,
    code          bigint               not null
        constraint t_sys_param_code_unique
            unique,
    description   varchar(150),
    name          varchar(48)          not null,
    deact_time    timestamp,
    value         varchar(150)         not null,
    project_code  bigint               not null
        constraint fk_sys_param_project
            references t_projects (code)
);

CREATE TABLE IF NOT EXISTS t_sys_param_aud
(
    id            bigint  not null,
    rev           integer not null
        constraint fk_sys_param_rev
            references revinfo,
    revtype       smallint,
    created_by    varchar(255),
    created_date  bigint,
    modified_by   varchar(255),
    modified_date bigint,
    is_active     boolean default true,
    code          bigint,
    description   varchar(255),
    name          varchar(48),
    deact_time    timestamp,
    value         varchar(255),
    project_code  bigint,
    constraint t_sys_param_aud_pkey
        primary key (id, rev)
);
