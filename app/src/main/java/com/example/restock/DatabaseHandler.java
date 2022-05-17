package com.example.restock;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.restock.objects.Item;
import com.example.restock.objects.Order;
import com.example.restock.objects.Profile;
import com.example.restock.objects.Supplier;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "restock.db";
    public static final String TABLE_PRODUCTS = "product";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_FAMILY = "product_family";
    public static final String COLUMN_PRICE = "price";

    public static final String TABLE_SUPPLIERS = "suppliers";
    public static final String COLUMN_SUPPLIER_ID = "supplier_id";
    public static final String COLUMN_SUPPLIER_NAME = "name";
    public static final String COLUMN_VAT_NUMBER = "VAT_number";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_ADDRESS = "address";

    public static final String TABLE_ORDER = "orders";
    public static final String TABLE_ITEMS = "items";
    //Constructor
    public DatabaseHandler(Context context, String name,
                           SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PRODUCTS + "(" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRODUCT_NAME + " TEXT," +
                COLUMN_PRODUCT_FAMILY + " TEXT," +
                COLUMN_PRICE + " REAL" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        String CREATE_SUPPLIERS_TABLE = "CREATE TABLE " +
                TABLE_SUPPLIERS + "(" +
                COLUMN_SUPPLIER_ID + " INTEGER PRIMARY KEY," +
                COLUMN_SUPPLIER_NAME + " TEXT," +
                COLUMN_VAT_NUMBER + " TEXT," +
                COLUMN_PHONE_NUMBER + " TEXT," +
                COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_SUPPLIERS_TABLE);

//        String CREATE_ORDER_TABLE = "CREATE TABLE " +
//                TABLE_ORDER + "(" +
//                "order_id" + " INTEGER PRIMARY KEY," +
//                "date" + " TEXT," +
//                "total_price" + " TEXT," +
//                "document_path" + " TEXT" + ")";
//        db.execSQL(CREATE_ORDER_TABLE);

        db.execSQL("DROP TABLE IF EXISTS `product`");
        db.execSQL("CREATE TABLE `product` (\n" +
                "  `product_id` int NOT NULL,\n" +
                "  `name` varchar(100) NOT NULL,\n" +
                "  `product_family` varchar(45) NOT NULL,\n" +
                "  `price` float NOT NULL,\n" +
                "  PRIMARY KEY (`product_id`)\n" +
                ")");
        db.execSQL("INSERT INTO `product` VALUES (181,'Tsakiris Chips salted','Snacks',1.3),(182,'Cheetos Pakotinia','Snacks',1.33),(183,'Bonora chips oregano ','Snacks',0.74),(184,'Bonora chips BBQ','Snacks',0.74),(185,'Poppers popcorn salted','Snacks',0.95),(186,'Lays oregano','Snacks',1.56),(187,'Cheetos Lotto','Snacks',1.33),(188,'Ruffles BBQ','Snacks',1.58),(189,'Ruffles oregano','Snacks',1.48),(190,'Doritos nacho cheese','Snacks',1.16),(191,'Tasty pitsinia','Snacks',1.33),(192,'Lays στο Φούρνο BBQ','Snacks',1.46),(193,'Lays πατατάκια ολικής άλεσης με πιπέρι','Snacks',1.7),(194,'Tsakiris πατατάκια με ρίγανη','Snacks',1.3),(195,'El Sabor Nacho chips Cheese','Snacks',1.75),(196,'Ruffles με αλάτι','Snacks',1.48),(197,'Cheetos δρακουλίνια','Snacks',1.33),(198,'Lays πατατάκια με αλάτι','Snacks',1.56),(199,'Poppers ποπ κορν με βούτηρο','Snacks',1.04),(200,'El Sabor nacho chips chili','Snacks',1.75),(201,'Lays πατατάκια με αλάτι και ξύδι','Snacks',1.56),(202,'Jumbo snack γαριδάρες','Snacks',0.97),(203,'Bonora πατατάκια με ρίγανη','Snacks',0.72),(204,'Bonora πατατάκια κυματιστά με αλάτι','Snacks',0.74),(205,'Lays πατατάκια Ολικής άλεσης με αλάτι','Snacks',1.7),(206,'ΑΛΛΑΤΙΝΗ κράκερς αλμυρά','Snacks',0.44),(207,'ΠΑΠΑΔΟΠΟΥΛΟΥ Batonettes crackers ','Snacks',0.54),(208,'ΠΑΠΑΔΟΠΟΥΛΟΥ Pick Crackers Classic','Snacks',1.08),(209,'ΑΛΛΑΤΙΝΗ Nak αλμυρά','Snacks',0.44),(210,'Elite crackers Μεσογειακά Γραβιέρα Μέλι & Σουσάμι','Snacks',1.26),(211,'ΠΑΠΑΔΟΠΟΥΛΟΥ mini Pick Crackers','Snacks',0.72),(212,'Πασατέμπος Tasty natural 70gr','Snacks',1.3),(213,'Ηλιόσπορος Tasty natural 71gr','Snacks',1.3),(214,'Κελυφωτά φιστίκια Tasty natural 85gr','Snacks',2.5),(215,'Αράπικα φιστίκια Tasty natural 85gr','Snacks',1.3),(216,'Φουντούκια Tasty natural 90gr','Snacks',3.2),(217,'Αμύγδαλα Tasty natural 90gr','Snacks',2.5),(218,'Κάσιους Tasty natural 90gr','Snacks',2.5),(219,'Peanuts με ρίγανη Pellito 150gr','Snacks',2),(220,'Mediterranean Mix Pellito 125gr','Snacks',4),(221,'Στεργίου αράβικη πίτα γαλοπούλα','Sandwich',2.3),(222,'7Days Bake Rolls κλασική 160gr','Snacks',1.3),(223,'7Days Bake Rolls σκόρδο 160gr','Snacks',1.3),(224,'7Days Bake Rolls pizza 160gr','Snacks',1.3),(225,'Στεργίου Φρατζολάκι ψητό κοτόπουλο','Sandwich',2.5),(226,'Στεργίου Κρουασάν καπνιστή γαλοπούλα','Sandwich',2.5),(227,'Panini σάντουιτς γαλοπούλα','Sandwich',2.5),(228,'Pringles original 165gr','Snacks',3.5),(229,'Panini strudel κοτόπουλο','Sandwich',2.8),(230,'Panini strudel γαλοπούλα','Sandwich',2.8),(231,'Παπαδοπούλου παξιμάδια Krispies με σουσάμι 200gr','Snacks',1.6),(232,'Elite crackers μεσογειακά φυσική γεύση με σουσάμι 105gr','Snacks',1.5),(233,'Elite crackers μεσογειακά πίγανη και φέτα 105gr','Snacks',1.5),(234,'Pick Crackers 100gr','Snacks',1.2),(235,'Pick cocktail 335gr','Snacks',2.1),(236,'Pick αστέρι','Snacks',2.1),(237,'Mini Pick crackers classic 250gr','Snacks',1.5),(238,'Mini Pick crackers pizza 90gr','Snacks',0.9),(239,'Mini Pick crackers bbq 90gr','Snacks',0.9),(240,'Mini Pick crackers ρίγανη 90gr','Snacks',0.9),(241,'Παπαδοπούλου Cream Crackers με κινόα 195gr','Snacks',2.2),(242,'Pringles sour cream 40gr','Snacks',1.1),(243,'Pringles paprika 40gr','Snacks',1.1),(244,'Pringles BBQ 40gr','Snacks',1.1),(245,'Pringles Texas BBQ 165gr','Snacks',2.5),(246,'Pringles Bacon 165gr','Snacks',2.3),(247,'Pringles Hot paprika 165gr','Snacks',2.7),(248,'Pringles Cream and Onion 165gr','Snacks',2.6),(249,'Tottis πατατάκια με αλάτι 110gr','Snacks',1),(250,'Tottis πατατάκια με ρίγανη 110gr','Snacks',1),(251,'Foudounia Tasty 125gr','Snacks',1.4),(252,'Ruffles πατατάκια ketchup 105gr','Snacks',1.6),(253,'Extra γαριδάκια pizza 115gr','Snacks',1),(254,'Extra γαριδάκια pizza 55gr','Snacks',0.5),(255,'Chipita πατατάκια ρίγανη 55gr','Snacks',0.6),(256,'Chipita πατατάκια αλάτι 55gr','Snacks',0.6),(257,'Chipita πατατάκια ρίγανη 120gr','Snacks',1.1),(258,'Chipita πατατάκια αλάτι 120gr','Snacks',1.1),(259,'Chipita πατατάκια BBQ 120gr','Snacks',1.1),(260,'Chipita πατατάκια tsipers κυματιστά αλάτι 120gr','Snacks',1.1),(261,'Chipita πατατάκια tsipers κυματιστά ρίγανη 120gr','Snacks',1.1),(262,'Chipita πατατάκια tsipers κυματιστά BBQ 120gr','Snacks',1.1),(263,'Tsakiris sticks αλάτι 100gr','Snacks',1.75),(264,'Tsakiris πατατάκια pizza 120gr','Snacks',1.9),(265,'Tsakiris πατατάκια ρίγανη 120gr','Snacks',1.9),(266,'Tsakiris πατατάκια κυματιστά BBQ 100gr','Snacks',1.5),(267,'Tsakiris πατατάκια κυματιστά αλάτι 100gr','Snacks',1.5),(268,'Tsakiris πατατάκια κυματιστά ρίγανη 100gr','Snacks',1.5),(269,'Tsakiris πατατάκια με γεύση πατάτες φούρνου 100gr','Snacks',1.6),(270,'Nacho chili 100gr','Snacks',1.1),(271,'Nacho natural 100gr','Snacks',1.1),(272,'Nacho cheese 100gr','Snacks',1.1),(273,'Jumbo fofico snack 100gr','Snacks',1.1),(274,'Jumbo sticks 100gr','Snacks',1.2),(275,'Pop O Tops classic 85gr','Snacks',1.25),(276,'Pop O Tops BBQ 85gr','Snacks',1.1),(277,'Jumbo chips κυματιστά αλάτι 280gr','Snacks',2.15),(278,'Jumbo chips κυματιστά ρίγανη 130gr','Snacks',1.1),(279,'Jumbo chips κυματιστά ρίγανη 280gr','Snacks',2),(280,'Jumbo chips χωρίς αλάτι 130gr','Snacks',1.1)");
        db.execSQL("INSERT INTO `product` VALUES (1,'Absolut Vodka 0.35 l','Alcoholic Drinks',15.2),(2,'Absolut Vodka 0.7 l','Alcoholic Drinks',25.4),(3,'Bacardi Breezer Lemon Bottle 0.275 l','Alcoholic Drinks',3.6),(4,'Bacardi Breezer Orange Bottle 0.275 l','Alcoholic Drinks',3.6),(5,'Bacardi Breezer Watermelon Bottle 0.275 l','Alcoholic Drinks',3.6),(6,'Bacardi Rum Blanca 0.7 l','Alcoholic Drinks',27.3),(7,'Beefeater Gin 24 0.7 l','Alcoholic Drinks',41.6),(8,'Belvedere Vodka 0.7 l','Alcoholic Drinks',53.4),(9,'Chivas Whiskey 12 years 0.7 l','Alcoholic Drinks',39.5),(10,'Cutty Shark Whiskey 0.35 l','Alcoholic Drinks',15.4),(11,'Cutty Shark Whiskey 0.7 l','Alcoholic Drinks',25.7),(12,'The Famous Grouse Whsikey 0.7 l','Alcoholic Drinks',26.2),(13,'Finsbury Gin 0.7 l','Alcoholic Drinks',20.8),(14,'Gordons Gin 0.35 l','Alcoholic Drinks',14),(15,'Gordons Gin 0.7 l','Alcoholic Drinks',25.8),(16,'Gordons Gin Premium Pink 0.7 l','Alcoholic Drinks',28.1),(17,'Grey Goose Vodka 0.7 l','Alcoholic Drinks',65.6),(18,'Haig Whiskey 0.7 l','Alcoholic Drinks',28.2),(19,'Havana Club Rum Anejo Reserva 0.7 l','Alcoholic Drinks',33.5),(20,'Havana Club Rum No.3 0.7 l','Alcoholic Drinks',27.1),(21,'Havana Club Rum No.7 0.7 l','Alcoholic Drinks',35.3),(22,'Jack Daniels Whiskey 0.35 l','Alcoholic Drinks',21.5),(23,'Jack Daniels Whsikey 0.7 l','Alcoholic Drinks',35.9),(24,'Jack Daniels Whiskey Honey 0.7 l','Alcoholic Drinks',37.1),(25,'Jagermeister Coolpack 0.35 l','Alcoholic Drinks',15.1),(26,'Jagermeister Liquer 0.7 l','Alcoholic Drinks',25),(27,'Jameson Whiskey 0.35 l','Alcoholic Drinks',16.7),(28,'Jameson Whiskey 0.7 l','Alcoholic Drinks',28.7),(29,'Johnnie Walker Whiskey Black Label 0.35 l','Alcoholic Drinks',20.4),(30,'Johnnie Walker Whiskey Black Label 0.7 l','Alcoholic Drinks',42.8),(31,'Johnnie Walker Whiskey Double Black 0.7 l','Alcoholic Drinks',52.2),(32,'Johnnie Walker Whiskey Red Label 0.35 l','Alcoholic Drinks',16.4),(33,'Johnnie Walker Whiskey Red Label 0.7 l','Alcoholic Drinks',27.5),(34,'Jose Cuervo Yellow Tequila 0.7 l','Alcoholic Drinks',31.5),(35,'Jose Cuervo White Tequila 0.7 l','Alcoholic Drinks',31.5),(36,'Martini Asti Champagne 0.75 l','Alcoholic Drinks',13.1),(37,'Metaxa Cognac No.3 0.35 l','Alcoholic Drinks',11.5),(38,'Metaxa Cognac No.3 0.7 l','Alcoholic Drinks',20.6),(39,'Metaxa Cognac No.5 0.2 l','Alcoholic Drinks',8),(40,'Metaxa Cognac No.5 0.7 l','Alcoholic Drinks',24.8),(41,'Metaxa Cognac No.7 0.7 l','Alcoholic Drinks',33.5),(42,'Moet & Chandon Champagne 0.75 l','Alcoholic Drinks',68.6),(43,'Serkova Vodka 0.7 l','Alcoholic Drinks',20.8),(44,'Sierra Yellow Tequila 0.7 l\t','Alcoholic Drinks',29.8),(45,'Stolichnaya Vodka 0.2 l','Alcoholic Drinks',10.3),(46,'Stolichnaya Vodka 0.7 l','Alcoholic Drinks',24.5),(47,'Stolichnaya Vodka Citros 0.7 l','Alcoholic Drinks',25.8),(48,'Stolichnaya Vodka Gold 0.7 l\t','Alcoholic Drinks',41.9),(49,'Stolichnaya Vodka Raspberry 0.7 l','Alcoholic Drinks',25.8),(50,'Stolichnaya Vodka Salted Caramel 0.7 l','Alcoholic Drinks',25.8),(51,'Stolichnaya Vodka Vanilla 0.7 l','Alcoholic Drinks',25.8),(52,'Tullamore Dew Whiskey 0.7 l','Alcoholic Drinks',29.9),(53,'Avantis Armonia Gis Red Dry Wine 0.75 l','Alcoholic Drinks',8.3),(54,'Avantis Armonia Gis White Wine 0.75 l','Alcoholic Drinks',8.3),(55,'Idoniko Tsipouro with Anise 0.2 l','Alcoholic Drinks',6.8),(56,'Idoniko Tsipouro with Anise 0.7 l','Alcoholic Drinks',20.9),(57,'Idoniko Tsipouro without Anise 0.2 l','Alcoholic Drinks',6.7),(58,'Idoniko Tsipouro without Anise 0.7 l','Alcoholic Drinks',20.6),(59,'Vivlia Chora Estate Red Wine 0.75 l','Alcoholic Drinks',23.9),(60,'Vivlia Chora Estate White Wine 0.75 l','Alcoholic Drinks',19),(61,'Kyr Gianni Akakies Rose Dry Wine 0.75 l','Alcoholic Drinks',12.2),(62,'Kyr Gianni Mple Trakter Red Dry Wine 0.75 l','Alcoholic Drinks',13.8),(63,'Kyr Gianni Akakies White Dry Wine 0.75 l','Alcoholic Drinks',13.3),(64,'Kyr Gianni Paragka Red Dry Wine 0.75 l','Alcoholic Drinks',12.6),(65,'Kyr Gianni Paragka White Wine 0.75 l','Alcoholic Drinks',10.7),(66,'Kyr Gianni Paragka Rose Dry Wine 0.75 l','Alcoholic Drinks',10.7),(67,'Boutari Simeio Stiksis Red Wine 0.75 l','Alcoholic Drinks',8.6),(68,'Ouzo 12 0.2 l','Alcoholic Drinks',5),(69,'Ouzo 12 0.35 l','Alcoholic Drinks',8.3),(70,'Ouzo 12 0.7 l','Alcoholic Drinks',14.8),(71,'Ouzo Plomariou 0.2 l','Alcoholic Drinks',5.3),(72,'Ouzo Plomariou 0.7 l','Alcoholic Drinks',18.4),(73,'Tsantali Caramelo Red Semi-Sweet Wine 0.75 l','Alcoholic Drinks',10.7),(74,'Tsantali Caramelo White Semi-Sweet Wine 0.75 l','Alcoholic Drinks',10.5),(75,'Tsantali Caramelo Rose Semi-Sweet Wine 0.75 l','Alcoholic Drinks',10.7),(76,'Tsantali Kanenas Red Wine 0.75 l','Alcoholic Drinks',17.3),(77,'Tsantali Kanenas White Wine 0.75 l','Alcoholic Drinks',13.4),(78,'Tsantali Kanenas Rose Wine 0.75 l','Alcoholic Drinks',15.3),(79,'Tsilili Tsipouro with Anise 0.2 l','Alcoholic Drinks',5.5),(80,'Tsilili Tsipouro with Anise 0.7 l','Alcoholic Drinks',19.1),(81,'Tsilili Tsipouro without Anise 0.2 l','Alcoholic Drinks',5.5),(82,'Tsilili Tsipouro without Anise 0.7 l','Alcoholic Drinks',19),(83,'Tsantali Ouzo 0.2 l','Alcoholic Drinks',5.3),(84,'Smirnoff Red Vodka 0.35 l','Alcoholic Drinks',14.3),(85,'Standard Vodka 0.7 l','Alcoholic Drinks',26.6),(86,'Campari Bitter 0.7 l','Alcoholic Drinks',25.7),(87,'Hell Energy Coffee Cappuccino 0.25 l','Energy Drinks',1.2),(88,'Hell Energy Drink Classic 0.25 l','Energy Drinks',1.2),(89,'Hell Energy Drink Red Grape 0.25 l','Energy Drinks',1.2),(90,'Hell Energy Drink Apple 0.25 l','Energy Drinks',1.2),(91,'Amita 100% Apple-Orange-Carrot 1 l','Juices',2.6),(92,'Amita Sour Cherry Paper Packaging 1 l','Juices',2.6),(93,'Amita Sour Cherry Paper Packaging 0.25 l','Juices',1.1),(94,'Amita Banana Paper Packaging 1 l','Juices',2.7),(95,'Amita Apple-Orange-Apricot Paper Packaging 1 l','Juices',2.6),(96,'Amita Apple-Orange-Apricot Paper Packaging 1 l','Juices',1.9),(97,'Amita Apple-Orange-Apricot Paper Packaging 0.25 l','Juices',1.1),(98,'Amita Peach Paper Packaging 1 l','Juices',1.1),(99,'Amita Motion Supermix Paper Packaging 1 l','Juices',3.6),(100,'Amita Motion Supermix Paper Packaging 0.33 l','Juices',1.8),(101,'Amita Motion Paper Packaging 0.33 l','Juices',1.6),(102,'Amita Apple-Orange-Carrot Paper Packaging 0.33 l','Juices',1.6),(103,'Amita Orange Paper Packaging 1 l','Juices',2.5),(104,'Coca Cola Light Can 0.33 l','Soft Drinks',1.1),(105,'Coca Cola Light Bottle 0.5 l','Soft Drinks',1.6),(106,'Coca Cola Light Bottle 1.5 l','Soft Drinks',2.5),(107,'Coca Cola Zero Can 0.33 l','Soft Drinks',1.1),(108,'Coca Cola Zero Lemon Can 0.33 l','Soft Drinks',1.1),(109,'Coca Cola Zero Bottle 0.5 l','Soft Drinks',1.6),(110,'Coca Cola Zero Bottle 1.5 l','Soft Drinks',2.5),(111,'Coca Cola Can 0.33 l','Soft Drinks',1.1),(112,'Coca Cola Bottle 0.5 l','Soft Drinks',1.5),(113,'Coca Cola Bottle 1.5 l','Soft Drinks',2.3),(114,'Fanta Lemon Can 0.33 l','Soft Drinks',1),(115,'Fanta Lemon Bottle 0.5 l','Soft Drinks',1.4),(116,'Fanta Lemon Bottle 1.5 l','Soft Drinks',2.3),(117,'Fanta Orange Can 0.33 l','Soft Drinks',1),(118,'Fanta Orange Bottle 0.5 l','Soft Drinks',1.4),(119,'Fanta Orange Bottle 1.5 l','Soft Drinks',2.3),(120,'7 up Can 0.33 l','Soft Drinks',1.3),(121,'Frulite Boost Orange-Apricot-Apple Bottle 0.5 l','Juices',2.3),(122,'Frulite Boost Orange-Nectarine Bottle 0.5 l','Juices',2.3),(123,'Frulite Raspberry-Lemon Paper Packaging 0.33 l','Juices',1.4),(124,'Frulite Pineapple-Coconut Paper Packaging 0.33 l','Juices',1.4),(125,'Frulite Carrot Paper Packaging 0.33 l','Juices',1.4),(126,'Frulite Tangerine-Sanguine Paper Packaging 0.33 l','Juices',1.4),(127,'Frulite Tangerine-Sanguine Paper Packaging 1 l','Juices',2.9),(128,'Frulite Banana-Sour Cherry Paper Packaging 0.33 l','Juices',1.4),(129,'Lipton Ice Tea No Sugar Lemon Bottle 0.5 l','Soft Drinks',1.9),(130,'Lipton Ice Tea No Sugar Green Lemon Bottle 0.5 l','Soft Drinks',1.9),(131,'Lipton Ice Tea No Sugar Peach Bottle 0.5 l','Soft Drinks',1.9),(132,'Lipton Ice Tea Lemon Bottle 0.5 l','Soft Drinks',1.7),(133,'Lipton Ice Tea Green Lemon Bottle 0.5 l','Soft Drinks',1.7),(134,'Lipton Ice Tea Green Lemon Can 0.33 l','Soft Drinks',1.2),(135,'Lipton Ice Tea Peach Can 0.33 l','Soft Drinks',1.2),(136,'Lipton Ice Tea Peach Bottle 0.5 l','Soft Drinks',1.7),(137,'Monster Energy Drink Absolutely Zero Can 0.5 l','Energy Drinks',2.2),(138,'Monster Energy Drink Classic Can 0.5 l','Energy Drinks',2.2),(139,'Monster Energy Drink Juiced Can 0.5 l','Energy Drinks',2.2),(140,'Monster Energy Drink Punch Pacific Can 0.5 l','Energy Drinks',2.2),(141,'Monster Energy Drink Punch Pipeline Can 0.5 l','Energy Drinks',2.2),(142,'Monster Energy Drink The Doctor Can 0.5 l','Energy Drinks',2.2),(143,'Monster Energy Drink Ultra Zero Can 0.5 l','Energy Drinks',2.2),(144,'Pepsi Cola Max Bottle 0.5 l','Soft Drinks',1.3),(145,'Pepsi Cola Max Bottle 1.5 l','Soft Drinks',2.3),(146,'Pepsi Cola Twist Can 0.33 l','Soft Drinks',1),(147,'Pepsi Cola Twist Lemon Bottle 1.5 l','Soft Drinks',2.2),(148,'Pepsi Cola Can 0.33 l','Soft Drinks',1),(149,'Pepsi Cola Bottle 1.5 l','Soft Drinks',2.2),(150,'Powerade Energy Drink Mountain Blast 0.5 l','Energy Drinks',1.9),(151,'Red Bull Energy Drink Can 0.25 l','Energy Drinks',1.9),(152,'Red Bull Energy Drink Can 0.355 l','Energy Drinks',2.4),(153,'Red Bull Sugar Free Energy Drink Can 0.25 l','Energy Drinks',1.9),(154,'Schweppes Ginger Can 0.33 l','Soft Drinks',1.2),(155,'Schweppes Mojito Can 0.33 l','Soft Drinks',1.2),(156,'Schweppes Orangeade Can 0.33 l','Soft Drinks',1.2),(157,'Schweppes Pink Grapefruit Can 0.33 l','Soft Drinks',1.2),(158,'Schweppes Lemonade Bergamot-Ibiscus Can 0.33 l','Soft Drinks',1.2),(159,'Schweppes Soda Can 0.33 l','Soft Drinks',0.8),(160,'Schweppes Soda Bottle 0.5 l','Soft Drinks',1.2),(161,'Schweppes Tonic Can 0.33 l','Soft Drinks',0.8),(162,'Schweppes Tonic Bottle 0.5 l','Soft Drinks',1.2),(163,'Sprite Can 0.33 l','Soft Drinks',1.1),(164,'Sprite Bottle 0.5 l','Soft Drinks',1.4),(165,'Sprite Bottle 1.5 l','Soft Drinks',2.3),(166,'Vikos Water 1.5 l','Waters',0.7),(167,'Zagori Athletic Water 0.75 l','Waters',0.9),(168,'Zagori Water Bottle 1.5 l','Waters',0.7),(169,'Mitsikeli Water Bottle 0.5 l','Waters',0.5),(170,'Mitsikeli Water Bottle 0.75 l','Waters',0.6),(171,'Amstel Beer Bottle 0.5 l','Alcoholic Drinks',2.3),(172,'Fix Beer Can 0.5 l','Alcoholic Drinks',1.5),(173,'Henninger Beer Can 0.33 l','Alcoholic Drinks',1.1),(174,'Kaiser Beer Bottle','Alcoholic Drinks',2.7),(175,'Mythos Beer Can 0.33 l','Alcoholic Drinks',1.3),(176,'Mythos Beer Bottle 0.5 l','Alcoholic Drinks',1.3),(177,'Somersby Cider Pear Bottle 0.33 l','Alcoholic Drinks',1.7),(178,'Somersby Cider Watermelon Bottle 0.33 l','Alcoholic Drinks',1.7),(179,'Somersby Cider Apple Bottle 0.33 l','Alcoholic Drinks',1.7),(180,'Vergina Beer Bottle 0.5 l','Alcoholic Drinks',1.8);\n");
        db.execSQL("INSERT INTO `product` VALUES (281,'Old Holborn Blonde 30g','Cigars & Tobacco',7.7),(282,'Old Holborn Yellow 30g','Cigars & Tobacco',7.7),(283,'Old Holborn Blue 30g','Cigars & Tobacco',7.7),(284,'Old Holborn Green 30g','Cigars & Tobacco',7.7),(285,'Old Holborn Orange 30g','Cigars & Tobacco',7.7),(286,'Karelia Blond 30g','Cigars & Tobacco',7.4),(287,'Karelia Blue 30g','Cigars & Tobacco',7.4),(288,'Karelia Orange 30g','Cigars & Tobacco',7.4),(289,'Karelia Blue 20s','Cigars & Tobacco',3.9),(290,'Karelia White 20s','Cigars & Tobacco',3.9),(291,'Karelia Mint 20s','Cigars & Tobacco',3.9),(292,'Karelia Blue 100s','Cigars & Tobacco',3.9),(293,'Karelia White 100s','Cigars & Tobacco',3.9),(294,'Karelia Mint 100s','Cigars & Tobacco',3.9),(295,'Karelia Standard','Cigars & Tobacco',4.3),(296,'Karelia Standard Light','Cigars & Tobacco',4.3),(297,'Ome Slims White','Cigars & Tobacco',3.9),(298,'Ome Slims Mint','Cigars & Tobacco',3.9),(299,'Ome Slims Pink','Cigars & Tobacco',3.9),(300,'Ome Slims Orange','Cigars & Tobacco',3.9),(301,'Karelia Slims','Cigars & Tobacco',3.9),(302,'Karelia Slims Mint','Cigars & Tobacco',3.9),(303,'Karelia Slims Blue','Cigars & Tobacco',3.9),(304,'Karelia Slims White','Cigars & Tobacco',3.9),(305,'Marlboro Red 20s','Cigars & Tobacco',4.6),(306,'Marlboro Gold 20s','Cigars & Tobacco',4.6),(307,'Marlboro Red Soft 20s','Cigars & Tobacco',4.6),(308,'Marlboro Gold Soft 20s','Cigars & Tobacco',4.6),(309,'Marlboro Red 100s','Cigars & Tobacco',4.6),(310,'Marlboro Red 100s','Cigars & Tobacco',4.6),(311,'Marlboro Red 24s','Cigars & Tobacco',5),(312,'Marlboro Gold 24s','Cigars & Tobacco',5),(313,'Marlboro Red Pocket 20s','Cigars & Tobacco',4.1),(314,'Marlboro Gold Pocket 20s','Cigars & Tobacco',4.1),(315,'Marlboro Red 30g','Cigars & Tobacco',7.7),(316,'Marlboro Gold 30g','Cigars & Tobacco',7.7),(317,'Marlboro Touch 30g','Cigars & Tobacco',7.7),(318,'Marlboro Touch 20s','Cigars & Tobacco',4.1),(319,'Marlboro Touch Slims','Cigars & Tobacco',4.1),(320,'Davidoff Gold 20s','Cigars & Tobacco',4.6),(321,'Davidoff White 20s','Cigars & Tobacco',4.6),(322,'Davidoff Blue 20s','Cigars & Tobacco',4.6),(323,'Davidoff Classic 20s','Cigars & Tobacco',4.6),(324,'Davidoff Silver 20s','Cigars & Tobacco',4.6),(325,'Davidoff Gold Slims','Cigars & Tobacco',4.6),(326,'Davidoff Blue Slims','Cigars & Tobacco',4.6),(327,'Davidoff Classic Slims','Cigars & Tobacco',4.6),(328,'Davidoff SilverSlims','Cigars & Tobacco',4.6),(329,'Davidoff Magenta Slims','Cigars & Tobacco',4.6),(330,'Winston Red 20s','Cigars & Tobacco',4),(331,'Winston Blue 20s','Cigars & Tobacco',4),(332,'Winston Red 100s','Cigars & Tobacco',4),(333,'Winston Blue 100s','Cigars & Tobacco',4),(334,'Winston Blue Slims','Cigars & Tobacco',3.8),(335,'Winston White Slims','Cigars & Tobacco',3.8),(336,'Winston Blue Super Slims','Cigars & Tobacco',3.8),(337,'Winston White Super Slims','Cigars & Tobacco',3.8),(338,'Craven Yellow 30g','Cigars & Tobacco',7.7),(339,'Craven Blue 30g','Cigars & Tobacco',7.7),(340,'Lucky Strike 30g','Cigars & Tobacco',7.5),(341,'Lucky Strike 20s','Cigars & Tobacco',4.1),(342,'Lucky Strike Soft 20s','Cigars & Tobacco',4.1),(343,'Drum Yellow 30g','Cigars & Tobacco',7.6),(344,'Drum White 30g','Cigars & Tobacco',7.6),(345,'Drum Blue 30g','Cigars & Tobacco',7.6),(346,'Drum Brown 30g','Cigars & Tobacco',7.6),(347,'Heets Red','Cigars & Tobacco',4),(348,'Heets Yellow','Cigars & Tobacco',4),(349,'Heets Silver','Cigars & Tobacco',4),(350,'Heets Brown','Cigars & Tobacco',4),(351,'Heets Mint','Cigars & Tobacco',4),(352,'Heets Purple','Cigars & Tobacco',4),(353,'Heets Orange','Cigars & Tobacco',4),(354,'Old Holborn Filters','Tobacco Essentials',0.5),(355,'Old Holborn Filters Big','Tobacco Essentials',1.1),(356,'Rizla Papers Red','Tobacco Essentials',0.5),(357,'Rizla Papers Blue','Tobacco Essentials',0.5),(358,'Rizla Papers Green','Tobacco Essentials',0.5),(359,'Rizla Papers White','Tobacco Essentials',0.5),(360,'Rizla Papers Pink','Tobacco Essentials',0.5),(361,'Rizla Papers Orange','Tobacco Essentials',0.5),(362,'Rizla Papers Cyan','Tobacco Essentials',0.5),(363,'Rizla Papers Black','Tobacco Essentials',0.5),(364,'Rizla Papers Silver','Tobacco Essentials',0.5),(365,'Rizla Papers Micron','Tobacco Essentials',0.5),(366,'Old Holborn Papers Green','Tobacco Essentials',0.5),(367,'Old Holborn Papers Blue','Tobacco Essentials',0.5),(368,'Old Holborn Papers Silver','Tobacco Essentials',0.5),(369,'Rizla Filters Red','Tobacco Essentials',1.1),(370,'Rizla Filters Yellow','Tobacco Essentials',1.1),(371,'Rizla Filters Blue','Tobacco Essentials',0.5),(372,'Rizla Filters Blue Big','Tobacco Essentials',1.1),(373,'Rizla Filters Black','Tobacco Essentials',0.5),(374,'Rizla Filters Black Big','Tobacco Essentials',1.1),(375,'Swan Yellow','Tobacco Essentials',1.1),(376,'Swan Mint','Tobacco Essentials',1.1),(377,'Swan Red','Tobacco Essentials',1.1),(378,'Tou Pappou Pink','Tobacco Essentials',0.5),(379,'Tou Pappou White','Tobacco Essentials',0.5),(380,'Tou Pappou Brown','Tobacco Essentials',0.5),(381,'Tou Pappou Semi-Brown','Tobacco Essentials',0.5),(382,'Clipper Lighter Small','Tobacco Essentials',1),(383,'Clipper Lighter','Tobacco Essentials',1.2),(384,'Big Lighter Small','Tobacco Essentials',1),(385,'Big Lighter','Tobacco Essentials',1.2),(386,'OCB Papers Blue','Tobacco Essentials',0.5),(387,'OCB Papers Black','Tobacco Essentials',0.5),(388,'OCB Papers Blue Big','Tobacco Essentials',1),(389,'OCB Papers Black Big','Tobacco Essentials',1),(390,'OCB Filtrers','Tobacco Essentials',0.5),(391,'Raw Filtrers','Tobacco Essentials',0.5),(392,'Raw Papers','Tobacco Essentials',0.5),(393,'Raw Papers Natural','Tobacco Essentials',0.5),(394,'Pueblo Yellow 30g','Cigars & Tobacco',8.3),(395,'Pueblo Cyan 30g','Cigars & Tobacco',8.3),(396,'Van Nelle 30g','Cigars & Tobacco',7.7),(397,'Golden Virginia Yellow 30g','Cigars & Tobacco',7.3),(398,'Golden Virginia Green 30g','Cigars & Tobacco',7.3),(399,'Golden Virginia Pink 30g','Cigars & Tobacco',7.3),(400,'Oriental Mist 30g','Cigars & Tobacco',7.4);\n");
        db.execSQL("INSERT INTO `product` VALUES (401,'ION 100g','Chocolates',2),(402,'ION 70g','Chocolates',1.3),(403,'ION Almond 100g','Chocolates',2),(404,'ION Almond 70g','Chocolates',1.3),(405,'Pavlidis Dark 100g','Chocolates',1.8),(406,'Pavlidis Dark with Orange 100g','Chocolates',1.8),(407,'Lacta 100g','Chocolates',1.7),(408,'Lacta Oreo 100g','Chocolates',1.7),(409,'Lacta Strawberry 100g','Chocolates',1.7),(410,'Lacta Caramel 100g','Chocolates',1.7),(411,'Soft Cookies Chocolate 180g','Biscuits',2.4),(412,'Soft Cookies Dark Chocolate 180g','Biscuits',2.4),(413,'Papadopoulou Caprice 400g','Biscuits',5),(414,'Papadopoulou Caprice 250g','Biscuits',4),(415,'Papadopoulou Caprice 115g','Biscuits',2.1),(416,'Papadopoulou Digestive Chocolate 200g','Biscuits',2.8),(417,'Papadopoulou Digestive Dark Chocolate 200g','Biscuits',2.8),(418,'Papadopoulou Gemista 200g','Biscuits',1.8),(419,'Papadopoulou Gemista Vanilla 200g','Biscuits',1.8),(420,'Papadopoulou Gemista Coconut 200g','Biscuits',1.8),(421,'Papadopoulou Gemista Banana 200g','Biscuits',1.8),(422,'Papadopoulou Gemista Strawberry 200g','Biscuits',1.8),(423,'Papadopoulou Gemista Lemon 200g','Biscuits',1.8),(424,'Papadopoulou Gemista Orange 200g','Biscuits',1.8),(425,'Papadopoulou Miranta 250g','Biscuits',2),(426,'7days Mini Croissants','Croissants',1),(427,'7days Mini Croissants Double Chocolate','Croissants',1),(428,'7days Mini Croissants Spumante','Croissants',1),(429,'7days Mini Croissants Millfeuille','Croissants',1),(430,'Molto 110g','Croissants',0.9),(431,'Molto Double Chocolate 110g','Croissants',0.9),(432,'Molto Cream and Cookies 110g','Croissants',0.9),(433,'Molto Cherry 110g','Croissants',0.9),(434,'7days Mousse 75g','Croissants',0.5),(435,'7days Mousse Chocolate 75g','Croissants',0.5),(436,'7days Strudel Apple 85g','Croissants',0.5),(437,'Choco Big 250g','Croissants',2),(438,'Bonjour Bueno 150g','Croissants',1),(439,'Bonjour Oreo 150g','Croissants',1),(440,'Bonjour Kiss 150g','Croissants',1),(441,'Algida Classic','Ice Cream',2.1),(442,'Algida Almond','Ice Cream',2.1),(443,'Algida White Chocolate','Ice Cream',2.1),(444,'Algida Classic Cone','Ice Cream',2.1),(445,'Algida Almond Cone','Ice Cream',2.1),(446,'BOSS Classic','Ice Cream',2.2),(447,'BOSS Almond','Ice Cream',2.2),(448,'BOSS Cookies','Ice Cream',2.2),(449,'Nirvana Praline and Cream 665g','Ice Cream',5.5),(450,'Nirvana Cookies and Cream 665g','Ice Cream',5.5);");

        db.execSQL("DROP TABLE IF EXISTS `suppliers`");
        db.execSQL("CREATE TABLE `suppliers` (\n" +
                "  `supplier_id` int NOT NULL,\n" +
                "  `name` varchar(45) NOT NULL,\n" +
                "  `VAT_number` varchar(10) NOT NULL,\n" +
                "  `phone_number` varchar(10) NOT NULL,\n" +
                "  `address` varchar(100) NOT NULL,\n" +
                "  `email` varchar(100) NOT NULL,\n" +
                "  PRIMARY KEY (`supplier_id`)\n" +
                ")");
        db.execSQL("INSERT INTO `suppliers` VALUES (1,'Σύλλελης','166269834','2310782923','Εθνικής Αμύνης 12','silleliskkk@gmail.com'),(2,'Καλούδης','168728092','2310392033','Λεωφόρος Ωραιοκάστρου 26','kaloudisandreas@gmail.com'),(3,'Τροφοεμπορική','167320923','2108944383','Αθανάσιου Διάκου 23, Χαλάνδρι','trofoemporikiinfo@gmail.com'),(4,'Τσακίρης','162198302','2103293744','25ης Μαρτίου 17-19, Αττική','tsakirisAE@gmail.com')");

        db.execSQL("CREATE TABLE `orders` (\n" +
                "  `order_id` int NOT NULL,\n" +
                "  `date` varchar(50) NOT NULL,\n" +
                "  `total_price` float NOT NULL,\n" +
                "  `document_path` varchar(150) NOT NULL,\n" +
                "  PRIMARY KEY (`order_id`)\n" +
                ")");

        db.execSQL("DROP TABLE IF EXISTS `category`");
        db.execSQL("CREATE TABLE `category` (\n" +
                "  `category_id` int NOT NULL,\n" +
                "  `name` varchar(50) NOT NULL,\n" +
                "  `supplier_id` varchar(100) NOT NULL,\n" +
                "  PRIMARY KEY (`category_id`)\n" +
                ")");
        db.execSQL("INSERT INTO `category` VALUES (1, 'Soft Drinks', 2),(2,'Alcoholic Drinks', 2),(3,'Energy Drinks', 2),(4, 'Juices', 2),(5, 'Waters', 2),(6, 'Snacks', 4),(7, 'Sandwich', 4),(8, 'Chocolates', 3),(9, 'Biscuits', 3),(10, 'Croissants', 3),(11, 'Ice Cream', 3),(12, 'Cigars & Tobacco', 1),(13, 'Tobacco Essentials', 1)");

        db.execSQL("CREATE TABLE `user` (\n" +
                "  `user_id` int NOT NULL,\n" +
                "  `password` varchar(50) NOT NULL,\n" +
                "  `name` varchar(100) NOT NULL,\n" +
                "  `VAT_number` varchar(10) NOT NULL,\n" +
                "  `email` varchar(100) NOT NULL,\n" +
                "  `phone` varchar(10) NOT NULL,\n" +
                "  `address` varchar(100) NOT NULL,\n" +
                "  `signedIn` boolean NOT NULL,\n" +
                "  PRIMARY KEY (`user_id`)\n" +
                ")");

        db.execSQL("CREATE TABLE `items` (\n" +
                "  `order_id` int NOT NULL,\n" +
                "  `product_id` int NOT NULL,\n" +
                "  `quantity` int NOT NULL,\n" +
                "  PRIMARY KEY (`order_id`,`product_id`)\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addItems(int order_id, Item[][] items){
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0; i<items.length; i++){
            for(int j=0; j<items[i].length; j++){
                if(items[i][j].getQuantity()>0){
                    ContentValues values = new ContentValues();
                    values.put("order_id", order_id);
                    values.put("product_id",items[i][j].getId());
                    values.put("quantity",items[i][j].getQuantity());
                    db.insert("items", null , values);
                }
            }
        }
        db.close();
    }

    public void updateItems(int order_id, Item[][] items){
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0; i<items.length; i++){
            for(int j=0; j<items[i].length; j++){
                if(items[i][j].getQuantity() > 0){
                    if(findItem(order_id,items[i][j].getId())){
                        ContentValues values = new ContentValues();
                        values.put("quantity",items[i][j].getQuantity());
                        long k = db.update("items", values , "order_id = ? and product_id = ?", new String[]{String.valueOf(order_id), String.valueOf(items[i][j].getId())});
                    }
                    else{
                        ContentValues values = new ContentValues();
                        values.put("order_id", order_id);
                        values.put("product_id",items[i][j].getId());
                        values.put("quantity",items[i][j].getQuantity());
                        db.insert("items", null , values);
                    }
                }
                else if(items[i][j].getQuantity() == 0){
                    if(findItem(order_id,items[i][j].getId()))
                        db.delete("items","order_id = ? and product_id = ?", new String[]{String.valueOf(order_id), String.valueOf(items[i][j].getId())});
                }
            }
        }
    }

    public boolean findItem(int order_id, int product_id){

        String query = "SELECT * FROM " + TABLE_ITEMS + " WHERE " +
                "order_id" + " = " + order_id + " and product_id = " + product_id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        return cursor.moveToFirst();
    }

    public int[][] getItems(int order_id){

        String query = "SELECT * FROM " + TABLE_ITEMS + " WHERE order_id = " + order_id ;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        int[][] items = new int[cursor.getCount()][2];
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            for(int i=0; i<cursor.getCount(); i++){
                items[i][0] = cursor.getInt(1);
                items[i][1] = cursor.getInt(2);
                try{
                    cursor.moveToNext();
                }catch (Exception e){
                    cursor.close();
                }
            }
        } else {
            items = null;
            Log.d("db","items in getItems is null");
        }
        db.close();
        return items;
    }

    public int getCategoryId(String category_name){
        String query = "SELECT * FROM category WHERE name = '" + category_name + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int category_id = 0;
        if(cursor.moveToFirst())
            category_id = cursor.getInt(0);
        return category_id;
    }

    public Item findProduct(int id) {
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCT_ID + " = " + id ;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Item product = new Item();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setName(cursor.getString(1));
            product.setPrice(Double.parseDouble(cursor.getString(3)));
            product.setCategory_id(getCategoryId(cursor.getString(2)) - 1);
            cursor.close();
        } else {
            product = null;
        }
        db.close();
        return product;
    }

    public Item[] getProducts(String category){
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCT_FAMILY + " = '" + category + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Item[] products = new Item[cursor.getCount()];
        if (cursor.moveToFirst()) {
            for(int i=0; i<cursor.getCount(); i++){
                products[i] = new Item();
                products[i].setId(Integer.parseInt(cursor.getString(0)));
                products[i].setName(cursor.getString(1));
                products[i].setPrice(Double.parseDouble(cursor.getString(3)));
                try{
                    cursor.moveToNext();
                }catch (Exception e){
                    cursor.close();
                }
            }

        } else {
            products = null;
            Log.d("db","products in getProducts is null");
        }
        db.close();
        return products;

    }

    public boolean addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("order_id", order.getOrderNumber());
        values.put("date", order.getDate());
        values.put("total_price", order.getTotalPrice());
        values.put("document_path", order.getDocumentPath());

        long i = db.insert("orders", null , values);
        db.close();
        addItems(order.getOrderNumber(), order.getItems());
        return i != -1;
    }

    public void updateOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("order_id", order.getOrderNumber());
        values.put("date", order.getDate().toString());
        values.put("total_price", order.getTotalPrice());
        values.put("document_path", order.getDocumentPath());

        db.update("orders", values , "order_id = ?", new String[]{String.valueOf(order.getOrderNumber())});
        Log.d("db", "orders have been updated");
        updateItems(order.getOrderNumber(), order.getItems());
        //db.close();
    }
    public Order getOrder(int id) {
        String query = "SELECT * FROM " + TABLE_ORDER+ " WHERE " +
                "order_id" + " = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        Order order = new Order();
        if (cursor.moveToFirst()) {
            Log.d("db",String.valueOf(cursor.getCount()));
            order.setOrderNumber(cursor.getInt(0));
            order.setDate(cursor.getString(1));
            order.setTotalPrice(Double.parseDouble(cursor.getString(2)));
            order.setDocumentPath(cursor.getString(3));
        } else {
            order = null;
        }
        db.close();
        return order;
    }

    public Order[] getAllOrders() {
        String query = "SELECT * FROM " + TABLE_ORDER + " ORDER BY order_id ASC";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Order[] orders = new Order[cursor.getCount()];
        if (cursor.moveToFirst()) {
            for(int i=0; i<cursor.getCount(); i++){
                orders[i] = new Order();
                orders[i].setOrderNumber(cursor.getInt(0));
                orders[i].setDate(cursor.getString(1));
                orders[i].setTotalPrice(Double.parseDouble(cursor.getString(2)));
                orders[i].setDocumentPath(cursor.getString(3));
                try{
                    cursor.moveToNext();
                }catch (Exception e){
                    cursor.close();
                }
            }

        }
        else {
            orders = null;
        }
        db.close();
        return orders;
    }

    public void deleteOrder(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ORDER, "'order_id' = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public Profile getSignedInUser(){
        String query = "SELECT * FROM 'user' WHERE " +
                "signedIn = true";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Profile user = new Profile();
        if(cursor.moveToFirst()){
            user.setProfileID(cursor.getInt(0));
            user.setPassword(cursor.getString(1));
            user.setOwnership(cursor.getString(2));
            user.setAfm(cursor.getString(3));
            user.setEmail(cursor.getString(4));
            user.setPhone(cursor.getString(5));
            user.setAddress(cursor.getString(6));
            user.setSignedIn(Boolean.parseBoolean(cursor.getString(7)));
        }
        else
            user = null;
        db.close();
        return user;
    }

    public Profile getUser(int id){
        String query = "SELECT * FROM 'user' WHERE " +
                "user_id = ?";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            Profile user = new Profile();
            user.setProfileID(cursor.getInt(0));
            user.setPassword(cursor.getString(1));
            user.setOwnership(cursor.getString(2));
            user.setAfm(cursor.getString(3));
            user.setEmail(cursor.getString(4));
            user.setPhone(cursor.getString(5));
            user.setAddress(cursor.getString(6));
            user.setSignedIn(Boolean.parseBoolean(cursor.getString(7)));
            db.close();
            return user;
        } else {
            db.close();
            return null;
        }
    }

    public int getNumberOfOrdersInDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM orders", null);
        cursor.moveToFirst();
        db.close();
        return cursor.getInt(0);
    }

    public Supplier getSupplierFromCategoryId(int categoryId) {
        Supplier supplier;
        SQLiteDatabase db = this.getWritableDatabase();
        //Getting supplier_id from category_id
        Cursor cursor = db.rawQuery("SELECT supplier_id FROM category WHERE category_id = " + String.valueOf(categoryId), null);
        cursor.moveToFirst();
        int supplierId = cursor.getInt(0);
        ////Getting supplier object from supplier_id
        cursor = db.rawQuery("SELECT * FROM suppliers WHERE supplier_id = " + String.valueOf(supplierId), null);
        cursor.moveToFirst();
        supplier = new Supplier(supplierId, cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        //db isn't closed because this function will close the connection pool for at least one more query in another function
        return supplier;
    }

    public int getCategoryIdFromProductId(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Getting category name from product_id
        Cursor cursor = db.rawQuery("SELECT product_family FROM product WHERE product_id = " + String.valueOf(productId), null);
        cursor.moveToFirst();
        String productFamily = "\"" +cursor.getString(0) + "\"";
        // //Getting category_id from category name
        cursor = db.rawQuery("SELECT category_id FROM category WHERE name = " + productFamily,null);
        //db isn't closed because this function will close the connection pool for at least one more query in another function
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public ArrayList<Supplier> getOrderSuppliers(int orderId) {
        ArrayList<Integer> productIds = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        //Getting product_ids from order's list of items and putting them in a list
        Cursor cursor = db.rawQuery("SELECT product_id FROM items WHERE order_id = " + String.valueOf(orderId), null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++) {
            productIds.add(cursor.getInt(0));
            cursor.moveToNext();
        }
        //Getting category_ids from product_ids (with use of custom function) and putting them in a list
        ArrayList<Integer> categoryIds = new ArrayList<>();
        for(int i = 0; i < productIds.size(); i++)
            categoryIds.add(getCategoryIdFromProductId(productIds.get(i)));
        //Getting supplier_objects from category_ids (with use of custom function) and putting them in a list
        ArrayList<Supplier> suppliers = new ArrayList<>();
        boolean duplicate = false;
        for(int i = 0; i < categoryIds.size(); i++) {
            //Checking for  duplicate supplier entries
            for (int j = 0; j < suppliers.size(); j++)
                if (suppliers.get(j).getSupplierId() == getSupplierFromCategoryId(categoryIds.get(i)).getSupplierId())
                    duplicate = true;
                if(!duplicate)
                    suppliers.add(getSupplierFromCategoryId(categoryIds.get(i))); //if the supplier isn't already in the list, add it
                duplicate = false;
        }
        db.close();
        return suppliers;
    }
}