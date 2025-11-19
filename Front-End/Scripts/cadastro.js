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
  const data_nascimento = document
    .getElementById("data_nascimento")
    .value.trim();
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
