###### **Create database**

_create database customers;_

###### **Setup a user and password**

_CREATE USER 'customer-manager'@'localhost' IDENTIFIED BY 'M!Cust0mers';_

_GRANT ALL PRIVILEGES ON customers.* TO 'customer-manager'@'localhost';_

###### **Create customer table**

```
CREATE TABLE IF NOT EXISTS `customers` (
  `ID` INT AUTO_INCREMENT PRIMARY KEY,
  `NAME` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `LOYALTY_AMOUNT` INT DEFAULT NULL
) ENGINE=INNODB;
```
