window.addEventListener('load', () => {
    const dialog = document.querySelector(".alert-dialog")
    const dialogContainer = dialog.querySelector(".dialog-container")
    const dialogHeader = dialogContainer.querySelector(".dialog-header")
    const dialogBody = dialogContainer.querySelector(".dialog-body")
    const btnCancel = dialogContainer.querySelector(".btn-cancel")
    const btnOk = dialogContainer.querySelector(".btn-ok")
  
    btnCancel.addEventListener("click", () => {
      dialog.style.display = "none"
    })
  
    btnOk.addEventListener("click", () => {
      dialog.style.display = "none"
    })
  
    window.Dialog = {
      show(title, body = "") {
        dialogHeader.innerText = title
        dialogBody.innerText = body
        dialog.style.display = "block"
      },
      close() {
        dialog.style.display = "none"
      }
    }
  })