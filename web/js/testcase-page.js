const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/;

window.addEventListener('load', () => {
  const titleFormGroup = document.querySelector("#title-form-group")
  const title = titleFormGroup.querySelector("input")
  const titleError = titleFormGroup.querySelector("span")

  const descriptionFormGroup = document.querySelector("#description-form-group")
  const description = descriptionFormGroup.querySelector("input")
  const descriptionError = descriptionFormGroup.querySelector("span")

  const taskFormGroup = document.querySelector("#task-form-group")
  const task = taskFormGroup.querySelector("input")
  const taskError = taskFormGroup.querySelector("span")

  const form = document.querySelector('form')
  form.addEventListener("submit", event => {
    event.preventDefault()

    let hasTitleError = false
    let hasDescriptionError = false
    let hasTaskError = false

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

    if(task.value === "") {
        hasTaskError = true
        taskError.innerText = "A task é obrigatória"
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

    if(hasTaskError) {
        taskFormGroup.classList.add("input-group-error")
      } else {
        taskFormGroup.classList.remove("input-group-error")
    }

    if(!(hasTitleError || hasDescriptionError || hasTaskError )) {
      Dialog.show("Sucesso", "Novo caso de teste criado")
    }
  })
})