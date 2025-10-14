function getExercicios() {
  const session = JSON.parse(localStorage.getItem("lastSession"));
  if (!session) return;
  const email = session.email;
  const exercicios = [];

  fetch(`http://localhost:8080/treino?email=${email}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then((data) => {
      if (data && data.length > 0) {
        console.log(data);
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
              <h1>${e[0].name}</h1>
              <span>Por: Alex Miguel</span>
              <hr>
              <ul> `;

        for (let i = 1; i < e.length; i++) {
          let row = document.createElement("li");
          row.textContent = `${e[i].exercicios.nome} - ${e[i].serie} x ${e[i].repeticoes}`;
          card.querySelector("ul").appendChild(row);
        }

        card.innerHTML += `
              </ul>
              <hr>
              <a href="detalhes.html">
                <button>Iniciar</button>
              </a>`;
        console.log(card);
        container.appendChild(card);
      });
    })
    .catch((err) => console.error(err));
}


getExercicios();