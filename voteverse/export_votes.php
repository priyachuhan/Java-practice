<?php
session_start();
if (!isset($_SESSION['admin'])) {
    header("Location: admin_login.html");
    exit;
}

$conn = new mysqli("localhost", "root", "asdf123!@/#&*()", "voteverse"); // Replace with your DB

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Set headers to force download CSV file
header('Content-Type: text/csv; charset=utf-8');
header('Content-Disposition: attachment; filename=votes_export_' . date('Y-m-d') . '.csv');

$output = fopen('php://output', 'w');

// Column headers for CSV
fputcsv($output, ['Fingerprint ID', 'Candidate', 'Vote Time', 'Receipt ID']);

// Fetch votes data
$query = "SELECT fingerprint_id, candidate, vote_time, receipt_id FROM votes ORDER BY vote_time DESC";
$result = $conn->query($query);

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        fputcsv($output, [$row['fingerprint_id'], $row['candidate'], $row['vote_time'], $row['receipt_id']]);
    }
}

fclose($output);
exit();
