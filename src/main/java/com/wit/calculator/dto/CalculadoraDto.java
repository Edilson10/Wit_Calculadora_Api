package com.wit.calculator.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

public class CalculadoraDto implements Serializable {

    @NotNull(message = "Por favor digita um número válido para operação")
    private Double num1;

    @NotBlank(message = "Por favor Selecione a operação")
    private String operacao;


    @NotNull(message = "Por favor digita um número válido para operação")
    private Double num2;

    private Double resultado;

    public Double getNum1() {
        return num1;
    }

    public void setNum1(Double num1) {
        this.num1 = num1;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Double getNum2() {
        return num2;
    }

    public void setNum2(Double num2) {
        this.num2 = num2;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Double Resultado() {
        switch (this.operacao) {
            case "+":
                this.resultado = this.num1 + this.num2;
                break;
            case "-":
                this.resultado = this.num1 - this.num2;
                break;
            case "*":
                this.resultado = this.num1 * this.num2;
                break;
            case "/":
                this.resultado = this.num1 / this.num2;
                break;
        }
        return this.resultado;
    }
}







