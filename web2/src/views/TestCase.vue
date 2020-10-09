<template>
  <div class="test-case">
    <Header></Header>
    <SubHeaderWithTitle title="Criar novo caso de teste"/>

    <main>
        <form action="#">
            <div class="input-group input-group-ligth" id="title-form-group">
                <label for="title">Título</label>
                <input type="text" id="title" placeholder="" v-model="titulo">
                <span>Título inválido</span>
            </div>
            <div class="input-group input-group-ligth" id="description-form-group">
                <label for="description">Descrição</label>
                <input type="text" id="description" placeholder="" v-model="descricao">
                <span>Descrição inválida</span>
            </div>
            <div class="input-group input-group-ligth" id="task-form-group">
                <label for="task">Task ID:</label>
                <input type="text" id="task" placeholder="" v-model="taskid">
                <span>Task inválida</span>
            </div>
            <button type="submit" class="btn-form">Criar caso de teste</button>
        </form>
    </main>
    <Dialog></Dialog>
</div>
</template>

<script>
import Header from '@/components/Header.vue'
import SubHeaderWithTitle from '@/components/SubHeaderWithTitle.vue'
import Dialog from '@/components/Dialog.vue'

export default {
  name: "TestCase",
  components: {
      Header,
      SubHeaderWithTitle,
      Dialog,
  },  
    data: function() {
      return {
        titulo: "",
        descricao: "",
        taskid: "",
        baseURI: "",
      };
    },
    methods: {
      insertTestCase: function() {
        let obj = {
          titulo: this.titulo,
          descricao: this.descricao,
          taskid: this.taskid,
        };

        const titleFormGroup = document.querySelector("#title-form-group")
        const titleError = titleFormGroup.querySelector("span")

        const descriptionFormGroup = document.querySelector("#description-form-group")
        const descriptionError = descriptionFormGroup.querySelector("span")

        const taskFormGroup = document.querySelector("#task-form-group")
        const taskError = taskFormGroup.querySelector("span")

        this.$http.post(this.baseURI, obj).then((result) => {
          let hasTitleError = false
          let hasDescriptionError = false
          let hasTaskError = false

          if(obj.titulo === "") {
            hasTitleError = true
            titleError.innerText = "O título é obrigatório"
          }
          if(description.value === "") {
            hasDescriptionError = true
            descriptionError.innerText = "A descrição é obrigatória"
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
          if (hasTitleError && hasDescriptionError && hasTaskError ) {
            this.$router.push({ name: 'TestCases'});
          } 
        });
      },
    },
};
</script>

<style>
.test-case body {
    display: flex;
    flex-direction: column;
  }
  
  #sub-header {
    min-height: 60px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
  }
  
  #sub-header h1 {
    font-family: Roboto;
    font-weight: 500;
    font-size: 2.2rem;
    line-height: 2.6rem;
    text-align: center;
    letter-spacing: 0.02em;
    color: var(--dark-text);
  }
  
  .test-case main {
    display: flex;
    flex: 1;
    flex-direction: column;
    align-items: center;
    background-color: var(--light-green);
    padding: 20px;
  }
  
  .test-case main form {
    width: 100%;
    max-width: 600px;
    margin-top: 30px;
  }
  
  .test-case main form .input-group {
    margin-bottom: 22px;
  }
  
  .test-case main form .file-input-preview img {
    width: 100%;
    height: 180px;
    object-fit: fill;
  }
  
  .test-case main form .btn-form {
    margin-top: 30px;
  }
</style>