<?php

class DB_CONNECT {

    var $con;

    // constructor
    function __construct() {
        // connecting to database
        $this->connect();
    }

    function connect() {
        // import database connection variables
        require_once __DIR__ . '/db_config.php';
        // Connecting to mysql database
        $con = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        if ($con->connect_errno > 0) {
            die('Unable to connect to database [' . $con->connect_error . ']');
        }
        // returing connection cursor
        $this->con = $con;
        return $this->con;
    }

    function close() {
        // closing db connection
        mysqli_close($con);
    }

}

?>