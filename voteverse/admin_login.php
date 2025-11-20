<?php
session_start();

$conn = new mysqli("localhost", "root", "asdf123!@/#&*()", "voteverse");
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$username = $_POST['admin_user'] ?? '';
$password = $_POST['admin_pass'] ?? '';

// Prepare SQL statement to avoid injection
$stmt = $conn->prepare("SELECT * FROM admins WHERE username = ?");
$stmt->bind_param("s", $username);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows === 1) {
    $row = $result->fetch_assoc();
    // Plain text password check (since your password is plain)
    if ($password === $row['password']) {
        $_SESSION['admin'] = $username;
        header("Location: admin_dashboard.php");
        exit();
    } else {
        echo "<script>alert('Invalid credentials'); window.location.href='admin_login.html';</script>";
    }
} else {
    echo "<script>alert('Invalid credentials'); window.location.href='admin_login.html';</script>";
}

$stmt->close();
$conn->close();
?>
