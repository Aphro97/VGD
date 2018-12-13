<?php

class GEN_GUARDID {

    function generate() {

        // array for JSON response
        //$response = array();
        $results = "";
        // include db connect class
        require_once __DIR__ . '/db_connect.php';

        // connecting to db
        $db = new DB_CONNECT();
        if (!$db) {
            echo "Error: Unable to connect to MySQL.";
            exit;
        }

        // check if row selected or not
        if (!$result = mysqli_query($db->con, "SELECT * FROM guards ORDER BY id DESC LIMIT 1;")) {
            // failed to select row
            //$response["success"] = 0;
            //$response["message"] = "Oops! An error occurred.";
            // echoing JSON response
            //echo json_encode($response);
            die('There was an error running the query [' . $db->con->error . ']');
        } else {
            // successfully selected from database
            while ($row = $result->fetch_assoc()) {
                $tmp = substr($row['guardID'], 5) + 1;
                //echo '<br />guard'.$tmp. '<br />';
                $results = 'guard' . $tmp;
            }
            //$response["success"] = 1;
            //$response["message"] = "guard".$tmp;
            // echoing JSON response
            //echo json_encode($response);
        }
        return $results;
    }

}

?>
