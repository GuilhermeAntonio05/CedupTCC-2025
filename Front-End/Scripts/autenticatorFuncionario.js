function Autenticated() {

  const isWorker = JSON.parse(localStorage.getItem("isWorker"));

  const logged = isWorker.worker;
  if (!logged) {
    window.location.href = "../../pages/funcionario/loginFuncionario.html";
    localStorage.removeItem("login");
  }

  let adminAcess = document.getElementsByClassName("AdminGet");

  if(!isWorker.admin){
    Array.from(adminAcess).forEach((data) => {
      console.log(data);
      data.style.display = "none";
    });
  }
}

function main() {
  Autenticated();
}

main();
