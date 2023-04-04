document.addEventListener("DOMContentLoaded", function () {

    const dressButton = document.getElementById('newDressButton')
    const dressField = document.getElementById('newDress')

    const accessoryButton = document.getElementById('newAccessoryButton')
    const accessoryField = document.getElementById('newAccessory')

    const allDressesButton = document.getElementById('allDressesButton')
    const allDressesField = document.getElementById('allDresses')

    const allAccessoriesButton = document.getElementById('allAccessoriesButton')
    const allAccessoriesField = document.getElementById('allAccessories')

    dressButton.addEventListener('click', function (e) {
        dressField.style.display = "block";
        allDressesField.style.display = "none";
        accessoryField.style.display = "none";
        allAccessoriesField.style.display = "none";
    })

    allDressesButton.addEventListener('click', function (e) {
        allDressesField.style.display = "block";
        dressField.style.display = "none";
        accessoryField.style.display = "none";
        allAccessoriesField.style.display = "none";

    })

    accessoryButton.addEventListener('click', function (e) {
        accessoryField.style.display = "block";
        allDressesField.style.display = "none";
        dressField.style.display = "none";
        allAccessoriesField.style.display = "none";
    })

    allAccessoriesButton.addEventListener('click', function (e) {
        console.log("tu")
        allAccessoriesField.style.display = "block";
        accessoryField.style.display = "none";
        allDressesField.style.display = "none";
        dressField.style.display = "none";
    })

})
