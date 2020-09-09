const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/;

window.addEventListener('load', () => {
  const nameFormGroup = document.querySelector("#name-form-group")
  const name = nameFormGroup.querySelector("input")
  const nameError = nameFormGroup.querySelector("span")

  const descriptionFormGroup = document.querySelector("#description-form-group")
  const description = descriptionFormGroup.querySelector("input")
  const descriptionError = descriptionFormGroup.querySelector("span")

  const githubFormGroup = document.querySelector("#github-uri-form-group")
  const github = githubFormGroup.querySelector("input")
  const githubError = githubFormGroup.querySelector("span")

  const form = document.querySelector('form')
  form.addEventListener("submit", event => {
    event.preventDefault()

    let hasNameError = false
    let hasDescriptionError = false
    let hasGithubError = false

    if(!name.value || name.value.length < 5) {
      hasNameError = true
      if(!name.value) {
        nameError.innerText = "O nome é obrigatório"
      } else {
        nameError.innerText = "O nome deve ter pelo menos 5 caracteres"
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

    if(!github.value || !GITHUB_URI_REGEX.test(github.value)) {
      hasGithubError = true
      if(!github.value) {
        githubError.innerText = "A url do github é obrigatória"
      } else {
        githubError.innerText = "Url inválida"
      }
    }

    if(hasNameError) {
      nameFormGroup.classList.add("input-group-error")
    } else {
      nameFormGroup.classList.remove("input-group-error")
    }

    if(hasDescriptionError) {
      descriptionFormGroup.classList.add("input-group-error")
    } else {
      descriptionFormGroup.classList.remove("input-group-error")
    }

    if(hasGithubError) {
      githubFormGroup.classList.add("input-group-error")
    } else {
      githubFormGroup.classList.remove("input-group-error")
    }

    if(!(hasNameError || hasDescriptionError || hasGithubError)) {
      Dialog.show("Sucesso", "Novo projeto criado")
    }
  })
})