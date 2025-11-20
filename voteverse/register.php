<?php
session_start();
$conn = new mysqli("localhost", "root", "asdf123!@/#&*()", "voteverse");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $full_name = $_POST['full_name'] ?? '';
    $email = $_POST['email'] ?? '';
    $fingerprint_id = $_POST['fingerprint_id'] ?? '';
    $district = $_POST['area'] ?? '';

    if (empty($full_name) || empty($email) || empty($fingerprint_id) || empty($district)) {
        echo "All fields are required!";
        exit();
    }

    // Check for duplicates in voters table
    $stmt = $conn->prepare("SELECT * FROM voters WHERE email = ? OR fingerprint_id = ?");
    $stmt->bind_param("ss", $email, $fingerprint_id);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        header("Location: already_registered.html");
        exit();

    }

    // Insert voter into voters table
    $stmt = $conn->prepare("INSERT INTO voters (fingerprint_id, full_name, email, district, has_voted) VALUES (?, ?, ?, ?, 0)");
    $stmt->bind_param("ssss", $fingerprint_id, $full_name, $email, $district);
    if ($stmt->execute()) {
        $_SESSION['full_name'] = $full_name;
        header("Location: vote.html?fingerprint_id=" . urlencode($fingerprint_id));
        exit();
    } else {
        echo "Error during registration.";
    }

} else {
    echo "Invalid request.";
}
?>
