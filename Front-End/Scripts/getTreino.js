function getTreinoExercicios() {
  const grupo = localStorage.getItem("treino");
  const email = JSON.parse(localStorage.getItem("lastSession")).email;

  fetch(
    `http://localhost:8080/treino/exercicios?email=${email}&grupo=${grupo}`,
    {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    }
  )
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        let container = document.getElementById("inputs-container");
        data.forEach((element) => {
          let div = document.createElement("div");
          div.className = "inputs";
          div.innerHTML = `<h1>${element.exercicios.nome} - ${element.serie} X ${element.repeticoes}</h1><hr><br> `;

          for (let i = 0; i < element.serie; i++) {
            div.innerHTML += `
                <div style="display:flex; flex-direction:row; align-items:center;">
                  <label for="peso">${i + 1}° Série</label>
                  <input id="${
                    element.exercicios.nome
                  }" type="number" placeholder="Inisira o Peso" required>
                </div>
                <br>`;
          }

          div.innerHTML += `<button value="false" class="buttonConfirmar" onclick="changeButton(this)">Confirmar</button>`;

          container.appendChild(div);
        });
      }
    })
    .catch((err) => console.error(err));
}

function changeButton(button) {
  if ("false" === button.value) {
    button.style.backgroundColor = "var(--background)";
    button.style.color = "var(--gray)";
    button.textContent = "Confirmado";
    button.value = true;
  } else {
    button.style.backgroundColor = "var(--green)";
    button.style.color = "white";
    button.textContent = "Confirmar";
    button.value = false;
  }

  checkInputsCamps();
}

function checkInputsCamps() {
  let inputs = document
    .getElementById("inputs-container")
    .getElementsByTagName("input");
  let buttons = document.getElementsByClassName("buttonConfirmar");
  let verificaBotaoConfirmado = true;
  let verificaCamposPreenchidos = false;

  Array.from(buttons).forEach((button) => {
    if (button.value === "false") {
      verificaBotaoConfirmado = false;
    }
  });

  Array.from(inputs).forEach((input) => {
    if (input.value !== "") {
      verificaCamposPreenchidos = true;
    }
  });

  if (verificaBotaoConfirmado && verificaCamposPreenchidos) {
    let buttonSubmit = document.getElementById("submit-button");
    buttonSubmit.style.backgroundColor = "var(--green)";
    buttonSubmit.style.color = "white";
    buttonSubmit.style.cursor = "pointer";
    buttonSubmit.removeAttribute("disabled");
  } else {
    let buttonSubmit = document.getElementById("submit-button");
    buttonSubmit.style.backgroundColor = "var(--gray)";
    buttonSubmit.style.color = "var(--background)";
    buttonSubmit.style.cursor = "not-allowed";
    buttonSubmit.setAttribute("disabled", true);
  }
}

function enviarTreino() {
  let inputs = document
    .getElementById("inputs-container")
    .getElementsByClassName("inputs");

  Array.from(inputs).forEach((input) => {
    let exec = input.getElementsByTagName("h1")[0].innerText;
    exec = exec.split(" ");

    for (let i = exec.length - 1; i >= 0; i--) {
      if (exec[i].includes("-") || exec[i].includes("X")) {
        exec.splice(i, 1);
      }
    }

    const series = exec[exec.length - 2];
    const repeticoes = exec[exec.length - 1];

    exec.pop();
    exec.pop();
    const nome = exec.join(" ");

    const qntPesos = input.getElementsByTagName("input").length;
    let valorPesoMedia = 0;

    valorInput = input.getElementsByTagName("input");

    Array.from(valorInput).forEach((pesoInput) => {
      valorPesoMedia += parseFloat(pesoInput.value);
    });

    const media = (valorPesoMedia /= qntPesos).toFixed(2);

    const body = {
      email: JSON.parse(localStorage.getItem("lastSession")).email,
      grupoMuscular: localStorage.getItem("treino"),
      nome: nome,
      series: series,
      repeticoes: repeticoes,
      media: media,
    };

    fetch("http://localhost:8080/historico", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    })
      .then(() => {
        localStorage.removeItem("treino");
        window.location.href = "treino.html";
      })
      .catch((error) => {
        console.error("Erro:", error);
      });
  });
}


getTreinoExercicios();
