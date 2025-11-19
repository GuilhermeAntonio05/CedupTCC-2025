async function fetchStatusAlunos() {
    const response = await fetch("http://localhost:8080/home/dashboard/pagamentosMes");
    const data = await response.json(); 
    return data;
}

window.addEventListener('DOMContentLoaded', async () => {
    const ctx = document.getElementById('statusAlunosChart').getContext('2d');

    const verde = ctx.createLinearGradient(0, 0, 0, 400);
    verde.addColorStop(0, '#1ed760');
    verde.addColorStop(1, '#0f8a2e');

    const amarelo = ctx.createLinearGradient(0, 0, 0, 400);
    amarelo.addColorStop(0, '#ffe066');
    amarelo.addColorStop(1, '#d1a700');

    const vermelho = ctx.createLinearGradient(0, 0, 0, 400);
    vermelho.addColorStop(0, '#ff4d4d');
    vermelho.addColorStop(1, '#b31212');



    const statusData = await fetchStatusAlunos();

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Pagos', 'Em aberto', 'Cancelados'],
            datasets: [{
                label: 'Status dos Alunos',
                data: [
                    statusData.PAGO,
                    statusData.PENDENTE,
                    statusData.CANCELADO,
                ],
                backgroundColor: [
                    verde,
                    amarelo,
                    vermelho
                ],
                borderColor: [
                    verde,
                    amarelo,
                    vermelho
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: { position: 'top' },
                title: { display: false }
            },
            scales: { y: { beginAtZero: true } }
        }
    });
});
