window.addEventListener('DOMContentLoaded', () => {
  const ctx = document.getElementById('matriculasMensaisChart').getContext('2d');

  const matriculasMensaisChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [
        'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 
        'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'
      ],
      datasets: [{
        label: 'Matrículas',
        data: [15, 22, 18, 30, 45, 50, 55, 48, 40, 60, 65, 70],
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
            stepSize: 10
          }
        }
      }
    }
  });
});
