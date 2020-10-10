<template>
    <div class="signup-page">
        <main>
            <h2 class="main-title">Junte-se ao Test Docs</h2>
            <div class="join-infos">
                <div class="join-infos-items">
                    <div class="info-item">
                        <img src="@/assets/correct1.svg" alt="icon">
                        <label for="info-a">Faça solicitações de testes para usuários específicos.</label>
                    </div>
                    
                    <div class="info-item">
                        <img src="@/assets/correct2.svg" alt="icon">
                        <label for="info-b">Monte suítes de testes, com casos personalizados para cada projeto.</label>
                    </div>
                    
                    <div class="info-item">
                        <img src="@/assets/correct1.svg" alt="icon">
                        <label for="info-a">Conecte seu projeto com o github, referenciando releases e issues.</label>
                    </div>

                    <div class="info-item">
                        <img src="@/assets/correct2.svg" alt="icon">
                        <label for="info-b">Forneça feedback para cada caso de teste informando onde está os erros ou acertos</label>
                    </div>

                    <div class="info-item">
                        <img src="@/assets/correct1.svg" alt="icon">
                        <label for="info-a">Receba notificações pelo seu email e quando entrar no sistema.</label>
                    </div>
                </div>
                <div class="info-pessoa">
                    <img src="@/assets/image.svg" alt="Pessoa"> 
                </div>         
            </div>        
        </main>
        <div class="sign-up-container">
            <form action="#" autocomplete="off">
                <h1>Sign-Up</h1>
                <div class="input-group" id="email-form-group">
                    <label for="email">Email</label>
                    <input type="text" id="email" placeholder="Digite seu email" v-model="email">
                    <span>{{textEmailError}}</span>
                </div>
                <div class="input-group" id="password-form-group">
                    <label for="password">Senha</label>
                    <input type="password" id="password" placeholder="Digite sua senha" v-model="password">
                    <span>{{textPasswordError}}</span>
                </div>
                <div class="input-group" id="nome-form-group">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" placeholder="Digite seu nome" v-model="nome">
                    <span>textNomeError</span>
                </div>
                <div class="input-group" id="cargo-form-group">
                    <label for="cargo">Cargo</label>
                    <input type="text" id="cargo" placeholder="Programador" v-model="cargo">
                    <span>Cargo inválido</span>
                </div>
                <div class="input-group" id="descricao-form-group">
                    <label for="descricao">Descrição</label>
                    <input type="text" id="descricao" placeholder="Descreva-se aqui" v-model="descricao">
                    <span>Descricao inválido</span>
                </div>
                <div class="input-group" id="github-form-group">
                    <label for="github">GitHub</label>
                    <input type="text" id="github" placeholder="https://github.com/user" v-model="github">
                    <span>{{textGitHubError}}</span>
                </div>
                <div class="helps">
                    <span class="link-help">
                        <router-link to="*">Já tenho uma conta</router-link>
                    </span>
                    <img src="@/assets/avatar.png" alt="Avatar">
                </div>
                <button @click="postRegister" class="btn-form">Cadastrar</button>
            </form>
        </div>
        <Dialog></Dialog>
    </div>
</template>

<script>
const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const NOME_REGEX = /^[a-zA-ZéúíóáÉÚÍÓÁèùìòàçÇÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄ\-\ \s]+$/;
const GITHUB_URI_REGEX = /^https:\/\/github.com\/(.)*\/(.)*/;
import Dialog from '@/components/Dialog.vue'

export default {
    name: "SignUp",
    components : {
        Dialog
    },
    data: function() {
        return {
        email: '',
        password: '',
        nome: '',
        cargo: '',
        descricao: '',
        github: '',
        baseURI: '',
        hasEmailError: false,
        hasPasswordError: false,
        hasNomeError: false,
        hasGitHubError: false,
        textEmailError: '',
        textPasswordError: '',
        textNomeError: '',
        textGitHubError: '',
        };
    },
    methods: {
        postRegister: function() {
        let obj = {
            email: this.email,
            password: this.password,
            nome: this.nome,
            cargo: this.cargo,
            descricao: this.descricao,
            github: this.github,
        };
        this.$http.post(this.baseURI, obj).then((result) => {
            if (this.fieldValidation()) {
            localStorage.setItem("user", JSON.stringify(result.data));
            this.$router.push('*');
            window.location.href = "*";
            } else {
            alert("Verifique os campos.");
            }
        });
        },
        fieldValidation(){
            this.hasEmailError = false
            this.hasPasswordError = false
            this.hasNomeError = false
            this.hasGitHubError = false

            if (!EMAIL_REGEX.test(this.email)) {
                this.hasEmailError = true
                this.textEmailError = "Email inválido"
            }
            if (this.email === "") {
                this.hasEmailError = true
                this.textEmailError = "Campo obrigatório"
            }
            if (this.password.length < 7) {
                this.hasPasswordError = true
                this.textPasswordError = "A senha deve ter mais de 6 caracteres"
            }
            if (this.password === "") {
                this.hasPasswordError = true
                this.textPasswordError = "Campo obrigatório"
            }
            if  (!NOME_REGEX.test(this.nome)){
                this.hasNomeError = true
                this.textNomeError = "Nome inválido"
            }
            if (this.nome === "") {
                this.hasNomeError = true
                this.textNomeError = "Campo obrigatório"
            }
            if(this.github === ""){
                this.hasGitHubError = false
            }else if (!GITHUB_URI_REGEX.test(this.github)){
                this.hasGitHubError = true
                this.textGitHubError = "Link inválido"
            }

            return (!hasEmailError && !hasPasswordError && !hasNomeError && !hasGitHubError)
        }
    },
};
</script>

<style>
.signup-page {
    height: 100%;
    min-height: 660px;
    overflow: auto;
    display: flex;
}

.signup-page .sign-up-container{
    display: flex;
    height: auto;
    width: 400px;
    background-color: var(--primary-color);
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

.signup-page .sign-up-container form {
    width: 100%;
    height: 100%;
    padding: 20px 30px;
    flex-direction: column;
    display: flex;
    align-items: center;
}

.signup-page .sign-up-container form h1 {
    align-self: flex-start;
    margin-bottom: 30px;
    color: #FFFFFF;
}

.signup-page .sign-up-container form .input-group {
    margin-bottom: 15px;
}

.signup-page .sign-up-container form .btn-form {
    margin-top: 30px;
}

.signup-page .sign-up-container form .helps{
    width: 100%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
}
.signup-page .sign-up-container form .helps a{
    font-family: Roboto;
    font-style: normal;
    font-weight: normal;
    font-size: 16px;
    color: #BCB8B1;
    text-decoration: none;
}
.signup-page .sign-up-container form .helps img{
    border-radius: 5px;
}

.signup-page main {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    flex: 1;
  }
  
.signup-page main .main-title {
    width: 100%;
    max-width: 800px;
    font-size: 2.5rem;
    line-height: 3.7rem;
    color: var(--dark-text);
    text-align: left;
    font-family: Roboto;
    font-weight: bold;
    font-size: 36px;
    letter-spacing: 0.02em;
    padding-left: 71px;
    color: #313638;
  }

.signup-page main .join-infos {
    margin-top: 15px;
    display: flex;
    max-width: 800px;
    flex-direction: row;
    align-items: center;
    font-family: Roboto;
  }
.signup-page main .join-infos .info-item {
    display: flex;
    flex-direction: row;
    align-items: center;
    max-width: 600px;
    max-height: 200px;
    font-family: Roboto;
    font-size: 1.8em;
    line-height: 1.2em;
  }

.signup-page main .join-infos .info-item + .info-item {
    margin-top: 30px;
  }

.signup-page main .join-infos .join-infos-item .info-item{
      width: 55px;
      height: 55px;
  }

.signup-page main .join-infos .info-item label{
    margin-left: 15px;

    font-family: Roboto;
    font-weight: 300;
    font-size: 2.0rem;
    line-height: 2.6rem;
    text-align: justify;
    letter-spacing: 0.02em;

    color: rgba(0, 0, 0, 0.75);
  }
.signup-page main .join-infos .info-pessoa{
      margin-left: 142px;
  }

@media (max-width: 1230px) {
    .signup-page .info-pessoa {
        margin-left: 0;
        display: none;
    }
    .signup-page main {
        padding: 10px;
    }
}

@media (max-width: 738px) {
  .signup-page main {
      display: none;
  }

  .signup-page .sign-up-container {
      flex: 1;
  }
}
</style>