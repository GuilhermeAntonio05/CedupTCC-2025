function cadastrarExercicios() {
  let grupoMuscular = document.getElementById("grupoMuscular").value;
  let nome = document.getElementById("nome").value;

  fetch("http://localhost:8080/cadastro/exercicio", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ grupoMuscular, nome }),
  }).then(() => {
    window.location.href = "homeFuncionario.html";
  });
}
