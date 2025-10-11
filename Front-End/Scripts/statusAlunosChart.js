window.addEventListener('DOMContentLoaded', () => {
    const ctx = document.getElementById('statusAlunosChart').getContext('2d');
    const statusAlunosChart = new Chart(ctx, {
        type: 'bar',  // mudou de doughnut para bar
        data: {
            labels: ['Pagaram', 'Cancelaram', 'Pendentes'],
            datasets: [{
                label: 'Status dos Alunos',
                data: [120, 30, 15],
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
                legend: {
                    position: 'top'
                },
                title: {
                    display: false
                }
            },
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});
