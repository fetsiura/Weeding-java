document.addEventListener("DOMContentLoaded", function () {

    const restorePasswordButton = document.getElementById('restorePasswordButton')
    const loginPasswordButton = document.getElementById('loginPasswordButton')
    const restorePasswordField = document.getElementById('product')
    const loginPasswordField = document.getElementById('products')


    restorePasswordButton.addEventListener('click', function (e) {
        restorePasswordField.style.display = "block";
        loginPasswordField.style.display = "none";
    })

    loginPasswordButton.addEventListener('click', function (e) {
        loginPasswordField.style.display = "block";
        restorePasswordField.style.display = "none";
    })

})
