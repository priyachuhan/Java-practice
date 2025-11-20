<?php
session_start();
if (!isset($_SESSION['admin'])) {
    header("Location: admin_login.html");
    exit;
}

$conn = new mysqli("localhost", "root", "asdf123!@/#&*()", "voteverse"); // Update with your DB credentials
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Initial fetch for page load (will be updated live later)
$totalVoters = $conn->query("SELECT COUNT(*) as total FROM voters")->fetch_assoc()['total'];
$totalVotes = $conn->query("SELECT COUNT(*) as total FROM votes")->fetch_assoc()['total'];

$result = $conn->query("SELECT candidate, COUNT(*) as total_votes FROM votes GROUP BY candidate");

$candidates = [];
$votes = [];

while ($row = $result->fetch_assoc()) {
    $candidates[] = $row['candidate'];
    $votes[] = (int)$row['total_votes'];
}

$latestVoteRes = $conn->query("SELECT vote_time FROM votes ORDER BY vote_time DESC LIMIT 1");
$latestVote = $latestVoteRes->num_rows ? $latestVoteRes->fetch_assoc()['vote_time'] : "No votes yet";
?>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Admin Dashboard - Voteverse</title>
<style>
  @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap');

  * {
    box-sizing: border-box;
  }
  body {
    margin: 0; padding: 0;
    font-family: 'Inter', sans-serif;
    background: #0f172a;
    color: #e0e7ff;
    min-height: 100vh;
  }
  header {
    background: #1e293b;
    padding: 20px 40px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-shadow: 0 3px 10px rgba(59,130,246,0.5);
  }
  header h1 {
    margin: 0;
    font-weight: 700;
    color: #3b82f6;
    font-size: 28px;
  }
  button.logout-btn {
    background: #ef4444;
    border: none;
    padding: 10px 22px;
    border-radius: 8px;
    color: white;
    font-weight: 700;
    cursor: pointer;
    transition: background 0.3s ease;
  }
  button.logout-btn:hover {
    background: #dc2626;
  }
  button.export-btn {
    background: #2563eb; /* blue */
    border: none;
    padding: 10px 22px;
    border-radius: 8px;
    color: white;
    font-weight: 700;
    cursor: pointer;
    margin-left: 10px;
    transition: background 0.3s ease;
  }
  button.export-btn:hover {
    background: #1d4ed8;
  }

  main {
    max-width: 1200px;
    margin: 30px auto;
    padding: 0 20px;
    display: grid;
    grid-template-columns: repeat(auto-fit,minmax(280px,1fr));
    gap: 24px;
  }
  .card {
    background: #1e293b;
    border-radius: 12px;
    box-shadow: 0 6px 15px rgba(59,130,246,0.3);
    padding: 25px;
  }
  .card h2 {
    margin-top: 0;
    font-weight: 700;
    color: #60a5fa;
    font-size: 22px;
    margin-bottom: 15px;
  }
  .stat-number {
    font-size: 48px;
    font-weight: 900;
    color: #3b82f6;
  }
  table {
    width: 100%;
    border-collapse: collapse;
    color: #e0e7ff;
  }
  th, td {
    padding: 14px 12px;
    border-bottom: 1px solid #334155;
    text-align: left;
  }
  th {
    background-color: #3b82f6;
    color: white;
    font-weight: 700;
  }
  td {
    background-color: #334155;
    border-radius: 6px;
  }
  .footer {
    text-align: center;
    margin: 40px 0;
    color: #94a3b8;
  }
</style>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

<header>
  <h1>Voteverse Admin Dashboard</h1>
  <div>
    <button class="logout-btn" onclick="window.location.href='logout.php'">Logout</button>
    <button class="export-btn" onclick="window.location.href='export_votes.php'">Export CSV</button>
  </div>
</header>

<main>
  <section class="card">
    <h2>Total Registered Voters</h2>
    <div class="stat-number" id="totalVoters"><?php echo $totalVoters; ?></div>
  </section>

  <section class="card">
    <h2>Total Votes Cast</h2>
    <div class="stat-number" id="totalVotes"><?php echo $totalVotes; ?></div>
  </section>

  <section class="card" style="grid-column: 1 / -1;">
    <h2>Vote Distribution</h2>
    <canvas id="votesChart" style="max-height: 400px;"></canvas>
  </section>

  <section class="card" style="grid-column: 1 / -1;">
    <h2>Votes Breakdown</h2>
    <table>
      <thead>
        <tr><th>Candidate</th><th>Total Votes</th><th>Percentage</th></tr>
      </thead>
      <tbody id="votesTableBody">
        <?php
        $sumVotes = array_sum($votes);
        for ($i = 0; $i < count($candidates); $i++) {
          $percent = $sumVotes > 0 ? round(($votes[$i] / $sumVotes) * 100, 2) : 0;
          echo "<tr>";
          echo "<td>" . htmlspecialchars($candidates[$i]) . "</td>";
          echo "<td>" . $votes[$i] . "</td>";
          echo "<td>" . $percent . "%</td>";
          echo "</tr>";
        }
        ?>
      </tbody>
    </table>
  </section>

  <section class="card" style="grid-column: 1 / -1; text-align:center; color:#94a3b8; font-size:14px;">
    <p>Latest Vote Time: <span id="latestVoteTime"><?php echo $latestVote; ?></span></p>
  </section>
</main>

<footer class="footer">
  &copy; <?php echo date("Y"); ?> Voteverse — Secure & Transparent Voting
</footer>

<script>
  let chart;

  async function fetchVoteData() {
    try {
      const response = await fetch('vote_data_api.php');
      if (!response.ok) throw new Error('Network response was not ok');
      const data = await response.json();

      // Update total voters & total votes
      document.getElementById('totalVoters').textContent = data.totalVoters;
      document.getElementById('totalVotes').textContent = data.totalVotes;
      document.getElementById('latestVoteTime').textContent = data.latestVote;

      // Update chart data
      chart.data.labels = data.candidates;
      chart.data.datasets[0].data = data.votes;
      chart.update();

      // Update table
      const tbody = document.getElementById('votesTableBody');
      tbody.innerHTML = '';
      let sumVotes = data.votes.reduce((a, b) => a + b, 0);
      data.candidates.forEach((candidate, i) => {
        const percent = sumVotes > 0 ? ((data.votes[i] / sumVotes) * 100).toFixed(2) : 0;
        const row = `
          <tr>
            <td>${candidate}</td>
            <td>${data.votes[i]}</td>
            <td>${percent}%</td>
          </tr>
        `;
        tbody.insertAdjacentHTML('beforeend', row);
      });
    } catch (error) {
      console.error('Error fetching vote data:', error);
    }
  }

  window.onload = () => {
    const ctx = document.getElementById('votesChart').getContext('2d');
    chart = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: <?php echo json_encode($candidates); ?>,
        datasets: [{
          label: 'Votes',
          data: <?php echo json_encode($votes); ?>,
          backgroundColor: [
            '#3b82f6', '#22c55e', '#facc15', '#ef4444',
            '#8b5cf6', '#14b8a6', '#f97316'
          ],
          borderWidth: 0
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'bottom',
            labels: {color: '#e0e7ff'}
          },
          tooltip: {
            callbacks: {
              label: function(context) {
                let label = context.label || '';
                if (label) label += ': ';
                label += context.parsed;
                return label + ' votes';
              }
            }
          }
        }
      }
    });

    fetchVoteData(); // initial fetch
    setInterval(fetchVoteData, 30000); // refresh every 30 seconds
  };
</script>

</body>
</html>
