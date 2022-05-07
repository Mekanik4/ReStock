-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: appdb
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `product_family` varchar(45) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Tsakiris Chips salted','snacks',1.3),(2,'Cheetos Pakotinia','snacks',1.33),(3,'Bonora chips oregano ','snacks',0.74),(4,'Bonora chips BBQ','snacks',0.74),(5,'Poppers popcorn salted','snacks',0.95),(6,'Lay\'s oregano','snacks',1.56),(7,'Cheetos Lotto','snacks',1.33),(8,'Ruffles BBQ','snacks',1.58),(9,'Ruffles oregano','snacks',1.48),(10,'Doritos nacho cheese','snacks',1.16),(11,'Tasty pitsinia','snacks',1.33),(12,'Lay\'s στο Φούρνο BBQ','snacks',1.46),(13,'Lay\'s πατατάκια ολικής άλεσης με πιπέρι','snacks',1.7),(14,'Tsakiris πατατάκια με ρίγανη','snacks',1.3),(15,'El Sabor Nacho chips Cheese','snacks',1.75),(16,'Ruffles με αλάτι','snacks',1.48),(17,'Cheetos δρακουλίνια','snacks',1.33),(18,'Lay\'s πατατάκια με αλάτι','snacks',1.56),(19,'Poppers ποπ κορν με βούτηρο','snacks',1.04),(20,'El Sabor nacho chips chili','snacks',1.75),(21,'Lay\'s πατατάκια με αλάτι και ξύδι','snacks',1.56),(22,'Jumbo snack γαριδάρες','snacks',0.97),(23,'Bonora πατατάκια με ρίγανη','snacks',0.72),(24,'Bonora πατατάκια κυματιστά με αλάτι','snacks',0.74),(25,'Lay\'s πατατάκια Ολικής άλεσης με αλάτι','snacks',1.7),(26,'ΑΛΛΑΤΙΝΗ κράκερς αλμυρά','snacks',0.44),(27,'ΠΑΠΑΔΟΠΟΥΛΟΥ Batonettes crackers ','snacks',0.54),(28,'ΠΑΠΑΔΟΠΟΥΛΟΥ Pick Crackers Classic','snacks',1.08),(29,'ΑΛΛΑΤΙΝΗ Nak αλμυρά','snacks',0.44),(30,'Elite crackers Μεσογειακά Γραβιέρα Μέλι & Σουσάμι','snacks',1.26),(31,'ΠΑΠΑΔΟΠΟΥΛΟΥ mini Pick Crackers','snacks',0.72),(32,'Πασατέμπος Tasty natural 70gr','snacks',1.3),(33,'Ηλιόσπορος Tasty natural 71gr','snacks',1.3),(34,'Κελυφωτά φιστίκια Tasty natural 85gr','snacks',2.5),(35,'Αράπικα φιστίκια Tasty natural 85gr','snacks',1.3),(36,'Φουντούκια Tasty natural 90gr','snacks',3.2),(37,'Αμύγδαλα Tasty natural 90gr','snacks',2.5),(38,'Κάσιους Tasty natural 90gr','snacks',2.5),(39,'Peanuts με ρίγανη Pellito 150gr','snacks',2),(40,'Mediterranean Mix Pellito 125gr','snacks',4),(41,'Στεργίου αράβικη πίτα γαλοπούλα','sandwich',2.3),(42,'7Days Bake Rolls κλασική 160gr','snacks',1.3),(43,'7Days Bake Rolls σκόρδο 160gr','snacks',1.3),(44,'7Days Bake Rolls pizza 160gr','snacks',1.3),(45,'Στεργίου Φρατζολάκι ψητό κοτόπουλο','sandwich',2.5),(46,'Στεργίου Κρουασάν καπνιστή γαλοπούλα','sandwich',2.5),(47,'Panini σάντουιτς γαλοπούλα','sandwich',2.5),(48,'Pringles original 165gr','snacks',3.5),(49,'Panini strudel κοτόπουλο','sandwich',2.8),(50,'Panini strudel γαλοπούλα','sandwich',2.8),(51,'Παπαδοπούλου παξιμάδια Krispies με σουσάμι 200gr','snacks',1.6),(52,'Elite crackers μεσογειακά φυσική γεύση με σουσάμι 105gr','snacks',1.5),(53,'Elite crackers μεσογειακά πίγανη και φέτα 105gr','snacks',1.5),(54,'Pick Crackers 100gr','snacks',1.2),(55,'Pick cocktail 335gr','snacks',2.1),(56,'Pick αστέρι','snacks',2.1),(57,'Mini Pick crackers classic 250gr','snacks',1.5),(58,'Mini Pick crackers pizza 90gr','snacks',0.9),(59,'Mini Pick crackers bbq 90gr','snacks',0.9),(60,'Mini Pick crackers ρίγανη 90gr','snacks',0.9),(61,'Παπαδοπούλου Cream Crackers με κινόα 195gr','snacks',2.2),(62,'Pringles sour cream 40gr','snacks',1.1),(63,'Pringles paprika 40gr','snacks',1.1),(64,'Pringles BBQ 40gr','snacks',1.1),(65,'Pringles Texas BBQ 165gr','snacks',2.5),(66,'Pringles Bacon 165gr','snacks',2.3),(67,'Pringles Hot paprika 165gr','snacks',2.7),(68,'Pringles Cream and Onion 165gr','snacks',2.6),(69,'Tottis πατατάκια με αλάτι 110gr','snacks',1),(70,'Tottis πατατάκια με ρίγανη 110gr','snacks',1),(71,'Foudounia Tasty 125gr','snacks',1.4),(72,'Ruffles πατατάκια ketchup 105gr','snacks',1.6),(73,'Extra γαριδάκια pizza 115gr','snacks',1),(74,'Extra γαριδάκια pizza 55gr','snacks',0.5),(75,'Chipita πατατάκια ρίγανη 55gr','snacks',0.6),(76,'Chipita πατατάκια αλάτι 55gr','snacks',0.6),(77,'Chipita πατατάκια ρίγανη 120gr','snacks',1.1),(78,'Chipita πατατάκια αλάτι 120gr','snacks',1.1),(79,'Chipita πατατάκια BBQ 120gr','snacks',1.1),(80,'Chipita πατατάκια tsipers κυματιστά αλάτι 120gr','snacks',1.1),(81,'Chipita πατατάκια tsipers κυματιστά ρίγανη 120gr','snacks',1.1),(82,'Chipita πατατάκια tsipers κυματιστά BBQ 120gr','snacks',1.1),(83,'Tsakiris sticks αλάτι 100gr','snacks',1.75),(84,'Tsakiris πατατάκια pizza 120gr','snacks',1.9),(85,'Tsakiris πατατάκια ρίγανη 120gr','snacks',1.9),(86,'Tsakiris πατατάκια κυματιστά BBQ 100gr','snacks',1.5),(87,'Tsakiris πατατάκια κυματιστά αλάτι 100gr','snacks',1.5),(88,'Tsakiris πατατάκια κυματιστά ρίγανη 100gr','snacks',1.5),(89,'Tsakiris πατατάκια με γεύση πατάτες φούρνου 100gr','snacks',1.6),(90,'Nacho chili 100gr','snacks',1.1),(91,'Nacho natural 100gr','snacks',1.1),(92,'Nacho cheese 100gr','snacks',1.1),(93,'Jumbo fofico snack 100gr','snacks',1.1),(94,'Jumbo sticks 100gr','snacks',1.2),(95,'Pop O Top\'s classic 85gr','snacks',1.25),(96,'Pop O Top\'s BBQ 85gr','snacks',1.1),(97,'Jumbo chips κυματιστά αλάτι 280gr','snacks',2.15),(98,'Jumbo chips κυματιστά ρίγανη 130gr','snacks',1.1),(99,'Jumbo chips κυματιστά ρίγανη 280gr','snacks',2),(100,'Jumbo chips χωρίς αλάτι 130gr','snacks',1.1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `supplier_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `VAT_number` varchar(10) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (1,'Σύλλελης','166269834','2310782923','Εθνικής Αμύνης 12'),(2,'Καλούδης','168728092','2310392033','Λεωφόρος Ωραιοκάστρου 26'),(3,'Τροφοεμπορική','167320923','2108944383','Αθανάσιου Διάκου 23, Χαλάνδρι '),(4,'Τσακίρης','162198302','2103293744','25ης Μαρτίου 17-19, Αττική');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-06 19:50:51
