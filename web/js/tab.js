window.addEventListener('load', () => {
  const tabs = document.querySelectorAll(".tab")
  tabs.forEach(tab => {
    const tabItems = tab.querySelectorAll(".tab-item")
    tabItems.forEach(tabItem => {
      const label = tabItem.querySelector(".tab-item-label label")
      label.addEventListener("click", event => {
        tabItems.forEach(tItem => tItem.classList.remove("tab-active"))
        tabItem.classList.add("tab-active")
      })
    })
  })
})