<template>
  <div class="login-page">
    <body>
      <main>
        <img src="@/assets/slogan.svg" alt="Slogan" />
        <h2 class="main-title">
          Documente seus <b>testes</b> com <b>Test Docs</b> como um humano, não
          como uma máquina
        </h2>
        <div class="test-process">
          <div class="test-process-item">
            <div>
              <div class="bg-dark">
                <span>Requisitos</span>
              </div>
              <svg
                width="24"
                height="40"
                viewBox="0 0 24 40"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M24 21L0 40V0L24 21Z" fill="#4D4D4D" />
              </svg>
            </div>
            <p>
              Desenvolva novas features e solicite testes para aquela versão
            </p>
          </div>
          <div class="test-process-item">
            <div>
              <div class="bg-red">
                <span>Testes</span>
              </div>
              <svg
                width="24"
                height="40"
                viewBox="0 0 24 40"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M24 21L0 40V0L24 21Z" fill="#E62E4A" />
              </svg>
            </div>
            <p>
              Crie seus teste com feedback em tempo real e intuitivo para os
              integrantes
            </p>
          </div>
          <div class="test-process-item">
            <div>
              <div class="bg-blue">
                <span>Relatório</span>
              </div>
              <svg
                width="24"
                height="40"
                viewBox="0 0 24 40"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M24 21L0 40V0L24 21Z" fill="#0681B3" />
              </svg>
            </div>
            <p>Relatórios dos testes aprovando ou reprovando a versão</p>
          </div>
        </div>
      </main>
      <div class="login-container">
        <img src="@/assets/logo.svg" alt="Logo" />
        <form action="#" autocomplete="off">
          <h1>Login</h1>
          <div class="input-group" id="email-form-group">
            <label for="email">Email</label>
            <input
              type="text"
              id="email"
              placeholder="digite seu email"
              v-model="email"
            />
            <span>Email inválido</span>
          </div>
          <div class="input-group" id="password-form-group">
            <label for="password">Senha</label>
            <input
              type="password"
              id="password"
              placeholder="digite sua senha"
              v-model="password"
            />
            <span>Senha inválida</span>
          </div>

          <button @click="postLogin" class="btn-form">Entrar</button>
          <a href="" class="link-help"
            ><router-link to="/signup">Criar nova conta</router-link></a
          >
        </form>
      </div>
      <Dialog></Dialog>
    </body>
  </div>
</template>

<script>
const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

import Dialog from '@/components/Dialog.vue'
  
export default {
  name: "Login",
    components: {
      Dialog
  },
  data() {
    return {
      email: "",
      password: "",
      baseURI: "",
    };
  },
  created: function() {
    if(localStorage.getItem("user")) {
        this.$router.replace("/");
    } 
  },
  methods: {
    postLogin: function() {
      let obj = {
        email: this.email,
        password: this.password,
      };


    const emailFormGroup = document.querySelector("#email-form-group")
    const emailError = emailFormGroup.querySelector("span")

    const passworFormGroup = document.querySelector("#password-form-group")
    const passwordError = passworFormGroup.querySelector("span")

    let hasEmailError = false
    let hasPasswordError = false
      
      this.$http.post(this.baseURI, obj).then((result) => {
        if(!EMAIL_REGEX.test(obj.email)){
          hasEmailError =  true;
          emailError.innerText = "Email inválido";
        }
        if(obj.email === "") {
          hasEmailError =  true
          emailError.innerText = "Campo obrigatório"
        }
        if(obj.password.length < 7) {
          hasPasswordError =  true
          passwordError.innerText = "A senha deve ter mais de 6 caracteres"
        }
        if(obj.password === "") {
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
          localStorage.setItem("user", JSON.stringify(result.data));
          this.$router.push('../main/dashboard.vue');
        } 
      });
    },
  },
};
</script>

<style>
.login-page {
  height: 100%;
  width: 100%;
}
.login-page body {
  min-height: 657px;
}

.login-page .login-container {
  display: flex;
  height: 100%;
  width: 400px;
  background-color: var(--primary-color);
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.login-page .login-container img {
  margin-bottom: 80px;
}

.login-page .login-container form {
  width: 100%;
  padding: 0px 30px;
  flex-direction: column;
  display: flex;
  align-items: center;
}

.login-page .login-container form h1 {
  align-self: flex-start;
  margin-bottom: 50px;
  color: #ffffff;
}

.login-page .login-container form .input-group {
  margin-bottom: 30px;
}

.login-page .login-container form .btn-form {
  margin-top: 60px;
}

.login-page .login-container form .link-help {
  margin-top: 17px;
}

.login-page .login-container form a {
  font-family: Roboto;
  font-style: normal;
  font-weight: normal;
  font-size: 16px;
  color: #bcb8b1;
  text-decoration: none;
}

.login-page main {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex: 1;
  /* margin-top: 50px; */
}

.login-page main img {
  width: 280px;
}
.login-page main .main-title {
  margin-top: 35px;
  font-size: 3.2rem;
  line-height: 3.7rem;
  font-weight: 400;
  color: var(--dark-text);
  text-align: center;
  max-width: 500px;
}

.login-page main .test-process {
  margin-top: 50px;
  display: flex;
  max-width: 620px;
  flex-direction: row;
}

.login-page main .test-process .test-process-item {
  flex: 1;
}

.login-page main .test-process .test-process-item div {
  display: flex;
  flex-direction: row;
  flex: 1;
  position: relative;
}

.login-page main .test-process .test-process-item div svg {
  position: absolute;
  right: -24px;
  top: 0;
  z-index: 5;
}

.login-page main .test-process .test-process-item div div {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 40px;
}

.login-page main .test-process .test-process-item div div span {
  font-size: 1.8rem;
  font-weight: 400;
  text-align: center;

  color: #ffffff;
}

.login-page main .test-process .test-process-item p {
  margin-top: 15px;
  text-align: center;
  font-size: 1.5rem;
  color: var(--dark-text);
  line-height: 1.5rem;
  font-weight: 300;
  letter-spacing: 0.02em;
  padding: 10px;
}

@media (max-width: 1100px) {
  .login-page .login-container {
    width: 350px;
  }
}

@media (max-width: 1040px) {
  .login-page main .test-process {
    display: none;
  }

  .login-page main {
    justify-content: center;
  }
}

@media (max-width: 860px) {
  .login-page main .main-title {
    font-size: 2.8rem;
  }

  .login-page main {
    padding: 40px;
  }
}

@media (max-width: 680px) {
  .login-page main {
    display: none;
  }

  .login-page .login-container {
    flex: 1;
  }
}
</style>