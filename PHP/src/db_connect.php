<?php 
	class DB_CONNECT {
 
    // constructor
    function __construct() {
        // connecting to database
        $this->connect();
    }
 
    // destruct
 
    /**
     * Function to connect with database
     */
    function connect() {
 
        // Connecting to mysql database
        $con = mysqli_connect("localhost", "root", "","test") or die(mysql_error());
 
        // Selecing database
        //$db = mysql_select_db("bloodbank") or die(mysql_error()) or die(mysql_error());
 
        // returing connection cursor
        return $con;
    }
 
}
?>