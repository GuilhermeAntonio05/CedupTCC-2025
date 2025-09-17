const form = document.getElementById("teste");
form.addEventListener("submit", (e) => {
  e.preventDefault();
  const email = document.getElementById("email");
  const senha = document.getElementById("senha");

  console.log(email.value, senha.value);
});

fetch("http://localhost:8080/login")
  .then((e) => e.text())
  .then((e) => console.log(e));

const senhaInput = document.getElementById("senha");
const toggleSenha = document.getElementById("toggleSenha");

toggleSenha.addEventListener("click", () => {
  const tipo =
    senhaInput.getAttribute("type") === "password" ? "text" : "password";
  senhaInput.setAttribute("type", tipo);

  if (tipo === "password") {
    toggleSenha.classList.remove("bxs-lock-open");
    toggleSenha.classList.add("bxs-lock");
  } else {
    toggleSenha.classList.remove("bxs-lock");
    toggleSenha.classList.add("bxs-lock-open");
  }
});
        const emailInput = document.getElementById("email");
        const lembrarCheck = document.getElementById("lembrar");

        window.onload = () => {
          const savedEmail = localStorage.getItem("email");
          const savedSenha = localStorage.getItem("senha");

          if (savedEmail) {
            emailInput.value = savedEmail;
            lembrarCheck.checked = true;
          }
          if (savedSenha) {
            senhaInput.value = savedSenha;
          }
        };
        document
          .getElementById("loginForm")
          .addEventListener("submit", function () {
            if (lembrarCheck.checked) {
              localStorage.setItem("email", emailInput.value);
            } else {
              localStorage.removeItem("email");
              localStorage.removeItem("senha");
            }
          });
      
