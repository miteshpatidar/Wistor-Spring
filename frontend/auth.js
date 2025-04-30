// auth.js

// Save JWT Token
function saveToken(token) {
    localStorage.setItem('token', token);
}

// Get JWT Token
function getToken() {
    return localStorage.getItem('token');
}

// Remove JWT Token (Logout)
function removeToken() {
    localStorage.removeItem('token');
}
