function openMenu() {
  let menu = document.getElementById("menu");
  let findBar = document.getElementById("findBar") || null;
  const display = menu.style.display.includes("flex");

  if (!display) {
    menu.style.display = "flex";
    if (findBar) findBar.style.display = "none";
    getUsername();
  } else {
    menu.style.display = "none";
    if (findBar) findBar.style.display = "flex";
  }
}

function getUsername() {
  
  fetch(
    `http://localhost:8080/home/aluno/email?email=${JSON.parse(localStorage.getItem("lastSession")).email}`
  )
    .then((response) => response.json())
    .then(
      (data) => (document.getElementById("Username").innerText = data.nome)
    );
}

function logout() {
  localStorage.removeItem("login");
  window.location.href = "../../pages/aluno/login.html";
}

function changeAccount() {
  window.location.href = "../../pages/aluno/chooseAccount.html";
}
function closeMenu() {
  document.getElementById("menu").style.display = "none";
}

