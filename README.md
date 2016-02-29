![SensorApp.png](https://bitbucket.org/repo/4G68rp/images/1240670306-SensorApp.png)

# README #

## What is this repository for? ##

* This is a repository for a simple weather station implemented using threads and created for the object oriented programming course at the Faculty of Engineering and Technology of the the University of Buea.
* Program has been tested using Ubuntu 15.04 and Windows 8.1 both running Java 8 and PostgreSQL 9.4
* Version 1.0

## How do I get set up? ##

* Please install java and postgreSQL. 
Once postgres is installed and psql is in your class path just run the following command: 
      
***Windows*** 
               
 *   *psql -U postgres -f simpleweatherstation\backup.sql*
                  
*** linux***

 *   *psql -U postgres -f simpleweatherstation/backup.sql*
                  
It is assumed that postgres is your a role in your DBMS which can create roles and databases and tables and sequences. 

2. With postgres set you are now ready to run the program in your favorite IDE.