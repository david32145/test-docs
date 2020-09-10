const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

window.addEventListener('load', () => {
  const emailFormGroup = document.querySelector("#email-form-group")
  const email = emailFormGroup.querySelector("input")
  const emailError = emailFormGroup.querySelector("span")

  const passworFormGroup = document.querySelector("#password-form-group")
  const password = passworFormGroup.querySelector("input")
  const passwordError = passworFormGroup.querySelector("span")

  const form = document.querySelector("form")
  form.addEventListener("submit", event => {
    event.preventDefault()
    let hasEmailError = false
    let hasPasswordError = false
    if(!EMAIL_REGEX.test(email.value)) {
      hasEmailError =  true
      emailError.innerText = "Email inválido"
    }
    if(email.value === "") {
      hasEmailError =  true
      emailError.innerText = "Campo obrigatório"
    }
    if(password.value.length < 7) {
      hasPasswordError =  true
      passwordError.innerText = "A senha deve ter mais de 6 caracteres"
    }
    if(password.value === "") {
      hasPasswordError =  true
      passwordError.innerText = "Campo obrigatório"
    }

    if(hasEmailError) {
      emailFormGroup.classList.add("input-group-error")
    }else {
      emailFormGroup.classList.remove("input-group-error")
    }
    if(hasPasswordError) {
      passworFormGroup.classList.add("input-group-error")
    }else{
      passworFormGroup.classList.remove("input-group-error")
    }
    if(!hasEmailError && !hasPasswordError) {
      window.location = "/web/dashboard-page.html"
    }
  })
})