const cpfInput = document.getElementById("cpf");
cpfInput.addEventListener("input", function () {
  let value = cpfInput.value.replace(/\D/g, "");
  value = value.replace(/(\d{3})(\d)/, "$1.$2");
  value = value.replace(/(\d{3})(\d)/, "$1.$2");
  value = value.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
  cpfInput.value = value;
});
document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("teste");
  const senha = document.getElementById("senha");
  const confirmarSenha = document.getElementById("confirmarSenha");

  confirmarSenha.addEventListener("input", () => {
    if (confirmarSenha.value !== senha.value) {
      confirmarSenha.setCustomValidity("As senhas não conferem!");
    } else {
      confirmarSenha.setCustomValidity("");
    }
  });

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    if (!form.checkValidity()) {
      form.reportValidity();
      return;
    }
    window.location.href = "index.html";
  });
});
