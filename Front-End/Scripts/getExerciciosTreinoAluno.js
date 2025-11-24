const email = localStorage.getItem("alunoEmail");
function getExercicios() {
  const exercicios = [];

  fetch(`http://localhost:8080/treino?email=${email}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then((data) => {
      if (data && data.length > 0) {
        data.forEach((treino) => {
          const grupo = treino.exercicios.grupoMuscular;
          let grupoExistente = exercicios.find((e) => e[0].name === grupo);

          if (grupoExistente) {
            grupoExistente.push(treino);
          } else {
            exercicios.push([{ name: grupo }, treino]);
          }
        });
      }

      let container = document.getElementById("cardContainer");
      exercicios.forEach((e) => {
        let card = document.createElement("div");
        card.className = "card";
        card.innerHTML = `
              <div class="card-header">      
                <h1>${e[0].name}</h1>
                <img src="../../images/icons/bin.png" height="30px" width="30px" onclick="deletarTreino('${e[0].name}')">
              </div>
              <hr>
              <ul> `;

        for (let i = 1; i < e.length; i++) {
          let row = document.createElement("li");
          row.textContent = `${e[i].exercicios.nome} - ${e[i].serie} x ${e[i].repeticoes}`;
          card.querySelector("ul").appendChild(row);
        }

        card.innerHTML += `
              </ul>`;
        container.appendChild(card);
      });
    })
    .catch((err) => console.error(err));
}

function saveTreino(name) {
  localStorage.setItem("treino", name);
}

function deletarTreino(name) {
  fetch(
    `http://localhost:8080/treino?grupo=${name}&email=${email}`,
    {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
    }
  )
    .then((response) => {
      if (response.ok) {
        console.log("Treino deletado com sucesso");
        window.location.reload();
      } else {
        console.error("Erro ao deletar treino");
      }
    })
    .catch((error) => {
      console.error("Erro ao deletar treino:", error);
    });
}

getExercicios();
