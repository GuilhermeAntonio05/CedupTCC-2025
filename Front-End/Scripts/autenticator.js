function Autenticated() {
  const logged = Boolean(localStorage.getItem("login") === "true")  && Boolean(localStorage.getItem("isWorker") === "false");
  if (!logged) {
    window.location.href = "../../pages/aluno/login.html";
    localStorage.removeItem("login");
  }
}

function main() {
  Autenticated();
}

main();
