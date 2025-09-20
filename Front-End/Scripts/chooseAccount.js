const dataUser = new Map();

window.onload = () => {
  let account = (localStorage.getItem("dataAccess") ?? "")
    .split(",")
    .filter((result) => result != "");
  account.forEach((element) => {
    fetch(
      `http://localhost:8080/chooseAccount?email=${encodeURIComponent(
        element
      )}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    )
      .then((response) => response.text())
      .then((data) => {
        dataUser.set(data, element);

        if (data) {
          let container = document.getElementById("container");
          let accountDiv = document.createElement("div");
          accountDiv.className = "caixa-login";
          accountDiv.innerHTML = `<div class="usuario" onclick="selectAccount('${data}')">
              <img src="../images/icons/do-utilizador.png" alt="icone usuário" />
              <span>${data}</span>
              </div>
              <img src="../images/icons/close.png" alt="icone fechar" class="close-icon" onclick="removeAccount('${data}')"/>
            `;
          container.appendChild(accountDiv);
        }
      })
      .catch((error) => {
        console.error("Erro:", error);
      });
  });
};

function removeAccount(user) {
  localStorage.setItem(
    "dataAccess",
    localStorage
      .getItem("dataAccess")
      .split(",")
      .filter((result) => result != dataUser.get(user))
      .join(",")
  );

  if (localStorage.getItem("dataAccess").valueOf() === "") {
    localStorage.removeItem("dataAccess");
  }

  location.reload();
}

function selectAccount(user) {
  const email = dataUser.get(user);
  localStorage.setItem("lastSession", `{"email":"${email}","lembrar":"true"}`);
  window.location.href = "../index.html";
}

function addAccount() {
  window.location.href = "./login.html";
}
