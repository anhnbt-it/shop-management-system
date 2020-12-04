DROP DATABASE `petshop_db`;
CREATE DATABASE `petshop_db`;
USE `petshop_db`;
-- Tao bang type
CREATE TABLE `type` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- Them du lieu vao bang type
INSERT INTO `type` (`name`) VALUES ('Dog'),('Cat');

-- Tao bang categories
CREATE TABLE `categories` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `type_id` int unsigned NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `categories_type_id_fk` (`type_id`),
  CONSTRAINT `categories_type_id_fk` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`)
);

-- Chen du lieu vao bang categories
INSERT INTO `categories` (`id`, `name`, `description`, `image`, `created_at`, `type_id`) VALUES (1,'Cho viet','Long vang mat den','/img/cho/husky/husky_1.jpg','2020-12-03 05:53:00',1),(2,'Cho Duc','Long Den, Mat xanh','','2020-12-03 05:53:33',1),(3,'Cho Nhat','Nho, Long xoan',NULL,'2020-12-03 05:53:48',1),(4,'Meo Nha','Long den, mat xanh',NULL,'2020-12-03 05:54:07',2),(5,'Meo Anh','Long xanh, mat tron',NULL,'2020-12-03 05:54:35',2),(6,'Meo Phap','Long do',NULL,'2020-12-03 05:54:52',2);

-- Tao bang products
CREATE TABLE `products` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int unsigned NOT NULL,
  `name` varchar(70) NOT NULL,
  `description` text NOT NULL,
  `quantityInStock` smallint NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `discount` decimal(10,2) NOT NULL,
  `img` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `categories_category_id_fk` (`category_id`),
  CONSTRAINT `categories_category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
);

-- Them du lieu
INSERT INTO `products` (`id`, `name`, `category_id`,`description`,`quantityInStock`, `price`, `discount`, `img`, `created_at`) VALUES (1,'HusKy',2,'Long den mat do',10,500.00,10.00,'/img/cho/husky/husky_1.jpg','2020-12-03 05:56:33'),(2,'Alaska',3,'Long vang tai cut',10,300.00,5.00,'/img/cho/husky/husky_2.jpg','2020-12-03 06:02:52'),(3,'Alusky',3,'Long vang tai cut',10,600.00,15.00,'/img/cho/husky/husky_3.jpg','2020-12-03 06:02:52'),(4,'Phu Quoc',1,'Long den',10,650.00,20.00,'/img/cho/husky/husky_4.jpg','2020-12-03 06:02:52'),(5,'Meo tam the',4,'Long vang tai cut',10,450.00,25.00,'/img/meo/Meo-1.jpg','2020-12-03 06:02:52'),(6,'Meo vua',4,'Long vang tai cut',10,500.00,15.00,'/img/meo/Meo-2.jpg','2020-12-03 06:02:52'),(7,'Meo to',5,'Long vang tai cut',10,200.00,10.00,'/img/meo/Meo-3.jpg','2020-12-03 06:02:52'),(8,'Meo beo',6,'Long vang tai cut',10,100.00,5.00,'/img/meo/Meo-4.jpg','2020-12-03 06:02:52');

-- Tao bang administrator
create table administrator
(
    id         int unsigned auto_increment
        primary key,
    username   varchar(45)                         not null,
    password   varchar(45)                         not null,
    roles      tinyint   default 2                 null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null
);
-- Chen du lieu vao bang administrator
INSERT INTO `administrator` (`username`, `password`, `roles`) VALUES ('admin', 'admin', 1);

-- Tao bang customers
create table customers
(
    id         int unsigned auto_increment
        primary key,
    name       varchar(50)                         not null,
    email      varchar(100)                        not null,
    password   varchar(50)                         not null,
    phone      varchar(50)                         not null,
    address    varchar(50)                         not null,
    gender     tinyint   default 1                 null,
    status     tinyint   default 1                 null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null,
    constraint email_unique
        unique (email)
);
-- Tao bang orders
CREATE TABLE `orders` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int unsigned NOT NULL,
  `orderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(15) DEFAULT 'pending',
  `comments` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `orders_customer_id_fk` (`customer_id`),
  CONSTRAINT `orders_customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
);
-- Tao bang orderdetails
CREATE TABLE `orderdetails` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,	
  `order_id` int unsigned NOT NULL,
  `product_id` int unsigned NOT NULL,
  `quantityOrdered` int NOT NULL,
  `priceEach` decimal(10,2) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `order_id`,`product_id`),
  KEY `orderdetails_order_id_fk` (`order_id`),
  KEY `orderdetails_product_id_fk` (`product_id`),
  CONSTRAINT `orderdetails_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `orderdetails_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
);