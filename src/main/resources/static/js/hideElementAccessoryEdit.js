document.addEventListener("DOMContentLoaded", function () {

    const accessoryButton = document.getElementById('editAccessoryButton')
    const accessoryField = document.getElementById('editAccessory')
    const editPicturesButton = document.getElementById('editPicturesButton')
    const editPicture = document.getElementById('editPictures')

    accessoryButton.addEventListener('click', function ( e){
            accessoryField.style.display = "block";
            editPicture.style.display = "none";
    })

    editPicturesButton.addEventListener('click', function ( e){
            editPicture.style.display = "block";
            accessoryField.style.display = "none";
    })
})
