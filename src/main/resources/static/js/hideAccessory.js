document.addEventListener("DOMContentLoaded", function () {

    const accessoryButton = document.getElementById('newAccessoryButton')
    const accessoryField = document.getElementById('newAccessory')
    const allAccessoriesButton = document.getElementById('allAccessoriesButton')
    const allAccessoriesField = document.getElementById('allAccessories')

    accessoryButton.addEventListener('click', function (e) {
        accessoryField.style.display = "block";
        allAccessoriesField.style.display = "none"
    })

    allAccessoriesButton.addEventListener('click', function (e) {
        allAccessoriesField.style.display = "block"
        accessoryField.style.display = "none";
    })


})
