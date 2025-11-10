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
  const nome = document.getElementById("nome").value;
  const email = document.getElementById("email").value;
  const cpf = document.getElementById("cpf").value;
  const telefone = document.getElementById("telefone").value;
  const salario = document.getElementById("salario").value;
  const dataNascimento = document.getElementById("data_nascimento").value;
  const cargo = document.getElementById("cargo").value;
  const genero = document.getElementById("genero").value;
  const senha = document.getElementById("senha").value;

  fetch("http://localhost:8080/cadastro/funcionario", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nome,
      email,
      cpf,
      cargo,
      telefone,
      dataNascimento,
      genero,
      salario,
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
