<?php
session_start();
if (!isset($_SESSION['admin'])) {
    http_response_code(403);
    echo json_encode(['error' => 'Unauthorized']);
    exit;
}

$conn = new mysqli("localhost", "root", "asdf123!@/#&*()", "voteverse");
if ($conn->connect_error) {
    http_response_code(500);
    echo json_encode(['error' => 'DB connection failed']);
    exit;
}

// Total voters
$totalVoters = $conn->query("SELECT COUNT(*) as total FROM voters")->fetch_assoc()['total'];

// Total votes
$totalVotes = $conn->query("SELECT COUNT(*) as total FROM votes")->fetch_assoc()['total'];

// Votes per candidate
$result = $conn->query("SELECT candidate, COUNT(*) as total_votes FROM votes GROUP BY candidate");

$candidates = [];
$votes = [];
while ($row = $result->fetch_assoc()) {
    $candidates[] = $row['candidate'];
    $votes[] = (int)$row['total_votes'];
}

// Latest vote time
$latestVoteRes = $conn->query("SELECT vote_time FROM votes ORDER BY vote_time DESC LIMIT 1");
$latestVote = $latestVoteRes->num_rows ? $latestVoteRes->fetch_assoc()['vote_time'] : "No votes yet";

header('Content-Type: application/json');
echo json_encode([
    'totalVoters' => $totalVoters,
    'totalVotes' => $totalVotes,
    'candidates' => $candidates,
    'votes' => $votes,
    'latestVote' => $latestVote,
]);
