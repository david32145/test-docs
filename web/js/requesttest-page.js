const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/;

window.addEventListener('load', () => {
  const versionFormGroup = document.querySelector("#version-form-group")
  const version = versionFormGroup.querySelector("input")
  const versionError = versionFormGroup.querySelector("span")

  const titleFormGroup = document.querySelector("#title-form-group")
  const title = titleFormGroup.querySelector("input")
  const titleError = titleFormGroup.querySelector("span")

  const descriptionFormGroup = document.querySelector("#description-form-group")
  const description = descriptionFormGroup.querySelector("input")
  const descriptionError = descriptionFormGroup.querySelector("span")

  const testeridFormGroup = document.querySelector("#testeruserid-form-group")
  const testerid = testeridFormGroup.querySelector("input")
  const testeridError = testeridFormGroup.querySelector("span")

  const form = document.querySelector('form')
  form.addEventListener("submit", event => {
    event.preventDefault()

    let hasVersionError = false
    let hasTitleError = false
    let hasDescriptionError = false
    let hasTesterIdError = false

    if(version.value === "") {
        hasVersionError = true
        versionError.innerText = "A versão do projeto é obrigatória"
    }
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

    if(testerid.value === "") {
        hasTesterIdError = true
        testeridError.innerText = "O ID do tester é obrigatório"
    }


    if(hasVersionError) {
      versionFormGroup.classList.add("input-group-error")
    } else {
      versionFormGroup.classList.remove("input-group-error")
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

    if(hasTesterIdError) {
        testeridFormGroup.classList.add("input-group-error")
      } else {
        testeridFormGroup.classList.remove("input-group-error")
    }



    if(!(hasVersionError || hasTitleError || hasDescriptionError || hasTesterIdError)) {
      window.location = "/web/list-release-page.html"
    }
  })
})