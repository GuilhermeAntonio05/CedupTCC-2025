// Função para buscar status dos alunos
async function fetchStatusAlunos() {
    const response = await fetch("http://localhost:8080/home/dashboard/pagamentosMes");
    const data = await response.json(); // agora retorna JSON
    return data;
}

window.addEventListener('DOMContentLoaded', async () => {
    const ctx = document.getElementById('statusAlunosChart').getContext('2d');

    // Buscar dados do backend
    const statusData = await fetchStatusAlunos();

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Pagaram', 'Cancelaram', 'Pendentes'],
            datasets: [{
                label: 'Status dos Alunos',
                data: [
                    statusData.PAGO,
                    statusData.CANCELADO,
                    statusData.PENDENTE
                ],
                backgroundColor: [
                    'rgba(75, 192, 192, 0.7)',
                    'rgba(255, 99, 132, 0.7)',
                    'rgba(255, 206, 86, 0.7)'
                ],
                borderColor: [
                    'rgba(75, 192, 192, 1)',
                    'rgba(255, 99, 132, 1)',
                    'rgba(255, 206, 86, 1)'
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
