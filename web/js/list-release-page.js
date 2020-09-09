window.addEventListener("load", () => {
  const list = document.querySelector(".release-list")

  const deleteButtons = document.querySelectorAll(".btn-delete-release")
  deleteButtons.forEach( deleteButton =>{
    deleteButton.addEventListener("click", event => {
      list.removeChild(deleteButton.parentElement)
    })
  })
})

