# Employee-CSV-Migration

#### Overall Goals

* To read data from a CSV file, parse it, populate objects, and add to a collection.
* To efficiently write the data from the objects to a relational database using JDBC.
* To demonstrate good programming practices.

#### Implemented

- [x] Reading data from CSV File
- [x] Storing data from CSV File in a collection of Employees
- [x] Successfully migrated data to a relational database
- [x] Validation checks
- [x] SOLID Principles
- [x] Cleaned the data from duplicates
- [x] Provided new unique employeeID's to previous duplicated data and merged together as one after cleansing
- [x] Carried out multithreading using ExecutorService
- [x] Exception Handling
- [x] Observations on multithreading
- [x] Usage of lambdas


#### Observations

Using Multithreading  to insert data from EmployeeRecordsLarge.csv to a relational database speeds up the 
parallelizable part of the program, and can be executed faster by throwing more hardware at it, but the number of threads
should be limited to an optimal number. Too many threads for a program when not needed will actually take longer to complete,
because time is wasted switching between them.

In EmployeeRecords.csv I was able to discover 57 duplicates as a result of EmployeeID not being unique. These 57 duplicates
were then given a new unique EmployeeID based on the last EmployeeID in the data +1 and merged together under one table.



