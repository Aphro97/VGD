<?php

// array for JSON response
$response = array();

// check for required fields        ACTUAL CODE
if (isset($_POST['email']) && isset($_POST['name']) && isset($_POST['phone']) && isset($_POST['password'])) {

    $email = $_POST['email'];
    $name = $_POST['name'];
    $phone = $_POST['phone'];
    $password = $_POST['password'];

//TESTING 
//    $email = "guard4@gmail.com";
//    $name = "I am guard 4";
//    $phone = "0124444444";
//    $password = "guard4pw";
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
    //generate next guardID
    require_once __DIR__ . '/generateGuardID.php';
    $id = new GEN_GUARDID();
    $guardID = $id->generate();

    //SQL query
    $statement = $db->con->prepare("INSERT INTO guards(guardID, email, name, phone, password) VALUES(?, ?, ?, ?, ?)");
    $statement->bind_param('sssss', $guardID, $email, $name, $phone, $password);

    //check insert row
    if ($statement->execute()) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Users successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
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