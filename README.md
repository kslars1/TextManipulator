# TextManipulator
Kris &amp; Joel project 1

## SQL Server JDBC Driver Setup

This project uses the Microsoft SQL Server JDBC driver.

1. Download `mssql-jdbc` from Microsoft:
https://learn.microsoft.com/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server
2. Create a `lib` folder in the project root.
3. Copy the JDBC jar (for example `mssql-jdbc-12.8.1.jre11.jar`) into `lib`.
4. Compile with classpath:

```powershell
javac -cp ".;lib/*" CodeSnippets.java TextManipulator.java
```

5. Run with classpath:

```powershell
java -cp ".;lib/*" CodeSnippets
```
