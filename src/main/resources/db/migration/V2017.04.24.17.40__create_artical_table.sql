CREATE TABLE `ARTICAL` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100),
  `content` text,
  `publish_time` datetime DEFAULT NULL,
  `student_id` bigint(20) NOT NULL,
  CONSTRAINT pk_artical_id PRIMARY KEY (`id`),
  CONSTRAINT fk_artical_id FOREIGN KEY (`student_id`) REFERENCES STUDENT(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;