const logged = Boolean(localStorage.getItem("login") == "true")
if(!logged){
    window.location.href = "../pages/login.html";
    localStorage.removeItem("login")
}