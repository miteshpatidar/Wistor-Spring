function getToken() {
    return localStorage.getItem('token');
}

function logout() {
    localStorage.removeItem('token');
    window.location.href = 'index.html';
}

function showUpdateAddressForm() {
    document.getElementById('updateAddressForm').style.display = 'block';
}

function hideUpdateAddressForm() {
    document.getElementById('updateAddressForm').style.display = 'none';
}

function showAddVisitorForm() {
    document.getElementById('addVisitorForm').style.display = 'block';
}

function hideAddVisitorForm() {
    document.getElementById('addVisitorForm').style.display = 'none';
}

async function fetchMemberAddress() {
    const token = getToken();
    if (!token) {
        window.location.href = 'index.html';
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/member/address', {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });

        const addressElement = document.getElementById('currentAddress');
        if (response.ok) {
            const data = await response.json();
            addressElement.textContent = `Your Address: ${data.address}`;
        } else {
            addressElement.textContent = 'Failed to load address';
        }
    } catch (err) {
        console.error(err);
        document.getElementById('currentAddress').textContent = 'Error loading address';
    }
}

async function updateAddress() {
    const token = getToken();
    if (!token) {
        window.location.href = 'index.html';
        return;
    }

    const newAddress = document.getElementById('newAddress').value.trim();
    if (!newAddress) {
        alert("Please enter a new address.");
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/member/address', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify({ address: newAddress })
        });

        if (response.ok) {
            alert('Address updated successfully!');
            document.getElementById('newAddress').value = '';
            hideUpdateAddressForm();
            fetchMemberAddress();
        } else {
            alert('Failed to update address');
        }
    } catch (error) {
        console.error('Error updating address:', error);
        alert('Server error while updating address');
    }
}

async function fetchVisitors() {
    const token = getToken();
    if (!token) {
        window.location.href = 'index.html';
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/member/visitor', {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });

        if (!response.ok) throw new Error('Failed to fetch visitors');

        const visitors = await response.json();
        populateVisitorTable(visitors);
    } catch (error) {
        console.error('Error fetching visitors:', error);
        alert('Unable to load visitors');
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
                <td>${new Date(visitor.visitDate).toLocaleDateString()}</td>
                <td>
                    <button onclick="deleteVisitor(${visitor.id})">Delete</button>
                </td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

async function addVisitor() {
    const token = getToken();
    const name = document.getElementById('visitorName').value;
    const mobile = document.getElementById('visitorMobile').value;
    const address = document.getElementById('visitorAddress').value;
    const reason = document.getElementById('visitorReason').value;
    const visitDate = document.getElementById('visitDate').value;

    if (!name || !mobile || !address || !reason || !visitDate) {
        alert('Please fill all fields');
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/member/visitor', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify({ name, mobile, address, reason, visitDate })
        });

        if (response.ok) {
            alert('Visitor added successfully!');
            hideAddVisitorForm();
            fetchVisitors();
        } else {
            alert('Failed to add visitor.');
        }
    } catch (error) {
        console.error('Error adding visitor:', error);
        alert('Server error while adding visitor');
    }
}

async function deleteVisitor(visitorId) {
    const token = getToken();

    if (!confirm('Are you sure you want to delete this visitor?')) return;

    try {
        const response = await fetch(`http://localhost:8080/member/visitor/${visitorId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });

        if (response.ok) {
            alert('Visitor deleted successfully!');
            fetchVisitors();
        } else {
            alert('Failed to delete visitor.');
        }
    } catch (error) {
        console.error('Error deleting visitor:', error);
        alert('Server error while deleting visitor');
    }
}

// Initialize dashboard
document.addEventListener('DOMContentLoaded', () => {
    fetchVisitors();
    fetchMemberAddress();
});
