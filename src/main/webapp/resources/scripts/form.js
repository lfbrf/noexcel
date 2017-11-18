$(document).on("change", ":input", function () {
    console.log("From change event on any input: " + this.id);
    validateAll('#form');
    $("#compid").mask("99/99/9999");

});
$(function ($) {
    // Aqui fazemos uso do plugin MASKED INPUT


});
function validateAll(form) {

    var ra = $('#studentid').val();
    var cpf = $('#compid').val();
    var email = $('#email').val();
    var pass = $('#pass').val();
    var repass = $('#repass').val();
    var name = $('#name').val();
    var valid = true;
    var opt = $('input[name="console"]:checked').val();

    if (opt == "student") {
        if (ra == "" || ra == null || !isRa(ra)) {
            $('input[id="studentid"]').addClass('error');
            PF('ra-error').renderMessage({"summary": "Informe seu ra ",
                "severity": "error"});
            $("#error-id").show();
            $("#ok-id").hide();
            valid = false;
        } else {
            $('input[id="studentid"]').removeClass('error');
            $("#error-id").hide();
            $("#ok-id").show();
        }
    } else if (opt == "visitor") {
        if (cpf == "" || cpf == null || !isCpf(cpf)) {
            $('input[id="compid"]').addClass('error');
            $("#error-id").show();
            $("#ok-id").hide();
            valid = false;
        } else {
            $('input[id="compid"]').removeClass('error');
            $("#error-id").hide();
            $("#ok-id").show();
        }
    }
    if (email == "" || email == null || !isEmail(email)) {
        $('input[id="email"]').addClass('error');
        $("#error-email").show();
        $("#ok-email").hide();
        valid = false;
    } else {
        $('input[id="email"]').removeClass('error');
        $("#error-email").hide();
        $("#ok-email").show();
    }
    if (pass == "" || pass == null || !isPass(pass)) {
        $('input[id="pass"]').addClass('error');
        $("#error-pass").show();
        $("#ok-pass").hide();
        valid = false;
    } else {
        $('input[id="pass"]').removeClass('error');
        $("#error-pass").hide();
        $("#ok-pass").show();
    }

    if (repass == "" || repass == null || !validaterePass()) {
        $('input[id="repass"]').addClass('error');
        $("#error-repass").show();
        $("#ok-repass").hide();
        valid = false;
    } else {
        $('input[id="repass"]').removeClass('error');
        $("#error-repass").hide();
        $("#ok-repass").show();
    }

    if (name == "" || name == null || !isName(name)) {
        $('input[id="name"]').addClass('error');
        $("#error-name").show();
        $("#ok-name").hide();
        valid = false;
    } else {
        $('input[id="name"]').removeClass('error');
        $("#error-name").hide();
        $("#ok-name").show();
    }
    return valid;
}



$("#repass").focus(function () {
    if (isPass($('#pass').val())) {
        $('input[id="pass"]').removeClass('error');
        $('input[id="pass"]').addClass('default-input');
    } else
        $('input[id="pass"]').addClass('error');
});
function isName(name) {
    var regex = /^[A-Z][a-z]+([ ][A-Z][a-z]+)*$/;
    return regex.test(name);
}
function isRa(ra) {
    var regex = /^\d{8,8}$/;
    return regex.test(ra);
}
function isCpf(cpf) {
    var regex = /([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})/;
    return regex.test(cpf);
}
$("login-form").submit(function (e) {
    if (isPass($('#pass').val()))
        alert("OKKK");
    else
        e.preventDefault();
});
$("#enviar").click(function go() {

    var isChecked = jQuery("input[name=console]:checked").val();
    var booleanVlaueIsChecked = false;
    var cpf = $('#compid').val();
    var ra = $('#studentid').val();
    var opt = $('input[name="console"]:checked').val();
    if (!isChecked) {
        booleanVlaueIsChecked = true;
        PF('ratio-error').renderMessage({"summary": "Selecione seu vinculo com a faculdade",
            "severity": "error"});
    }
    if (opt == "student") {

        if (ra == "") {
            PF('cpf-error').renderMessage({"summary": "Informe o RA corretamente",
                "severity": "error"});
            $('input[id="studentid"]').addClass('error');
        } else if (!isRa(ra)) {
            $('input[id="studentid"]').addClass('error');
        } else {

            validateForm();
        }
    } else if (opt == "visitor") {
        if (cpf == "") {
            PF('cpf-error-missing').renderMessage({"summary": "Informe o cpf",
                "severity": "error"});
            $('input[id="compid"]').addClass('error');
        } else if ((!isCpf(cpf))) {
            PF('cpf-error').renderMessage({"summary": "CPF incorreto, apenas numeros, por exemplo: 12345678912",
                "severity": "error"});
            $('input[id="compid"]').addClass('error');
        } else {
            validateForm();
        }
    }


});
function validaterePass() {
    var pass = $('#pass').val();
    var repass = $('#repass').val();
    if (pass == repass) {
        return true;
    } else {
        PF('pass-error').renderMessage({"summary": "As senhas nao conferem",
            "severity": "error"});
        return false;
    }
}
function validatePass() {
    var pass = $('#pass').val();
    if (isPass(pass)) {
        validaterePass();
    } else
        PF('pass-error').renderMessage({"summary": "Snha incorreta, deve conter pelo menos um numero e uma letra, no minimo 6 digitos",
            "severity": "error"});
    $('input[id="compid"]').addClass('error');
}
function validateForm() {
    var name = $('#name').val();
    var email = $('#email').val();
    if ($('#name').val() == "") {

        PF('name-error').renderMessage({"summary": "Informe seu nome respeitando caracters maisculos e minusculos",
            "severity": "error"});
        // $('input[id="name"]').addClass('error');

    } else if (!isName(name)) {

        PF('name-error').renderMessage({"summary": "Nome nao aceito",
            "severity": "error"});
    } else if ($('#email').val() == "") {
        PF('email-error').renderMessage({"summary": "Informe seu email",
            "severity": "error"});
        $('input[id="email"]').addClass('error');
    } else if (!isEmail($('#email').val())) {

        PF('email-error').renderMessage({"summary": "Email incorreto, por exemplo: joao@domain.com",
            "severity": "error"});
        $('input[id="email"]').addClass('error');
    } else
        validatePass();
}


function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}
function isPass(pass) {
    var regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/;
    return regex.test(pass);
}

$("input[id=studentid]").blur(function () {

    var ra = $('#studentid').val();
    if (ra == "" || ra == null || !isRa(ra)) {
        $('input[id="studentid"]').addClass('error');
        PF('ra-error').renderMessage({"summary": "Informe seu ra",
            "severity": "error"});
        $("#error-id").show();
        $("#ok-id").hide();
    } else {
        $('input[id="studentid"]').removeClass('error');
        $("#error-id").hide();
        $("#ok-id").show();
    }
});
$("input[id=compid]").blur(function () {

    var cpf = $('#compid').val();
    if (cpf == "" || cpf == null || !isCpf(cpf)) {
        $('input[id="compid"]').addClass('error');
        $("#error-id").show();
        $("#ok-id").hide();
    } else {
        $('input[id="compid"]').removeClass('error');
        $("#error-id").hide();
        $("#ok-id").show();
    }
});
$("input[id=email]").blur(function () {

    var email = $('#email').val();
    if (email == "" || email == null || !isEmail(email)) {
        $('input[id="email"]').addClass('error');
        $("#error-email").show();
        $("#ok-email").hide();
    } else {
        $('input[id="email"]').removeClass('error');
        $("#error-email").hide();
        $("#ok-email").show();
    }
});
$("input[id=pass]").blur(function () {

    var pass = $('#pass').val();
    if (pass == "" || pass == null || !isPass(pass)) {
        $('input[id="pass"]').addClass('error');
        $("#error-pass").show();
        $("#ok-pass").hide();
    } else {
        $('input[id="pass"]').removeClass('error');
        $("#error-pass").hide();
        $("#ok-pass").show();
    }
});
$("input[id=repass]").blur(function () {

    var repass = $('#repass').val();
    if (repass == "" || repass == null || !validaterePass()) {
        $('input[id="repass"]').addClass('error');
        $("#error-repass").show();
        $("#ok-repass").hide();
    } else {
        $('input[id="repass"]').removeClass('error');
        $("#error-repass").hide();
        $("#ok-repass").show();
    }
});
$("input[id=name]").blur(function () {

    var errName = $("#okname"); //Element selector
    errName.html("Please enter name"); // Put the message content inside div
    errName.addClass('error-msg'); //add a class to the element
    var name = $('#name').val();
    if (name == "" || name == null || !isName(name)) {
        $('input[id="name"]').addClass('error');
        $("#error-name").show();
        $("#ok-name").hide();
    } else {
        $('input[id="name"]').removeClass('error');
        $("#error-name").hide();
        $("#ok-name").show();
    }
});


