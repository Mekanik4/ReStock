package com.example.restock;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.restock.objects.Item;

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
        db.execSQL("DROP TABLE IF EXISTS `product`");
        db.execSQL("CREATE TABLE `product` (\n" +
                "  `product_id` int NOT NULL,\n" +
                "  `name` varchar(100) NOT NULL,\n" +
                "  `product_family` varchar(45) NOT NULL,\n" +
                "  `price` float NOT NULL,\n" +
                "  PRIMARY KEY (`product_id`)\n" +
                ")");
        db.execSQL("INSERT INTO `product` VALUES (1,'Tsakiris Chips salted','snacks',1.3),(2,'Cheetos Pakotinia','snacks',1.33),(3,'Bonora chips oregano ','snacks',0.74),(4,'Bonora chips BBQ','snacks',0.74),(5,'Poppers popcorn salted','snacks',0.95),(6,'Lays oregano','snacks',1.56),(7,'Cheetos Lotto','snacks',1.33),(8,'Ruffles BBQ','snacks',1.58),(9,'Ruffles oregano','snacks',1.48),(10,'Doritos nacho cheese','snacks',1.16),(11,'Tasty pitsinia','snacks',1.33),(12,'Lays στο Φούρνο BBQ','snacks',1.46),(13,'Lays πατατάκια ολικής άλεσης με πιπέρι','snacks',1.7),(14,'Tsakiris πατατάκια με ρίγανη','snacks',1.3),(15,'El Sabor Nacho chips Cheese','snacks',1.75),(16,'Ruffles με αλάτι','snacks',1.48),(17,'Cheetos δρακουλίνια','snacks',1.33),(18,'Lays πατατάκια με αλάτι','snacks',1.56),(19,'Poppers ποπ κορν με βούτηρο','snacks',1.04),(20,'El Sabor nacho chips chili','snacks',1.75),(21,'Lays πατατάκια με αλάτι και ξύδι','snacks',1.56),(22,'Jumbo snack γαριδάρες','snacks',0.97),(23,'Bonora πατατάκια με ρίγανη','snacks',0.72),(24,'Bonora πατατάκια κυματιστά με αλάτι','snacks',0.74),(25,'Lays πατατάκια Ολικής άλεσης με αλάτι','snacks',1.7),(26,'ΑΛΛΑΤΙΝΗ κράκερς αλμυρά','snacks',0.44),(27,'ΠΑΠΑΔΟΠΟΥΛΟΥ Batonettes crackers ','snacks',0.54),(28,'ΠΑΠΑΔΟΠΟΥΛΟΥ Pick Crackers Classic','snacks',1.08),(29,'ΑΛΛΑΤΙΝΗ Nak αλμυρά','snacks',0.44),(30,'Elite crackers Μεσογειακά Γραβιέρα Μέλι & Σουσάμι','snacks',1.26),(31,'ΠΑΠΑΔΟΠΟΥΛΟΥ mini Pick Crackers','snacks',0.72),(32,'Πασατέμπος Tasty natural 70gr','snacks',1.3),(33,'Ηλιόσπορος Tasty natural 71gr','snacks',1.3),(34,'Κελυφωτά φιστίκια Tasty natural 85gr','snacks',2.5),(35,'Αράπικα φιστίκια Tasty natural 85gr','snacks',1.3),(36,'Φουντούκια Tasty natural 90gr','snacks',3.2),(37,'Αμύγδαλα Tasty natural 90gr','snacks',2.5),(38,'Κάσιους Tasty natural 90gr','snacks',2.5),(39,'Peanuts με ρίγανη Pellito 150gr','snacks',2),(40,'Mediterranean Mix Pellito 125gr','snacks',4),(41,'Στεργίου αράβικη πίτα γαλοπούλα','sandwich',2.3),(42,'7Days Bake Rolls κλασική 160gr','snacks',1.3),(43,'7Days Bake Rolls σκόρδο 160gr','snacks',1.3),(44,'7Days Bake Rolls pizza 160gr','snacks',1.3),(45,'Στεργίου Φρατζολάκι ψητό κοτόπουλο','sandwich',2.5),(46,'Στεργίου Κρουασάν καπνιστή γαλοπούλα','sandwich',2.5),(47,'Panini σάντουιτς γαλοπούλα','sandwich',2.5),(48,'Pringles original 165gr','snacks',3.5),(49,'Panini strudel κοτόπουλο','sandwich',2.8),(50,'Panini strudel γαλοπούλα','sandwich',2.8),(51,'Παπαδοπούλου παξιμάδια Krispies με σουσάμι 200gr','snacks',1.6),(52,'Elite crackers μεσογειακά φυσική γεύση με σουσάμι 105gr','snacks',1.5),(53,'Elite crackers μεσογειακά πίγανη και φέτα 105gr','snacks',1.5),(54,'Pick Crackers 100gr','snacks',1.2),(55,'Pick cocktail 335gr','snacks',2.1),(56,'Pick αστέρι','snacks',2.1),(57,'Mini Pick crackers classic 250gr','snacks',1.5),(58,'Mini Pick crackers pizza 90gr','snacks',0.9),(59,'Mini Pick crackers bbq 90gr','snacks',0.9),(60,'Mini Pick crackers ρίγανη 90gr','snacks',0.9),(61,'Παπαδοπούλου Cream Crackers με κινόα 195gr','snacks',2.2),(62,'Pringles sour cream 40gr','snacks',1.1),(63,'Pringles paprika 40gr','snacks',1.1),(64,'Pringles BBQ 40gr','snacks',1.1),(65,'Pringles Texas BBQ 165gr','snacks',2.5),(66,'Pringles Bacon 165gr','snacks',2.3),(67,'Pringles Hot paprika 165gr','snacks',2.7),(68,'Pringles Cream and Onion 165gr','snacks',2.6),(69,'Tottis πατατάκια με αλάτι 110gr','snacks',1),(70,'Tottis πατατάκια με ρίγανη 110gr','snacks',1),(71,'Foudounia Tasty 125gr','snacks',1.4),(72,'Ruffles πατατάκια ketchup 105gr','snacks',1.6),(73,'Extra γαριδάκια pizza 115gr','snacks',1),(74,'Extra γαριδάκια pizza 55gr','snacks',0.5),(75,'Chipita πατατάκια ρίγανη 55gr','snacks',0.6),(76,'Chipita πατατάκια αλάτι 55gr','snacks',0.6),(77,'Chipita πατατάκια ρίγανη 120gr','snacks',1.1),(78,'Chipita πατατάκια αλάτι 120gr','snacks',1.1),(79,'Chipita πατατάκια BBQ 120gr','snacks',1.1),(80,'Chipita πατατάκια tsipers κυματιστά αλάτι 120gr','snacks',1.1),(81,'Chipita πατατάκια tsipers κυματιστά ρίγανη 120gr','snacks',1.1),(82,'Chipita πατατάκια tsipers κυματιστά BBQ 120gr','snacks',1.1),(83,'Tsakiris sticks αλάτι 100gr','snacks',1.75),(84,'Tsakiris πατατάκια pizza 120gr','snacks',1.9),(85,'Tsakiris πατατάκια ρίγανη 120gr','snacks',1.9),(86,'Tsakiris πατατάκια κυματιστά BBQ 100gr','snacks',1.5),(87,'Tsakiris πατατάκια κυματιστά αλάτι 100gr','snacks',1.5),(88,'Tsakiris πατατάκια κυματιστά ρίγανη 100gr','snacks',1.5),(89,'Tsakiris πατατάκια με γεύση πατάτες φούρνου 100gr','snacks',1.6),(90,'Nacho chili 100gr','snacks',1.1),(91,'Nacho natural 100gr','snacks',1.1),(92,'Nacho cheese 100gr','snacks',1.1),(93,'Jumbo fofico snack 100gr','snacks',1.1),(94,'Jumbo sticks 100gr','snacks',1.2),(95,'Pop O Tops classic 85gr','snacks',1.25),(96,'Pop O Tops BBQ 85gr','snacks',1.1),(97,'Jumbo chips κυματιστά αλάτι 280gr','snacks',2.15),(98,'Jumbo chips κυματιστά ρίγανη 130gr','snacks',1.1),(99,'Jumbo chips κυματιστά ρίγανη 280gr','snacks',2),(100,'Jumbo chips χωρίς αλάτι 130gr','snacks',1.1)");
        db.execSQL("DROP TABLE IF EXISTS `suppliers`");
        db.execSQL("CREATE TABLE `suppliers` (\n" +
                "  `supplier_id` int NOT NULL,\n" +
                "  `name` varchar(45) NOT NULL,\n" +
                "  `VAT_number` varchar(10) NOT NULL,\n" +
                "  `phone_number` varchar(10) NOT NULL,\n" +
                "  `address` varchar(100) NOT NULL,\n" +
                "  PRIMARY KEY (`supplier_id`)\n" +
                ")");
        db.execSQL("INSERT INTO `suppliers` VALUES (1,'Σύλλελης','166269834','2310782923','Εθνικής Αμύνης 12'),(2,'Καλούδης','168728092','2310392033','Λεωφόρος Ωραιοκάστρου 26'),(3,'Τροφοεμπορική','167320923','2108944383','Αθανάσιου Διάκου 23, Χαλάνδρι '),(4,'Τσακίρης','162198302','2103293744','25ης Μαρτίου 17-19, Αττική')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public Item findProduct(String productname) {
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCT_NAME + " = '" + productname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        if(db != null)
            Log.d("db","OK");
        else
            Log.d("db","not OK");
        Cursor cursor = db.rawQuery(query, null);
        Item product = new Item();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setName(cursor.getString(1));
            product.setPrice(Double.parseDouble(cursor.getString(3)));
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
        if(db != null)
            Log.d("db","OK");
        else
            Log.d("db","not OK");
        Cursor cursor = db.rawQuery(query, null);
        Item[] products = new Item[cursor.getCount()];
        if (cursor.moveToFirst()) {
            Log.d("db",String.valueOf(cursor.getCount()));
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
        }
        db.close();
        return products;

    }
//    public Item[][] getProducts() {
//        String query = "SELECT * FROM " + TABLE_PRODUCTS;
//        SQLiteDatabase db = this.getWritableDatabase();
//        if(db != null)
//            Log.d("db","OK");
//        else
//            Log.d("db","not OK");
//        Cursor cursor = db.rawQuery(query, null);
//        Item[][] products = new Item[][]();
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            product.setId(Integer.parseInt(cursor.getString(0)));
//            product.setName(cursor.getString(1));
//            product.setPrice(Double.parseDouble(cursor.getString(3)));
//            cursor.close();
//        } else {
//            product = null;
//        }
//        db.close();
//        return product;
//    }

    public boolean deleteProduct(String productname) {
        boolean result = false;
        Item product = findProduct(productname);
        if (product != null){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_PRODUCTS, COLUMN_PRODUCT_ID + " = ?",
                    new String[] { String.valueOf(product.getId()) });
            result = true;
            db.close();
        }
        return result;
    }
}