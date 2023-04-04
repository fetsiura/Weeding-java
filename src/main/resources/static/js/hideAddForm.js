document.addEventListener("DOMContentLoaded", function () {

    const newGallery = document.getElementById('newGallery')
    const newGalleryItems = document.getElementById('newGalleryItems')


    newGallery.addEventListener('click', function (e) {
        console.log("ru")
        if(newGalleryItems.style.display === "block"){
            newGalleryItems.style.display = "none";

        }else {
            newGalleryItems.style.display = "block";
        }
    })

})
