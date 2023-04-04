const body = document.querySelector('body')
const spec = document.getElementById('spec')
const img = spec.querySelectorAll('img.fully')

img.forEach( ele => {
    ele.addEventListener('click', function (e){
        const div = document.createElement('div')
        div.classList.add('fullScreen')
        body.appendChild(div)
        const img = document.createElement('img')
        img.setAttribute('src',this.src)
        div.appendChild(img)


        const button = document.createElement('button')
        div.appendChild(button)
        button.classList.add('close')
        button.innerText = 'close'


        button.addEventListener('click', function (e){
            body.removeChild(div)
        })
    })
})

