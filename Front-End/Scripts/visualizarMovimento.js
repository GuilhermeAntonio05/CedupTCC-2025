const exercicios = [];
let resultados = [];

window.onload = async function () {
    await fetch('http://localhost:8080/treino/exercicios/todos', { method: 'GET' })
        .then(res => res.json())
        .then(data => exercicios.push(...data));

    document.getElementById("inputBar").addEventListener("keydown", function (e) {
        if (e.key === "Enter") {
            e.preventDefault();
            pesquisarExercicio();
            alert(JSON.stringify(resultados));
        }
    });

    document.getElementById("listaResultados").onchange = function () {
        const selecionado = resultados.find(ex => ex.id == this.value);
        if (selecionado) abrirVisualizarMovimento(selecionado.linkVideo);
    };
}

function pesquisarExercicio() {
    const query = document.getElementById("inputBar").value.toLowerCase();
    const lista = document.getElementById("listaResultados");

    resultados = exercicios
        .filter(ex => ex.nome.toLowerCase().includes(query))
        .slice(0, 3);

    lista.innerHTML = "";

    if (query.trim() === "" || resultados.length === 0) {
        lista.style.display = "none";
        return;
    }

    resultados.forEach(ex => {
        const option = document.createElement("option");
        option.value = ex.id;
        option.textContent = ex.nome;
        lista.appendChild(option);
    });

    lista.style.display = "block";
}

abrirVisualizarMovimento = (link) => {
    document.getElementById("inputBar").value = "";
    pesquisarExercicio();

    const popup = document.getElementById("popupVisualizarMovimento");
    popup.style.display = "block";

    const iframeAntigo = popup.querySelector("iframe");
    if (iframeAntigo) iframeAntigo.remove();
    
    const iframe = document.createElement("iframe");
    iframe.width = "560";
    iframe.height = "315";
    iframe.src = transformarEmEmbed(link);
    iframe.title = "YouTube video player";
    iframe.referrerPolicy = "strict-origin-when-cross-origin";
    iframe.allowFullscreen = true;

    popup.appendChild(iframe);
}

fecharVisualizarMovimento = () => {
    document.getElementById("popupVisualizarMovimento").style.display = "none";
    const popup = document.getElementById("popupVisualizarMovimento");
    const iframeAntigo = popup.querySelector("iframe");
    if (iframeAntigo) iframeAntigo.remove();
}

function transformarEmEmbed(link) {
    if (link.includes("watch?v=")) {
        const id = link.split("watch?v=")[1];
        return `https://www.youtube.com/embed/${id}`;
    }
    return link;
}
