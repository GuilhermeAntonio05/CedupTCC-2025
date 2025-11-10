const cpfInput = document.getElementById("cpf");
cpfInput.addEventListener("input", function () {
  let value = cpfInput.value.replace(/\D/g, "");
  value = value.replace(/(\d{3})(\d)/, "$1.$2");
  value = value.replace(/(\d{3})(\d)/, "$1.$2");
  value = value.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
  cpfInput.value = value;
});

document.addEventListener("DOMContentLoaded", () => {
  const senha = document.getElementById("senha");
  const confirmarSenha = document.getElementById("confirmarSenha");

  confirmarSenha.addEventListener("input", () => {
    if (confirmarSenha.value !== senha.value) {
      confirmarSenha.setCustomValidity("As senhas não conferem!");
    } else {
      confirmarSenha.setCustomValidity("");
    }
  });
});

function cadastrar() {
  const nome = document.getElementById("nome").value.trim();
  const email = document.getElementById("email").value.trim();
  const cpf = document.getElementById("cpf").value.trim();
  const telefone = document.getElementById("telefone").value.trim();
  const peso = document.getElementById("peso").value.trim();
  const data_nascimento = document.getElementById("data_nascimento").value.trim();
  const genero = document.getElementById("genero").value.trim();
  const senha = document.getElementById("senha").value.trim();

  fetch("http://localhost:8080/cadastro/aluno", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nome,
      email,
      cpf,
      telefone,
      peso,
      data_nascimento,
      genero,
      senha,
    }),
  })
    .then((data) => {
      if (data) {
        window.location.href = "HomeFuncionario.html";
      }
    })
    .catch((error) => console.error("Error:", error));
}
