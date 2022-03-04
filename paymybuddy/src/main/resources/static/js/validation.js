const form = document.getElementById("form");
form.noValidate = true;
form.addEventListener("submit", validate);

function validate(e) {
    const form = e.target;

    const field = Array.from(form.elements);
    field.forEach(f => {
        f.parentElement.classList.remove("invalid");
        f.setCustomValidity = "";
    });

    var mail = form.mailAddress.value;
    if(mail == null || mail == ""){
        form.mailAddress.setCustomValidity("Email cannot be empty");
    }
    if(!/^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$/.test(mail)){
        form.mailAddress.setCustomValidity("Email must be valid")
    }
    f.parentElement.classList.add("invalid");


    if (!form.checkValidity()) {
        e.preventDefault();
        e.stopImmediatePropagation();
    }

}