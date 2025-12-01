function Autenticated() {
  const isWorker = JSON.parse(localStorage.getItem("isWorker"));
  const logged = Boolean(localStorage.getItem("login") === "true") && Boolean(isWorker.worker === true);

  if (!logged) {
    window.location.href = "../../pages/funcionario/loginFuncionario.html";
    localStorage.removeItem("login");
    localStorage.removeItem("isWorker");
  }

  let adminAcess = document.getElementsByClassName("AdminGet");

  if (!isWorker.admin) {
    Array.from(adminAcess).forEach((data) => {
      data.style.display = "none";
    });
  }
}

Autenticated();