create table if not exists Patrons (
    id IDENTITY UNIQUE NOT NULL,
    profileMedia varchar(36940),
    firstname varchar(20) NOT NULL,
    lastName varchar(20) NOT NULL,
    otherNames varchar(20),
    country varchar(50) NOT NULL,
    state varchar(50) NOT NULL,
    lga varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    sex varchar(20) NOT NULL,
    cateringFor TINYINT NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    password varchar(50) NOT NULL,
    createdAt timestamp NOT NULL
);

create table if not exists Budgets (
    id IDENTITY UNIQUE NOT NULL,
    monthh varchar(50),
    income varchar(50),
    balance varchar(50),
    patronId Long,
    createdAt timestamp NOT NULL
);

create table if not exists Expenses (
    id IDENTITY UNIQUE NOT NULL,
    title varchar(50) NOT NULL,
    type varchar(10) NOT NULL,
    amount varchar(50),
    balance varchar(50),
    description varchar(255),
    budgetId Long,
    patronId Long,
    createdAt timestamp NOT NULL
)