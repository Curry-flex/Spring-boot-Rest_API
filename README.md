# Spring-boot-Rest_API




Spring boot REST API

This project is for demostration of spring boot restufl API CRUD employee

Requirements

1.Eclipse
2.Mysql

create database name ems on mysql workbench

got to src->main->resourecs->application modify depend on your database configuration username and password

spring.datasource.url=jdbc:mysql://localhost:3307/ems?useSSL=false
spring.datasource.username=root
spring.datasource.password="your username"

#Hibernate properties for sql queries for mysql

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

#create automatic table to database
spring.jpa.hibernate.ddl-auto=update
