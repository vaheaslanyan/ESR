'use strict';
// const url = "http://localhost:3000";

document.getElementById("loginButton").onclick = loginFunction;
let userData;

async function loginFunction() {

    console.log("clicked")

    //Getting user input for credentials
    let username = document.getElementById("emailInput").value;
    let password = document.getElementById("passwordInput").value;

    //Creating Json object with credentials
    let userCredentialsJson = JSON.stringify({
            username: username,
            password: password
        })

    // console.log(userCredentials.JSON)

    //Fetching request to server, second param is configurations for the request
    let response = await fetch(url + "/login", {
        method: "POST",
        body: userCredentialsJson,
        credentials: "include" //will capture cookie to enable sessions. Will need to include this to fetches after login as well
    });//include

    if (response.status >= 200 && response.status < 300) {

        let userData = await response.json();

        //Saving userId and userRoleId cookie for entire domain and redirecting user to appropriate dashboard
        document.cookie = `userId=${userData.user_id}; path=/;`;
        document.cookie = `userRoleId=${userData.user_role_id_fk}; path=/;`;
        takeToDashboard(userData);
    } else {
        showAlert("Login failed. Please check credentials and try again.", "warning");
    }

}

async function takeToDashboard(userData) {
    if (userData.user_role_id_fk == 1) {
        window.location.href = "/portal/manager_portal.html";
    } else {
        window.location.href = "/portal/employee_portal.html";
    }
}