/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/1/4 ÐÇÆÚÁù 0:13:22                         */
/*==============================================================*/


drop index Index_1 on BusNode;

drop table if exists BusNode;

drop index Index_1 on NodeService;

drop table if exists NodeService;

drop index Index_1 on ServiceInfo;

drop table if exists ServiceInfo;

drop index Index_1 on ServiceNode;

drop table if exists ServiceNode;

drop index Index_1 on ServiceNodePort;

drop table if exists ServiceNodePort;

/*==============================================================*/
/* Table: BusNode                                               */
/*==============================================================*/
create table BusNode
(
   id                   varchar(36) not null,
   name                 varchar(100) not null,
   host                 varchar(100) not null,
   url                  varchar(100) not null,
   status               varchar(10),
   description          varchar(255),
   primary key (id)
);

alter table BusNode comment 'BusNode ÊµÌå±í';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on BusNode
(
   url
);

/*==============================================================*/
/* Table: NodeService                                           */
/*==============================================================*/
create table NodeService
(
   id                   varchar(36) not null,
   serviceId            varchar(36) not null,
   serviceNodeId        varchar(36) not null,
   status               varchar(2),
   primary key (id)
);

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on NodeService
(
   serviceId,
   serviceNodeId
);

/*==============================================================*/
/* Table: ServiceInfo                                           */
/*==============================================================*/
create table ServiceInfo
(
   id                   varchar(36) not null,
   category             varchar(30) not null,
   name                 varchar(50) not null,
   version              varchar(10) not null,
   paramsFormat         text,
   status               varchar(10),
   description          varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on ServiceInfo
(
   category,
   name,
   version
);

/*==============================================================*/
/* Table: ServiceNode                                           */
/*==============================================================*/
create table ServiceNode
(
   id                   varchar(36) not null,
   host                 varchar(50) not null,
   url                  varchar(100) not null,
   name                 varchar(100),
   status               varchar(10),
   description          varchar(255),
   primary key (id)
);

alter table ServiceNode comment '·þÎñ½ÚµãÊµÌå±í';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on ServiceNode
(
   url
);

/*==============================================================*/
/* Table: ServiceNodePort                                       */
/*==============================================================*/
create table ServiceNodePort
(
   id                   varchar(36) not null,
   port                 int not null,
   transportType        varchar(20) not null,
   serviceNodeId        varchar(36) not null,
   status               varchar(10),
   description          varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on ServiceNodePort
(
   port,
   transportType
);

alter table NodeService add constraint FK_Relationship_3 foreign key (serviceNodeId)
      references ServiceNode (id) on delete restrict on update restrict;

alter table NodeService add constraint FK_Relationship_4 foreign key (serviceId)
      references ServiceInfo (id) on delete restrict on update restrict;

alter table ServiceNodePort add constraint FK_Relationship_1 foreign key (serviceNodeId)
      references ServiceNode (id) on delete restrict on update restrict;

