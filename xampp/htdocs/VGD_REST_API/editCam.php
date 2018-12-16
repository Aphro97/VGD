<?php

// array for JSON response
$response = array();

// check for required fields        ACTUAL CODE
//if (isset($_POST['id']) && isset($_POST['ip']) && isset($_POST['location']) && isset($_POST['status'])) {
//
//    $id = $_POST['id'];
//    $ip = $_POST['email'];
//    $location = $_POST['location'];
//    $status = $_POST['satatus'];

//TESTING 
    $id = "VGD3";
    $location = "Block B";
    $ip = "192.168.0.146:7777";
    $status = "Disabled";
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
    $statement = $db->con->prepare("UPDATE devices SET ip=?,location=?,status=? WHERE deviceID = ?");
    $statement->bind_param('ssss', $ip, $location,$status,$id);
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
//} else {
//    // required field is missing
//    $response["success"] = 0;
//    $response["message"] = "Required field(s) is missing";
//
//    // echoing JSON response
//    echo json_encode($response);
//}
?>