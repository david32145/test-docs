<template>
  <div class="login-view">
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
          <p>Desenvolva novas features e solicite testes para aquela versão</p>
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
        <div
          :class="['input-group', { 'input-group-error': hasEmailError }]"
          id="email-form-group"
        >
          <label for="email">Email</label>
          <input
            type="text"
            id="email"
            placeholder="digite seu email"
            v-model="email"
          />
          <span>Email inválido</span>
        </div>
        <div
          :class="['input-group', { 'input-group-error': hasPasswordError }]"
          id="password-form-group"
        >
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
        <span class="link-help">
          <router-link to="/signup">Criar nova conta</router-link>
        </span>
      </form>
    </div>
    <Dialog></Dialog>
  </div>
</template>

<script>
const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
import httpClient from "../providers/http-client";
import Dialog from "@/components/Dialog.vue";

export default {
  name: "login-view",
  components: {
    Dialog,
  },
  data() {
    return {
      email: "",
      password: "",
      baseURI: "",
      hasEmailError: false,
      hasPasswordError: false,
      textEmailError: "",
      textPasswordError: "",
    };
  },
  created: function () {
    if (localStorage.getItem("user")) {
      this.$router.replace("/");
    }
  },
  methods: {
    postLogin(event) {
      event.preventDefault();
      if (this.fieldsValidation()) {
        const requestBody = {
          email: this.email,
          password: this.password,
        };
        httpClient
          .post("/auth", requestBody)
          .then((response) => {
            const userId = response.data
            localStorage.setItem('@testdocs/user', JSON.stringify({
              id: userId
            }))
            this.$router.push({name: 'Dashboard'})
          })
          .catch((err) => {
            alert(err.response.data.message || "Ocorreu um error inesperado");
          });
      }
    },
    fieldsValidation() {
      this.hasEmailError = false;
      this.hasPasswordError = false;

      if (!EMAIL_REGEX.test(this.email)) {
        this.hasEmailError = true;
        this.textEmailError = "Email inválido";
      }
      if (this.email === "") {
        this.hasEmailError = true;
        this.textEmailError = "Campo obrigatório";
      }
      if (this.password.length < 5) {
        this.hasPasswordError = true;
        this.textPasswordError = "A senha deve ter mais de 4 caracteres";
      }
      if (this.password === "") {
        this.hasPasswordError = true;
        this.textPasswordError = "Campo obrigatório";
      }
      return !this.hasEmailError && !this.hasPasswordError;
    },
  },
};
</script>

<style>
.login-view {
  display: flex;
  flex: 1;
  flex-direction: row;
  height: 100%;
}

.login-view .login-container {
  display: flex;
  height: 100%;
  width: 400px;
  background-color: var(--primary-color);
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.login-view .login-container img {
  margin-bottom: 80px;
}

.login-view .login-container form {
  width: 100%;
  padding: 0px 30px;
  flex-direction: column;
  display: flex;
  align-items: center;
}

.login-view .login-container form h1 {
  align-self: flex-start;
  margin-bottom: 50px;
  color: #ffffff;
}

.login-view .login-container form .input-group {
  margin-bottom: 30px;
}

.login-view .login-container form .btn-form {
  margin-top: 60px;
}

.login-view .login-container form .link-help {
  margin-top: 17px;
}

.login-view .login-container form a {
  font-family: Roboto;
  font-style: normal;
  font-weight: normal;
  font-size: 16px;
  color: #bcb8b1;
}

.login-view main {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex: 1;
  margin-top: 50px;
}

.login-view main img {
  width: 280px;
}
.login-view main .main-title {
  margin-top: 35px;
  font-size: 3.2rem;
  line-height: 3.7rem;
  font-weight: 400;
  color: var(--dark-text);
  text-align: center;
  max-width: 500px;
}

.login-view main .test-process {
  margin-top: 50px;
  display: flex;
  max-width: 620px;
  flex-direction: row;
}

.login-view main .test-process .test-process-item {
  flex: 1;
}

.login-view main .test-process .test-process-item div {
  display: flex;
  flex-direction: row;
  flex: 1;
  position: relative;
}

.login-view main .test-process .test-process-item div svg {
  position: absolute;
  right: -24px;
  top: 0;
  z-index: 5;
}

.login-view main .test-process .test-process-item div div {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 40px;
}

.login-view main .test-process .test-process-item div div span {
  font-size: 1.8rem;
  font-weight: 400;
  text-align: center;

  color: #ffffff;
}

.login-view main .test-process .test-process-item p {
  margin-top: 15px;
  text-align: center;
  font-size: 1.5rem;
  color: var(--dark-text);
  line-height: 1.5rem;
  font-weight: 300;
  letter-spacing: 0.02em;
  padding: 10px;
}

.login-view .login-container form .link-help {
  font-family: Roboto;
  font-weight: 400;
  font-size: 1.6rem;
  letter-spacing: 0.02em;
  text-decoration: none;
  color: #bcb8b1;
}

.login-view .login-container form .link-help:hover {
  opacity: 0.9;
}

@media (max-width: 1100px) {
  .login-view .login-container {
    width: 350px;
  }
}

@media (max-width: 1040px) {
  .login-view main .test-process {
    display: none;
  }

  .login-view main {
    justify-content: center;
  }
}

@media (max-width: 860px) {
  .login-view main .main-title {
    font-size: 2.8rem;
  }

  .login-view main {
    padding: 40px;
  }
}

@media (max-width: 680px) {
  .login-view main {
    display: none;
  }

  .login-view .login-container {
    flex: 1;
  }
}
</style>