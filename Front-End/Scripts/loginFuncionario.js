const form = document.getElementById("teste");
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const email = document.getElementById("email").value;
  const senha = document.getElementById("senha").value;
  const lembrar = document.getElementById("lembrar").checked;

  fetch("http://localhost:8080/login/funcionario", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ email, senha }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        window.location.href = "HomeFuncionario.html";
        localStorage.setItem("login", "true");
        localStorage.setItem("isWorker", "true");
      }
    })
    .catch((error) => {
      console.error("Erro:", error);
    });
});