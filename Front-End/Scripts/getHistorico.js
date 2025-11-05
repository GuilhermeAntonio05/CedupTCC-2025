function getHistorico() {
  fetch(
    `http://localhost:8080/historico?email=${
      JSON.parse(localStorage.getItem("lastSession")).email
    }`
  )
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        let container = document.getElementById("container-historico");
        container.innerHTML = ""; // limpa antes de renderizar

        data.forEach((item) => {
          let div = document.createElement("div");
          div.className = "historico";
          div.innerHTML = `
            <h1>TREINOS - ${item.inicio} até ${item.fim}</h1>
            <hr>
            <div style="display: flex; flex-direction: row; flex-wrap: wrap;"></div>
          `;

          let flexContainer = div.querySelector("div");

          Object.entries(item.gruposMusculares).forEach(([grupo, exercicios]) => {
            // separa por dia dentro do grupo muscular
            const porDia = {};
            exercicios.forEach((exercicio) => {
              const dia = exercicio.data || exercicio.treino.data || "Data não informada";
              if (!porDia[dia]) porDia[dia] = [];
              porDia[dia].push(exercicio);
            });

            // monta os blocos por dia
            Object.entries(porDia).forEach(([dia, listaExercicios]) => {
              let ul = document.createElement("ul");
              ul.style.marginRight = "20px";
              ul.style.listStyle = "none";

              let tituloDia = document.createElement("li");
              tituloDia.innerHTML = `<strong>${grupo}</strong>`;
              ul.appendChild(tituloDia);

              listaExercicios.forEach((exercicio) => {
                let li = document.createElement("li");
                li.textContent = `${exercicio.treino.exercicios.nome} - ${exercicio.treino.serie}x${exercicio.treino.repeticoes}`;
                ul.appendChild(li);
              });

              let button = document.createElement("button");
              button.textContent = "Iniciar";
              ul.appendChild(button);

              flexContainer.appendChild(ul);
            });
          });

          container.appendChild(div);
        });
      }
    })
    .catch((error) => {
      console.error("Erro no fetch:", error);
    });
}

getHistorico();
