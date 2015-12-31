-- Disciplina: Laboratório de programação 2
-- Professor: Lossurdo
-- Entrega 02: Restaurante
-- Aluno: Fabiano da Rosa Gomes<gomes.fdr@gmail.com>

-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'FUNCIONARIO'
-- 
-- ---

DROP TABLE IF EXISTS `FUNCIONARIO`;
		
CREATE TABLE `FUNCIONARIO` (
  `id` INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `perfil` INTEGER NOT NULL,
  `salario` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'MESA'
-- 
-- ---

DROP TABLE IF EXISTS `MESA`;
		
CREATE TABLE `MESA` (
  `id` INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  `capacidade` INTEGER NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'PRODUTO'
-- 
-- ---

DROP TABLE IF EXISTS `PRODUTO`;
		
CREATE TABLE `PRODUTO` (
  `id` INTEGER NULL AUTO_INCREMENT,
  `descricao` VARCHAR(30) NOT NULL,
  `tipo` INTEGER NOT NULL,
  `preco` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'PEDIDO'
-- 
-- ---

DROP TABLE IF EXISTS `PEDIDO`;
		
CREATE TABLE `PEDIDO` (
  `id` INTEGER NULL AUTO_INCREMENT,
  `dt_pedido` DATE NOT NULL,
  `mesa` INTEGER NOT NULL,
  `garcom` INTEGER NOT NULL,
  `aberto` INTEGER NOT NULL,  
  `total_pedido` DECIMAL(9,2) NOT NULL,  
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'PEDIDO_PRODUTO'
-- 
-- ---

DROP TABLE IF EXISTS `PEDIDO_PRODUTO`;
		
CREATE TABLE `PEDIDO_PRODUTO` (
  `id` INTEGER NULL AUTO_INCREMENT,
  `pedido` INTEGER NOT NULL,
  `produto` INTEGER NOT NULL,
  `quantidade` INTEGER NOT NULL,  
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'TIPO_PRODUTO'
-- 
-- ---



-- ---
-- Foreign Keys 
-- ---

ALTER TABLE `PEDIDO` ADD FOREIGN KEY (mesa) REFERENCES `MESA` (`id`);
ALTER TABLE `PEDIDO` ADD FOREIGN KEY (garcom) REFERENCES `FUNCIONARIO` (`id`);
ALTER TABLE `PEDIDO_PRODUTO` ADD FOREIGN KEY (pedido) REFERENCES `PEDIDO` (`id`);
ALTER TABLE `PEDIDO_PRODUTO` ADD FOREIGN KEY (produto) REFERENCES `PRODUTO` (`id`);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE `FUNCIONARIO` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `MESA` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `PRODUTO` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `PEDIDO` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `PEDIDO_PRODUTO` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `TIPO_PRODUTO` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `FUNCIONARIO` (`cpf`,`nome`,`perfil`,`salario`) VALUES
-- ('','','','');
-- INSERT INTO `MESA` (`id`,`capacidade`) VALUES
-- ('','');
-- INSERT INTO `PRODUTO` (`id`,`descricao`,`tipo`) VALUES
-- ('','','');
-- INSERT INTO `PEDIDO` (`id`,`dt_pedido`,`mesa`,`garcom`,`aberto`,`parcial_pedido`,`total_pedido`,`paga_comissao`,`comissao`) VALUES
-- ('','','','','','','','','');
-- INSERT INTO `PEDIDO_PRODUTO` (`pedido`,`produto`,`quantidade`,`total`) VALUES
-- ('','','','');
-- INSERT INTO `TIPO_PRODUTO` (`id`,`descricao`) VALUES
-- ('','');

