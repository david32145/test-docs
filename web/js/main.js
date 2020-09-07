window.addEventListener('load', () => {
  const inputGroups = document.querySelectorAll(".input-group")
  inputGroups.forEach(inputGroup => {
    const input = inputGroup.querySelector("input")
    input.addEventListener("focus", () => {
      inputGroup.classList.add("input-group-active")
    })
    input.addEventListener("blur", () => {
      inputGroup.classList.remove("input-group-active")
    })
  })


  const previewImages = document.querySelectorAll(".file-input-preview")
  previewImages.forEach(previewImage => {
    const image = previewImage.querySelector("img")
    const input = previewImage.querySelector("input")
    image.addEventListener("click", () => {
      input.click()
    })
    input.addEventListener("change", event => {
      const files = event.target.files
      if(files.length <= 0) {
        return
      }

      const file = files[0]
      const urlPreview = URL.createObjectURL(file)
      image.src = urlPreview
    })
  })
})