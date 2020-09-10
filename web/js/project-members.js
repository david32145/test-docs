window.addEventListener("load", () => {
  const list = document.querySelector(".members-list")
  const deleteButtons = document.querySelectorAll(".members-list-item button")
  const addUserInput = document.querySelector(".project-members-header input")
  deleteButtons.forEach(deleteButton => {
    deleteButton.addEventListener("click", () => {
      list.removeChild(deleteButton.parentElement)
    })
  })

  addUserInput.addEventListener("keypress", (event) => {
    if(event.keyCode === 13) {
      const li = document.createElement('li')
      li.classList.add("members-list-item")
      
      const memberData = document.createElement("div")
      memberData.classList.add("member-data")

      const img = document.createElement("img")
      img.src = "./img/user_avatar.svg"
      img.alt = "Avatar do Membro"

      const memberInfo = document.createElement("div")
      memberInfo.classList.add("member-info")

      const memberName_and_type = document.createElement("div")
      memberName_and_type.classList.add("member-name_and_type")

      const p = document.createElement("p")
      p.classList.add("member-name")
      p.innerText = "Rafael vieira"
      const span = document.createElement("span")
      span.classList.add("type-member")
      span.classList.add("owner-member")
      span.innerText = "dono"

      memberName_and_type.append(p, span)

      const p2 = document.createElement("p")
      p2.classList.add("member-email")
      p2.innerText = "Rafael@gmail.com"

      memberInfo.append(memberName_and_type, p2)

      memberData.append(img, memberInfo)

      const button = document.createElement("button")
      button.innerText = "Remover"
      button.addEventListener("click", () => {
        list.removeChild(button.parentElement)
      })

      li.append(memberData, button)

      list.append(li)
    }
  })
})