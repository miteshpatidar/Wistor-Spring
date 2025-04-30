document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById("loginForm");
    const registerForm = document.getElementById("registerForm");

    // Login form handling
    if (loginForm) {
        loginForm.addEventListener("submit", async function(event) {
            event.preventDefault();
            console.log("Login form submitted"); // Check if the form is triggering the event

            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            try {
                const response = await fetch('http://localhost:8080/login', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ username, password })
                });

                const data = await response.json(); // Assuming the response is JSON

                if (response.ok) {
                    saveToken(data.token); // Save token
                    const decodedToken = jwt_decode(data.token); // decode the JWT
                    const role = (decodedToken.role || "").toUpperCase(); // safely extract role
                
                    if (role === "MEMBER") {
                        window.location.href = 'member-dashboard.html';
                    } else if (role === "GUARD") {
                        window.location.href = 'guard-dashboard.html';
                    } else {
                        alert('Unknown role. Cannot redirect.');
                    }
                } else {
                    alert(data.error || 'Login failed.');
                }
                
            } catch (error) {
                console.error('Error:', error);
                alert('Server error. Please try again.');
            }
        });
    }

    // Register form handling
    if (registerForm) {
        registerForm.addEventListener("submit", async function(event) {
            event.preventDefault();
    
            const username = document.getElementById("username").value;
            const name = document.getElementById("name").value;
            const contact = document.getElementById("contact").value;
            const password = document.getElementById("password").value;
            const role = document.getElementById("role").value;
    
            try {
                const response = await fetch('http://localhost:8080/register', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ username, name, contact, password, role })
                });
    
                const data = await response.json();
    
                if (response.ok) {
                    alert('Registration successful! Now you can login.');
                    window.location.href = 'index.html'; // Redirect to login
                } else {
                    alert(data.error || 'Registration failed. Try again.');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Server error. Please try again.');
            }
        });
    }
    
});

// Helper function to save the JWT token in localStorage
function saveToken(token) {
    localStorage.setItem('token', token); // Save the JWT token
}
