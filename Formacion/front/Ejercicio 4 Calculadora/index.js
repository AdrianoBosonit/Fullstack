var todo = "";
var segundoValor = false;
var pantalla = "";
var operacion = "";
var coma = false;
window.onload = function () {
    document.querySelector('#sumar').addEventListener('click', function (e) { return guardaPantalla("+"); });
    document.querySelector('#restar').addEventListener('click', function (e) { return guardaPantalla("-"); });
    document.querySelector('#multiplicar').addEventListener('click', function (e) { return guardaPantalla("*"); });
    document.querySelector('#dividir').addEventListener('click', function (e) { return guardaPantalla("/"); });
    document.querySelector('#igual').addEventListener('click', function (e) { return operar(); });
    document.querySelector('#nueve').addEventListener('click', function (e) { return actualizaPantalla("9"); });
    document.querySelector('#ocho').addEventListener('click', function (e) { return actualizaPantalla("8"); });
    document.querySelector('#siete').addEventListener('click', function (e) { return actualizaPantalla("7"); });
    document.querySelector('#seis').addEventListener('click', function (e) { return actualizaPantalla("6"); });
    document.querySelector('#cinco').addEventListener('click', function (e) { return actualizaPantalla("5"); });
    document.querySelector('#cuatro').addEventListener('click', function (e) { return actualizaPantalla("4"); });
    document.querySelector('#tres').addEventListener('click', function (e) { return actualizaPantalla("3"); });
    document.querySelector('#dos').addEventListener('click', function (e) { return actualizaPantalla("2"); });
    document.querySelector('#uno').addEventListener('click', function (e) { return actualizaPantalla("1"); });
    document.querySelector('#cero').addEventListener('click', function (e) { return actualizaPantalla("0"); });
    document.querySelector('#coma').addEventListener('click', function (e) { return actualizaPantalla("."); });
    document.querySelector('#borrar').addEventListener('click', function (e) { return clean(); });
    document.querySelector('#exp').addEventListener('click', function (e) { return guardaPantalla("**"); });
};
function actualizaPantalla(valor) {
    var val = document.getElementById('pantalla').value;
    if (segundoValor && (valor == "+" || valor == "-" || valor == "*" || valor == "/" || valor == "**")) {
        document.getElementById('pantalla').value = eval(todo);
        todo += valor;
        operacion = "realizada";
        coma = false;
    }
    else if (((todo == "" && pantalla == "") || (segundoValor && pantalla == val) || operacion == "realizada") && (valor != ".")) {
        document.getElementById('pantalla').value = valor;
        pantalla = valor;
        operacion == "";
    }
    else {
        console.log(valor + "   " + coma);
        if (valor == ".") {
            if (!coma) {
                coma = true;
                document.getElementById('pantalla').value += valor;
            }
        }
        else {
            document.getElementById('pantalla').value += valor;
        }
    }
}
function clean() {
    segundoValor = false;
    pantalla = "";
    operacion = "";
    todo = "";
    coma = false;
    document.getElementById('pantalla').value = 0;
}
function guardaPantalla(oper) {
    if (!segundoValor) {
        pantalla = document.getElementById('pantalla').value;
        todo += pantalla + oper;
        segundoValor = true;
        operacion = oper;
    }
    else {
        todo += document.getElementById('pantalla').value;
        operacion = oper;
        actualizaPantalla(oper);
    }
}
function operar() {
    var res;
    if (operacion != "realizada") {
        res = eval(pantalla + operacion + document.getElementById('pantalla').value);
        todo = res;
        operacion = "realizada";
    }
    else {
        res = eval(todo + document.getElementById('pantalla').value);
    }
    segundoValor = false;
    pantalla = "";
    operacion = "";
    todo = "";
    coma = false;
    actualizaPantalla(res);
}
