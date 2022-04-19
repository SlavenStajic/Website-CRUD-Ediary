-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ediary2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ediary2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ediary2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ediary2` ;

-- -----------------------------------------------------
-- Table `ediary2`.`classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ediary2`.`classes` (
  `class_id` INT NOT NULL,
  `class_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`class_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ediary2`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ediary2`.`students` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `age` INT NULL DEFAULT NULL,
  `classes_class_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_students_classes1_idx` (`classes_class_id` ASC) VISIBLE,
  CONSTRAINT `fk_students_classes1`
    FOREIGN KEY (`classes_class_id`)
    REFERENCES `ediary2`.`classes` (`class_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ediary2`.`grades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ediary2`.`grades` (
  `id` INT NOT NULL,
  `English` VARCHAR(45) NULL DEFAULT NULL,
  `Math` VARCHAR(45) NULL DEFAULT NULL,
  `Physics` VARCHAR(45) NULL DEFAULT NULL,
  `Art` VARCHAR(45) NULL DEFAULT NULL,
  `Music` VARCHAR(45) NULL DEFAULT NULL,
  `students_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grades_students1_idx` (`students_id` ASC) VISIBLE,
  CONSTRAINT `FK42i6r4jskgiuvtlml65w7iq75`
    FOREIGN KEY (`students_id`)
    REFERENCES `ediary2`.`students` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ediary2`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ediary2`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ediary2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ediary2`.`user` (
  `user_id` INT NOT NULL,
  `userName` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `roles` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ediary2`.`parent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ediary2`.`parent` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `user_user_id` INT NOT NULL,
  `students_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_parent_user1_idx` (`user_user_id` ASC) VISIBLE,
  INDEX `fk_parent_students1_idx` (`students_id` ASC) VISIBLE,
  CONSTRAINT `fk_parent_students1`
    FOREIGN KEY (`students_id`)
    REFERENCES `ediary2`.`students` (`id`),
  CONSTRAINT `fk_parent_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `ediary2`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ediary2`.`teachers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ediary2`.`teachers` (
  `teacher_id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `user_user_id` INT NULL DEFAULT NULL,
  `classes_class_id` INT NOT NULL,
  PRIMARY KEY (`teacher_id`),
  INDEX `fk_teachers_user_idx` (`user_user_id` ASC) VISIBLE,
  INDEX `fk_teachers_classes1_idx` (`classes_class_id` ASC) VISIBLE,
  CONSTRAINT `fk_teachers_classes1`
    FOREIGN KEY (`classes_class_id`)
    REFERENCES `ediary2`.`classes` (`class_id`),
  CONSTRAINT `fk_teachers_user`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `ediary2`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
