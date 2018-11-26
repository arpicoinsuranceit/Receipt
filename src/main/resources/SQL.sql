DROP TABLE IF EXISTS `inpromises`;
CREATE TABLE `inpromises` (
  `promid` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `duedat` datetime DEFAULT NULL,
  `loccod` varchar(255) DEFAULT NULL,
  `phnnum` varchar(255) DEFAULT NULL,
  `polnum` varchar(255) DEFAULT NULL,
  `prpnum` varchar(255) DEFAULT NULL,
  `sbucod` varchar(255) DEFAULT NULL,
  `setdat` datetime DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  PRIMARY KEY (`promid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;