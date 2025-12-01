const form = document.getElementById("teste");
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const email = document.getElementById("email").value;
  const senha = document.getElementById("senha").value;

  fetch("http://localhost:8080/login/funcionario", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ email, senha }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.every(el => el === true)) {
        localStorage.setItem("login", `${data[0]}`);
        localStorage.setItem("isWorker", `{"worker": ${data[0]}, "admin":${data[1]}}`);

        if (data[0]) {
          window.location.href = "HomeFuncionario.html";
        }

      } else {
        alert("Email ou senha incorretos.");
      }
    })
    .catch((error) => {
      console.error("Erro:", error);
    });
});