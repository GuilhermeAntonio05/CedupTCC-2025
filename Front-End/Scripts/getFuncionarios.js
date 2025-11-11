let position = 10;

function fetchAlunos() {
  let tableBody = document.getElementById("tableBody");

  fetch(`http://localhost:8080/home/funcionario?position=${position}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => response.json())
    .then((data) => {
      if (data && data.length > 0) {
        let message = document.getElementById("errorMessage");
        message.textContent = "";

        for (let funcionario of data) {
          let row = document.createElement("tr");
          row.innerHTML = `
                <td>${funcionario.id}</td>
                <td>${funcionario.nome}</td>
                <td>${funcionario.email}</td>
                <td>${funcionario.cpf}</td>
                <td>${funcionario.telefone}</td>
                <td>${funcionario.dataNascimento}</td>
                <td>${funcionario.cargo}</td>
                <td>${funcionario.salario}</td>
                <td>${
                  funcionario.genero === "m"
                    ? "Masculino"
                    : funcionario.genero === "f"
                    ? "Feminino"
                    : "Outro"
                }</td>
                <td class="actions">
                  <button class="tbButton" onclick="editarFuncionario(${
                    funcionario.id
                  })"><img class="tbIcon" src="../../images/icons/pencil.png" alt="editar"></button>
                  <button class="tbButton" onclick="openMenuDelete(${
                    funcionario.id
                  }, '${
            funcionario.nome
          }')"><img class="tbIcon" src="../../images/icons/bin.png" alt="deletar"></button>
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
      message.textContent = "Nenhum funcionario encontrado.";
      console.error("Erro ao buscar funcionario:", error);
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
  fetch(`http://localhost:8080/home/funcionario?id=${UserForDelete}`, {
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

function editarFuncionario(id){
  localStorage.setItem("funcionarioID", id);
  window.location.href = "editarFuncionario.html";
}

fetchAlunos();
