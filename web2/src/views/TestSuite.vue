<template>
  <div class="test-suite">
    <Header></Header>
    <SubHeaderWithTitle title="Criar nova suíte de testes"/>
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
            <button @click="insertTestSuite" class="btn-form">Criar suíte</button>
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
  name: "UserInsert",
  components: {
      Header,
      SubHeaderWithTitle,
      Dialog
  }, 
    data: function() {
      return {
        titulo: "",
        descricao: "",
        baseURI: "",
      };
    },

    methods: {
      insertTestSuite: function() {
        let obj = {
          titulo: this.titulo,
          descricao: this.descricao,
        };

        const titleFormGroup = document.querySelector("#title-form-group")
        const titleError = titleFormGroup.querySelector("span")
        const descriptionFormGroup = document.querySelector("#description-form-group")
        const descriptionError = descriptionFormGroup.querySelector("span")

        this.$http.post(this.baseURI, obj).then((result) => {
          let hasTitleError = false
          let hasDescriptionError = false

          if(obj.titulo === "") {
              hasTitleError = true
              titleError.innerText = "O título é obrigatório"
          }     
          if (hasTitleError) {
            this.$router.push({ name: 'TestSuites'});
          } 
        });
      },
    },
};
</script>

<style>
.test-suite {
    height: 100%;
    display: flex;
    flex-direction: column;
  }
  
  .test-suite main {
    display: flex;
    flex: 1;
    flex-direction: column;
    align-items: center;
    background-color: var(--light-green);
    padding: 20px;
  }
  
  .test-suite main form {
    width: 100%;
    max-width: 600px;
    margin-top: 30px;
  }
  
  .test-suite main form .input-group {
    margin-bottom: 22px;
  }
  
  .test-suite main form .file-input-preview img {
    width: 100%;
    height: 180px;
    object-fit: fill;
  }
  
  .test-suite main form .btn-form {
    margin-top: 30px;
  }
</style>