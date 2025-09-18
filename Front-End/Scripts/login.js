const form = document.getElementById("teste");
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const email = document.getElementById("email").value;
  const senha = document.getElementById("senha").value;

  fetch("http://localhost:8080/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ email, senha }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        window.location.href = "../index.html";
        localStorage.setItem("login", "true");
        localStorage.setItem(
          "dataAccess",
          (localStorage.getItem("dataAccess") || "") + " " + email
        );
        localStorage.setItem("session", email);
      }
    })
    .catch((error) => {
      console.error("Erro:", error);
    });
});
