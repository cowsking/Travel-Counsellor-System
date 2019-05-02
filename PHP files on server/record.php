<?php

$file = "log.txt";
// Create the connection of database in server
$link = mysqli_connect('student.csc.liv.ac.uk', 'sgwche11', 'cwq67826', 'sgwche11');

    // Receive the userId and questionnaire result for recording which was sent from client
    $userId = $_POST['userId'];
    $preference = $_POST['preference'];

    // Record log for test
    file_put_contents($file, $userId."User request record：".$preference."\n",FILE_APPEND);

    // Update user's preference record 
    $updatePreference = "update accounts set preference = '$preference' where id='$userId' ";
    // Establish connection
    $result1 = mysqli_query($link, $updatePreference);
    // Close connection
    mysqli_close($link);


