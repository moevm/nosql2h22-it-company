export const ACCESS_TOKEN = "accessToken";
export const REFRESH_TOKEN = "refreshToken";
export const EMPTY_TOKEN = ""
export const ADVANCED_ROLE_LIST = ["HR", "PM"];
export const AUTH_PAGE_IMAGE = "auth_image.png";
export const AUTH_PAGE_TITLE = "Auth";
export const HOME_PAGE_TITLE = "Home";
export const WATCHER_PAGE_TITLE = "Watcher";
export const PERSON_PAGE_TITLE = "Person";
export const DOCUMENT_PAGE_TITLE = "Document";
export const PERSON_ADVANCED_SEARCH_ENUMS = [
    {
        name: "sex",
        value: "Пол",
        enum: [
            {name: "UNDEFINED", value: "Не имеет значения"},
            {name: "MALE", value: "Мужчина"},
            {name: "FEMALE", value: "Женщина"}
        ]
    },
    {
        name: "position",
        value: "Должность",
        enum: [
            {name: "JUNIOR_BACKEND_DEVELOPER", value: "Junior backend developer"},
            {name: "MIDDLE_BACKEND_DEVELOPER", value: "Middle backend developer"},
            {name: "SENIOR_BACKEND_DEVELOPER", value: "Senior backend developer"},
            {name: "JUNIOR_FRONTEND_DEVELOPER", value: "Junior frontend developer"},
            {name: "MIDDLE_FRONTEND_DEVELOPER", value: "Middle frontend developer"},
            {name: "SENIOR_FRONTEND_DEVELOPER", value: "Senior frontend developer"},
            {name: "JUNIOR_QA_DEVELOPER", value: "Junior QA developer"},
            {name: "MIDDLE_QA_DEVELOPER", value: "Middle QA developer"},
            {name: "SENIOR_QA_DEVELOPER", value: "Senior QA developer"},
            {name: "JUNIOR_DEVOPS_DEVELOPER", value: "Junior devops developer"},
            {name: "MIDDLE_DEVOPS_DEVELOPER", value: "Middle devops developer"},
            {name: "SENIOR_DEVOPS_DEVELOPER", value: "Senior devops developer"},
            {name: "HR", value: "HR"},
            {name: "PM", value: "PM"},
            {name: "ADMINISTRATION", value: "Administrator"},
            {name: "OFFICE_MANAGER", value: "Office manager"}
        ]
    },
    {
        name: "status",
        value: "Статус",
        enum: [
            {name: "WORKING", value: "Работает"},
            {name: "ON_HOLIDAY", value: "В отпуске"},
            {name: "SEEK_LEAVE", value: "На больничном"},
            {name: "NOT_WORKING", value: "Уволен"}
        ]
    }
];
export const ROLES = {
    JUNIOR_BACKEND_DEVELOPER: "DEVELOPER",
    MIDDLE_BACKEND_DEVELOPER: "DEVELOPER",
    SENIOR_BACKEND_DEVELOPER: "DEVELOPER",
    JUNIOR_FRONTEND_DEVELOPER: "DEVELOPER",
    MIDDLE_FRONTEND_DEVELOPER: "DEVELOPER",
    SENIOR_FRONTEND_DEVELOPER: "DEVELOPER",
    JUNIOR_QA_DEVELOPER: "DEVELOPER",
    MIDDLE_QA_DEVELOPER: "DEVELOPER",
    SENIOR_QA_DEVELOPER: "DEVELOPER",
    JUNIOR_DEVOPS_DEVELOPER: "DEVOPS_DEVELOPER",
    MIDDLE_DEVOPS_DEVELOPER: "DEVOPS_DEVELOPER",
    SENIOR_DEVOPS_DEVELOPER: "DEVOPS_DEVELOPER",
    HR: "HR",
    PM: "PROJECT_MANAGER",
    ADMINISTRATION: "ADMINISTRATOR",
    OFFICE_MANAGER: "OFFICE_MANAGER"
};

export const DOCUMENT_TYPES = [
    {
        name: "INCOME_STATEMENT",
        value: "Отчёт о доходах"
    },
    {
        name: "WORK_STATEMENT",
        value: "Отчёт об отработанных днях"
    }
];
