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

function getAluno() {
  let id = localStorage.getItem("funcionarioID");
  console.log(id);
  //localStorage.removeItem("funcionarioID");
  fetch(`http://localhost:8080/edit/funcionario?id=${id}`, { method: "GET" })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      if (data) {
        console.log(data);
        document.getElementById("nome").value = data.nome;
        document.getElementById("cpf").value = data.cpf;
        document.getElementById("telefone").value = data.telefone;
        document.getElementById("cargo").value = data.cargo;
        document.getElementById("data_nascimento").value = data.dataNascimento;
        document.getElementById("genero").value = data.genero;
        document.getElementById("email").value = data.email;
        document.getElementById("salario").value = data.salario;
        document.getElementById("senha").value = data.senha;
      }
    });
}

function enviarEdicao() {
  let id = localStorage.getItem("funcionarioID");
  let nome = document.getElementById("nome").value.trim();
  let cpf = document.getElementById("cpf").value.trim();
  let telefone = document.getElementById("telefone").value.trim();
  let cargo = document.getElementById("cargo").value.trim();
  let dataNascimento = document.getElementById("data_nascimento").value.trim();
  let email = document.getElementById("email").value.trim();
  let salario = document.getElementById("salario").value.trim();
  let genero = document.getElementById("genero").value.trim();
  let senha = document.getElementById("senha").value.trim();

  console.log(dataNascimento);

  const campos = [
    nome,
    cpf,
    telefone,
    salario,
    senha,
    dataNascimento,
    genero,
    email,
    cargo,
  ];

  if (campos.some((valor) => !valor)) {
    alert("Preencha todos os campos antes de enviar!");
    return;
  }

  fetch(`http://localhost:8080/edit/funcionario?id=${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nome,
      cpf,
      telefone,
      salario,
      senha,
      dataNascimento,
      genero,
      email,
      cargo,
    }),
  }).then(() => (window.location.href = "consultarFuncionarios.html"));
}
getAluno();
