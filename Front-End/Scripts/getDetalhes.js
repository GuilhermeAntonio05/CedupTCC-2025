let nomeExercicios = [];
let cargaAtualExercicios = [];
let cargaAnteriorExercicios = [];

function getDetalhes() {
    nomeExercicios = [];
    cargaAtualExercicios = [];

    const arr = JSON.parse(localStorage.getItem("exercicioDetalhes"));
    if (!arr) return;

    const params = arr.map(id => `historico=${id}`).join("&");

    fetch(`http://localhost:8080/historico/getDetalhes?${params}`, {
        method: "GET",
        headers: { "Content-Type": "application/json" },
    })
        .then(response => response.json())
        .then(data => {
            let lista = document.getElementById("exerciseList");
            lista.innerHTML = "";

            Object.entries(data).forEach(([_, exercicio]) => {
                const li = document.createElement("li");
                li.textContent =
                    `${exercicio.treino.repeticoes} x ${exercicio.treino.serie} ` +
                    `${exercicio.treino.exercicios.nome}`;
                lista.appendChild(li);

                nomeExercicios.push(exercicio.treino.exercicios.nome);
                cargaAtualExercicios.push(exercicio.peso);
            });

            grafico();
        });
}

function grafico() {
    const ctx = document.getElementById("grafico").getContext("2d");

    const arr = JSON.parse(localStorage.getItem("exercicioDetalhes"));
    if (!arr) return;

    const params = arr.map(id => `historico=${id}`).join("&");

    fetch(`http://localhost:8080/historico/getPesoGraficos?${params}`, { method: "GET" })
        .then(response => response.json())
        .then(data => {
            cargaAnteriorExercicios = data;

            new Chart(ctx, {
                type: "bar",
                data: {
                    labels: nomeExercicios,
                    datasets: [
                        {
                            label: "Último Treino",
                            data: cargaAnteriorExercicios,
                            backgroundColor: "#8884d8",
                        },
                        {
                            label: "Atual",
                            data: cargaAtualExercicios,
                            backgroundColor: "#2BFF50",
                        },
                    ],
                },
                options: {
                    responsive: true,
                    plugins: { legend: { position: "bottom" } },
                },
            });
        });

    JSON.parse(localStorage.removeItem("exercicioDetalhes"));
}

getDetalhes();
