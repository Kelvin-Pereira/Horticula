create table HORTICOLA._USER (
    ID_USER    BIGINT not null
        primary key,
    CPF        CHARACTER VARYING(11),
    EMAIL      CHARACTER VARYING(60),
    FIRST_NAME CHARACTER VARYING(20),
    LAST_NAME  CHARACTER VARYING(100),
    PASSWORD   CHARACTER VARYING(255)
);

create table HORTICOLA._PERFIL (
    ID_PERFIL BIGINT not null primary key,
    ROLE      CHARACTER VARYING(255),
    ID_USER   BIGINT,
    constraint FKC97CC4LXJSNS89D3UH1SSIEJA foreign key (ID_USER) references HORTICOLA._USER
);


-- TODO quando salva nao está reconhecendo id sequence