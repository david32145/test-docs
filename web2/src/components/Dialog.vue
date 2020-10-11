<template>
    <div class="alert-dialog">
        <div v-show="!alertDialog" class="dialog-container">
          <h1 class="dialog-header">Título</h1>
          <p class="dialog-body">Context of dialog</p>
          <div class="actions">
            <button class="btn-cancel">Cancelar</button>
            <button class="btn-ok">Ok</button>
          </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Dialog",
    data: {
        dialog : {
        
        }
    },
    methods:{
        alertDialog: function(){
        const dialog = document.querySelector(".alert-dialog")
        if(!dialog){
            return
        }
        const dialogContainer = dialog.querySelector(".dialog-container")
        const dialogHeader = dialogContainer.querySelector(".dialog-header")
        const dialogBody = dialogContainer.querySelector(".dialog-body")
        const btnCancel = dialogContainer.querySelector(".btn-cancel")
        const btnOk = dialogContainer.querySelector(".btn-ok")

        btnCancel.addEventListener("click", () => {
            dialog.style.display = "none"
        })

        btnOk.addEventListener("click", () => {
            dialog.style.display = "none"
        })

        window.Dialog = {
            show(title, body = "") {
            dialogHeader.innerText = title
            dialogBody.innerText = body
            dialog.style.display = "block"
            },
            close() {
            dialog.style.display = "none"
            }
        }
        }
    }
}
</script>

<style>
.alert-dialog {
  width: 100vw;
  height: 100vh;
  position: fixed;
  background: rgba(0, 0, 0, 0.4);
  z-index: 20;
  display: none;
}

@keyframes card-in{
  from{
    transform: translateY(100vh);
  }
  to{
    transform: translateY(0);
  }
}

.alert-dialog .dialog-container {
  margin: 20px auto;
  padding: 20px;
  width: 100%;
  height: auto;
  max-width: 500px;
  background-color: #FFF;
  border-radius: 4px;
  transform: translateY(100vh);
  animation: card-in 0.3s ease-in-out;
  animation-fill-mode: forwards;
}

.alert-dialog .dialog-container h1 {
  font-family: Roboto;
  font-weight: bold;
  font-size: 32px;
  letter-spacing: 0.02em;

  color: #313638;
}

.alert-dialog .dialog-container p {
  font-family: Roboto;
  font-weight: 300;
  font-size: 16px;
  line-height: 18px;
  letter-spacing: 0.02em;
  margin: 5px 0;

  color: rgba(0, 0, 0, 0.75);
}

.alert-dialog .dialog-container .actions {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-end;
}

.alert-dialog .dialog-container .actions button {
  background: transparent;
  border: none;
  font-size: 14px;
  text-transform: uppercase;
  margin-left: 15px;
  cursor: pointer;
  color: #E52E4A;
}

.alert-dialog .dialog-container .actions button + button {
  color: #313638;
}

</style>