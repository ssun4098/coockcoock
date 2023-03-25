drop table if exists `recipy_category`;
drop table if exists `categories`;
drop table if exists `great`;
drop table if exists `comments`;
drop table if exists `images`;
drop table if exists `recipe_ingredient`;
drop table if exists `ingredients`;
drop table if exists `recipes`;
drop table if exists `members`;
drop table if exists `grades`;

CREATE TABLE `members` (
                           `id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
                           `login_id`	varchar(32)	NULL UNIQUE,
                           `password`	varchar(256)	NULL,
                           `sign_up_date`	date	NULL,
                           `ban`	boolean	NULL	DEFAULT false,
                           `ban_reason`	varchar(256),
                           `boolean`	boolean	NULL	DEFAULT false,
                           `grade_id`	bigint	NOT NULL
);

CREATE TABLE `recipes` (
                           `id`	bigint	NOT NULL,
                           `title`	varchar(256)	NULL,
                           `cookery`	varchar(1024)	NULL,
                           `write_date`	date	NULL,
                           `score`	integer	NULL	DEFAULT 0,
                           `create_datetime`	datetime	NULL,
                           `update_datetime`	datetime	NOT NULL,
                           `delete`	boolean	NULL,
                           `writer`	bigint	NOT NULL
);

CREATE TABLE `grades` (
                          `id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
                          `name`	varchar(32)	NULL
);

CREATE TABLE `ingredients` (
                               `id`	bigint	NOT NULL,
                               `name`	VARCHAR(255)	NULL
);

CREATE TABLE `recipe_ingredient` (
                                     `ingredient_id`	bigint	NOT NULL,
                                     `recipe_id`	bigint	NOT NULL,
                                     `amount`	varchar(32)	NULL
);

CREATE TABLE `images` (
                          `id`	bigint	NOT NULL,
                          `name`	VARCHAR(255)	NULL,
                          `recipe_id`	bigint	NOT NULL
);

CREATE TABLE `comments` (
                            `id`	VARCHAR(255)	NOT NULL,
                            `content`	varchar(256)	NULL,
                            `depth`	integer	NULL,
                            `edit_date`	date	NULL,
                            `recipe_id`	bigint	NOT NULL,
                            `member_id`	bigint	NOT NULL,
                            `comment_id`	VARCHAR(255)	NULL
);

CREATE TABLE `great` (
                         `member_id`	bigint	NOT NULL,
                         `recipe_id`	bigint	NOT NULL,
                         `enroll_date`	date	NULL
);

CREATE TABLE `categories` (
                              `id`	bigint	NOT NULL,
                              `name`	VARCHAR(255)	NULL
);

CREATE TABLE `recipy_category` (
                                   `recipy_id`	bigint	NOT NULL,
                                   `category_id`	bigint	NOT NULL
);

ALTER TABLE `recipes` ADD CONSTRAINT `PK_RECIPES` PRIMARY KEY (
                                                               `id`
    );

ALTER TABLE `ingredients` ADD CONSTRAINT `PK_INGREDIENTS` PRIMARY KEY (
                                                                       `id`
    );

ALTER TABLE `recipe_ingredient` ADD CONSTRAINT `PK_RECIPE_INGREDIENT` PRIMARY KEY (
                                                                                   `ingredient_id`,
                                                                                   `recipe_id`
    );

ALTER TABLE `images` ADD CONSTRAINT `PK_IMAGES` PRIMARY KEY (
                                                             `id`
    );

ALTER TABLE `comments` ADD CONSTRAINT `PK_COMMENTS` PRIMARY KEY (
                                                                 `id`
    );

ALTER TABLE `great` ADD CONSTRAINT `PK_GREAT` PRIMARY KEY (
                                                           `member_id`,
                                                           `recipe_id`
    );

ALTER TABLE `categories` ADD CONSTRAINT `PK_CATEGORIES` PRIMARY KEY (
                                                                     `id`
    );

ALTER TABLE `recipy_category` ADD CONSTRAINT `PK_RECIPY_CATEGORY` PRIMARY KEY (
                                                                               `recipy_id`,
                                                                               `category_id`
    );

ALTER TABLE `members` ADD CONSTRAINT `FK_grades_TO_members_1` FOREIGN KEY (
                                                                           `grade_id`
    )
    REFERENCES `grades` (
                         `id`
        );

ALTER TABLE `recipes` ADD CONSTRAINT `FK_members_TO_recipes_1` FOREIGN KEY (
                                                                            `writer`
    )
    REFERENCES `members` (
                          `id`
        );

ALTER TABLE `recipe_ingredient` ADD CONSTRAINT `FK_ingredients_TO_recipe_ingredient_1` FOREIGN KEY (
                                                                                                    `ingredient_id`
    )
    REFERENCES `ingredients` (
                              `id`
        );

ALTER TABLE `recipe_ingredient` ADD CONSTRAINT `FK_recipes_TO_recipe_ingredient_1` FOREIGN KEY (
                                                                                                `recipe_id`
    )
    REFERENCES `recipes` (
                          `id`
        );

ALTER TABLE `images` ADD CONSTRAINT `FK_recipes_TO_images_1` FOREIGN KEY (
                                                                          `recipe_id`
    )
    REFERENCES `recipes` (
                          `id`
        );

ALTER TABLE `comments` ADD CONSTRAINT `FK_recipes_TO_comments_1` FOREIGN KEY (
                                                                              `recipe_id`
    )
    REFERENCES `recipes` (
                          `id`
        );

ALTER TABLE `comments` ADD CONSTRAINT `FK_members_TO_comments_1` FOREIGN KEY (
                                                                              `member_id`
    )
    REFERENCES `members` (
                          `id`
        );

ALTER TABLE `comments` ADD CONSTRAINT `FK_comments_TO_comments_1` FOREIGN KEY (
                                                                               `comment_id`
    )
    REFERENCES `comments` (
                           `id`
        );

ALTER TABLE `great` ADD CONSTRAINT `FK_members_TO_great_1` FOREIGN KEY (
                                                                        `member_id`
    )
    REFERENCES `members` (
                          `id`
        );

ALTER TABLE `great` ADD CONSTRAINT `FK_recipes_TO_great_1` FOREIGN KEY (
                                                                        `recipe_id`
    )
    REFERENCES `recipes` (
                          `id`
        );

ALTER TABLE `recipy_category` ADD CONSTRAINT `FK_recipes_TO_recipy_category_1` FOREIGN KEY (
                                                                                            `recipy_id`
    )
    REFERENCES `recipes` (
                          `id`
        );

ALTER TABLE `recipy_category` ADD CONSTRAINT `FK_categories_TO_recipy_category_1` FOREIGN KEY (
                                                                                               `category_id`
    )
    REFERENCES `categories` (
                             `id`
        );