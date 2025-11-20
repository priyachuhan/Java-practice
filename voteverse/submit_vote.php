<?php
session_start();

$conn = new mysqli("localhost", "root", "asdf123!@/#&*()", "voteverse");
if ($conn->connect_error) {
    echo "Database connection error.";
    exit();
}

$fingerprint_id = $_POST['fingerprint_id'] ?? '';
$candidate = $_POST['candidate'] ?? '';

if (empty($fingerprint_id) || empty($candidate)) {
    echo "Missing fingerprint ID or candidate.";
    exit();
}

// Check if already voted
$stmt = $conn->prepare("SELECT 1 FROM votes WHERE fingerprint_id = ?");
$stmt->bind_param("s", $fingerprint_id);
$stmt->execute();
$stmt->store_result();

if ($stmt->num_rows > 0) {
    echo "already_voted";
    exit();
}
$stmt->close();

// Insert vote
$stmt = $conn->prepare("INSERT INTO votes (fingerprint_id, candidate, vote_time) VALUES (?, ?, NOW())");
$stmt->bind_param("ss", $fingerprint_id, $candidate);

if ($stmt->execute()) {
    echo "success";
} else {
    echo "Error submitting vote.";
}

$stmt->close();
$conn->close();
?>
