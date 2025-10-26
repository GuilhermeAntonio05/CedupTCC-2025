function getGruposMusculares() {
  fetch("http://localhost:8080/cadastro/treino", {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        let campoInput = document.getElementById("grupoMuscular");
        data.forEach((grupo) => {
          campoInput.innerHTML += `<option value="${grupo}">${grupo}</option>`;
        });
      }
    });
}

function getExerciciosPorGrupo() {
  const grupo = document.getElementById("grupoMuscular").value;

  let campoInput = document.getElementsByClassName("exercicio");
  Array.from(campoInput).forEach((campo) => {
    campo.innerHTML = "";
  });

  Array.from(campoInput).forEach((campo) => {
    campo.innerHTML = `<option value="">Exercício</option>`;
  });

  fetch(`http://localhost:8080/cadastro/treino/exercicios?grupo=${grupo}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        let campoInput = document.getElementsByClassName("exercicio");

        Array.from(campoInput).forEach((campo) => {
          data.forEach((exercicio) => {
            campo.innerHTML += `<option value="${exercicio}">${exercicio}</option>`;
          });
        });
      }
    });
}

function enviarCadastro() {
  let grupoMuscular = document.getElementById("grupoMuscular").value;
  let exercicios = Array.from(document.getElementsByClassName("exercicio")).map(
    (select) => select.value
  );
  let series = Array.from(document.getElementsByClassName("series")).map(
    (select) => select.value
  );
  let email = JSON.parse(localStorage.getItem("lastSession")).email;

  console.log({
    grupoMuscular,
    exercicios,
    series,
    email,
  });

  fetch("http://localhost:8080/cadastro/treino", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      grupoMuscular,
      email,
      series,
      exercicios,
    }),
  })
    
    .then((data) => {
      if (data) {
        console.log("Cadastro realizado com sucesso:", data);
      }
    })
    .catch((error) => {
      console.error("Erro ao realizar cadastro:", error);
    });
}



getGruposMusculares();
