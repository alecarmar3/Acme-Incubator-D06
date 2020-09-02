
    create table `accounting_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `final_mode` bit,
        `title` varchar(255),
        `bookkeeper_id` integer not null,
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `activity` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `budget_amount` double precision,
        `budget_currency` varchar(255),
        `end_date` datetime(6),
        `start_date` datetime(6),
        `title` varchar(255),
        `web_links` varchar(255),
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `creation_date` datetime(6),
        `investment_money_offer_amount` double precision,
        `investment_money_offer_currency` varchar(255),
        `justification` varchar(255),
        `statement` varchar(255),
        `status` integer,
        `ticker` varchar(255),
        `update_date` datetime(6),
        `investment_round_id` integer not null,
        `investor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `brand` varchar(255),
        `credit_card_number` varchar(255),
        `cvv` varchar(255),
        `expiration_date` varchar(255),
        `holder_name` varchar(255),
        `picture` varchar(255),
        `slogan` varchar(255),
        `target_url` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm_name` varchar(255),
        `responsibility_statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper_request` (
       `id` integer not null,
        `version` integer not null,
        `approved` bit not null,
        `firm_name` varchar(255),
        `responsibility_statement` varchar(255),
        `status` integer,
        `authenticated_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `category` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `average_goal` varchar(255),
        `average_reward_amount` double precision,
        `average_reward_currency` varchar(255),
        `deadline` datetime(6),
        `description` varchar(255),
        `expert_goal` varchar(255),
        `expert_reward_amount` double precision,
        `expert_reward_currency` varchar(255),
        `rookie_goal` varchar(255),
        `rookie_reward_amount` double precision,
        `rookie_reward_currency` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `configuration` (
       `id` integer not null,
        `version` integer not null,
        `activity_sectors` varchar(255),
        `spam_threshold` double precision,
        `spam_words` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `entrepreneur` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `qualification_record` varchar(255),
        `skills_record` varchar(255),
        `start_up_name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `forum` (
       `id` integer not null,
        `version` integer not null,
        `creation_date` datetime(6),
        `title` varchar(255),
        `investment_round_id` integer not null,
        `owner_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `inquiry` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `email` varchar(255),
        `money_max_amount` double precision,
        `money_max_currency` varchar(255),
        `money_min_amount` double precision,
        `money_min_currency` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_round` (
       `id` integer not null,
        `version` integer not null,
        `additional_info` varchar(255),
        `amount_of_money_amount` double precision,
        `amount_of_money_currency` varchar(255),
        `creation_date` datetime(6),
        `description` varchar(255),
        `final_mode` bit,
        `kind_of_round` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        `entrepreneur_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `firm_name` varchar(255),
        `profile` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `forum_id` integer not null,
        `owner_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `notice` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `header_picture` varchar(255),
        `title` varchar(255),
        `web_links` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `overture` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `email` varchar(255),
        `money_max_amount` double precision,
        `money_max_currency` varchar(255),
        `money_min_amount` double precision,
        `money_min_currency` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `participates_in` (
       `id` integer not null,
        `version` integer not null,
        `forum_id` integer not null,
        `participant_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `inventor_name` varchar(255),
        `source_type` varchar(255),
        `stars` integer,
        `title` varchar(255),
        `update_date` datetime(6),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `research` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `email` varchar(255),
        `money_max_amount` double precision,
        `money_max_currency` varchar(255),
        `money_min_amount` double precision,
        `money_min_currency` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `technology_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `inventor_name` varchar(255),
        `source_type` varchar(255),
        `stars` integer,
        `title` varchar(255),
        `update_date` datetime(6),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `inventor_name` varchar(255),
        `source_type` varchar(255),
        `stars` integer,
        `title` varchar(255),
        `update_date` datetime(6),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDXh8dbpsf17u4nh0t16sw1b86pt on `accounting_record` (`investment_round_id`);
create index IDX2fie4tfqjhatmlwtwev59aqva on `accounting_record` (`bookkeeper_id`);
create index IDX1l5yvnnpt4wxudkqaoaqup4xw on `activity` (`investment_round_id`);
create index IDX2q2747fhp099wkn3j2yt05fhs on `application` (`status`);
create index IDXh86ff6by9fbmye5anrmj56xrq on `application` (`investment_round_id`);
create index IDXj1shjic6mip5nyik4ywhvxiid on `application` (`ticker` asc);
create index IDXa91bud5njtdgbcs992l3fc8dl on `application` (`investor_id`);
create index IDXougcduaeldommk3nf4satsqdn on `application` (`investment_round_id`, `investor_id`);

    alter table `application` 
       add constraint UK_ao7wxw7e7mkj6g5q49yq2fw8d unique (`ticker`);
create index IDX6kh58rnb79vi7dflxamp4ye4c on `bookkeeper` (`user_account_id`);
create index IDXdxb7pmiadoafycn8trxof2cuy on `bookkeeper_request` (`authenticated_id`);

    alter table `bookkeeper_request` 
       add constraint UK_qvxp9h7at8vjbwgpi9q5s3fpl unique (`authenticated_id`);
create index IDXnr284tes3x8hnd3h716tmb3fr on `challenge` (`deadline`);
create index IDX8f40bqatbcvi4ru3bjwo3yo6d on `entrepreneur` (`user_account_id`);
create index IDX801jxtf8obi1te3ru5paurkru on `forum` (`investment_round_id`);
create index IDXg315o0l90grqbj4wcc9dyasjg on `forum` (`owner_id`);
create index IDX9u3lu85o98y0tro95qasghg8e on `inquiry` (`deadline`);
create index IDXcaskyi2xiccqj4na2coao5m4o on `investment_round` (`entrepreneur_id`);
create index IDX9tsve2s3eqtxjjxxoqql2ul81 on `investment_round` (`ticker`);

    alter table `investment_round` 
       add constraint UK_408l1ohatdkkut5bkt0eu6ifs unique (`ticker`);
create index IDXay1h91e0c79xwvohu2dy19um1 on `investor` (`user_account_id`);
create index IDX3qepdx8lwew1ng35nkn22076s on `message` (`forum_id`);
create index IDXrcpel5hblr62lfjr9gmpk2wgi on `notice` (`deadline`);
create index IDX3ianip0mmnj1316lpeas2yw71 on `overture` (`deadline`);
create index IDXa8qbvbh5xna72e01lucu56w7t on `participates_in` (`participant_id`);
create index IDX8m0ykoycrhcuhp138wn9n3wjd on `participates_in` (`forum_id`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `accounting_record` 
       add constraint `FK41jm4vk7runvmg5tderffrele` 
       foreign key (`bookkeeper_id`) 
       references `bookkeeper` (`id`);

    alter table `accounting_record` 
       add constraint `FKk1pmfnppwk0kav7xloy8u71uq` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `activity` 
       add constraint `FK1ufotopeofii4jlefyk9c7os5` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKk5ibe41quxsif8im882xv4afo` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `application` 
       add constraint `FKl4fp0cd8c008ma79n6w58xhk9` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `bookkeeper` 
       add constraint FK_krvjp9eaqyapewl2igugbo9o8 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `bookkeeper_request` 
       add constraint `FKtvgadivkof5jo6nfcbk4slan` 
       foreign key (`authenticated_id`) 
       references `user_account` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `entrepreneur` 
       add constraint FK_r6tqltqvrlh1cyy8rsj5pev1q 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `forum` 
       add constraint `FKq8ggcjgl5by5gf6l5bji632hu` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `forum` 
       add constraint `FKohunr24m4s1n5mgs3eoqtscsv` 
       foreign key (`owner_id`) 
       references `authenticated` (`id`);

    alter table `investment_round` 
       add constraint `FKkj1l8c2ftn9c65y061me6t37j` 
       foreign key (`entrepreneur_id`) 
       references `entrepreneur` (`id`);

    alter table `investor` 
       add constraint FK_dcek5rr514s3rww0yy57vvnpq 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `message` 
       add constraint `FKfwwpivgx5j4vw4594dgrw884q` 
       foreign key (`forum_id`) 
       references `forum` (`id`);

    alter table `message` 
       add constraint `FKjkp3iptdb6euvvnncku7jxblb` 
       foreign key (`owner_id`) 
       references `authenticated` (`id`);

    alter table `participates_in` 
       add constraint `FKgem9s6jnp8gjaptj40buukpjd` 
       foreign key (`forum_id`) 
       references `forum` (`id`);

    alter table `participates_in` 
       add constraint `FKp8dubhjpvwx0mgn144chnj2ya` 
       foreign key (`participant_id`) 
       references `authenticated` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
