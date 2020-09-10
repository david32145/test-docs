const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/;

window.addEventListener('load', () => {
  const titleFormGroup = document.querySelector("#title-form-group")
  const title = titleFormGroup.querySelector("input")
  const titleError = titleFormGroup.querySelector("span")

  const descriptionFormGroup = document.querySelector("#description-form-group")
  const description = descriptionFormGroup.querySelector("input")
  const descriptionError = descriptionFormGroup.querySelector("span")

  const statusFormGroup = document.querySelector("#status-form-group")
  const status = statusFormGroup.querySelector("input")
  const statusError = statusFormGroup.querySelector("span")

  const reportFormGroup = document.querySelector("#report-form-group")
  const report = reportFormGroup.querySelector("input")
  const reportError = reportFormGroup.querySelector("span")

  const taskFormGroup = document.querySelector("#task-form-group")
  const task = taskFormGroup.querySelector("input")
  const taskError = taskFormGroup.querySelector("span")

  const descriptionimageFormGroup = document.querySelector("#descriptionimage-form-group")
  const descriptionimage = descriptionimageFormGroup.querySelector("input")
  const descriptionimageError = descriptionimageFormGroup.querySelector("span")

  const form = document.querySelector('form')
  form.addEventListener("submit", event => {
    event.preventDefault()

    let hasTitleError = false
    let hasDescriptionError = false
    let hasStatusError = false
    let hasReportError = false
    let hasTaskError = false
    let hasDescriptionImageError = false

    if(!title.value || title.value.length < 5) {
      hasTitleError = true
      if(!title.value) {
        titleError.innerText = "O título é obrigatório"
      } else {
        titleError.innerText = "O título deve ter pelo menos 5 caracteres"
      }
    }

    if(!description.value || description.value.length < 10) {
      hasDescriptionError = true
      if(!description.value) {
        descriptionError.innerText = "A descrição é obrigatória"
      } else {
        descriptionError.innerText = "A descrição deve ter pelo menos 10 caracteres"
      }
    }

    if(status.value === "") {
        hasStatusError = true
        statusError.innerText = "O status do teste é obrigatório"
 
    }

    if(report.value === "") {
        hasReportError = true
        reportError.innerText = "A descrição é obrigatória"
    }

    if(task.value === "") {
        hasTaskError = true
        taskError.innerText = "A task é obrigatória"
    }

    if(descriptionimage.value === "") {
        hasdescriptionimageError = true
        descriptionimageError.innerText = "A descrição da imagem é obrigatória"
    }

    if(hasTitleError) {
      titleFormGroup.classList.add("input-group-error")
    } else {
      titleFormGroup.classList.remove("input-group-error")
    }

    if(hasDescriptionError) {
      descriptionFormGroup.classList.add("input-group-error")
    } else {
      descriptionFormGroup.classList.remove("input-group-error")
    }

    if(hasStatusError) {
        statusFormGroup.classList.add("input-group-error")
      } else {
        statusFormGroup.classList.remove("input-group-error")
    }

    if(hasReportError) {
        reportFormGroup.classList.add("input-group-error")
      } else {
        reportFormGroup.classList.remove("input-group-error")
    }

    if(hasTaskError) {
        taskFormGroup.classList.add("input-group-error")
      } else {
        taskFormGroup.classList.remove("input-group-error")
    }

    if(hasDescriptionImageError) {
        descriptionimageFormGroup.classList.add("input-group-error")
      } else {
        descriptionimageFormGroup.classList.remove("input-group-error")
    }


    if(!(hasTitleError || hasDescriptionError || hasStatusError || hasReportError || hasTaskError || hasDescriptionImageError )) {
      Dialog.show("Sucesso", "Novo caso de teste criado")
    }
  })
})