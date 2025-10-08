function Autenticated() {
  const logged = Boolean(localStorage.getItem("login") == "true" && localStorage.getItem("isWorker") == "true");
  if (!logged) {
    window.location.href = "../../pages/funcionario/loginFuncionario.html";
    localStorage.removeItem("login");
  }
}

function main() {
  Autenticated();
}

main();

