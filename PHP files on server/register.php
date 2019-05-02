<?php

// File to record log
$file = "log.txt";
// Create the connection of database in server
$link = mysqli_connect('student.csc.liv.ac.uk', 'sgwche11', 'cwq67826', 'sgwche11');


if(!$link){
    $response = array();
    // If connection failed return the response value of 3
    $response["success"] = 3;
    // Return the result value of register request as json array
    echo json_encode($response);
}else {
    // Receive the username and password for register sent from client
    $username = $_POST['username'];
    $password = $_POST['password'];
    // Check whether this username exists
    $checkUserName = "select * from accounts where email='$username'";
    $result1 = mysqli_query($link, $checkUserName);
    $num1 = mysqli_num_rows($result1);
    if ($num1 > 0) {
        // Username already exists
        $response["success"] = 4;

    } else {
        // Insert new username and password record into data table
        $createNewAccount = "insert into accounts (email,password) values('{$_POST['username']}','{$_POST['password']}')";
        // Establish connection
        $result2 = mysqli_query($link, $createNewAccount);
        if ($result2) {
            // Registered successfully
            $response["success"] = 5;
            // Fetch the userId of the last inserted user registration record
            $row=mysqli_insert_id($link);
            $response["userId"] = $row;
        } else {
            // Inserting error
            $response["success"] = 6;
        }
    }
    file_put_contents($file, "User request register：" . $username . "pwd：" . $password . " userId " . $response["userId"] ."\n", FILE_APPEND);
    // Return the result value of register request and userId of last inserted user registration record as json array
    echo json_encode($response);
    }
    // Close the connection with database
    mysqli_close($link);

