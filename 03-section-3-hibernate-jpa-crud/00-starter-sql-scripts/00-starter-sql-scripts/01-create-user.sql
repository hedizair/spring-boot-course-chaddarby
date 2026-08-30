DROP USER if exists 'springstudent'@'%' ;
CREATE USER 'springstudent'@'%' IDENTIFIED BY 'springstudent';
GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'%';