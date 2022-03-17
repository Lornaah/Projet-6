var inputClassName;

function toggle(className) {
    document.querySelectorAll(className).forEach(element => {
        if (element.readOnly) element.readOnly = false
    });
}

function toggleAndClearInput(className) {
    toggle(className);
    inputClassName = className;
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

        if (btn.innerText === "Change") {
            btn.innerText = "Validate";
            btn.style.background = "rgb(0, 118, 217)";
            document.querySelectorAll(inputClassName).forEach(element => {
                element.value = "";
            });
        }
        else if (btn.innerText === "Validate") {
            var myModal = new bootstrap.Modal(document.getElementById("confirmModal"));
            myModal.toggle();
        }
    });
});

var confirmChange = document.getElementById("confirmChange");
confirmChange.addEventListener("click", function (event) {
    var form = document.getElementById(currentForm);

    form.requestSubmit();
});

document.getElementById("floatingOldPassword").value = "password";
document.getElementById("floatingNewPassword").value = "password";