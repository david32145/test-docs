<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>

<style>
html, body {
  height: 100%;
  width: 100%;
}

html {
  font-size: 62.5%;
  line-height: 62.5%;
}

body {
  display: flex;
  flex-direction: row;
  min-height: 600px;
}

* {
  margin: 0;
  padding: 0;
  outline: none;
  font-family: "Roboto", Arial, Helvetica, sans-serif;
  box-sizing: border-box;
}

#app {
  width: 100%;
  height: 100%;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

ul, li {
  list-style: none;
}

input, span, button {
  font-family: "Roboto", Arial, Helvetica, sans-serif;
}

a {
  text-decoration: none;
}

:root{
  --primary-color: #343A40;
  --dark-color: #4D4D4D;
  --dark-text: #313638 ;
  --bg-red: #E62E4A;
  --bg-blue: #0681B3;
  --gray: #7C8B99;
  --gray-light: #91A2B2;
  --light-green: #F5F7FB;
}

/* Titles */
h1{
  font-family: Roboto;
  font-weight: bold;
  font-size: 3.6rem;
  line-height: 4.2rem;
  letter-spacing: 0.02em;
}

/* Input Text */

.input-group {
  display: flex;
  width: 100%;
  flex-direction: column;
  flex: 1;
}

.input-group label {
  font-family: Roboto;
  font-weight: 300;
  font-size: 1.2rem;
  letter-spacing: 0.05em;
  text-transform: uppercase;
  color: #7C8B99;
  margin-bottom: 7px;
  transition: 0.3s;
}

.input-group input {
  background: transparent;
  outline: none;
  border: none;
  font-family: Roboto;
  font-weight: 300;
  font-size: 1.4rem;
  letter-spacing: 0.02em;
  padding: 4px;

  color: #91A2B3;

  border-bottom: 2px solid #91A2B3;
  transition: 0.3s;
}

.input-group span {
  color: #E62E4A;
  visibility: hidden;
  font-weight: 300;
  font-size: 1.2rem;
  margin-top: 8px;
}

/*Override de algumas propriedades acima para reaproveitar sem  duplicar*/
.input-group-ligth label {
  font-weight: 400;
  color: rgba(49, 54, 56, 0.8);
}

.input-group-ligth input {
  color: var(--dark-text);
  border-bottom-color: var(--gray);
}

.input-group-ligth.input-group-active label {
  color: var(--dark-text);
}

.input-group-ligth.input-group-active input {
  border-bottom-color: var(--dark-text);
}

.input-group-active input {
  border-bottom-color: #FFF;
}

.input-group-active label {
  color: #FFF;
}

.input-group-error input {
  border-bottom-color: #E62E4A;
}

.input-group-error label {
  color: #E62E4A;
}

.input-group-error span {
  visibility: visible;
}

/* Buttons */
.btn-form {
  border: none;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #0681B3;
  box-shadow: 0px 0px 4px rgba(255, 255, 255, 0.25);
  border-radius: 5px;
  font-family: Roboto;
  font-weight: 500;
  font-size: 2.0rem;
  letter-spacing: 0.02em;
  height: 50px;
  color: #FFFFFF;
  cursor: pointer;
}

.btn-form:hover {
  background-color: #0084B9;
}

/* Backgrounds */
.bg-primary {
  background: var(--primary-color);
}

.bg-dark {
  background: var(--dark-color);
}

.bg-red {
  background-color: var(--bg-red);
}

.bg-blue {
  background-color: var(--bg-blue);
}

</style>
