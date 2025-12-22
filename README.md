# EMS_FSD_Java
A full-stack Employee Management System (EMS) built with Spring Boot (Java) on the backend and Thymeleaf for rendering dynamic HTML views on the frontend. The system allows organizations to manage employee records such as personal details, job roles, salary, and performance evaluations.

<br>
1. create the database

drop database Ems_project;
create database Ems_project;

2. Run the Java Application and then run this sql commands
   
use Ems_project;
show tables;
insert into user(username,password) values('tejas08','123');
select * from user;
