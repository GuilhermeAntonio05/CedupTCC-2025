let id = localStorage.getItem("alunoID");

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
  localStorage.removeItem("alunoID");
  fetch(`http://localhost:8080/edit/aluno?id=${id}`, { method: "GET" })
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        console.log(data);
        document.getElementById("nome").value = data.nome;
        document.getElementById("cpf").value = data.cpf;
        document.getElementById("telefone").value = data.telefone;
        document.getElementById("peso").value = data.peso;
        document.getElementById("data_nascimento").value = data.data_nascimento;
        document.getElementById("genero").value = data.genero;
        document.getElementById("email").value = data.email;
        document.getElementById("mensalidade").value = data.mensalidade;
      }
    });
}

function enviarEdicao() {
  let nome = document.getElementById("nome").value.trim();
  let cpf = document.getElementById("cpf").value.trim();
  let telefone = document.getElementById("telefone").value.trim();
  let peso = document.getElementById("peso").value.trim();
  let data_nascimento = document.getElementById("data_nascimento").value.trim();
  let genero = document.getElementById("genero").value.trim();
  let email = document.getElementById("email").value.trim();
  let mensalidade = document.getElementById("mensalidade").value.trim();

  const campos = [
    nome,
    cpf,
    telefone,
    peso,
    data_nascimento,
    genero,
    email,
    mensalidade,
  ];

  if (campos.some((valor) => !valor)) {
    alert("Preencha todos os campos antes de enviar!");
    return;
  }

  fetch(`http://localhost:8080/edit/aluno?id=${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nome,
      cpf,
      telefone,
      peso,
      data_nascimento,
      genero,
      email,
      mensalidade,
    }),
  }).then(() => (window.location.href = "consultarAlunos.html"));
}

document.getElementById("telefone").addEventListener("input", function () {
  let tel = this.value;

  tel = tel.replace(/\D/g, "");

  tel = tel.substring(0, 11);

  if (tel.length > 10) {
    tel = tel.replace(/(\d{2})(\d{5})(\d{4})/, "($1) $2-$3");
  } else if (tel.length > 6) {
    tel = tel.replace(/(\d{2})(\d{4})(\d+)/, "($1) $2-$3");
  } else if (tel.length > 2) {
    tel = tel.replace(/(\d{2})(\d+)/, "($1) $2");
  } else {
    tel = tel.replace(/(\d+)/, "($1");
  }

  this.value = tel;
});
document.getElementById("peso").addEventListener("input", function () {
  let v = this.value;

  v = v.replace(/\D/g, "");

  v = v.substring(0, 5);

  if (v.length >= 3) {
    v = v.replace(/(\d{1,3})(\d{2})$/, "$1.$2");
  }

  this.value = v;
});

getAluno();
