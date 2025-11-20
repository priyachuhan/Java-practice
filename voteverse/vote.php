<?php
session_start();
if (!isset($_SESSION['voter_id'])) {
    header("Location: register.php");
    exit();
}
require 'db.php'; // DB connection

// Fetch candidates
$sql = "SELECT id, name, party FROM candidates";
$result = mysqli_query($conn, $sql);
?>

<!DOCTYPE html>
<html>
<head>
    <title>Cast Your Vote</title>
    <style>
        body {
            font-family: Arial;
            background-color: #f0f2f5;
            padding: 40px;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            max-width: 500px;
            margin: auto;
        }
        input[type=submit] {
            padding: 10px 20px;
            background: darkblue;
            color: white;
            border: none;
            border-radius: 5px;
        }
        label {
            display: block;
            margin: 10px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Welcome, <?php echo $_SESSION['full_name']; ?>!</h2>
    <p>Select your candidate:</p>
    <form action="submit_vote.php" method="post">
        <?php while ($row = mysqli_fetch_assoc($result)) { ?>
            <label>
                <input type="radio" name="candidate_id" value="<?php echo $row['id']; ?>" required>
                <?php echo $row['name'] . " (" . $row['party'] . ")"; ?>
            </label>
        <?php } ?>
        <input type="submit" value="Cast Vote">
    </form>
</div>
</body>
</html>
