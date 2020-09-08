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
})