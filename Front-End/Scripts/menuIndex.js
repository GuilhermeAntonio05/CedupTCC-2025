function openMenu() {
  let menu = document.getElementById("menu");
  let findBar = document.getElementById("findBar");
  const display = menu.style.display.includes("flex");

  if (!display) {
    menu.style.display = "flex";
    findBar.style.display = "none";
    getUsername();
  } else {
    menu.style.display = "none";
    findBar.style.display = "flex";
  }
}

function getUsername() {
  fetch(
    `http://localhost:3306/home/aluno/email?=${
      JSON.parse(localStorage.getItem("lastSession")).email
    }`
  )
    .then((response) => response.json())
    .then(
      (data) => (document.getElementById("Username").innerText = data.email)
    );
}

function logout() {
  localStorage.removeItem("login");
  //localStorage.removeItem("session");
  window.location.href = "../../pages/aluno/login.html";
}

function changeAccount() {
  window.location.href = "../../pages/aluno/chooseAccount.html";
}
function closeMenu() {
    document.getElementById("menu").style.display = "none";
}

