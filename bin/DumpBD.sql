-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: sistematcc
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `correcoesusuario`
--

DROP TABLE IF EXISTS `correcoesusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `correcoesusuario` (
  `correcaoId` int NOT NULL AUTO_INCREMENT,
  `usuarioId` int DEFAULT NULL,
  `turmaId` int DEFAULT NULL,
  `testeId` int DEFAULT NULL,
  `perguntaTesteId` int DEFAULT NULL,
  `opcaoRespostaId` int DEFAULT NULL,
  `acertou` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`correcaoId`),
  KEY `usuarioId` (`usuarioId`),
  KEY `turmaId` (`turmaId`),
  KEY `testeId` (`testeId`),
  KEY `perguntaTesteId` (`perguntaTesteId`),
  KEY `opcaoRespostaId` (`opcaoRespostaId`),
  CONSTRAINT `correcoesusuario_ibfk_1` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`usuarioId`) ON UPDATE CASCADE,
  CONSTRAINT `correcoesusuario_ibfk_2` FOREIGN KEY (`turmaId`) REFERENCES `turma` (`turmaId`) ON UPDATE CASCADE,
  CONSTRAINT `correcoesusuario_ibfk_3` FOREIGN KEY (`testeId`) REFERENCES `teste` (`testeId`) ON UPDATE CASCADE,
  CONSTRAINT `correcoesusuario_ibfk_4` FOREIGN KEY (`perguntaTesteId`) REFERENCES `perguntateste` (`perguntaTesteId`) ,
  CONSTRAINT `correcoesusuario_ibfk_5` FOREIGN KEY (`opcaoRespostaId`) REFERENCES `opcaoresposta` (`opcaoRespostaId`) 
) ENGINE=InnoDB AUTO_INCREMENT=723 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `correcoesusuario`
--

LOCK TABLES `correcoesusuario` WRITE;
/*!40000 ALTER TABLE `correcoesusuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `correcoesusuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habilidade`
--

DROP TABLE IF EXISTS `habilidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habilidade` (
  `habilidadeId` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`habilidadeId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habilidade`
--

LOCK TABLES `habilidade` WRITE;
/*!40000 ALTER TABLE `habilidade` DISABLE KEYS */;
INSERT INTO `habilidade` VALUES (1,'Compreensao'),(2,'Abstracao'),(3,'Resolucao de problemas'),(4,'Resolucao algoritmica'),(5,'Avaliacao');
/*!40000 ALTER TABLE `habilidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habilidade_perguntateste`
--

DROP TABLE IF EXISTS `habilidade_perguntateste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habilidade_perguntateste` (
  `habilidadeId` int DEFAULT NULL,
  `perguntaTesteId` int DEFAULT NULL,
  KEY `habilidadeId` (`habilidadeId`),
  KEY `perguntaTesteId` (`perguntaTesteId`),
  CONSTRAINT `habilidade_perguntateste_ibfk_1` FOREIGN KEY (`habilidadeId`) REFERENCES `habilidade` (`habilidadeId`)  ON UPDATE CASCADE,
  CONSTRAINT `habilidade_perguntateste_ibfk_2` FOREIGN KEY (`perguntaTesteId`) REFERENCES `perguntateste` (`perguntaTesteId`)  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habilidade_perguntateste`
--

LOCK TABLES `habilidade_perguntateste` WRITE;
/*!40000 ALTER TABLE `habilidade_perguntateste` DISABLE KEYS */;
INSERT INTO `habilidade_perguntateste` VALUES (3,1),(2,2),(3,2),(1,3),(3,3),(4,3),(1,4),(3,4),(4,4),(5,4),(1,5),(3,5),(4,5),(5,5),(1,6),(2,6),(3,6),(1,7),(2,7),(3,7),(2,8),(3,8),(5,8),(3,9),(4,9),(5,9),(1,14),(2,14),(1,15),(2,15),(3,15),(1,16),(2,16),(3,16),(4,16),(5,16),(1,17),(2,17),(4,17),(1,18),(4,18),(5,18);
/*!40000 ALTER TABLE `habilidade_perguntateste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habilidade_usuario`
--

DROP TABLE IF EXISTS `habilidade_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habilidade_usuario` (
  `usuarioId` int DEFAULT NULL,
  `habilidadeId` int DEFAULT NULL,
  KEY `usuarioId` (`usuarioId`),
  KEY `habilidadeId` (`habilidadeId`),
  CONSTRAINT `habilidade_usuario_ibfk_1` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`usuarioId`)  ON UPDATE CASCADE,
  CONSTRAINT `habilidade_usuario_ibfk_2` FOREIGN KEY (`habilidadeId`) REFERENCES `habilidade` (`habilidadeId`)  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habilidade_usuario`
--

LOCK TABLES `habilidade_usuario` WRITE;
/*!40000 ALTER TABLE `habilidade_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `habilidade_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notas`
--

DROP TABLE IF EXISTS `notas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notas` (
  `notaId` int NOT NULL AUTO_INCREMENT,
  `usuarioId` int DEFAULT NULL,
  `turmaId` int DEFAULT NULL,
  `nPerguntasCorretas` int DEFAULT NULL,
  `nPerguntas` int DEFAULT NULL,
  `h1` int DEFAULT NULL,
  `h2` int DEFAULT NULL,
  `h3` int DEFAULT NULL,
  `h4` int DEFAULT NULL,
  `h5` int DEFAULT NULL,
  `notaProjetoFinal` double DEFAULT NULL,
  `notaTestes` double DEFAULT NULL,
  `notaFinal` double DEFAULT NULL,
  `recomendacao` varchar(100) DEFAULT NULL,
  `sabeProgramar` varchar(100) DEFAULT NULL,
  `desclassificado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`notaId`),
  KEY `usuarioId` (`usuarioId`),
  KEY `turmaId` (`turmaId`),
  CONSTRAINT `notas_ibfk_1` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`usuarioId`)  ON UPDATE CASCADE,
  CONSTRAINT `notas_ibfk_2` FOREIGN KEY (`turmaId`) REFERENCES `turma` (`turmaId`)  ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notas`
--

LOCK TABLES `notas` WRITE;
/*!40000 ALTER TABLE `notas` DISABLE KEYS */;
/*!40000 ALTER TABLE `notas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcaoresposta`
--

DROP TABLE IF EXISTS `opcaoresposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opcaoresposta` (
  `opcaoRespostaId` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `descricao` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `verdadeira` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`opcaoRespostaId`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcaoresposta`
--

LOCK TABLES `opcaoresposta` WRITE;
/*!40000 ALTER TABLE `opcaoresposta` DISABLE KEYS */;
INSERT INTO `opcaoresposta` VALUES (1,'VF','não entendi',1),(2,'VF','Sábado',1),(3,'VF','Quinta',0),(4,'VF','Terça',0),(5,'VF','Domingo',0),(6,'VF','D B C A E',0),(7,'VF','D C E B A',0),(8,'VF','E D C B A',1),(9,'VF','E C D A B',0),(10,'VF','não entendi a questão',1),(11,'VF','B',0),(12,'VF','não entendi a questão',1),(13,'VF','D',0),(14,'VF','A',0),(15,'VF','C',1),(16,'VF','não entendi a questão',1),(17,'VF','1',0),(18,'VF','2',0),(19,'VF','3',0),(20,'VF','4',0),(21,'VF','RIVER',1),(22,'VF','KNOCK',0),(23,'VF','não entendi a questão',1),(24,'VF','FLOOD',0),(25,'VF','LODGE',0),(26,'VF','Aldeia Y',1),(27,'VF','Aldeia  X',0),(28,'VF','não entendi a questão',1),(29,'VF','Aldeia Z',0),(30,'VF','Aldeia W',0),(31,'VF','C',0),(32,'VF','A',1),(33,'VF','D',0),(34,'VF','não entendi a questão',1),(35,'VF','B',0),(36,'VF','A',0),(37,'VF','B',0),(38,'VF','C',1),(39,'VF','D',0),(40,'VF','Não entendi a questão / no entendí la pregunta',1),(41,'VF','0',0),(42,'VF','1',1),(43,'VF','2',0),(44,'VF','3',0),(45,'VF','4',0),(46,'VF','Não entendi a questão / no entendí la pregunta',1),(63,'VF','A) Não entendi a questão',1),(64,'VF','B) Sim',0),(65,'VF','C) Não',0),(66,'VF','D) Em partes',1),(67,'VF','A) Não entendi a questão',1),(68,'VF','B) Sim',1),(69,'VF','C) Não',0),(70,'VF','D) Em partes',1),(71,'VF','A) Não entendi a questão',1),(72,'VF','B) Sim',1),(73,'VF','C) Não',0),(74,'VF','D) Em partes',1),(75,'VF','A) Não entendi a questão',1),(76,'VF','B) Sim',1),(77,'VF','C) Não',0),(78,'VF','D) Em partes',0),(79,'VF','A) Não entendi a questão',1),(80,'VF','B) 70',1),(81,'VF','C) 80',0),(82,'VF','D) 90',0),(83,'VF','Brasileiro(a)',1),(84,'VF','Uruguaio(a)',1),(85,'VF','Outro',1),(86,'VF','Não selecionado',1),(87,'VF','Feminino',1),(88,'VF','Masculinho',1),(89,'VF','Outro',1),(90,'VF','Não selecionado',1),(91,'VF','Branco(a)',1),(92,'VF','Negro(a)',1),(93,'VF','Pardo(a)/mulato(a)',1),(94,'VF','Amarelo(a) (de origem oriental)',1),(95,'VF','Indígena ou de origem indígena',1),(96,'VF','Sim',1),(97,'VF','Não',1),(98,'VF','Sim',1),(99,'VF','Não',1),(100,'VF','Não selecionado',1),(101,'VF','Não trabalho / nunca exerci atividade remunerada',1),(102,'VF','Trabalho / trabalhei eventualmente',1),(103,'VF','Trabalho / trabalhei até 20 horas semanais',1),(104,'VF','Trabalho / trabalhei mais de 20 horas semanais e menos de 40 horas semanais',1),(105,'VF','Trabalho / trabalhei em tempo integral – 40 horas semanais ou mais',1),(106,'VF','Não selecionado',1),(107,'VF','Sim, por meio de sistema de reserva de vagas com identificação étnico-racial (negros, pardos e indígenas)',1),(108,'VF','Sim, por meio de sistema de reserva de vagas com recorte social (egresso de escola pública, renda, etc.) ',1),(109,'VF','Sim, por sistema distinto dos anteriores',1),(110,'VF','Não',1),(111,'VF','Não selecionado',1),(112,'VF','Com os pais e(ou) com outros parentes',1),(113,'VF','Com o(a) esposo(a) e(ou) com o(s) filho(s)',1),(114,'VF','Com amigos (compartilhando despesas ou de favor). Com colegas, em alojamento universitário',1),(115,'VF','Sozinho(a)',1),(116,'VF','Não selecionado',1),(117,'VF','Nenhum',1),(118,'VF','Um ou dois',1),(119,'VF','Três ou quatro',1),(120,'VF','Cinco ou seis',1),(121,'VF','Mais de seis',1),(122,'VF','Não selecionado',1),(123,'VF','Comum ou de educação geral, no ensino regular',1),(124,'VF','Profissionalizante técnico (IFSul, UTU, outros), no ensino regular',1),(125,'VF','Liceo',1),(126,'VF','Supletivo',1),(127,'VF','Outro',1),(128,'VF','Sim',1),(129,'VF','Não',1),(130,'VF','Não selecionado',1),(131,'VF','0',1),(132,'VF','1',1),(133,'VF','2',1),(134,'VF','3',1),(135,'VF','4',1),(136,'VF','Não selecionado',1),(137,'VF','Nenhuma, apenas assisto às aulas',1),(138,'VF','Uma a duas',1),(139,'VF','Três a cinco',1),(140,'VF','Seis a oito',1),(141,'VF','Mais de oito',1),(142,'VF','Não selecionado',1),(143,'VF','Casa',1),(144,'VF','Trabalho',1),(145,'VF','Escola',1),(146,'VF','Não utilizo',1),(147,'VF','Sim',1),(148,'VF','Não',1),(149,'VF','Não selecionado',1),(150,'VF','Redes Sociais',1),(151,'VF','Jogos',1),(152,'VF','Estudos',1),(153,'VF','Pesquisas em Geral',1),(154,'VF','Não utilizo',1);
/*!40000 ALTER TABLE `opcaoresposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcaoresposta_perguntaquestionario`
--

DROP TABLE IF EXISTS `opcaoresposta_perguntaquestionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opcaoresposta_perguntaquestionario` (
  `perguntaQuestionarioId` int DEFAULT NULL,
  `opcaoRespostaId` int DEFAULT NULL,
  KEY `perguntaQuestionarioId` (`perguntaQuestionarioId`),
  KEY `opcaoRespostaId` (`opcaoRespostaId`),
  CONSTRAINT `opcaoresposta_perguntaquestionario_ibfk_1` FOREIGN KEY (`perguntaQuestionarioId`) REFERENCES `perguntaquestionario` (`perguntaQuestionarioId`)  ON UPDATE CASCADE,
  CONSTRAINT `opcaoresposta_perguntaquestionario_ibfk_2` FOREIGN KEY (`opcaoRespostaId`) REFERENCES `opcaoresposta` (`opcaoRespostaId`)  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcaoresposta_perguntaquestionario`
--

LOCK TABLES `opcaoresposta_perguntaquestionario` WRITE;
/*!40000 ALTER TABLE `opcaoresposta_perguntaquestionario` DISABLE KEYS */;
INSERT INTO `opcaoresposta_perguntaquestionario` VALUES (1,83),(1,84),(1,85),(3,86),(3,87),(3,88),(3,89),(4,90),(4,91),(4,92),(4,93),(4,94),(4,95),(5,96),(5,97),(6,98),(6,99),(7,100),(7,101),(7,102),(7,103),(7,104),(7,105),(8,106),(8,107),(8,108),(8,109),(8,110),(9,111),(9,112),(9,113),(9,114),(9,115),(10,116),(10,117),(10,118),(10,119),(10,120),(10,121),(11,122),(11,123),(11,124),(11,125),(11,126),(11,127),(12,128),(12,129),(18,130),(18,131),(18,132),(18,133),(18,134),(18,135),(19,136),(19,137),(19,138),(19,139),(19,140),(19,141),(20,142),(20,143),(20,144),(20,145),(20,146),(21,147),(21,148),(22,149),(22,150),(22,151),(22,152),(22,153),(22,154);
/*!40000 ALTER TABLE `opcaoresposta_perguntaquestionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcaoresposta_perguntateste`
--

DROP TABLE IF EXISTS `opcaoresposta_perguntateste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opcaoresposta_perguntateste` (
  `perguntaTesteId` int DEFAULT NULL,
  `opcaoRespostaId` int DEFAULT NULL,
  KEY `perguntaTesteId` (`perguntaTesteId`),
  KEY `opcaoresposta_perguntateste_ibfk_2` (`opcaoRespostaId`),
  CONSTRAINT `opcaoresposta_perguntateste_ibfk_1` FOREIGN KEY (`perguntaTesteId`) REFERENCES `perguntateste` (`perguntaTesteId`)  ON UPDATE CASCADE,
  CONSTRAINT `opcaoresposta_perguntateste_ibfk_2` FOREIGN KEY (`opcaoRespostaId`) REFERENCES `opcaoresposta` (`opcaoRespostaId`)  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcaoresposta_perguntateste`
--

LOCK TABLES `opcaoresposta_perguntateste` WRITE;
/*!40000 ALTER TABLE `opcaoresposta_perguntateste` DISABLE KEYS */;
INSERT INTO `opcaoresposta_perguntateste` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,6),(2,7),(2,8),(2,9),(2,10),(3,11),(3,12),(3,13),(3,14),(3,15),(4,16),(4,17),(4,18),(4,19),(4,20),(5,21),(5,22),(5,23),(5,24),(5,25),(6,26),(6,27),(6,28),(6,29),(6,30),(7,31),(7,32),(7,33),(7,34),(7,35),(8,36),(8,37),(8,38),(8,39),(8,40),(9,41),(9,42),(9,43),(9,44),(9,45),(9,46),(14,63),(14,64),(14,65),(14,66),(15,67),(15,68),(15,69),(15,70),(16,71),(16,72),(16,73),(16,74),(17,75),(17,76),(17,77),(17,78),(18,79),(18,80),(18,81),(18,82);
/*!40000 ALTER TABLE `opcaoresposta_perguntateste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perguntaquestionario`
--

DROP TABLE IF EXISTS `perguntaquestionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perguntaquestionario` (
  `perguntaQuestionarioId` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `descricao` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `tipo` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `opRespostaId` varchar(4) DEFAULT NULL,
  `resposta` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `obrigatorio` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`perguntaQuestionarioId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perguntaquestionario`
--

LOCK TABLES `perguntaquestionario` WRITE;
/*!40000 ALTER TABLE `perguntaquestionario` DISABLE KEYS */;
INSERT INTO `perguntaquestionario` VALUES (1,'Nacionalidade','Sua nacionalidade é? *','multipla escolha',NULL,NULL,1),(2,'Idade','Qual sua idade? (0 - 100) *','dissertativa',NULL,NULL,1),(3,'Sexo','Qual seu sexo? *','multipla escolha',NULL,NULL,1),(4,'Etnia','Como você se considera?','multipla escolha',NULL,NULL,0),(5,'Trabalha','Você trabalha? *','multipla escolha',NULL,NULL,1),(6,'Salario','Se você respondeu que trabalha, seu salário contribui para a renda familiar?','multipla escolha',NULL,NULL,0),(7,'Carga Horária','Se você trabalha ou já trabalhou, qual é (ou foi) a carga horária aproximada de sua atividade remunerada? (Não contar estágio e bolsas de pesquisa.)','multipla escolha',NULL,NULL,0),(8,'Método de ingresso','Seu ingresso no curso de graduação se deu por meio de políticas de ação afirmativa da instituição?','multipla escolha',NULL,NULL,0),(9,'Familiar','Com quem você mora atualmente?','multipla escolha',NULL,NULL,0),(10,'Quantidade na casa','Quantos membros de sua família moram com você?','multipla escolha',NULL,NULL,0),(11,'Curso','Que tipo de curso de ensino médio você concluiu?','multipla escolha',NULL,NULL,0),(12,'Programa','Você sabe programar?*','multipla escolha',NULL,NULL,1),(13,'Experiência na programação','Se respondeu SIM na pergunta anterior, me conte sua experiência!','dissertativa',NULL,NULL,0),(14,'Algoritmo','O que é ALGORITMO?*','dissertativa',NULL,NULL,1),(15,'Variaveis','O que são variáveis, comandos de condição e repetição? (se não souber deixe em branco)','dissertativa',NULL,NULL,0),(16,'Interesse pessoal','Ao fazer um curso de programação, quais são seus interesses pessoais ou profissionais?*','dissertativa',NULL,NULL,1),(17,'Expectativa','Qual sua expectativa com relação a este curso?*','dissertativa',NULL,NULL,1),(18,'Alfabetização digital','Marque na escala, como você considera seu conhecimento com relação a alfabetização digital (AD é \'a aquisição de habilidades básicas para o uso de computadores e da Internet\' (Portal da Educação) )','multipla escolha',NULL,NULL,0),(19,'Horas de estudo','Quantas horas por semana, aproximadamente, você dedica / dedicou aos estudos, excetuando as horas de aula?','multipla escolha',NULL,NULL,0),(20,'Lugar de estudo','Onde você mais utiliza o computador (ou tablet ) para estudar?','multipla escolha',NULL,NULL,0),(21,'Dados móveis','Você possui dados móveis?*','multipla escolha',NULL,NULL,1),(22,'Utilização de internet','Quando utiliza a internet, o que mais usa?','multipla escolha',NULL,NULL,0),(23,'Expectativa com a pesquisa','Qual sua expectativa com relação a este curso ( participação nesta pesquisa )?','dissertativa',NULL,NULL,0);
/*!40000 ALTER TABLE `perguntaquestionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perguntateste`
--

DROP TABLE IF EXISTS `perguntateste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perguntateste` (
  `perguntaTesteId` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `descricao` longtext CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  `pedido` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `opRespostaId` varchar(4) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`perguntaTesteId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perguntateste`
--

LOCK TABLES `perguntateste` WRITE;
/*!40000 ALTER TABLE `perguntateste` DISABLE KEYS */;
INSERT INTO `perguntateste` VALUES (1,'Bob, o castor',' Bob, o castor, só diz a verdade na segunda, quarta  \ne sexta-feira e sempre mente em todos os outros dias da semana. Hoje ele diz: “ Amanhã vou dizer a verdade.','Que dia é?',NULL,NULL),(2,'Cinco garrafas','Um castor coloca cinco garrafas em uma mesa.\n Ele as coloca de modo que cada garrafa apareça um pedaço. Ele coloca a primeira garrafa na \nparte de trás da mesa e cada garrafa subsequente na frente da anterior.','Em que ordem as garrafas foram colocadas conforme mostrado na figura?',NULL,'test1quest2.png'),(3,'Sequencia de lances','Depois da \nescola, os jovens castores costumam brincar juntos. Para evitar brigas sobre onde jogar, eles jogam um dado normal \nde seis lados. A decisão é encontrada de acordo com esta regra:','Qual sequência de lances mandará os jovens castores para o campo esportivo?',NULL,'test1quest3.png'),(4,'Fluxograma','Na escola, os \npequenos castores fazem cálculos complicados usando fluxograma. Eles começam com um número no começo! e siga as \ninstruções:','Ao começar com o número 18, qual é o número quando terminar?(se não entendeu a pergunta, responda \'não entendi a questão\')',NULL,'test1quest4.png'),(5,'Codificação de mensagens','O \ncastor Alex e o castora Betty enviam mensagens uns aos outros usando a seguinte sequência de transformações em \ncada palavra.','Por exemplo, a palavra \'BEAVER\' é transformada para \'WBFCSF\'.\nA castora Betty recebe a mensagem codificada \'PMGEP\' do castor Alex.\nO que Alex deseja dizer?',NULL,'test1quest5.png'),(6,'Aldeias','O Sr.Beaver \ntem 4 amigos que moram em aldeias diferentes e planeja visitar um desses amigos todas as tardes. \nInicialmente, todas as setas apontam para a estrada esquerda. Ao passar pelo cruzamento, o Sr.Beaver \nmudava a seta para a direção oposta. Por exemplo, no dia 1, o Sr. Beaver pega a estrada à esquerda na primeira \ninterseção, pega a estrada à esquerda na segunda interseção e chega a Aldeia W. No dia 2, o Sr. Beaver vira à \ndireita na primeira interseção, então à esquerda no segundo cruzamento e chega a Aldeia Y.','¿Qué pueblo visitará el señor Beaver el día 30?',NULL,'test1quest6.png'),(7,'Móbile','Um móbile é uma obra de arte pendurada no teto. Você deve se lembrar de um pendurado no teto em seu quarto. Um móbile consiste em paus e figuras. \nCada vara tem alguns pontos para os quais as figuras ou outras varas podem ser anexadas. Além disso, cada vara tem um ponto de suspensão, a partir do qual é presa a uma vara mais acima (ou ao teto). \nO seguinte exemplo de móbile pode ser descrito usando estes números e parenteses:\n(-3 (-1 1) (1 1)) (2 3)','Quais dos seguintes dispositivos móveis podem ser construídos usando estas instruções:\n(-3 (-1 4) (2 (-1 1) (1 1))) (2 (-1 6) (2 3)) ',NULL,'test1quest7.png'),(8,'Relógio Quebrado/ Reloj Roto','(BR) Um relógio digital exibe quatro dígitos. Cada dígito\né mostrado usando sete segmentos que podem estar\nligados ou desligados, conforme a figura abaixo.\n(UY) Un reloj digital muestra cuatro dígitos. Cada dígito\nse muestra usando siete segmentos que pueden\nestar prendidos o apagados, según la figura\nsiguiente: \n','Se o relógio quebrou e estava exibindo a hora acima,\nqual das seguintes opções pode ser a hora real?',NULL,'test2quest1.png'),(9,'Labirinto/Laberinto','(BR) Um carro robótico usa uma regra simples para\natravessar um labirinto:\nVire à direita sempre que possível.\nA imagem dá um exemplo de como o robô\nconduziria através de um labirinto.\n(UY) Un coche robótico utiliza una regla simple para\natravesar un laberinto:\nGire a la derecha siempre que sea posible.\nLa imagen presenta un ejemplo de cómo el robot\nconduciría a través de un laberinto. \n','Em quantos dos seguintes labirintos o carro alcançará o ponto vermelho se usar este sistema?\n',NULL,'test2quest2.png'),(14,'posteste-Interface','Você foi convidado a desenvolver este APP para calcular o IMC e mostrar o valor do IMC resultante e sua classificação. \nExplicação: IMC é a sigla para Índice de Massa Corporal que serve para avaliar o peso do indivíduo em relação à sua altura e assim indicar se está dentro do peso ideal, acima ou abaixo do peso desejado. Estar dentro do peso certo é importante porque estar acima ou abaixo do peso influencia na saúde, aumentando o risco de doenças como desnutrição quando se está abaixo do peso, e AVC e infarto, quando se está acima do peso. O cálculo do IMC deve ser feito usando a seguinte fórmula matemática: IMC = Peso (Kg) / Altura(m)²','Baseado na explicação anterior, responda as questões 01, 02,03 e 04. Marque somente uma alternativa. A interface (tela) está totalmente acordo com a descrição do problema? ',NULL,'test4quest1.png'),(15,'posteste-Tabela',' ','A tabela 01 apresenta dados que são importantes para a programação do APP?',NULL,NULL),(16,'posteste-Botoes',' ','Na interface proposta, é possível remover os botões de Sexo (Mulher e Homem) sem alterar a funcionalidade do aplicativo? ',NULL,NULL),(17,'posteste-Formula',' ','A figura abaixo mostra a fórmula do IMC implementada e parte da implementação da Tabela para mostra o resultado ao usuário. Esta porção do código está totalmente correta? ',NULL,'test4quest4.png'),(18,'posteste-Valor',' ','Qual o valor que será mostrado na interface no \'CampoDeTextoMostrarResultado\' (variável valor), segundo a figura abaixo?',NULL,'test4quest5.png');
/*!40000 ALTER TABLE `perguntateste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perguntateste_teste`
--

DROP TABLE IF EXISTS `perguntateste_teste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perguntateste_teste` (
  `testeId` int DEFAULT NULL,
  `perguntaTesteId` int DEFAULT NULL,
  KEY `testeId` (`testeId`),
  KEY `perguntaTesteId` (`perguntaTesteId`),
  CONSTRAINT `perguntateste_teste_ibfk_1` FOREIGN KEY (`testeId`) REFERENCES `teste` (`testeId`)  ON UPDATE CASCADE,
  CONSTRAINT `perguntateste_teste_ibfk_2` FOREIGN KEY (`perguntaTesteId`) REFERENCES `perguntateste` (`perguntaTesteId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perguntateste_teste`
--

LOCK TABLES `perguntateste_teste` WRITE;
/*!40000 ALTER TABLE `perguntateste_teste` DISABLE KEYS */;
INSERT INTO `perguntateste_teste` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(2,8),(2,9),(4,14),(4,15),(4,16),(4,17),(4,18);
/*!40000 ALTER TABLE `perguntateste_teste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionarioinicial`
--

DROP TABLE IF EXISTS `questionarioinicial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionarioinicial` (
  `questionarioId` int NOT NULL AUTO_INCREMENT,
  `disponibilidade` datetime DEFAULT NULL,
  `nome` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `visibilidade` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`questionarioId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionarioinicial`
--

LOCK TABLES `questionarioinicial` WRITE;
/*!40000 ALTER TABLE `questionarioinicial` DISABLE KEYS */;
INSERT INTO `questionarioinicial` VALUES (1,'2023-10-30 00:00:00','Conhecendo você',1);
/*!40000 ALTER TABLE `questionarioinicial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionarioturmas`
--

DROP TABLE IF EXISTS `questionarioturmas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionarioturmas` (
  `questionarioId` int DEFAULT NULL,
  `turmaId` int DEFAULT NULL,
  KEY `questionarioId` (`questionarioId`),
  KEY `turmaId` (`turmaId`),
  CONSTRAINT `questionarioturmas_ibfk_1` FOREIGN KEY (`questionarioId`) REFERENCES `questionarioinicial` (`questionarioId`)  ON UPDATE CASCADE,
  CONSTRAINT `questionarioturmas_ibfk_2` FOREIGN KEY (`turmaId`) REFERENCES `turma` (`turmaId`)  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionarioturmas`
--

LOCK TABLES `questionarioturmas` WRITE;
/*!40000 ALTER TABLE `questionarioturmas` DISABLE KEYS */;
INSERT INTO `questionarioturmas` VALUES (1,1);
/*!40000 ALTER TABLE `questionarioturmas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questpergunta`
--

DROP TABLE IF EXISTS `questpergunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questpergunta` (
  `questionarioId` int DEFAULT NULL,
  `perguntaQuestionarioId` int DEFAULT NULL,
  KEY `questionarioId` (`questionarioId`),
  KEY `questpergunta_ibfk_2` (`perguntaQuestionarioId`),
  CONSTRAINT `questpergunta_ibfk_1` FOREIGN KEY (`questionarioId`) REFERENCES `questionarioinicial` (`questionarioId`)  ON UPDATE CASCADE,
  CONSTRAINT `questpergunta_ibfk_2` FOREIGN KEY (`perguntaQuestionarioId`) REFERENCES `perguntaquestionario` (`perguntaQuestionarioId`)  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questpergunta`
--

LOCK TABLES `questpergunta` WRITE;
/*!40000 ALTER TABLE `questpergunta` DISABLE KEYS */;
INSERT INTO `questpergunta` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23);
/*!40000 ALTER TABLE `questpergunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regquestionarios`
--

DROP TABLE IF EXISTS `regquestionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regquestionarios` (
  `regQuestionarioId` int NOT NULL AUTO_INCREMENT,
  `questionarioId` int DEFAULT NULL,
  `usuarioId` int DEFAULT NULL,
  `turmaId` int DEFAULT NULL,
  PRIMARY KEY (`regQuestionarioId`),
  KEY `questionarioId` (`questionarioId`),
  KEY `usuarioId` (`usuarioId`),
  KEY `turmaId` (`turmaId`),
  CONSTRAINT `regquestionarios_ibfk_1` FOREIGN KEY (`questionarioId`) REFERENCES `questionarioinicial` (`questionarioId`)  ON UPDATE CASCADE,
  CONSTRAINT `regquestionarios_ibfk_2` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`usuarioId`) ON UPDATE CASCADE,
  CONSTRAINT `regquestionarios_ibfk_3` FOREIGN KEY (`turmaId`) REFERENCES `turma` (`turmaId`)  ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regquestionarios`
--

LOCK TABLES `regquestionarios` WRITE;
/*!40000 ALTER TABLE `regquestionarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `regquestionarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regquestionarios_respostaquestionario`
--

DROP TABLE IF EXISTS `regquestionarios_respostaquestionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regquestionarios_respostaquestionario` (
  `regQuestionarioId` int DEFAULT NULL,
  `respostaQuestionarioId` int DEFAULT NULL,
  KEY `regQuestionarioId` (`regQuestionarioId`),
  KEY `respostaQuestionarioId` (`respostaQuestionarioId`),
  CONSTRAINT `regquestionarios_respostaquestionario_ibfk_1` FOREIGN KEY (`regQuestionarioId`) REFERENCES `regquestionarios` (`regQuestionarioId`)  ON UPDATE CASCADE,
  CONSTRAINT `regquestionarios_respostaquestionario_ibfk_2` FOREIGN KEY (`respostaQuestionarioId`) REFERENCES `respostaquestionario` (`respostaQuestionarioId`)  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regquestionarios_respostaquestionario`
--

LOCK TABLES `regquestionarios_respostaquestionario` WRITE;
/*!40000 ALTER TABLE `regquestionarios_respostaquestionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `regquestionarios_respostaquestionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regtestes`
--

DROP TABLE IF EXISTS `regtestes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regtestes` (
  `regTestesId` int NOT NULL AUTO_INCREMENT,
  `testeId` int DEFAULT NULL,
  `usuarioId` int DEFAULT NULL,
  `turmaId` int DEFAULT NULL,
  PRIMARY KEY (`regTestesId`),
  KEY `testeId` (`testeId`),
  KEY `usuarioId` (`usuarioId`),
  KEY `turmaId` (`turmaId`),
  CONSTRAINT `regtestes_ibfk_1` FOREIGN KEY (`testeId`) REFERENCES `teste` (`testeId`),
  CONSTRAINT `regtestes_ibfk_2` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`usuarioId`),
  CONSTRAINT `regtestes_ibfk_3` FOREIGN KEY (`turmaId`) REFERENCES `turma` (`turmaId`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regtestes`
--

LOCK TABLES `regtestes` WRITE;
/*!40000 ALTER TABLE `regtestes` DISABLE KEYS */;
/*!40000 ALTER TABLE `regtestes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regtestes_respostateste`
--

DROP TABLE IF EXISTS `regtestes_respostateste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regtestes_respostateste` (
  `regTestesId` int DEFAULT NULL,
  `respostaTesteId` int DEFAULT NULL,
  KEY `regTestesId` (`regTestesId`),
  KEY `respostaTesteId` (`respostaTesteId`),
  CONSTRAINT `regtestes_respostateste_ibfk_1` FOREIGN KEY (`regTestesId`) REFERENCES `regtestes` (`regTestesId`)   ON UPDATE CASCADE,
  CONSTRAINT `regtestes_respostateste_ibfk_2` FOREIGN KEY (`respostaTesteId`) REFERENCES `respostateste` (`respostaTesteId`)   ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regtestes_respostateste`
--

LOCK TABLES `regtestes_respostateste` WRITE;
/*!40000 ALTER TABLE `regtestes_respostateste` DISABLE KEYS */;
/*!40000 ALTER TABLE `regtestes_respostateste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respostaquestionario`
--

DROP TABLE IF EXISTS `respostaquestionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respostaquestionario` (
  `respostaQuestionarioId` int NOT NULL AUTO_INCREMENT,
  `perguntaQuestionarioId` int DEFAULT NULL,
  `opRespostaId` int DEFAULT NULL,
  `tipo` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `resposta` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`respostaQuestionarioId`),
  KEY `perguntaQuestionarioId` (`perguntaQuestionarioId`),
  CONSTRAINT `respostaquestionario_ibfk_1` FOREIGN KEY (`perguntaQuestionarioId`) REFERENCES `perguntaquestionario` (`perguntaQuestionarioId`)   ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=507 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respostaquestionario`
--

LOCK TABLES `respostaquestionario` WRITE;
/*!40000 ALTER TABLE `respostaquestionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `respostaquestionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respostateste`
--

DROP TABLE IF EXISTS `respostateste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respostateste` (
  `respostaTesteId` int NOT NULL AUTO_INCREMENT,
  `perguntaTesteId` int DEFAULT NULL,
  `opRespostaId` int DEFAULT NULL,
  PRIMARY KEY (`respostaTesteId`),
  KEY `perguntaTesteId` (`perguntaTesteId`),
  CONSTRAINT `respostateste_ibfk_1` FOREIGN KEY (`perguntaTesteId`) REFERENCES `perguntateste` (`perguntaTesteId`)   ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=379 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respostateste`
--

LOCK TABLES `respostateste` WRITE;
/*!40000 ALTER TABLE `respostateste` DISABLE KEYS */;
/*!40000 ALTER TABLE `respostateste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES (1,'ROLE_ALUNO'),(2,'ROLE_PROF');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users_roles`
--

DROP TABLE IF EXISTS `tb_users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_users_roles` (
  `role_id` int DEFAULT NULL,
  `usuarioId` int DEFAULT NULL,
  KEY `role_id` (`role_id`),
  KEY `usuarioId` (`usuarioId`),
  CONSTRAINT `tb_users_roles_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `tb_users_roles_ibfk_2` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`usuarioId`)   ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users_roles`
--

LOCK TABLES `tb_users_roles` WRITE;
/*!40000 ALTER TABLE `tb_users_roles` DISABLE KEYS */;
INSERT INTO `tb_users_roles` VALUES (1,1),(1,2),(2,3);
/*!40000 ALTER TABLE `tb_users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teste`
--

DROP TABLE IF EXISTS `teste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teste` (
  `visibilidade` tinyint(1) DEFAULT NULL,
  `testeId` int NOT NULL AUTO_INCREMENT,
  `disponibilidade` datetime DEFAULT NULL,
  `nome` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`testeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teste`
--

LOCK TABLES `teste` WRITE;
/*!40000 ALTER TABLE `teste` DISABLE KEYS */;
INSERT INTO `teste` VALUES (1,1,'2023-10-10 00:00:00','pre-teste'),(1,2,'2023-11-10 00:00:00','teste 2'),(1,4,'2023-11-30 00:00:00','pós teste');
/*!40000 ALTER TABLE `teste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testes_nota`
--

DROP TABLE IF EXISTS `testes_nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testes_nota` (
  `notaId` int DEFAULT NULL,
  `testeId` int DEFAULT NULL,
  KEY `notaId` (`notaId`),
  KEY `testeId` (`testeId`),
  CONSTRAINT `testes_nota_ibfk_1` FOREIGN KEY (`notaId`) REFERENCES `notas` (`notaId`)   ON UPDATE CASCADE,
  CONSTRAINT `testes_nota_ibfk_2` FOREIGN KEY (`testeId`) REFERENCES `teste` (`testeId`)   ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testes_nota`
--

LOCK TABLES `testes_nota` WRITE;
/*!40000 ALTER TABLE `testes_nota` DISABLE KEYS */;
/*!40000 ALTER TABLE `testes_nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testesturma`
--

DROP TABLE IF EXISTS `testesturma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testesturma` (
  `turmaId` int DEFAULT NULL,
  `testeId` int DEFAULT NULL,
  KEY `turmaId` (`turmaId`),
  KEY `testeId` (`testeId`),
  CONSTRAINT `testesturma_ibfk_1` FOREIGN KEY (`turmaId`) REFERENCES `turma` (`turmaId`)   ON UPDATE CASCADE,
  CONSTRAINT `testesturma_ibfk_2` FOREIGN KEY (`testeId`) REFERENCES `teste` (`testeId`)   ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testesturma`
--

LOCK TABLES `testesturma` WRITE;
/*!40000 ALTER TABLE `testesturma` DISABLE KEYS */;
INSERT INTO `testesturma` VALUES (1,1),(1,2),(1,4);
/*!40000 ALTER TABLE `testesturma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turma` (
  `turmaId` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `visibilidade` tinyint DEFAULT NULL,
  PRIMARY KEY (`turmaId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
INSERT INTO `turma` VALUES (1,'Turma TADS1/2023',1);
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma_usuario`
--

DROP TABLE IF EXISTS `turma_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turma_usuario` (
  `usuarioId` int DEFAULT NULL,
  `turmaId` int DEFAULT NULL,
  KEY `usuarioId` (`usuarioId`),
  KEY `turmaId` (`turmaId`),
  CONSTRAINT `turma_usuario_ibfk_1` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`usuarioId`)   ON UPDATE CASCADE,
  CONSTRAINT `turma_usuario_ibfk_2` FOREIGN KEY (`turmaId`) REFERENCES `turma` (`turmaId`)   ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma_usuario`
--

LOCK TABLES `turma_usuario` WRITE;
/*!40000 ALTER TABLE `turma_usuario` DISABLE KEYS */;
INSERT INTO `turma_usuario` VALUES (1,1),(2,1),(3,1);
/*!40000 ALTER TABLE `turma_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `usuarioId` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) DEFAULT NULL,
  `identificador` varchar(30) DEFAULT NULL,
  `nome` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `telefone` bigint DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`usuarioId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4  ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'aluno','111','alunoTeste1','email1@gmail.com',55984,'2000-01-20','nao selecionado','alunoteste1','$2a$12$dv/6XzTyFska76E8DyXGOuVKyvrPNJ34rJsEsfzX5X2ubFNRyNIAe'),(2,'aluno','222','alunoTeste2','email2@gmail.com',55985,'2000-01-21','nao selecionado','alunoteste2','$2a$12$dv/6XzTyFska76E8DyXGOuVKyvrPNJ34rJsEsfzX5X2ubFNRyNIAe'),(3,'prof','333','profTeste1','email3@gmail.com',55986,'2000-01-22','nao selecionado','profteste1','$2a$12$dv/6XzTyFska76E8DyXGOuVKyvrPNJ34rJsEsfzX5X2ubFNRyNIAe');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-10 17:23:48
