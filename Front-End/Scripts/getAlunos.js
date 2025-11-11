let position = 10;

function fetchAlunos() {
  let tableBody = document.getElementById("tableBody");

  fetch(`http://localhost:8080/home/aluno?position=${position}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then((data) => {
      if (data && data.length > 0) {
        let message = document.getElementById("errorMessage");
        message.textContent = "";

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
                  <button class="tbButton" onclick="editarAluno(${aluno.id})">
                    <img class="tbIcon" src="../../images/icons/pencil.png" alt="editar">
                  </button>

                  <button class="tbButton" onclick="openMenuDelete(${aluno.id}, '${aluno.nome}')">
                    <img class="tbIcon" src="../../images/icons/bin.png" alt="deletar">
                  </button>
                </td>
                    `;
          tableBody.appendChild(row);
        }
      } else {
        message.textContent = "Nenhum funcionario encontrado.";
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
  if (position > 10) {
    position -= 10;
  }
  let tableBody = document.getElementById("tableBody");
  tableBody.innerHTML = "";
  fetchAlunos();
}

let UserForDelete;

function openMenuDelete(id, nome) {
  let box = document.getElementById("confirmDelete");
  box.style.display = "flex";
  document.getElementById("deleteId").innerText = `${id}, ${nome} `;
  UserForDelete = id;
}

function confirmDelete() {
  fetch(`http://localhost:8080/home/aluno?id=${UserForDelete}`, {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
  }).then(window.location.reload());
  let box = document.getElementById("confirmDelete");
  box.style.display = "none";
}

function cancelDelete() {
  let box = document.getElementById("confirmDelete");
  box.style.display = "none";
}

function editarAluno(id){
  localStorage.setItem("alunoID", id);
  window.location.href = "editarAluno.html";
}

fetchAlunos();
