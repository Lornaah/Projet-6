function toggle(className) {
    document.querySelectorAll(className).forEach(element => element.readOnly = !element.readOnly);
}

function clearInput(className) {
    document.querySelectorAll(className).forEach(element => {
        if (!element.readOnly) {
            element.value = "";
        }
    });
}

function toggleAndClearInput(className) {
    toggle(className);
    clearInput(className);
}

var buttons = document.querySelectorAll('.btn-validate');
var currentForm;

Array.from(buttons).forEach(btn => {
    btn.addEventListener('click', function (event) {
        if (btn.innerText === 'Validate') {

            if (btn.id === 'nameButton') {
                currentForm = "nameForm";
            }
            if (btn.id === 'passwordButton') {
                currentForm = "passwordForm";
            }
        }

        btn.innerText = btn.innerText === "Change" ? "Validate" : "Change";
        btn.style.background = btn.innerText === "Change" ? "rgb(92, 185, 92)" : "rgb(0, 118, 217)";
        if (btn.innerText === "Change") {
            var myModal = new bootstrap.Modal(document.getElementById("confirmModal"));
            myModal.toggle();
        }
    });
});

var confirmChange = document.getElementById("confirmChange");
confirmChange.addEventListener("click", function (event) {
    var form = document.getElementById(currentForm);
    console.log(form);
    form.requestSubmit();
});