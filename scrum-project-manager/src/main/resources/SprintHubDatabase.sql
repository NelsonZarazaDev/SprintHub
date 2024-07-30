CREATE SEQUENCE sequence_users
    INCREMENT BY 1
    MINVALUE 0000
    START 1000;

CREATE TABLE users
(
    id_user         INT DEFAULT NEXTVAL('sequence_users'),
    firstname_user  VARCHAR(50),
    last_name_user  VARCHAR(50),
    date_birth_user DATE,
    email_user      VARCHAR(255),
    password_user   VARCHAR(50),
    token_user      VARCHAR(255),
    CONSTRAINT NN_FIRSTNAME CHECK (firstname_user IS NOT NULL),
    CONSTRAINT NN_LAST_NAME CHECK (last_name_user IS NOT NULL),
    CONSTRAINT NN_DATE_BIRTH CHECK (date_birth_user IS NOT NULL),
    CONSTRAINT NN_EMAIL CHECK (email_user IS NOT NULL),
    CONSTRAINT NN_PASSWORD CHECK (password_user IS NOT NULL),
    CONSTRAINT NN_TOKEN_USER CHECK (token_user IS NOT NULL),
    CONSTRAINT UQ_EMAIL UNIQUE (email_user),
    CONSTRAINT PK_ID_USER PRIMARY KEY (id_user),
    CONSTRAINT UQ_TOKEN_USER UNIQUE (token_user)
);

CREATE TABLE roles
(
    id_role   SERIAL,
    name_role VARCHAR(150),
    CONSTRAINT NN_NAME_ROLE CHECK ( name_role IS NOT NULL),
    CONSTRAINT PK_ID_ROLE PRIMARY KEY (id_role)
);

CREATE TABLE projects
(
    id_project          SERIAL,
    name_project        VARCHAR(250),
    description_project TEXT,
    master_scrum_id     INT,
    start_date_project  DATE,
    end_date_project    DATE,
    token_project       VARCHAR(255),
    CONSTRAINT NN_PROJECT_NAME CHECK ( name_project IS NOT NULL),
    CONSTRAINT NN_PROJECT_START_DATE CHECK ( start_date_project IS NOT NULL ),
    CONSTRAINT NN_PROJECT_END_DATE CHECK ( end_date_project IS NOT NULL),
    CONSTRAINT NN_TOKEN_PROJECT CHECK (token_project IS NOT NULL),
    CONSTRAINT PK_ID_PROJECT PRIMARY KEY (id_project),
    CONSTRAINT FK_MASTER_SCRUM_ID_PROJECTS FOREIGN KEY (master_scrum_id) REFERENCES users (id_user) ON DELETE CASCADE,
    CONSTRAINT UQ_TOKEN_PROJECT UNIQUE (token_project)
);

CREATE TABLE usersProjects
(
    id_user_project      SERIAL,
    user_id              INT,
    project_id           INT,
    role_id              INT,
    token_users_projects VARCHAR(255),
    CONSTRAINT NN_USER_ID_USERS_PROJECTS CHECK (user_id IS NOT NULL),
    CONSTRAINT NN_PROJECT_ID_USERS_PROJECTS CHECK (project_id IS NOT NULL),
    CONSTRAINT NN_ROLE_ID_USERS_PROJECTS CHECK (role_id IS NOT NULL ),
    CONSTRAINT NN_TOKEN_USERS_PROJECTS CHECK (token_users_projects IS NOT NULL ),
    CONSTRAINT PK_ID_USER_PROJECT PRIMARY KEY (id_user_project),
    CONSTRAINT FK_USER_ID_USERS_PROJECTS FOREIGN KEY (user_id) REFERENCES users (id_user),
    CONSTRAINT FK_PROJECT_ID_USERS_PROJECTS FOREIGN KEY (project_id) REFERENCES projects (id_project) ON DELETE CASCADE,
    CONSTRAINT FK_ROLE_ID_USERS_PROJECTS FOREIGN KEY (role_id) REFERENCES roles (id_role),
    CONSTRAINT UQ_TOKEN_USERS_PROJECTS UNIQUE (token_users_projects)
);

CREATE TABLE epics
(
    id_epic          SERIAL,
    name_epic        VARCHAR(255),
    description_epic TEXT,
    project_id       INT,
    token_epic       VARCHAR(255),
    CONSTRAINT NN_EPIC_NAME CHECK (name_epic IS NOT NULL ),
    CONSTRAINT NN_PROJECT_ID CHECK (project_id IS NOT NULL ),
    CONSTRAINT NN_TOKEN_EPIC CHECK (token_epic IS NOT NULL),
    CONSTRAINT PK_ID_EPIC PRIMARY KEY (id_epic),
    CONSTRAINT FK_PROJECT_ID_EPICS FOREIGN KEY (project_id) REFERENCES projects (id_project) ON DELETE CASCADE,
    CONSTRAINT UQ_TOKEN_EPIC UNIQUE (token_epic)

);

CREATE TABLE modules
(
    id_module          SERIAL,
    name_module        VARCHAR(255),
    description_module TEXT,
    epic_id            INT,
    project_id         INT,
    token_module       VARCHAR(255),
    CONSTRAINT NN_MODULE_NAME CHECK (name_module IS NOT NULL ),
    CONSTRAINT NN_EPIC_ID CHECK (epic_id IS NOT NULL ),
    CONSTRAINT NN_PROJECT_ID CHECK (project_id IS NOT NULL ),
    CONSTRAINT NN_TOKEN_MODULE CHECK (token_module IS NOT NULL),
    CONSTRAINT PK_ID_MODULE PRIMARY KEY (id_module),
    CONSTRAINT FK_EPIC_ID_MODULES FOREIGN KEY (epic_id) REFERENCES epics (id_epic),
    CONSTRAINT FK_PROJECT_ID_MODULES FOREIGN KEY (project_id) REFERENCES projects (id_project) ON DELETE CASCADE,
    CONSTRAINT UQ_TOKEN_MODULE UNIQUE (token_module)
);

CREATE TABLE userStories
(
    id_user_story          SERIAL,
    name_hu                VARCHAR(255),
    description_hu         TEXT,
    as_hu                  VARCHAR(600),
    want_hu                VARCHAR(600),
    to_hu                  VARCHAR(600),
    priority_hu            VARCHAR(3),
    estimation_hu          VARCHAR(2),
    criteria_acceptance_hu TEXT,
    state_hu               BOOLEAN,
    epic_id                INT,
    module_id              INT,
    project_id             INT,
    token_hu               VARCHAR(255),
    CONSTRAINT NN_HU_NAME CHECK (name_hu IS NOT NULL ),
    CONSTRAINT NN_HU_AS CHECK (as_hu IS NOT NULL ),
    CONSTRAINT NN_HU_WANT CHECK (want_hu IS NOT NULL ),
    CONSTRAINT NN_HU_TO CHECK (to_hu IS NOT NULL ),
    CONSTRAINT NN_HU_CRITERIA_ACCEPTANCE CHECK (criteria_acceptance_hu IS NOT NULL ),
    CONSTRAINT NN_EPIC_ID_HU CHECK (epic_id IS NOT NULL ),
    CONSTRAINT NN_MODULE_ID_HU CHECK (module_id IS NOT NULL ),
    CONSTRAINT NN_PROJECT_ID_HU CHECK (project_id IS NOT NULL ),
    CONSTRAINT NN_STATE_HU CHECK (state_hu IS NOT NULL),
    CONSTRAINT NN_TOKEN_HU CHECK (token_hu IS NOT NULL),
    CONSTRAINT PK_ID_USER_STORY PRIMARY KEY (id_user_story),
    CONSTRAINT FK_EPIC_ID_HU FOREIGN KEY (epic_id) REFERENCES epics (id_epic),
    CONSTRAINT FK_MODULE_ID_HU FOREIGN KEY (module_id) REFERENCES modules (id_module),
    CONSTRAINT FK_PROJECT_ID_HU FOREIGN KEY (project_id) REFERENCES projects (id_project) ON DELETE CASCADE,
    CONSTRAINT UQ_TOKEN_HU UNIQUE (token_hu)
);

CREATE TABLE tasks
(
    id_task                     SERIAL,
    name_task                   VARCHAR(255),
    description_task            TEXT,
    estimated_time_task         VARCHAR(2),
    priority_task               VARCHAR(3),
    responsible_user_project_id INT,
    user_story_id               INT,
    module_id                   INT,
    epic_id                     INT,
    project_id                  INT,
    scrumboard_id               INT,
    token_task                  VARCHAR(255),
    CONSTRAINT NN_TASK_NAME CHECK ( name_task IS NOT NULL ),
    CONSTRAINT NN_TASK_RESPONSIBLE CHECK ( responsible_user_project_id IS NOT NULL ),
    CONSTRAINT NN_ESTIMATED_TIME_TASK CHECK ( estimated_time_task IS NOT NULL ),
    CONSTRAINT NN_TASK_PRIORITY CHECK ( priority_task IS NOT NULL ),
    CONSTRAINT NN_USER_STORY_ID_TASK CHECK ( user_story_id IS NOT NULL ),
    CONSTRAINT NN_MODULE_ID_TASK CHECK ( module_id IS NOT NULL ),
    CONSTRAINT NN_EPIC_ID_TASK CHECK ( epic_id IS NOT NULL ),
    CONSTRAINT NN_PROJECT_ID_TASK CHECK ( project_id IS NOT NULL ),
    CONSTRAINT NN_SCRUMBOARD_ID CHECK ( scrumboard_id IS NOT NULL ),
    CONSTRAINT NN_TOKEN_TASK CHECK (token_task IS NOT NULL),
    CONSTRAINT PK_ID_TASK PRIMARY KEY (id_task),
    CONSTRAINT FK_RESPONSIBLE_USER_PROJECT_TASKS FOREIGN KEY (responsible_user_project_id) REFERENCES usersProjects (id_user_project),
    CONSTRAINT FK_USER_STORY_ID_TASKS FOREIGN KEY (user_story_id) REFERENCES userStories (id_user_story),
    CONSTRAINT FK_MODULE_ID_TASKS FOREIGN KEY (module_id) REFERENCES modules (id_module),
    CONSTRAINT FK_EPIC_ID_TASKS FOREIGN KEY (epic_id) REFERENCES epics (id_epic),
    CONSTRAINT FK_SCRUMBOARD_ID_TASKS FOREIGN KEY (scrumboard_id) REFERENCES tasks (scrumboard_id),
    CONSTRAINT FK_PROJECT_ID_TASKS FOREIGN KEY (project_id) REFERENCES projects (id_project) ON DELETE CASCADE,
    CONSTRAINT UQ_TOKEN_TASK UNIQUE (token_task)
);

CREATE TABLE scrumBoards
(
    id_scrumboard             SERIAL,
    start_date_scrumboards    DATE,
    end_date_scrumboards      DATE,
    spring_number_scrumboards VARCHAR(50),
    state_scrumboards         BOOLEAN,
    user_story_id             INT,
    project_id                INT,
    token_scrumboards         VARCHAR(255),
    CONSTRAINT NN_SCRUMBOAR_START_DATE CHECK (start_date_scrumboards IS NOT NULL ),
    CONSTRAINT NN_SCRUMBOAR_END_DATE CHECK (end_date_scrumboards IS NOT NULL ),
    CONSTRAINT NN_SCRUMBOARD_SPRING_NUMBER CHECK ( spring_number_scrumboards IS NOT NULL ),
    CONSTRAINT NN_USER_STORY_ID_SCRUMBOARD CHECK (user_story_id IS NOT NULL ),
    CONSTRAINT NN_PROJECT_ID_SCRUMBOARD CHECK (project_id IS NOT NULL),
    CONSTRAINT NN_STATE_SCRUMBOARD CHECK (state_scrumboards IS NOT NULL),
    CONSTRAINT NN_TOKEN_SCRUMBOARD CHECK (token_scrumboards IS NOT NULL),
    CONSTRAINT PK_ID_SCRUMBOARDS PRIMARY KEY (id_scrumboard),
    CONSTRAINT FK_USER_STORY_ID_SCRUMBOARDS FOREIGN KEY (user_story_id) REFERENCES userStories (id_user_story),
    CONSTRAINT FK_TASK_ID_SCRUMBOARDS FOREIGN KEY (task_id) REFERENCES tasks (id_task),
    CONSTRAINT FK_PROJECT_ID_TASKS_SCRUMBOARDS FOREIGN KEY (project_id) REFERENCES projects (id_project) ON DELETE CASCADE,
    CONSTRAINT UQ_TOKEN_SCRUMBOAR UNIQUE (token_scrumboards)
);

CREATE TABLE planningPoker
(
    id_planning_poker     SERIAL,
    result_planning_poker INT,
    user_story_id         INT,
    project_id            INT,
    token_planning_poker  VARCHAR(255),
    CONSTRAINT NN_USER_STORY_ID_PLANNINNG_POKER CHECK (user_story_id IS NOT NULL ),
    CONSTRAINT NN_PROJECT_ID_PLANNINNG_POKER CHECK (project_id IS NOT NULL ),
    CONSTRAINT NN_PLANNING_POKER CHECK (token_planning_poker IS NOT NULL),
    CONSTRAINT PK_ID_PLANNINNG_POKER PRIMARY KEY (id_planning_poker),
    CONSTRAINT FK_USER_STORY_ID_PLANNINNG_POKER FOREIGN KEY (user_story_id) REFERENCES userStories (id_user_story),
    CONSTRAINT FK_PROJECT_ID_PLANNINNG_POKER FOREIGN KEY (project_id) REFERENCES projects (id_project) ON DELETE CASCADE,
    CONSTRAINT UQ_TOKEN_PLANNING_POKER UNIQUE (token_planning_poker)

);

CREATE TABLE usersProjectPlanningPoker
(
    id_user_project_planning_poker     SERIAL,
    voting_user_project_planning_poker INT,
    state_user_project_planning_poker  BOOLEAN,
    user_project_id                    INT,
    planning_poker_id                  INT,
    token_user_project_planning_poker  VARCHAR(255),
    CONSTRAINT NN_STATE_USER_PROJECT_PLANNING_POKER CHECK ( state_user_project_planning_poker IS NOT NULL),
    CONSTRAINT NN_USER_PROJECT_ID_USER_PROJECT_PLANNING_POKER CHECK ( user_project_id IS NOT NULL ),
    CONSTRAINT NN_PLANNING_POKER_ID_USER_PROJECT_PLANNING_POKER CHECK ( planning_poker_id IS NOT NULL ),
    CONSTRAINT NN_TOKEN_USER_PROJECT_ID_USER_PROJECT_PLANNING_POKER CHECK (token_user_project_planning_poker IS NOT NULL),
    CONSTRAINT PK_ID_USER_PROJECT_PLANNING_POKER PRIMARY KEY (id_user_project_planning_poker),
    CONSTRAINT FK_USER_PROJECT_ID_USER_PROJECT_PLANNING_POKER FOREIGN KEY (user_project_id) REFERENCES usersProjects (id_user_project),
    CONSTRAINT FK_PLANNING_POKER_ID_USER_PROJECT_PLANNING_POKER FOREIGN KEY (planning_poker_id) REFERENCES planningPoker (id_planning_poker),
    CONSTRAINT UQ_TOKEN_USER_PROJECT_ID_USER_PROJECT_PLANNING_POKER UNIQUE (token_user_project_planning_poker)

);

INSERT INTO roles (name_role)
VALUES ('Scrum Master'),
       ('Product Owner'),
       ('Developer'),
       ('Tester')