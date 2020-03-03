CREATE USER 'manager'@'localhost' IDENTIFIED BY '



```sql
CREATE TABLE `events` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `time` BIGINT NOT NULL,
   `userId` int(11) NOT NULL,
   `companyId` int(11) NOT NULL,
   `username` varchar(50) DEFAULT NULL,
   `userUuid` varchar(50) DEFAULT NULL,
   `userType` varchar(20) DEFAULT NULL,
   `companyType` varchar(50) DEFAULT NULL,
   `companyCity` varchar(50) DEFAULT NULL,
   `companyCountry` varchar(50) DEFAULT NULL,
   `companyName` varchar(50) DEFAULT NULL,
   `endpoint` varchar(200) DEFAULT NULL,
   `arguments` text,
   PRIMARY KEY (`id`));
```

The table for endpoint usage per company

```sql
CREATE TABLE `enpoint_usage_snapshot` (
  `time` bigint(20) NOT NULL,
  `aggregate` mediumtext NOT NULL,
  PRIMARY KEY (`time`)
)
```