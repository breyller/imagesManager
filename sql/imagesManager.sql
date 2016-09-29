
CREATE TABLE IF NOT EXISTS `album` (
  `idAlbum` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(150) NULL COMMENT '',
  `description` VARCHAR(255) NULL COMMENT '',
  PRIMARY KEY (`idAlbum`)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `fotos` (
  `idFotos` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(150) NULL COMMENT '',
  `description` VARCHAR(255) NULL COMMENT '',
  `path` VARCHAR(300) NULL COMMENT '',
  `hash` VARCHAR(50) NULL COMMENT '',
  PRIMARY KEY (`idFotos`)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `album_fotos` (
  `idalbum_fotos` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `id_album` INT NULL COMMENT '',
  `id_foto` INT NULL COMMENT '',
  `order` INT NULL COMMENT '',
  PRIMARY KEY (`idalbum_fotos`)  COMMENT '',
  INDEX `fk_id_foto_idx` (`id_foto` ASC)  COMMENT '',
  INDEX `fk_id_albuns_idx` (`id_album` ASC)  COMMENT '',
  CONSTRAINT `fk_id_foto`
    FOREIGN KEY (`id_foto`)
    REFERENCES `fotos` (`idFotos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_albuns`
    FOREIGN KEY (`id_album`)
    REFERENCES `album` (`idAlbum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
