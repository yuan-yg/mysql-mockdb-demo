# mysql-mockdb-demo
Toy app to use wix-embedded-mysql in junit test

This is a toy application to play with the spring configurations to use wix-embedded-mysql in the unit tests.

when one runs <code>mvn test</code>, wix-embedded-mysql will first check whether /home/{user}/.embedmysql/ folder contains the specified mysql version. If not, it would download one and extract there.

After the embedded mysql is in place, it would go ahead and run all unit tests, and it would shutdown the database when it is done.

The actual database file is created under <code>/target</code>, so mvn clean can clear it out.