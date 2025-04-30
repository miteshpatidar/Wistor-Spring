async function fetchAllVisitors() {
    const token = getToken();
    if (!token) {
        window.location.href = 'index.html';
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/guard/visitor', {
            headers: { 'Authorization': 'Bearer ' + token }
        });
        const visitors = await response.json();
        populateVisitorTable(visitors);
    } catch (error) {
        console.error('Error fetching all visitors:', error);
    }
}

function populateVisitorTable(visitors) {
    const tbody = document.querySelector("#visitorTable tbody");
    tbody.innerHTML = "";
    visitors.forEach(visitor => {
        const row = `
            <tr>
                <td>${visitor.name}</td>
                <td>${visitor.mobile}</td>
                <td>${visitor.address}</td>
                <td>${visitor.reason}</td>
                <td>${visitor.memberName}</td>
                <td>${visitor.memberAddress}</td>
                <td>${visitor.visitDate}</td>
                <td><button onclick="deleteVisitor(${visitor.id})">Delete</button></td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

async function addVisitor(event) {
    event.preventDefault();
    const token = getToken();
    if (!token) {
        alert("Session expired!");
        return;
    }

    const visitor = {
        name: document.getElementById("visitorName").value,
        mobile: document.getElementById("visitorMobile").value,
        address: document.getElementById("visitorAddress").value,
        reason: document.getElementById("visitorReason").value,
        memberName: document.getElementById("memberName").value,
        memberAddress: document.getElementById("memberAddress").value,
        visitDate: document.getElementById("visitDate").value
    };

    try {
        const response = await fetch('http://localhost:8080/guard/visitor', {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(visitor)
        });

        if (response.ok) {
            alert("Visitor added successfully");
            document.getElementById("addVisitorForm").reset();
            fetchAllVisitors();
        } else {
            alert("Failed to add visitor");
        }
    } catch (error) {
        console.error('Error adding visitor:', error);
    }
}

async function deleteVisitor(visitorId) {
    const token = getToken();
    if (!token) {
        alert("Session expired!");
        return;
    }

    if (!confirm("Are you sure you want to delete this visitor?")) return;

    try {
        const response = await fetch(`http://localhost:8080/guard/visitor/${visitorId}`, {
            method: 'DELETE',
            headers: { 'Authorization': 'Bearer ' + token }
        });

        if (response.ok) {
            alert("Visitor deleted");
            fetchAllVisitors();
        } else {
            alert("Failed to delete visitor");
        }
    } catch (error) {
        console.error('Error deleting visitor:', error);
    }
}

// Bind form submission
document.getElementById("addVisitorForm").addEventListener("submit", addVisitor);

// Fetch visitors on page load
document.addEventListener('DOMContentLoaded', fetchAllVisitors);
