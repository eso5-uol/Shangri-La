Drop table if exists Admin;

CREATE TABLE `Admin` (
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `passwordHash` longtext COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `Admin` VALUES
('admin','5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5','admin@lookout.com', 'john smith'),
('tester','36BBE50ED96841D10443BCB670D6554F0A34B761BE67EC9C4A8AD2C0C44CA42C', 'tester@lookout.com', 'trevor lawrence');

Drop table if exists HomeTestKit;
CREATE TABLE `HomeTestKit` (
`ttn` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
`used` boolean COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `HomeTestKit` VALUES ('34GC829B','1'),('4F7YKH9G','0'),('57UBS5J6','0'),('8RL4ENTK','0'),('CB8FBCCM','0'),('CCZTQ8KW','0'),('FEQQ6UUG','0'),('MM2874Z6','0'),('R9KZ2NXL','0'),('YBQUVXHL','0');

Drop table if exists TestResult;

CREATE TABLE `TestResult` (
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` longtext COLLATE utf8_unicode_ci,
  `postcode` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ttn` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `testresult` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `TestResult` VALUES ('abc@le.ac.uk','John Smith',30,'M','University Road, Leicester','LE1 7RH',NULL,'Negative');
