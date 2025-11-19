async function carregarValoresPagamentos() {
    try {
        const response = await fetch("http://localhost:8080/home/dashboard/valorPagamentosMes");

        if (!response.ok) {
            throw new Error("Erro ao buscar dados do dashboard");
        }

        const dados = await response.json();

        document.getElementById("valorCancelaram").innerText = `R$ ${((dados.cancelado!= undefined && dados.cancelado!= null)? dados.cancelado.toFixed(2): "0.00")}`;
        document.getElementById("valorPagaram").innerText = `R$ ${((dados.pago!= undefined && dados.pago!= null)? dados.pago.toFixed(2): "0.00")}`;
        document.getElementById("valorPendentes").innerText = `R$ ${((dados.pendente!= undefined && dados.pendente!= null)? dados.pendente.toFixed(2): "0.00")}`;

    } catch (e) {
        console.error("Erro:", e);
    }
}

carregarValoresPagamentos();
