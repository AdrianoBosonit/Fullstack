var calculator = {
    valorPorDefecto: '0',
    primeraParte: null,
    esperandoSegundaParte: false,
    operator: null
};
function inputDigit(digit) {
    var valorPorDefecto = calculator.valorPorDefecto, esperandoSegundaParte = calculator.esperandoSegundaParte;
    if (esperandoSegundaParte === true) {
        calculator.valorPorDefecto = digit;
        calculator.esperandoSegundaParte = false;
    }
    else {
        calculator.valorPorDefecto = valorPorDefecto === '0' ? digit : valorPorDefecto + digit;
    }
    updateDisplay();
}
function inputDecimal(dot) {
    if (calculator.esperandoSegundaParte === true) {
        calculator.valorPorDefecto = "0.";
        calculator.esperandoSegundaParte = false;
        return;
    }
    if (!calculator.valorPorDefecto.indexOf(dot)) {
        calculator.valorPorDefecto += dot;
    }
    updateDisplay();
}
function handleOperator(nextOperator) {
    var primeraParte = calculator.primeraParte, valorPorDefecto = calculator.valorPorDefecto, operator = calculator.operator;
    var inputValue = parseFloat(valorPorDefecto);
    if (operator && calculator.esperandoSegundaParte) {
        calculator.operator = nextOperator;
        return;
    }
    if (primeraParte == null && !isNaN(inputValue)) {
        calculator.primeraParte = inputValue;
    }
    else if (operator) {
        var result = calculate(primeraParte, inputValue, operator);
        calculator.valorPorDefecto = "".concat(parseFloat(result.toFixed(7)));
        calculator.primeraParte = result;
    }
    calculator.esperandoSegundaParte = true;
    calculator.operator = nextOperator;
}
function calculate(primeraParte, secondOperand, operator) {
    if (operator === '+') {
        return primeraParte + secondOperand;
    }
    else if (operator === '-') {
        return primeraParte - secondOperand;
    }
    else if (operator === '*') {
        return primeraParte * secondOperand;
    }
    else if (operator === '/') {
        return primeraParte / secondOperand;
    }
    return secondOperand;
}
function resetCalculator() {
    calculator.valorPorDefecto = '0';
    calculator.primeraParte = null;
    calculator.esperandoSegundaParte = false;
    calculator.operator = null;
    updateDisplay();
}
function updateDisplay() {
    document.getElementById('pantalla').value = calculator.valorPorDefecto;
}
updateDisplay();
window.onload = function () {
    var botones = document.querySelector('.botones');
    document.querySelector('#sumar').addEventListener('click', function (e) { return handleOperator("+"); });
    document.querySelector('#restar').addEventListener('click', function (e) { return handleOperator("-"); });
    document.querySelector('#multiplicar').addEventListener('click', function (e) { return handleOperator("*"); });
    document.querySelector('#dividir').addEventListener('click', function (e) { return handleOperator("/"); });
    document.querySelector('#igual').addEventListener('click', function (e) { return handleOperator("="); });
    document.querySelector('#nueve').addEventListener('click', function (e) { return inputDigit(9); });
    document.querySelector('#ocho').addEventListener('click', function (e) { return inputDigit(8); });
    document.querySelector('#siete').addEventListener('click', function (e) { return inputDigit(7); });
    document.querySelector('#seis').addEventListener('click', function (e) { return inputDigit(6); });
    document.querySelector('#cinco').addEventListener('click', function (e) { return inputDigit(5); });
    document.querySelector('#cuatro').addEventListener('click', function (e) { return inputDigit(4); });
    document.querySelector('#tres').addEventListener('click', function (e) { return inputDigit(3); });
    document.querySelector('#dos').addEventListener('click', function (e) { return inputDigit(2); });
    document.querySelector('#uno').addEventListener('click', function (e) { return inputDigit(1); });
    document.querySelector('#cero').addEventListener('click', function (e) { return inputDigit(0); });
    document.querySelector('#coma').addEventListener('click', function (e) { return inputDecimal("."); });
    document.querySelector('#borrar').addEventListener('click', function (e) { return resetCalculator(); });
    document.querySelector('#exp').addEventListener('click', function (e) { return handleOperator("**"); });
};
