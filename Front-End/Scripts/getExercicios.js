let gruposMusculares = [];
let posicao = 0;
let grupo;

function getExercicios() {
  fetch(`http://localhost:8080/cadastro/treino`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then((data) => {
      gruposMusculares = data;
      grupo = gruposMusculares[posicao];

      fetch(`http://localhost:8080/cadastro/treino/exercicios?grupo=${grupo}`, {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      })
        .then((response) => response.json())
        .then((data) => {
          console.log(grupo);
          console.log(data);

          let titulo = (document.getElementById("titulo").innerHTML = ``);
          titulo = document.getElementById(
            "titulo"
          ).innerHTML += `Exercicios - ${grupo}`;

          let linhas = (document.getElementById("tableBody").innerHTML = "");
          linhas = document.getElementById("tableBody");

          Array.from(data).forEach((exercicio) => {
            let teste = document.createElement("tr");

            teste.innerHTML = `
            <td>${exercicio}</td>
            <td>
             <button class="tbButton" onclick="openMenuDelete('${grupo}', '${exercicio}')">
                <img class="tbIcon" src="../../images/icons/bin.png" alt="deletar">
              </button>
            </td>`;

            linhas.appendChild(teste);
          });
        });
    });
}

function proximo() {
  posicao += 1;
  getExercicios();
}

function anterior() {
  posicao -= 1;
  getExercicios();
}

function openMenuDelete(grupo, exercicio) {
  console.log(grupo, exercicio);
  fetch(
    `http://localhost:8080/treino/exercicio?grupo=${grupo}&nome=${exercicio}`,
    { method: "DELETE" }
  ).then(response => {alert("Exercício deletado"); getExercicios()});
}

getExercicios();
