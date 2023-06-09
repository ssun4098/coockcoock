drop table if exists `recipe_category`;
drop table if exists `categories`;
drop table if exists `comments`;
drop table if exists `images`;
drop table if exists `recipe_ingredient`;
drop table if exists `recipes`;
drop table if exists `ingredients`;
drop table if exists `members`;
drop table if exists `grades`;

CREATE TABLE `grades` (
                          `id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
                          `name`	varchar(32)	NULL
);

CREATE TABLE `ingredients` (
                               `id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
                               `name`	VARCHAR(255)	NULL
);

CREATE TABLE `members` (
                           `id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
                           `login_id`	varchar(32)	NOT NULL UNIQUE,
                           `password`	varchar(256)	NOT NULL ,
                           `sign_up_date`	date	NOT NULL ,
                           `ban`	boolean	NOT NULL	DEFAULT false,
                           `ban_reason`	varchar(256),
                           `withdrawal`	boolean	NOT NULL	DEFAULT false,
                           `grade_id`	bigint	NOT NULL
);

CREATE TABLE `recipes` (
                           `id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
                           `title`	varchar(256)	NOT NULL,
                           `cookery`	varchar(1024)	NOT NULL,
                           `create_at`	date	NULL,
                           `update_at`	date	NOT NULL,
                           `is_delete`	boolean	NULL,
                           `writer_id`    bigint NOT NULL,
                           foreign key (writer_id)
                               references members(id)
);

CREATE TABLE `recipe_ingredient` (
                                     `ingredient_id`	bigint	NOT NULL,
                                     `recipe_id`	bigint	NOT NULL,
                                     `amount`	varchar(32)	NULL,
                                     foreign key (recipe_id)
                                         REFERENCES recipes(id),
                                     foreign key (ingredient_id)
                                         REFERENCES ingredients(id)
);

CREATE TABLE `images` (
                          `id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
                          `name`	VARCHAR(255)	NULL,
                          `recipe_id`	bigint	NOT NULL,
                          foreign key (recipe_id)
                              REFERENCES recipes(id)
);

CREATE TABLE `comments` (
                            `id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
                            `content`	varchar(256)	NULL,
                            `depth`	integer	NULL,
                            `edit_date`	date	NULL,
                            `recipe_id`	bigint	NOT NULL,
                            `member_id`	bigint	NOT NULL,
                            `comment_id` bigint	NULL,
                            foreign key (recipe_id)
                                REFERENCES recipes(id),
                            foreign key (member_id)
                                REFERENCES members(id),
                            foreign key (comment_id)
                                REFERENCES comments(id)
);

CREATE TABLE `categories` (
                              `id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
                              `name`	VARCHAR(255)	NULL
);

CREATE TABLE `recipe_category` (
                                   `recipe_id`	bigint	NOT NULL,
                                   `category_id`	bigint	NOT NULL,
                                   foreign key (recipe_id)
                                       REFERENCES recipes(id),
                                   foreign key (category_id)
                                       REFERENCES categories(id)
);