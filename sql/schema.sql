create table user

(

    user_id int auto_increment primary key,

    email varchar(255) not null,

    password varchar(255) not null,

    first_name varchar(255) not null,

    last_name varchar(255) not null

);



create table todo

(

    todo_id int auto_increment primary key,

    user_id int not null,

    description text not null,

    done tinyint(1) default 0 null,

    constraint todo_user_user_id_fk

    foreign key (user_id) references user (user_id)

);
