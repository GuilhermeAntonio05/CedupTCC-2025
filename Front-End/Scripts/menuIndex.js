function openMenu() {
  let menu = document.getElementById("menu");
  let findBar = document.getElementById("findBar");
  const display = menu.style.display.includes("flex");

  if (!display) {
    menu.style.display = "flex";
    findBar.style.display = "none";
  } else {
    menu.style.display = "none";
    findBar.style.display = "flex";
  }
}

function logout() {
  localStorage.removeItem("login");
  localStorage.removeItem("session");
  window.location.href = "./pages/login.html";
}

function changeAccount() {
  window.location.href = "./pages/chooseAccount.html";
}
