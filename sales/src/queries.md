###### **Create database**

_create database sales;_

###### **Setup a user and password**

_CREATE USER 'sales-manager'@'localhost' IDENTIFIED BY '@ManagedS4les';_

_GRANT ALL PRIVILEGES ON sales.* TO 'sales-manager'@'localhost';_

###### **Showing all the users**

_select user from mysql.users;_

###### **create sales table**

```
CREATE TABLE IF NOT EXISTS `sales` (
  `ID` bigint(25) AUTO_INCREMENT PRIMARY KEY,
  `CUSTOMER_ID` varchar(200) NOT NULL,
  `DATE` bigint(25) NOT NULL
  ) ENGINE=INNODB;
```

###### **create sales lines table**

```sql
CREATE TABLE IF NOT EXISTS `saleslines` (
  `ID` bigint(25) AUTO_INCREMENT PRIMARY KEY,
  `SALES_ID` bigint(25) NOT NULL,
  `PRODUCT_ID` INT NOT NULL,
  `AMOUNT` INT NOT NULL
  ) ENGINE=INNODB;
```

Add foreign key for sales

```sql
ALTER TABLE saleslines
ADD FOREIGN KEY (sales_id) REFERENCES sales(id);
```