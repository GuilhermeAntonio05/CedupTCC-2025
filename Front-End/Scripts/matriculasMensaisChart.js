window.addEventListener('DOMContentLoaded', async () => { // async aqui
  const ctx = document.getElementById('matriculasMensaisChart').getContext('2d');

  // Buscar dados do backend
  const response = await fetch('http://localhost:8080/home/dashboard/evolucaoMatriculas');
  const dados = await response.json();

  // Separar labels e valores
  const labels = Object.keys(dados); // ex: "JAN/2025"
  const valores = Object.values(dados); // contagem de matrículas

  const matriculasMensaisChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: labels,
      datasets: [{
        label: 'Matrículas',
        data: valores,
        borderColor: 'rgba(75, 192, 192, 1)',
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        fill: true,
        tension: 0.3,
        pointRadius: 5,
        pointHoverRadius: 7,
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
          beginAtZero: true,
          ticks: {
            stepSize: 1
          }
        }
      }
    }
  });
});
