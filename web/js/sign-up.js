const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const NOME_REGEX = /^[a-zA-ZéúíóáÉÚÍÓÁèùìòàçÇÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄ\-\ \s]+$/;
const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/;

window.addEventListener('load', () => {
    const emailFormGroup = document.querySelector("#email-form-group")
    const email = emailFormGroup.querySelector("input")
    const emailError = emailFormGroup.querySelector("span")

    const passworFormGroup = document.querySelector("#password-form-group")
    const password = passworFormGroup.querySelector("input")
    const passwordError = passworFormGroup.querySelector("span")

    const nomeFormGroup = document.querySelector("#nome-form-group")
    const nome = nomeFormGroup.querySelector("input")
    const nomeError = nomeFormGroup.querySelector("span")

    const gitFormGroup = document.querySelector("#github-form-group")
    const github = gitFormGroup.querySelector("input")
    const githubError = gitFormGroup.querySelector("span")


    const form = document.querySelector("form")
    form.addEventListener("submit", event => {
        event.preventDefault()
        let hasEmailError = false
        let hasPasswordError = false
        let hasNomeError = false;
        let hasGitHubError = false;

        if (!EMAIL_REGEX.test(email.value)) {
            hasEmailError = true
            emailError.innerText = "Email inválido"
        }
        if (email.value === "") {
            hasEmailError = true
            emailError.innerText = "Campo obrigatório"
        }
        if (password.value.length < 7) {
            hasPasswordError = true
            passwordError.innerText = "A senha deve ter mais de 6 caracteres"
        }
        if (password.value === "") {
            hasPasswordError = true
            passwordError.innerText = "Campo obrigatório"
        }
        if  (!NOME_REGEX.test(nome.value)){
            hasNomeError = true
            nomeError.innerText = "Nome inválido"
        }
        if (nome.value === "") {
            hasNomeError = true
            nomeError.innerText = "Campo obrigatório"
        }
        if(github.value === ""){
            hasGitHubError = false
        }else if (!GITHUB_URI_REGEX.test(github.value)){
            hasGitHubError = true
            githubError.innerText = "Link inválido"
        }

        if (hasEmailError) {
            emailFormGroup.classList.add("input-group-error")
        } else {
            emailFormGroup.classList.remove("input-group-error")
        }
        if (hasPasswordError) {
            passworFormGroup.classList.add("input-group-error")
        } else {
            passworFormGroup.classList.remove("input-group-error")
        }
        if (hasNomeError) {
            nomeFormGroup.classList.add("input-group-error")
        } else {
            nomeFormGroup.classList.remove("input-group-error")
        }
        if (hasGitHubError) {
            gitFormGroup.classList.add("input-group-error")
        } else {
            gitFormGroup.classList.remove("input-group-error")
        }
        if (!hasEmailError && !hasPasswordError && !hasNomeError && !hasGitHubError) {
            window.location = "/web/login-page.html"
        }
    })
})