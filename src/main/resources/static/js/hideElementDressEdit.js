document.addEventListener("DOMContentLoaded", function () {

    const dressButton = document.getElementById('editDressButton')
    const dressField = document.getElementById('editDress')
    const editPicturesButton = document.getElementById('editPicturesButton')
    const editPicture = document.getElementById('editPictures')

    dressButton.addEventListener('click', function ( e){
            dressField.style.display = "block";
            editPicture.style.display = "none";
    })

    editPicturesButton.addEventListener('click', function ( e){
            editPicture.style.display = "block";
            dressField.style.display = "none";
    })
})
