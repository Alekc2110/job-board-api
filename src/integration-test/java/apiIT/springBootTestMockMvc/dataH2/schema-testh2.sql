drop table if exists job_entity cascade;
drop table if exists job_entity_job_types cascade;
drop table if exists job_entity_tags cascade;
drop table if exists job_types cascade;
drop table if exists tags cascade;

create table job_entity (
        id bigint primary key,
        company_name varchar(255),
        location varchar(255),
        slug varchar(255) unique,
        title varchar(255),
        url varchar(255),
        description clob,
        remote boolean,
        created_at bigint
);

create table job_types (
        job_entity_id bigint not null,
        job_types varchar(255),
           constraint fk_job_types_job_entity
                    foreign key (job_entity_id)
                          references job_entity(id)
);

create table tags (
        job_entity_id bigint not null,
        tags varchar(255),
        constraint fk_tags_job_entity
            foreign key (job_entity_id)
                references job_entity(id)
);