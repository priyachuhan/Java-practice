// Handle vote form submission
document.getElementById("voteForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const formData = new FormData(this);

  fetch("register.php", {function showVoter() {
                           document.getElementById("voterSection").style.display = "block";
                           document.getElementById("adminSection").style.display = "none";
                         }

                         function showAdmin() {
                           document.getElementById("voterSection").style.display = "none";
                           document.getElementById("adminSection").style.display = "block";
                         }

                         // Optional: handle admin login
                         document.addEventListener("DOMContentLoaded", function () {
                           const adminForm = document.getElementById("adminLoginForm");

                           if (adminForm) {
                             adminForm.addEventListener("submit", function (e) {
                               e.preventDefault();
                               const code = this.adminCode.value;
                               if (code === "admin123") {
                                 window.location.href = "admin_dashboard.php"; // or however you want to handle it
                               } else {
                                 alert("Invalid admin code!");
                               }
                             });
                           }
                         });

    method: "POST",
    body: formData
  })
    .then(res => res.text())
    .then(data => {
      document.getElementById("responseMessage").textContent = data;
      this.reset(); // clear form
    })
    .catch(err => {
      document.getElementById("responseMessage").textContent = "Error submitting vote.";
      console.error(err);
    });
});

// Handle Admin Dashboard / Voter Portal toggle
document.addEventListener("DOMContentLoaded", function() {
  const adminBox = document.getElementById('adminBox');
  const voterPortalBox = document.getElementById('voterPortalBox');
  const adminForm = document.getElementById('adminAccessForm');
  const voterSection = document.getElementById('voterSection');

  adminBox.addEventListener('click', (e) => {
    e.preventDefault();  // prevent default link/anchor behavior
    adminForm.style.display = 'block';
    voterSection.style.display = 'none';
  });

  voterPortalBox.addEventListener('click', (e) => {
    e.preventDefault();
    voterSection.style.display = 'block';
    adminForm.style.display = 'none';
  });
});
