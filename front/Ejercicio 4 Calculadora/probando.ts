const calculator = {
    valorPorDefecto: '0',
    primeraParte: null,
    esperandoSegundaParte: false,
    operator: null,
};

function inputDigit(digit) {
    const { valorPorDefecto, esperandoSegundaParte } = calculator;

    if (esperandoSegundaParte === true) {
        calculator.valorPorDefecto = digit;
        calculator.esperandoSegundaParte = false;
    } else {
        calculator.valorPorDefecto = valorPorDefecto === '0' ? digit : valorPorDefecto + digit;
    }
    updateDisplay();
}

function inputDecimal(dot) {
    if (calculator.esperandoSegundaParte === true) {
        calculator.valorPorDefecto = "0."
        calculator.esperandoSegundaParte = false;
        return
    }

    if (!calculator.valorPorDefecto.indexOf(dot)) {
        calculator.valorPorDefecto += dot;
    }
    updateDisplay();
}

function handleOperator(nextOperator) {
    const { primeraParte, valorPorDefecto, operator } = calculator
    const inputValue = parseFloat(valorPorDefecto);

    if (operator && calculator.esperandoSegundaParte) {
        calculator.operator = nextOperator;
        return;
    }


    if (primeraParte == null && !isNaN(inputValue)) {
        calculator.primeraParte = inputValue;
    } else if (operator) {
        const result = calculate(primeraParte, inputValue, operator);

        calculator.valorPorDefecto = `${parseFloat(result.toFixed(7))}`;
        calculator.primeraParte = result;
    }

    calculator.esperandoSegundaParte = true;
    calculator.operator = nextOperator;
}

function calculate(primeraParte, secondOperand, operator) {
    if (operator === '+') {
        return primeraParte + secondOperand;
    } else if (operator === '-') {
        return primeraParte - secondOperand;
    } else if (operator === '*') {
        return primeraParte * secondOperand;
    } else if (operator === '/') {
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
    (document.getElementById('pantalla') as HTMLFormElement).value = calculator.valorPorDefecto;
}

updateDisplay();
window.onload = () => {
    const botones = document.querySelector('.botones');
    document.querySelector('#sumar').addEventListener('click', (e: Event) => handleOperator("+"));
    document.querySelector('#restar').addEventListener('click', (e: Event) => handleOperator("-"));
    document.querySelector('#multiplicar').addEventListener('click', (e: Event) => handleOperator("*"));
    document.querySelector('#dividir').addEventListener('click', (e: Event) => handleOperator("/"));
    document.querySelector('#igual').addEventListener('click', (e: Event) => handleOperator("="));
    document.querySelector('#nueve').addEventListener('click', (e: Event) => inputDigit(9));
    document.querySelector('#ocho').addEventListener('click', (e: Event) => inputDigit(8));
    document.querySelector('#siete').addEventListener('click', (e: Event) => inputDigit(7));
    document.querySelector('#seis').addEventListener('click', (e: Event) => inputDigit(6));
    document.querySelector('#cinco').addEventListener('click', (e: Event) => inputDigit(5));
    document.querySelector('#cuatro').addEventListener('click', (e: Event) => inputDigit(4));
    document.querySelector('#tres').addEventListener('click', (e: Event) => inputDigit(3));
    document.querySelector('#dos').addEventListener('click', (e: Event) => inputDigit(2));
    document.querySelector('#uno').addEventListener('click', (e: Event) => inputDigit(1));
    document.querySelector('#cero').addEventListener('click', (e: Event) => inputDigit(0));
    document.querySelector('#coma').addEventListener('click', (e: Event) => inputDecimal("."));
    document.querySelector('#borrar').addEventListener('click', (e: Event) => resetCalculator());
    document.querySelector('#exp').addEventListener('click', (e: Event) => handleOperator("**"));
}
