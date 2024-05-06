// function showRegister() {
//   document
//     .getElementById("registerForm")
//     .addEventListener("submit", function (event) {
//       event.preventDefault(); // Ngăn chặn chuyển hướng khi gửi form
//
//       var username = document.getElementById("username").value;
//       var password = document.getElementById("password").value;
//       var email = document.getElementById("password").value;
//
//       var data = {
//         username: username,
//         email: email,
//         password: password,
//       };
//       console.data(data);
//
//       fetch("register/save", {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//         },
//         body: JSON.stringify(data),
//       })
//         .then(function (response) {
//           if (!response.ok) {
//             throw new Error("Failed to register.");
//           }
//           return response.text();
//         })
//         .then(function (message) {
//           alert(message); // Hiển thị thông báo từ backend
//         })
//         .catch(function (error) {
//           console.error("Error:", error);
//           alert("An error occurred while registering.");
//         });
//     });
// }
function togglePasswordVisibility() {
    var passwordInput = document.getElementById("password");
    var passwordIcon = document.getElementById("passwordIcon");

    // Kiểm tra nếu đang ẩn password thì hiện lên và ngược lại
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        passwordIcon.classList.remove("fa-eye");
        passwordIcon.classList.add("fa-eye-slash");
    } else {
        passwordInput.type = "password";
        passwordIcon.classList.remove("fa-eye-slash");
        passwordIcon.classList.add("fa-eye");
    }
}

function togglePasswordConfirmVisibility() {
    var passwordInput = document.getElementById("confirmPassword");
    var passwordIcon = document.getElementById("showComfirmPassword");

    // Kiểm tra nếu đang ẩn password thì hiện lên và ngược lại
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        passwordIcon.classList.remove("fa-eye");
        passwordIcon.classList.add("fa-eye-slash");
    } else {
        passwordInput.type = "password";
        passwordIcon.classList.remove("fa-eye-slash");
        passwordIcon.classList.add("fa-eye");
    }
}