<?php
// Database connection settings
$host = "localhost";       // Usually localhost if MySQL is on your machine
$user = "root";            // Your MySQL username
$password = "asdf123!@/#&*()";            // Your MySQL password (empty if none)
$dbname = "voteverse";  // The database you created

// Create connection
$conn = new mysqli($host, $user, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Connection successful
// You can use $conn to run queries in other PHP scripts

?>
