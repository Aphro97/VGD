<?php

// array for JSON response
$response = array();

// check for required fields        ACTUAL CODE
if (isset($_POST['id']) && isset($_POST['email']) && isset($_POST['phone']) && isset($_POST['name'])) {

    $id = $_POST['id'];
    $email = $_POST['email'];
    $phone = $_POST['phone'];
    $name = $_POST['name'];

//TESTING 
//    $email = "guard4@gmail.com";
//    $name = "I am guard 4";
//    $id = "guard4";
//    $phone = "0124444444";
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
    $statement = $db->con->prepare("UPDATE guards SET email=?,name=?,phone=? WHERE guardID = ?");
    $statement->bind_param('ssss', $email, $name,$phone,$id);
    $statement->execute();
    //check insert row
    if ($statement->execute()) {
        // successfully update database
        $response["success"] = 1;
        $response["message"] = "Users successfully updated.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to update row
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