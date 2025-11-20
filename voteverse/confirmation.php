<?php
session_start();
require 'db.php';

if (!isset($_SESSION['voted_candidate']) || !isset($_SESSION['voter_id'])) {
    header("Location: register.php");
    exit();
}

$candidate_id = $_SESSION['voted_candidate'];
$vote_time = $_SESSION['vote_time'];

$result = mysqli_query($conn, "SELECT name, party FROM candidates WHERE id='$candidate_id'");
$candidate = mysqli_fetch_assoc($result);
?>

<!DOCTYPE html>
<html>
<head>
    <title>Vote Confirmation</title>
</head>
<body>
    <h2>Thank you for voting!</h2>
    <p><strong>Candidate:</strong> <?php echo $candidate['name']; ?> (<?php echo $candidate['party']; ?>)</p>
    <p><strong>Time:</strong> <?php echo $vote_time; ?></p>
    <p><strong>Voter ID:</strong> <?php echo $_SESSION['voter_id']; ?></p>
    <a href="register.php">Logout</a>
</body>
</html>
