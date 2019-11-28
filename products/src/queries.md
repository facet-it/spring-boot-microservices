###### **Create database**

_create database products;_

###### **Setup a user and password**

_CREATE USER 'product-manager'@'localhost' IDENTIFIED BY 'Pr0ducts4us';_

_GRANT ALL PRIVILEGES ON products.* TO 'product-manager'@'localhost';_

###### **Showing all the users**

_select user from mysql.users;_

###### **Product table**

```
CREATE TABLE IF NOT EXISTS `products` (
  `ID` INT AUTO_INCREMENT PRIMARY KEY,
  `PLU` varchar(25) NOT NULL,
  `NAME` varchar(200) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `PRICE` double DEFAULT NULL
  ) ENGINE=INNODB;
```

**Product categories table**

```$sql
CREATE TABLE IF NOT EXISTS `productcategories` (
  `ID` INT AUTO_INCREMENT PRIMARY KEY,
  `NAME` varchar(100) DEFAULT NULL,
  `DESCRIPTION` varchar(150) DEFAULT NULL,
  `COMPANY_ID` int(11) NOT NULL,
  `SEQUENCE` int(11) DEFAULT NULL
  ) ENGINE=INNODB
```

###### **Adding a table**

```$sql
ALTER TABLE products
ADD COLUMN loyalty_points INT;
```