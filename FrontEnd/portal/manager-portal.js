"use strict";

//NOTE: PJS* mark indicates that variable or function is created in portal.js file

/* MARK: - Saving elements to vars ---------------------------------------------------------------*/
let dasboardDiv = document.getElementById("dashboardDiv");
let requestsDiv = document.getElementById("requestsDiv");

// Buttons
// let reimbsButton = document.getElementById("reimbsButton"); // Called Requests in the UI
let reimbDenyButton = document.getElementById("reimbDenyButton");
let reimbApproveButton = document.getElementById("reimbApproveButton");

/* MARK: - Adding Event Listeners ------------------------------------------------------------------*/
document.addEventListener("DOMContentLoaded", setupLoadedPage);

dashboardButton.addEventListener("click", function () {
  offcanvasButtonPressed(dashboardButton);
});

reimbsButton.addEventListener("click", function () {
  offcanvasButtonPressed(reimbsButton);
});

reimbDenyButton.addEventListener("click", function () {
  updateReimbStatus(2);
});

reimbApproveButton.addEventListener("click", function () {
  updateReimbStatus(3);
});

/* MARK: - Handling Navigation -----------------------------------------------------------------------*/
function offcanvasButtonPressed(button, reimbTableFilter) {
  dashboardButton.classList.remove("pressed-button");
  reimbsButton.classList.remove("pressed-button");

  button.classList.add("pressed-button");

  switch (button) {
    case dashboardButton:
      dashboardButtonPressed();
      break;
    case reimbsButton:
      reimbsButtonPressed(reimbTableFilter);
      break;
  }
}

function dashboardButtonPressed() {
  requestsDiv.style.display = "none";
  requestCardDiv.style.display = "none"; //PJS*
  dasboardDiv.style.display = "block";

  mainHeader.innerHTML = "Dashboard"; //PJS*
  setupDashboard() //PJS*
}

function reimbsButtonPressed(filter, callback) {
  dasboardDiv.style.display = "none";
  requestCardDiv.style.display = "none"; //PJS*
  requestsDiv.style.display = "block";

  mainHeader.innerHTML = "Requests"; //PJS*

  loadReimbsTable(filter, callback); //PJS*
}

// This function is called via onClick attribute in HTML
function tableRowPressed(reimbId) {
  dasboardDiv.style.display = "none";
  requestsDiv.style.display = "none";
  // RequestCardDiv visibility is toggled in portal.js to make sure the data is received before it is rendered

  loadReimbCard(reimbId); //PJS*
}

async function updateReimbStatus(reimbStatusIdFK) {
  let response = await fetch(url + `/reimbs/${activeReimbId}`, {
    method: "PUT",
    body: JSON.stringify(reimbStatusIdFK),
  });

  if (response.status >= 200 && response.status < 300) {
    console.log("updated");
    reimbsButtonPressed(0, showAlert("Request status sucessfully updated.", "success"));
  } else {
    showAlert("Failed to update request status. Please try again.", "danger");
  }
}
