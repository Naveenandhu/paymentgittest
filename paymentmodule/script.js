document.addEventListener("DOMContentLoaded", function () {
    const paymentForm = document.getElementById("paymentForm");
    const paymentStatus = document.getElementById("paymentStatus");
    const paymentList = document.getElementById("paymentList");

    // Function to fetch and update the payment list
    function fetchPaymentList() {
        fetch("/payment/getAllPayments")
            .then((response) => response.json())
            .then((data) => {
                paymentList.innerHTML = ""; // Clear the existing list

                data.forEach((payment) => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${payment.customerId}</td>
                        <td>${payment.paymentId}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.paymentStatus}</td>
                        <td>
                            <button class="updateBtn" data-id="${payment.paymentId}">Update</button>
                            <button class="deleteBtn" data-id="${payment.paymentId}">Delete</button>
                        </td>
                    `;

                    paymentList.appendChild(row);
                });
            })
            .catch((error) => {
                console.error("Error fetching payment list: " + error);
            });
    }

    // Initial fetch to load the payment list
    fetchPaymentList();

    paymentForm.addEventListener("submit", function (e) {
        e.preventDefault();
        const customerId = document.getElementById("customerId").value;
        const paymentId = document.getElementById("paymentId").value;
        const amount = document.getElementById("amount").value;

        // Create a new payment object
        const newPayment = {
            customerId: customerId,
            paymentId: paymentId,
            amount: amount,
        };

        // Send a POST request to add the new payment
        fetch("/payment/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newPayment),
        })
            .then((response) => response.json())
            .then((data) => {
                // Update the payment status based on the response
                if (data.paymentStatus === "SUCCESS") {
                    paymentStatus.innerText = "Payment Status: Success";
                } else {
                    paymentStatus.innerText = "Payment Status: Failed";
                }

                // Fetch the updated payment list
                fetchPaymentList();
            })
            .catch((error) => {
                console.error("Error adding payment: " + error);
            });
    });

    // Event delegation for update and delete buttons
    paymentList.addEventListener("click", function (e) {
        if (e.target.classList.contains("updateBtn")) {
            const paymentId = e.target.getAttribute("data-id");

            // Implement your update logic here (e.g., opening a modal to edit payment data)
            // You can make another fetch request to update the payment on the server
            console.log("Update button clicked for Payment ID: " + paymentId);
        } else if (e.target.classList.contains("deleteBtn")) {
            const paymentId = e.target.getAttribute("data-id");

            // Send a DELETE request to delete the payment
            fetch(`/payment/${paymentId}`, {
                method: "DELETE",
            })
                .then(() => {
                    // Fetch the updated payment list after deletion
                    fetchPaymentList();
                })
                .catch((error) => {
                    console.error("Error deleting payment: " + error);
                });
        }
    });
});
