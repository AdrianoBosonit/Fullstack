let primerValor = null;
let oper=null;

window.onload = () => {
    document.querySelector('#sumar').addEventListener('click', (e: Event) => guardaPantalla("+"));
    document.querySelector('#restar').addEventListener('click', (e: Event) => guardaPantalla("-"));
    document.querySelector('#multiplicar').addEventListener('click', (e: Event) => guardaPantalla("*"));
    document.querySelector('#dividir').addEventListener('click', (e: Event) => guardaPantalla("/"));
    document.querySelector('#igual').addEventListener('click', (e: Event) => operar());
    document.querySelector('#nueve').addEventListener('click', (e: Event) => inputNumber("9"));
    document.querySelector('#ocho').addEventListener('click', (e: Event) => inputNumber("8"));
    document.querySelector('#siete').addEventListener('click', (e: Event) => inputNumber("7"));
    document.querySelector('#seis').addEventListener('click', (e: Event) => inputNumber("6"));
    document.querySelector('#cinco').addEventListener('click', (e: Event) => inputNumber("5"));
    document.querySelector('#cuatro').addEventListener('click', (e: Event) => inputNumber("4"));
    document.querySelector('#tres').addEventListener('click', (e: Event) => inputNumber("3"));
    document.querySelector('#dos').addEventListener('click', (e: Event) => inputNumber("2"));
    document.querySelector('#uno').addEventListener('click', (e: Event) => inputNumber("1"));
    document.querySelector('#cero').addEventListener('click', (e: Event) => inputNumber("0"));
    document.querySelector('#coma').addEventListener('click', (e: Event) => inputNumber("."));
    document.querySelector('#borrar').addEventListener('click', (e: Event) => clean());
    document.querySelector('#exp').addEventListener('click', (e: Event) => guardaPantalla("**"));
}
function inputNumber(valor: string) {
    if (oper == null) {
        primerValor += valor;
        updatePantalla(primerValor);
    }

}

funtion inputOperacion(valor: string){
    
}

function updatePantalla(valor: string) {
    (document.getElementById('pantalla') as HTMLFormElement).value = valor;
}
/**
 * 
 * let todo = "";
let segundoValor = false;
let pantalla = "";
let operacion = "";
let coma = false;

 * window.onload = () => {
    document.querySelector('#sumar').addEventListener('click', (e: Event) => guardaPantalla("+"));
    document.querySelector('#restar').addEventListener('click', (e: Event) => guardaPantalla("-"));
    document.querySelector('#multiplicar').addEventListener('click', (e: Event) => guardaPantalla("*"));
    document.querySelector('#dividir').addEventListener('click', (e: Event) => guardaPantalla("/"));
    document.querySelector('#igual').addEventListener('click', (e: Event) => operar());
    document.querySelector('#nueve').addEventListener('click', (e: Event) => actualizaPantalla("9"));
    document.querySelector('#ocho').addEventListener('click', (e: Event) => actualizaPantalla("8"));
    document.querySelector('#siete').addEventListener('click', (e: Event) => actualizaPantalla("7"));
    document.querySelector('#seis').addEventListener('click', (e: Event) => actualizaPantalla("6"));
    document.querySelector('#cinco').addEventListener('click', (e: Event) => actualizaPantalla("5"));
    document.querySelector('#cuatro').addEventListener('click', (e: Event) => actualizaPantalla("4"));
    document.querySelector('#tres').addEventListener('click', (e: Event) => actualizaPantalla("3"));
    document.querySelector('#dos').addEventListener('click', (e: Event) => actualizaPantalla("2"));
    document.querySelector('#uno').addEventListener('click', (e: Event) => actualizaPantalla("1"));
    document.querySelector('#cero').addEventListener('click', (e: Event) => actualizaPantalla("0"));
    document.querySelector('#coma').addEventListener('click', (e: Event) => actualizaPantalla("."));
    document.querySelector('#borrar').addEventListener('click', (e: Event) => clean());
    document.querySelector('#exp').addEventListener('click', (e: Event) => guardaPantalla("**"));
} 
function actualizaPantalla(valor: string) {
    let val = (document.getElementById('pantalla') as HTMLFormElement).value;
    if (segundoValor && (valor == "+" || valor == "-" || valor == "*" || valor == "/" || valor == "**")) {
        (document.getElementById('pantalla') as HTMLFormElement).value = eval(todo);
        todo += valor;
        operacion = "realizada";
        coma = false;
    } else if (((todo == "" && pantalla == "") || (segundoValor && pantalla == val) || operacion == "realizada") && (valor != ".")) {
        (document.getElementById('pantalla') as HTMLFormElement).value = valor;
        pantalla = valor;
        operacion == "";
    } else {
        console.log(valor + "   " + coma);
        if (valor == ".") {
            if (!coma) {
                coma = true;
                (document.getElementById('pantalla') as HTMLFormElement).value += valor;
            }
        } else {
            (document.getElementById('pantalla') as HTMLFormElement).value += valor;
        }
    }
}

function clean() {
    segundoValor = false;
    pantalla = "";
    operacion = "";
    todo = "";
    coma = false;
    (document.getElementById('pantalla') as HTMLFormElement).value = 0;
}

function guardaPantalla(oper: string) {
    if (!segundoValor) {
        pantalla = (document.getElementById('pantalla') as HTMLFormElement).value;
        todo += pantalla + oper;
        segundoValor = true;
        operacion = oper;
    } else {
        todo += (document.getElementById('pantalla') as HTMLFormElement).value;
        operacion = oper;
        actualizaPantalla(oper)
    }

}

function operar() {
    let res;
    if (operacion != "realizada") {
        res = eval(pantalla + operacion + (document.getElementById('pantalla') as HTMLFormElement).value);
        todo = res;
        operacion = "realizada";
    } else {
        res = eval(todo + (document.getElementById('pantalla') as HTMLFormElement).value);
    }
    segundoValor = false;
    pantalla = "";
    operacion = "";
    todo = "";
    coma = false;
    actualizaPantalla(res);
}

