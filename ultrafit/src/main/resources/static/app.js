// ===============================
// Confirm delete reservation
// ===============================
document.addEventListener("DOMContentLoaded", () => {

    const deleteForms = document.querySelectorAll("form[action='/reservations/delete']");

    deleteForms.forEach(form => {
        form.addEventListener("submit", (e) => {
            const ok = confirm("Are you sure you want to delete this reservation?");
            if (!ok) e.preventDefault();
        });
    });

});

// ===============================
// Validate reservation creation
// ===============================
function validateReservationForm() {
    const memberId = document.querySelector("input[name='memberId']");
    const trainerId = document.querySelector("input[name='trainerId']");

    if (memberId.value <= 0 || trainerId.value <= 0) {
        alert("Member ID and Trainer ID must be positive numbers.");
        return false;
    }

    return true;
}

// ===============================
// Validate contact form (index.mustache)
// ===============================
function validateContactForm() {
    const email = document.getElementById("email");
    const phone = document.getElementById("telefono");

    if (!email.value.includes("@")) {
        alert("Please enter a valid email.");
        return false;
    }

    if (phone.value.length < 9) {
        alert("Phone number must have at least 9 digits.");
        return false;
    }

    return true;
}
