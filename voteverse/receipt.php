<?php
$fingerprint_id = $_GET['fingerprint_id'] ?? 'NA12345';
$candidate = $_GET['candidate'] ?? 'Unknown Candidate';
$vote_time = date("Y-m-d H:i:s");
$receipt_id = "RCPT-" . rand(100000, 999999);
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Vote Receipt - Voteverse</title>
  <style>
    :root {
      --bg: lightblue;
      --box: #1e293b;
      --lightbox: #334155;
      --accent: #3b82f6;
      --success: #22c55e;
      --muted: #94a3b8;
    }
    body {
      margin: 0;
      background-color: var(--bg);
      font-family: 'Segoe UI', sans-serif;
      color: white;
    }
    header {
      background-color: var(--box);
      padding: 20px 40px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      box-shadow: 0 0 15px #3b82f6;
    }
    header h1 {
      margin: 0;
      font-size: 24px;
      color: var(--accent);
    }
    .logout {
      background: #ef4444;
      padding: 8px 16px;
      border: none;
      color: white;
      border-radius: 8px;
      cursor: pointer;
    }
    .container {
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      padding: 60px 20px;
    }
    .receipt-box {
      background-color: var(--lightbox);
      padding: 40px;
      border-radius: 20px;
      width: 400px;
      box-shadow: 0 0 15px rgba(59, 130, 246, 0.3);
      text-align: center;
      position: relative;
    }
    .receipt-box .success-icon {
      font-size: 40px;
      color: var(--success);
      margin-bottom: 10px;
    }
    .receipt-box h2 {
      margin-bottom: 25px;
      color: var(--success);
    }
    .receipt-details {
      text-align: left;
      margin-bottom: 25px;
    }
    .receipt-details p {
      margin: 8px 0;
      font-size: 15px;
    }
    .thank-box {
      background: var(--box);
      padding: 15px;
      border-radius: 10px;
      color: var(--muted);
      font-size: 14px;
    }
    .back-btn {
      margin-top: 30px;
      background-color: var(--accent);
      color: white;
      border: none;
      padding: 12px 20px;
      border-radius: 10px;
      cursor: pointer;
      font-size: 16px;
      font-weight: bold;
      transition: background 0.3s;
    }
    .back-btn:hover {
      background-color: #2563eb;
    }
  </style>
</head>
<body>
<header>
  <h1>Voteverse</h1>
  <button class="logout" onclick="window.location.href='register.html'">Logout</button>
</header>
<div class="container">
  <div class="receipt-box">
    <div class="success-icon">✅</div>
    <h2>Your Vote Has Been Recorded</h2>
    <div class="receipt-details">
      <p><strong>Fingerprint ID:</strong> <?= htmlspecialchars($fingerprint_id) ?></p>
      <p><strong>Candidate:</strong> <?= htmlspecialchars($candidate) ?></p>
      <p><strong>Time:</strong> <?= $vote_time ?></p>
      <p><strong>Receipt ID:</strong> <?= $receipt_id ?></p>
    </div>
    <div class="thank-box">
      Thank you for voting. Your vote is secured and cannot be changed.
    </div>
  </div>
  <button class="back-btn" onclick="window.location.href='register.html'">
    Return to Login Page
  </button>
</div>
</body>
</html>
