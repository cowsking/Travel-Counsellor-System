<?php

$file = "log.txt";
// Create the connection of database in server
$link = mysqli_connect('student.csc.liv.ac.uk', 'sgwche11', 'cwq67826', 'sgwche11');
if(!$link){
    // If connection failed return the response value of 3
  $response = array();
  $response["success"] = 3;
  echo json_encode($response);
}else {
// Receive the username and password for login sent from client
    $username = $_POST['username'];
    $password = $_POST['password'];
    // Log for test
    file_put_contents($file, "User request login：".$username."pwd：".$password,FILE_APPEND);
    // Check whether this username exists
    $checkUserName = "select * from accounts where email='$username'";
    // Establish connection
    $result1 = mysqli_query($link, $checkUserName);
    // Retrieve row number of request result
    $num1 = mysqli_num_rows($result1);

    $response = array();
    // If row number of request result is greater than 1, username exists
    if ($num1 > 0) {
        // Username exists, validate the username and password pair
        $validatePassword = "select * from accounts where email='$username' and password='$password'";
        $result2 = mysqli_query($link, $validatePassword);
        $num2 = mysqli_num_rows($result2);
        if ($num2 > 0) {
          // Password correct
          $response["success"] = 1;
            $retrieveUserInfo = "select id, preference from accounts where email='$username' and password='$password'";
            $resultUserInfo = mysqli_query($link, $retrieveUserInfo);
            $row=mysqli_fetch_array($resultUserInfo,MYSQLI_ASSOC);
          $response["preference"] = $row['preference'];
          $response["userId"] = $row['id'];
        }
        else{
          // Password incorrect
          $response["success"] = 2;
        }
    } else {
        // Username does not exist
        $response["success"] = 0;
    }
    file_put_contents($file, "return value：".$response["success"]."preference ".$response["preference"]."userId ".$response["userId"]."\n",FILE_APPEND);
    // Return the result value of login request as json array
    echo json_encode($response);
    // Close the connection with database
    mysqli_close($link);

}
