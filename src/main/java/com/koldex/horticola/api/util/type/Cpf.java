package com.koldex.horticola.api.util.type;

import org.springframework.http.converter.HttpMessageConversionException;

import java.util.Formatter;

public final class Cpf {

    private static final int[] PESO_CPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    private static final int ZERO = 0;
    private static final int NOVE = 9;
    private final String cpf;

    public Cpf(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new HttpMessageConversionException("O valor do CPF não pode ser nulo ou vazio");
        }
        this.cpf = valor.replaceAll("\\D", "");
        if (!isValido()) {
            throw new HttpMessageConversionException("CPF inválido: " + valor);
        }
    }

    public String formatado() {
        try (Formatter formatter = new Formatter()) {
            formatter.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
            return formatter.toString();
        }
    }

    public String digitos() {
        return this.cpf;
    }

    public boolean isValido() {
        if (cpf.length() != 11) {
            return false;
        }
        return verificaDigitos(cpf);
    }

    private boolean verificaDigitos(String cpf) {
        int digito1 = calcularDigito(cpf.substring(ZERO, NOVE));
        int digito2 = calcularDigito(cpf.substring(ZERO, NOVE) + digito1);
        return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
    }

    private int calcularDigito(String str) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            soma += Integer.parseInt(str.substring(indice, indice + 1))
                    * Cpf.PESO_CPF[Cpf.PESO_CPF.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

}