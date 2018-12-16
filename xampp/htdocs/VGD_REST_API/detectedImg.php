<?php

// array for JSON response
$response = array();

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
$statement = $db->con->prepare("SELECT * FROM detectedimg");
$statement->execute();
$result = $statement->get_result();
//check insert row
if ($result->num_rows > 0) {
    $response["success"] = 1;
    $data = "";
    while ($row = $result->fetch_assoc()) {
        $data .= $row['imgName'].",".$row['time'].",".$row['perc']."@";
    }
    $response["message"] = $data;
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
?>