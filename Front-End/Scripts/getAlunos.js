let position = 10;

function fetchAlunos() {
  let tableBody = document.getElementById("tableBody");

  fetch(`http://localhost:8080/home?position=${position}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then((data) => {
      if (data) {
        for (let aluno of data) {
          let row = document.createElement("tr");
          row.innerHTML = `
                <td>${aluno.id}</td>
                <td>${aluno.nome}</td>
                <td>${aluno.email}</td>
                <td>${aluno.cpf}</td>
                <td>${aluno.peso}</td>
                <td>${aluno.telefone}</td>
                <td>${aluno.data_nascimento}</td>
                <td>${aluno.mensalidade.estado}</td>
                <td>${aluno.data_vencimento}</td>
                <td>${
                  aluno.genero === "m"
                    ? "Masculino"
                    : aluno.genero === "f"
                    ? "Feminino"
                    : "Outro"
                }</td>
                <td class="actions">
                  <button class="tbButton" onclick="${aluno.id}"><img class="tbIcon" src="../images/icons/pencil.png" alt="editar"></button>
                  <button class="tbButton" onclick="deletar('${aluno.id}')"><img class="tbIcon" src="../images/icons/bin.png" alt="deletar"></button>
                </td>
                    `;
          tableBody.appendChild(row);
        }
      }
    })
    .catch((error) => {
        let message = document.getElementById("errorMessage");
        message.textContent = "Nenhum aluno encontrado.";
        console.error("Erro ao buscar alunos:", error);
    });
}

function proximo() {
  position += 10;
  let tableBody = document.getElementById("tableBody");
  tableBody.innerHTML = "";
  fetchAlunos();
}

function anterior() {
  position -= 10;
  if (position < 0) position = 0;
  tableBody.innerHTML = "";
  fetchAlunos();
}

fetchAlunos();
