window.addEventListener('DOMContentLoaded', async () => {
  const ctx = document.getElementById('matriculasMensaisChart').getContext('2d');

  // Buscar dados do backend
  const response = await fetch('http://localhost:8080/home/dashboard/evolucaoMatriculas');
  const dados = await response.json();

  // Ordenar e formatar os meses
  const mesesNomes = ["JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"];

  const labels = Object.keys(dados).sort().map(item => {
    const [ano, mes] = item.split("-");
    return `${mesesNomes[parseInt(mes) - 1]}/${ano}`;
  });

  const valores = Object.values(dados);

  // Criar o gráfico
  new Chart(ctx, {
    type: 'line',
    data: {
      labels: labels,
      datasets: [{
        label: 'Matrículas no mês',
        data: valores,
        borderColor: 'rgba(75, 192, 192, 1)',
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        fill: true,
        tension: 0.35,
        borderWidth: 3,
        pointRadius: 5,
        pointHoverRadius: 7,
        pointBackgroundColor: 'rgba(75, 192, 192, 1)'
      }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          display: true,
          position: 'top'
        }
      },
      scales: {
        y: {
          beginAtZero: true,
          ticks: {
            precision: 0,
            stepSize: 1
          }
        }
      }
    }
  });
});
