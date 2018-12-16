<?php

// array for JSON response
$response = array();

// check for required fields        ACTUAL CODE
if (isset($_POST['id']) && isset($_POST['password'])) {

    $id = $_POST['id'];
    $password = $_POST['password'];

//TESTING 
//    $email = "guard4@gmail.com";
//    $name = "I am guard 4";
    //$id = "guard2";
    //$password = "guard2pw";
    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();
    if (!$db) {
        //connection failed
        echo "Error: Unable to connect to MySQL.";
        exit;
    }
    // connection established
    //SQL query
    $statement = $db->con->prepare("SELECT * FROM guards WHERE guardID = ? AND password = ?");
    $statement->bind_param('ss', $id, $password);
    $statement->execute();
    $result = $statement->get_result();
    //check insert row
    if ($result->num_rows > 0) {
        $response["success"] = 1;
        while ($row = $result->fetch_assoc()) {
            $response["message"] = $row['guardID'];
        }
        // successfully read from database
        
        

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to read row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";

        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>