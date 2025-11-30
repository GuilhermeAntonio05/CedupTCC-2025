const form = document.getElementById("teste");
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const email = document.getElementById("email").value;
  const senha = document.getElementById("senha").value;
  const lembrar = document.getElementById("lembrar").checked;

  fetch("http://localhost:8080/login/aluno", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ email, senha }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        if (!localStorage.getItem("dataAccess") || !localStorage.getItem("dataAccess").includes(email)) {
          localStorage.setItem(
            "dataAccess",
            email + "," + (localStorage.getItem("dataAccess") || "")
          );
        }

        localStorage.setItem(
          "lastSession",
          `{"email":"${email}","lembrar":"${lembrar}"}`
        );

        window.location.href = "../../";
        localStorage.setItem("login", "true");
        localStorage.setItem("isWorker", "false");
      } else{
        alert("Email ou senha incorretos.");
      }
    })
    .catch((error) => {
      console.error("Erro:", error);
    });
});

window.onload = () => {
  const lastSession = JSON.parse(localStorage.getItem("lastSession"));
  localStorage.removeItem("lastSession");
  const emailInput = document.getElementById("email");
  const senhaInput = document.getElementById("senha");
  const lembrar = lastSession.lembrar === "true";
  
  if (lembrar) {
    fetch(`http://localhost:8080/login/aluno?email=${lastSession.email}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => response.json())
    .then((data) => {
      if (data) {
          document.getElementById("lembrar").checked = true;
          emailInput.value = data.email;
          senhaInput.value = data.senha;
        }else{
          alert("Erro ao carregar os dados da última sessão.");
        }
      })
      .catch((error) => {
        console.error("Erro:", error);
      });
  }
};

async function getPublicIP() {
  const res = await fetch("https://api.ipify.org?format=json");
  const data = await res.json();
  return data.ip;
}
