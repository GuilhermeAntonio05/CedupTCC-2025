let historicoData = [];
let historicoIndex = 0;
const semanasPorPagina = 2;

function renderHistorico() {
  let container = document.getElementById("container-historico");
  let slice = historicoData.slice(historicoIndex, historicoIndex + semanasPorPagina);

  slice.forEach((item) => {
    let div = document.createElement("div");
    div.className = "historico";
    div.innerHTML = `
      <h1>TREINOS - ${item.inicio} até ${item.fim}</h1>
      <hr>
      <div style="display: flex; flex-direction: row; flex-wrap: wrap;"></div>
    `;

    let flexContainer = div.querySelector("div");

    Object.entries(item.gruposMusculares).forEach(([grupo, exercicios]) => {
      const porDia = {};
      exercicios.forEach((exercicio) => {
        const dia = exercicio.data || exercicio.treino.data || "Data não informada";
        if (!porDia[dia]) porDia[dia] = [];
        porDia[dia].push(exercicio);
      });

      Object.entries(porDia).forEach(([dia, listaExercicios]) => {
        let ul = document.createElement("ul");
        ul.style.marginRight = "20px";
        ul.style.listStyle = "none";

        let tituloDia = document.createElement("li");
        tituloDia.innerHTML = `<strong>${grupo}</strong>`;
        ul.appendChild(tituloDia);

        let hr = document.createElement("hr");
        ul.appendChild(hr);

        listaExercicios.forEach((exercicio) => {
          let li = document.createElement("li");
          li.textContent = `${exercicio.treino.exercicios.nome} - ${exercicio.treino.serie}x${exercicio.treino.repeticoes}`;
          ul.appendChild(li);
        });

        let button = document.createElement("button");
        button.textContent = "Ver Detalhes";
        ul.appendChild(button);

        flexContainer.appendChild(ul);
      });
    });

    container.appendChild(div);
  });

  historicoIndex += semanasPorPagina;

  // botão centralizado com hr de cada lado
  let btnWrapper = document.querySelector(".load-more-container");

  if (btnWrapper) btnWrapper.remove(); // remove antes de recriar

  if (historicoIndex < historicoData.length) {
    btnWrapper = document.createElement("div");
    btnWrapper.className = "load-more-container";

    let hrLeft = document.createElement("hr");
    let hrRight = document.createElement("hr");

    let btnMais = document.createElement("button");
    btnMais.id = "btn-carregar-mais";
    btnMais.textContent = "Carregar mais";
    btnMais.onclick = () => {
      btnWrapper.remove();
      renderHistorico();
    };

    btnWrapper.appendChild(hrLeft);
    btnWrapper.appendChild(btnMais);
    btnWrapper.appendChild(hrRight);
    container.appendChild(btnWrapper);
  }
}

function getHistorico() {
  fetch(
    `http://localhost:8080/historico?email=${
      JSON.parse(localStorage.getItem("lastSession")).email
    }`
  )
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        historicoData = data;
        historicoIndex = 0;
        let container = document.getElementById("container-historico");
        container.innerHTML = "";
        renderHistorico();
      }
    })
    .catch((error) => console.error("Erro no fetch:", error));
}

getHistorico();
