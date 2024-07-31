create table order_tb
(
    order_id      int          not null
        primary key,
    user_id       varchar(100) null,
    software_info text         null comment '软件信息列表',
    create_time   datetime     null,
    price         float        null comment '订单总金额'
);

create table software
(
    software_id    int              not null
        primary key,
    software_name  varchar(255)     null,
    author         varchar(255)     null,
    user_id        varchar(100)     null,
    tags           varchar(255)     null,
    description    text             null,
    download_count int              null,
    status         tinyint unsigned null comment '下架0 上架1'
);

create table software_info_temp
(
    software_info_temp_id int              not null
        primary key,
    user_id               varchar(100)     null,
    software_name         varchar(255)     null,
    version_type          tinyint unsigned null comment '普0 专1',
    version               varchar(50)      null comment '版本号',
    brief_description     text             null,
    detailed_description  text             null,
    application_time      datetime         null,
    win_url               varchar(255)     null,
    linux_url             varchar(255)     null,
    mac_url               varchar(255)     null,
    tags                  varchar(255)     null,
    type_status           tinyint unsigned null comment '类型状态码(新软件0/新版本1)',
    passed_status         tinyint unsigned null comment '通过状态码 0保留 1通过 2驳回',
    author                varchar(100)     null
);

create table software_version_download
(
    software_version_download_id int                  not null
        primary key,
    software_id                  int                  null,
    version_type                 tinyint unsigned     null,
    version                      varchar(50)          null,
    price                        float                null,
    win_url                      varchar(255)         null,
    linux_url                    varchar(255)         null,
    mac_url                      varchar(255)         null,
    create_time                  date default (now()) null,
    detailed_description         text                 null,
    constraint software_version_download_ibfk_1
        foreign key (software_id) references software (software_id)
);

create index software_id
    on software_version_download (software_id);

create table user
(
    user_id     varchar(100)                                                                                                                                                                                                                                                     not null comment '用户ID'
        primary key,
    password    varchar(20)                                                                                                                                                                                                                                                      not null comment '用户密码',
    email       varchar(100)                                                                                                                                                                                                                                                     not null comment '邮箱`',
    username    varchar(10)                                                                                                                                                                                                                                                      null comment '用户姓名',
    description varchar(256)                                                                                                                                                                                                                                                     null comment '个性签名',
    image       varchar(512) default 'https://th.bing.com/th/id/R.373fae27d55de2508d4d01b9e3e9b67b?rik=JQ8Qq5O5qijKSw&riu=http%3a%2f%2fpic.616pic.com%2fys_bnew_img%2f00%2f09%2f56%2fEssxkRuF8O.jpg&ehk=xFsR41r66d8n2BzMdSumBd5o%2b%2fQCyz6lYak31zI6tBA%3d&risl=&pid=ImgRaw&r=0' null comment '头像地址',
    create_time date         default (now())                                                                                                                                                                                                                                     null comment '注册时间',
    update_time date         default (now())                                                                                                                                                                                                                                     null comment '更新时间'
)
    comment '用户表';

create table user_hardware
(
    user_hardware_id int          not null
        primary key,
    user_id          varchar(100) null,
    fingerprint      varchar(100) null comment '硬件指纹',
    harware_name     varchar(20)  null comment '硬件别名'
)
    comment '用户-硬件表';

create table user_software_auth
(
    user_software_auth_id int auto_increment
        primary key,
    user_id               varchar(100)     null,
    software_id           int              null,
    version_type          tinyint unsigned null comment '普0 专1',
    expired_time          datetime         null comment '过期时间'
)
    comment '用户-软件授权表, 用于保存用户点击下载时进行是否含有有效授权的逻辑判断所需数据, 请和
后缀为_license的表区分';

create table user_software_download
(
    user_software_download_id int              not null
        primary key,
    user_id                   varchar(100)     null,
    software_id               int              null,
    version_type              tinyint unsigned null comment '普0 专1',
    version                   varchar(50)      null
);

create table user_software_license
(
    user_software_license_id int          not null
        primary key,
    user_id                  varchar(100) null,
    software_list            varchar(512) null comment '软件信息列表 含有  软件名 软件id 软件版本',
    auth_time                datetime     null,
    expiration_time          datetime     null,
    fingerprint              varchar(255) null
)
    comment '用户购买新的许可, 发送到服务器, 一条数据对应一个授权文件';