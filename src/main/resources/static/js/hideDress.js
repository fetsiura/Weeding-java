document.addEventListener("DOMContentLoaded", function () {

    const dressButton = document.getElementById('newDressButton')
    const dressField = document.getElementById('newDress')

    const allDressesButton = document.getElementById('allDressesButton')
    const allDressesField = document.getElementById('allDresses')

    dressButton.addEventListener('click', function (e) {
        dressField.style.display = "block";
        allDressesField.style.display = "none";
    })

    allDressesButton.addEventListener('click', function (e) {
        allDressesField.style.display = "block";
        dressField.style.display = "none";
    })

})
