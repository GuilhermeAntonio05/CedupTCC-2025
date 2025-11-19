async function carregarValoresPagamentos() {
    try {
        const response = await fetch("http://localhost:8080/home/dashboard/valorPagamentosMes");

        if (!response.ok) {
            throw new Error("Erro ao buscar dados do dashboard");
        }

        const dados = await response.json();

        console.log(dados)
        document.getElementById("valorCancelaram").innerText = `R$ ${dados.cancelado.toFixed(2)}`;
        document.getElementById("valorPagaram").innerText = `R$ ${dados.pago.toFixed(2)}`;
        document.getElementById("valorPendentes").innerText = `R$ ${dados.pendente.toFixed(2)}`;

    } catch (e) {
        console.error("Erro:", e);
    }
}

carregarValoresPagamentos();
