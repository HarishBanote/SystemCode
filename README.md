# SystemCode
Leave Management System
=======================

Submited by -  Harish Banote 
Contact on harishbanote96@gmail.com

Steps for Installation
==================

Software Requiremnets
-------------------------------

1. Java V.8 (Build 1.8)

2. Eclipse Neon.3 configured with SWT Designer(WindowBuilder).
SWT Designer is a visual editor used to create graphical user interfaces.

3. MySQL Community Server (GPL)  V 5.5.18
Hear create a database named as 'leave_sys'. inside database create two tables 'employees' and 'leaves'.

Structure of both the tables are,

employees
---------------

+-----------	+-----------------+------+-----------+---------+----------+
| Field  	| Type            | Null   | Key 	| Default | Extra |
+-----------	+-----------------+------+--------------+---------+-------+
| empid  	| int(11)         | NO   | PRI 	| NULL	|          |
| name  	| varchar(60) | YES  |    	| NULL    	|          |
| uname  | varchar(20) | YES  |     	| NULL    	|          |
| pass   	| varchar(20) | YES  |    	| NULL    	|          |
| gender 	| varchar(20) | YES  |    	| NULL    	|          |
| type   	| varchar(20) | YES  |     	| NULL    	|          |
| leaves 	| int(11)         | YES  |     	| NULL    	|          |
+-----------	+----------------+-------+----------	+-----------+--------+

leaves
---------

+--------------+-----------------+------+-------+----------------+------------------------+
| Field         | Type             | Null | Key | Default        | Extra                     |
+--------------+-----------------+------+-------+----------------+-------------------------+
| id       	   | int(11)          | YES  |        | NULL           |                               |
| count       | int(11)          | NO   | PRI  | NULL           | auto_increment    |
| name       | varchar(60) | YES  |         | NULL           |                	      |
| fromdate | date             | YES  |         | 0000-00-00 |                               |
| todate      | date             | YES  |         | 0000-00-00 |                               |
| reason     | varchar(60)  | YES  |         | NULL           |                               | 
| status      | varchar(20)  | YES  |         | NULL            |                              |
+-------------+------------------+------+-------+-----------------+------------------------+

4. After creation of databases and tables import the project file into the workspace of eclipse.

5. Nevigate to the src -> com.harish.main -> LoginPage.java and run it as application.
(If any error occure during runtime please import 'swing2swt.jar' and 'mysql-connector.jar' file into the build path which is present in project folder)
