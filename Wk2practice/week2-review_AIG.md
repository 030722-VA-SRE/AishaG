# SQL

### Relations

* Explain what SQL is. What are some SQL databases?
Structured Query Langauge, it provides us a way to interact with data entities
* How is data structured in a SQL database?
Structured based on schemas. Which are the guidelines that determine how the collections of tables, views, triggers, and functions interact in your database. 
* What is an ERD? How is it useful?
An entity relationship diagram, is a type of flowchart that illustrates how entities(people, objects, concepts) relate to each other in a system. ER Diagrams are most often used to design or debug relational databases. 
https://database.guide/wp-content/uploads/2016/06/sakila_full_database_schema_diagram.png
https://www.lucidchart.com/pages/er-diagrams
* What are the different multiplicity relationships? How would you create these relations?
one-to-one: A relationship like passport identification to citizen
row references another row

one-to-many/ many-to-one: Relationship like Naruto villages, each ninja has a village, many ninja call a village home, but one village can have many ninja.
one row in a table is referenced by potentially many rows in another table

many-to-many: The relationship of movie theater chains to current movie showings.
junction table(table that has a primary key which is a composite key of foreign key)
several rows can be referenced by several rows in another table.

* What kind of relationship would exist between Students and Classes? Students and Textbooks?
Many-many, many to one
* Explain the concept of referential integrity
The practice that a database will always keep itself from performing outside of constraints or executing a function that 

* What is a cascade delete?
a delete function that is able to delete a record and any records referencing it as well

* List the integrity constraints
Domain Integrity: set of rules that restricts the kind of attributes or values a column or relation can hold in the database table. For example, we can specify if a particular column can hold null values or not, if the values have to be unique or not,

Entity Integrity:used to ensure the uniqueness of each record or row in the data table. There are primarily two types of integrity constraints that help us in ensuring the uniqueness of each row, namely, UNIQUE constraint and PRIMARY KEY constraint.

Referential Integrity:ensures that there always exists a valid relationship between two tables.This makes sure that if a foreign key exists in a table relationship then it should always reference a corresponding value in the second table or it should be null.

Key Constraints:ensure that an entity or record is uniquely or differently identified in the database. There can be more than one key in the table but it can have only one primary key.
Main key constraints:
Primary Key Constraint
Foreign Key Constraint
Unique Key Constraint

* Define the word "schema"
    a representation of a plan or theory in the form of an outline or model(general)
    A schema is a collection of logical structures of data, or schema objects. A schema is owned by a database user and has the same name as that user. Each user owns a single schema. Schema objects can be created and manipulated with SQL.(Data Science definition)

* What is a candidate key? What about a surrogate key?
A candidate key is the key which contains no redundant attribute, it is a subset or part of the Super key. attribute holding a candidate key can never have values that are null.
To implement candidate key in MySQL, set more than one column as unique key. These keys would qualify for candidate key as in the below syntax 

Surrogate Key:
 synthetic primary key, is generated  when a new record is inserted into a table automatically by a database that can be declared as the primary key of that table . It is the sequential number outside of the database that is made available to the user and the application or it acts as an object that is present in the database but is not visible to the user or application.

 we do not have a natural primary key in a table, then we need to artificially create one in order to uniquely identify a row in the table , this key is called the surrogate key or synthetic primary key of the table. However , surrogate key is not always the primary key 

### Sublanguages & Queries
    
* What are the 5 sublanguages of SQL? List some commands for each
    DML(Data Manipulation Langauge): 
    Insert: insert data into a table.
    Update: update existing data within a table.
    Delete: delete records from a database table.
    DDL(Data Definition Language): 
    Create: This command is used to create the database or its objects (like table, index, function, views, store procedure, and triggers)
    Drop:This command is used to delete objects from the database.
    Alter:This is used to alter the structure of the database.
    Truncate: This is used to remove all records from a table, including all spaces allocated for the records are removed.
    Rename: This is used to rename an object existing in the database.
    DQL(Data Query Language): 
        Select(used to select the attribute based on the condition described by WHERE clause.)
    DC(Data Control Language):
     Grant: gives users access privileges to the database. 
     Revoke withdraws the user’s access privileges given by using the GRANT command.
    TCL(Transaction Control Language)-deal with the transaction within the database.: 
    Commit: Commits a Transaction.
    Rollback: Rollbacks a transaction in case of any error occurs.
    Savepoint:Sets a savepoint within a transaction.
    Set Transaction: Specify characteristics for the transaction.

* What is the difference between DELETE, DROP, and TRUNCATE commands?
    delete a single record, delete multiple records or tables, remove all records
* What are some SQL clauses you can use with SELECT statements?
   select * from (table_name)
   select id from (table_name)


* What is the difference between joins and set operators?
Joins are queries that can return data from two or more tables.

Set operators are operators used to combine multiple queries intead of tables. 
* What are the types of joins? Explain the differences.
 Inner Join(Join) - a join of two or more tbales that returns ONLY the rows that satisfy the condition
 
 Left Join- LEFT [OUTER] JOIN
  Performs an outer join of two tables, returning all the rows from the left or "first" table queried

 Right Join- RIGHT [OUTER] JOIN 
 Performs an outer join of two tables, returning all the rows from the right or "second" table queried

 Full Join FULL [OUTER] JOIN
Performs an outer join of two tables, returning all the rows from the left and right tables
* Explain the difference between UNION, UNION ALL, and INTERSECT

### Transactions

* What are the properties a transaction must follow?
 ACID 
 Atomicity
 one unit, transaction should finish to completion or not at all.

 Consistency
 DB should be in a valid state before and after a transaction, in regards to the constraints and business logic

 Isolation
 a transaction should not interfere with another transaction
 -if transactions execute concurrently they should be the same as if they occurred in order

 Durability
 Commits are final, changes should be persisted to the database

* Explain the different isolation levels. What read phenomena do each prevent?

Read uncommitted
-transactions are able to read/interact with uncommitted data 
read committed
-only able to interact with committed data
repeatable read
You can only see data that has been committed before the transaction has begun(
serializable
-strictest level of isolation making transactions happen one after another
### Practicals

Given the following table 'employees'...

| id | firstName | lastName | salary | dept |
| --- | -------- | -------- | ------ | ---- |
| 1  | Michael   | Scott    | 65     | Sales|
| 2  | Dwight    | Schrute  | 75     | Sales|
| 3  | Toby      | Flenderson| 80    | HR  |
| 4  | Jim       | Halpert  | 90     | Sales|
| 5  | Oscar     | Martinez | 90     | Accounting |
| 6  | Angela    | Martin   | 75     | Accounting |
| 7  | Kevin     | Malone   | 70     | Accounting |
| 8  | Holly     | Flax     | 60     | HR |
| 9  | Creed     | Branton  | 70     | Quality Assurance |

* Write a query to find all data in the table

    select * from employees;

* Write a query to find employees with a salary over 75

    select * from employees where salary > 75

* Write a query to find employees whose first name contains an 'e' or whose last name begins with 'S'\

    select * from employees where firstname like '%e%'
    union all 
    select * from employees where lastName like 'S%';

* Write a query to find the first name of all employees who do not work in accounting

    select firstname from employees where dept != 'Accounting';

* Write a query to find the average salary of all employees whose last names begin with 'M'

    select avg(salary) from employees where lastname like 'M%';

* Write a query to find the highest paid salesperson

select firstname, 
max(salary)
from employees where dept = 'Sales'
group by firstname, salary 
order by salary desc;

* Write a query to combine the resultsets of any two previous queries

    select * from employees where firstname like '%e%'
    union all 
    select * from employees where lastName like 'S%';

* Remove all members of accounting from the database

delete from employees where dept = 'Accounting';

* Given removing the dept column from the employees table and creating a table 'department' and linking the two via a foreign key relationship

| dept_id | name |
| ------- | ---  |
| 1       | Sales |
| 2       | HR   |
| 3       | Accounting |
| 4       | Customer Service |

* Write a query to find the salary of the lowest paid salesperson (HINT: use a join)

select firstname, 
max(salary)
from employees where dept_id = 1
group by firstname, salary 
order by salary;


* Write a query to find the average salary of each department

select avg(salary) from employees group by dept;

* Write a query to find all possible combinations of employees and departments. How many records do you expect?

Select * from Employees
Cross Join 
department;

36

* Change the name of department 4 to 'Quality Assurance'

    Update departments
    set name = 'Quality Assurance'
    where name = 'Custome Service';


* Remove both tables
drop table employees
drop table departments

# JDBC
1.	What is JDBC?
Java Database Conectivity, Java API to connect and execute the query with the database.
can use JDBC API to access tabular data stored in any relational database
help of JDBC API, we can save, update, delete and fetch data from the database.
popular interfaces of JDBC API:
Driver interface

2.	What are the core interfaces / classes in JDBC?

Connection interface:
a session between a Java application and a database. It helps to establish a connection with the database.
 
 Connection interface is a factory of Statement, PreparedStatement, and DatabaseMetaData, i.e.
 , an object of Connection can be used to get the object of Statement and DatabaseMetaData.

Statement interface
provides methods to execute queries with the database.

PreparedStatement interface
a subinterface of Statement. It is used to execute parameterized query.
value will be set by calling the setter methods of PreparedStatement.
CallableStatement interface
Improves performance: The performance of the application will be faster if you use PreparedStatement interface because query is compiled only once.

ResultSet interface:
object of ResultSet maintains a cursor pointing to a row of a table.
can make this object to move forward and backward direction by passing either TYPE_SCROLL_INSENSITIVE or TYPE_SCROLL_SENSITIVE in createStatement(int,int)

ResultSetMetaData interface
 metadata means data about data i.e. we can get further information from the data.
 ResultSetMetaData interface is useful because it provides methods to get metadata from the ResultSet object.
 public String getColumnTypeName(int index)

DatabaseMetaData interface
DatabaseMetaData interface provides methods to get meta data of a database such as database product name, database product version, driver name, name of total number of tables, name of total number of views etc.

RowSet interface
 - it returns the column type name for the specified index.
popular classes of JDBC API:

DriverManager class -
acts as an interface between users and drivers. It keeps track of the drivers that are available and handles establishing a connection between a database and the appropriate driver.
*before interacting with a Database, it is a mandatory process to register the driver; otherwise, an exception is thrown.

Blob class

Clob class

Types class


3.	What is a stored procedure and how would you call it in Java?
group of one or more pre-compiled SQL statements into a logical unit. It is stored as an object inside the database server.
procedure in SQL Server always contains a name, parameter lists, and Transact-SQL statements
to call the stored procedure, you can call execute, executeQuery, or executeUpdate depending on how many ResultSet objects the procedure returns. However, if you are not sure how many ResultSet objects the procedure returns, call execute.

4.	What is the difference between Statement and PreparedStatement?

5.	Steps to executing an SQL query using JDBC?

# AWS

### Cloud / AWS Overview
* How would you describe AWS? What is "the cloud" or "cloud computing" and why is it so popular now?
AWS is a cloud computing service, this means that it offers solutions for people who are looking to utilize some level of database without having to physically purchase or acquire servers. Instead they can use Amazon's(which are offered at cheaper rates) at one of the 3 levels of Cloud Computing model(Iaas, Paas, and Saas)

* Define Infrastructure, Platform, and Software as a Service
 Iaas(Infrasructure as a service)
-At this level, users need to provide application and manage the application, data, runtime environment, middleware and O/S.
 Paas(Platform as a service)
service provides Runtime environment, middleware, O/s, virtualization, server, storage, and network. Users provide data and application
 Saas(Software as a service)
The service provides all of the components to the user

* What's the difference between a Region and an Availability Zone (AZ)?
 Regions are how groups of Availability Zones are organized, Availability Zones are the individual data centers that have infrastructure for supporting AWS services.

* How are you charged for using AWS services? Does it vary by service?
 You are charged on a pay-as-you-go model, based on the needs of your business. However, this is based on the components of the service(i.e the type of instance for Amazon EC2) and the service(there are a large number of them so pricing is based on how many services you add and the configuration of your usage of those services)


* Different ways to interact with AWS services?
RDS- Database hosting through AWS
EC2- Cloud-based virtual machine 
AMI- template for software cofiguration for each of your EC2 instances
EBS- A virtual hard drive that can be accessed by your instances
Security Groups- Able to set up firewalls and traffic filters to determine accessibility. 


### EC2

* What are the configuration options for EC2?
 AWS Management Console: a web based UI
* What are the different EC2 instance sizes/types?
 Interface types: t4g, t2, M6g, M5, A1, C4, c4.large

* Once you create an EC2, how to connect to it?
 
* What are Security Groups? When defining a rule for a security group, what 3 things do you need to specify?

Security groups are virtual firewalls for your EC2 that control traffic in and out
To specify your security group you need: port range, icmp type and code, and a source/destination(IPV4)
  
* What's the difference between scalability, elasticity, and resiliency?
Scalibility- 	Scalability(pay as you go, not paying for more than you need)
		ex: crashing from user load, similar to Revature Pro last Monday where the site received too many visitors, you scale it up as demand increased
Elasticity- Being able to anticipate or respond dynamically to increased or decreased demands
        ex: Once you know more about your needs increasing or 
Resiliency-
 
* Ways of paying for EC2?
-On demand, savings plans

### RDS

* What's an RDS?
A relational database organizes data into tables which can be linked by common data to each other. This means you can access new tables from data in one or more tables with a single query command.
* Which vendors are supported?
MySql, PostgreSQL, Oracle, Amazon

# UNIX/LINUX

* What are the differences between a Thread and Process and a Service?

* Write a basic bash script 
    * ie: installing java/maven/git to an ec2
    * if you're feeling ambitious, a script that would delete a file if it exists

#!/bin/bash

ssh -i [file.pem] ec2-user@[address]
sudo yum update -y
sudo yum install git
sudo yum install java-1.8.0-openjdk-devel -y

sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven

\*
read -p 'filename'
if [-f $filename ]; then
rm -i "$filename"
if [ -f $filename]; then
    echo "$filename is not removed"
else
    echo "$filename is removed"
fi
else
    echo "File does not exist"
fi
*/
