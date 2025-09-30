package com.teste.acdnb.core.domain.shared.valueobject;

import java.util.regex.Pattern;

public class Cpf {
    private String value;
    private static final Pattern pattern = Pattern.compile("^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$");

    private Cpf(String value) {
        this.value = value;
    }

    public static Cpf of(String value) {
        if(value == null || !pattern.matcher(value).matches()) {
            throw new IllegalArgumentException("CPF inválido");
        }

        value = value.replaceAll("[-.]","");

        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(value.charAt(i)) * peso--;
        }

        int digito1 = 11 - (soma % 11);
        if (digito1 == 10 || digito1 == 11) digito1 = 0;

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(value.charAt(i)) * peso--;
        }

        int digito2 = 11 - (soma % 11);
        if (digito2 == 10 || digito2 == 11) digito2 = 0;

        boolean valido = digito1 == Character.getNumericValue(value.charAt(9)) && digito2 == Character.getNumericValue(value.charAt(10));

        if(valido){
            return new Cpf(value);
        }else{
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public String getValue() {
        return value;
    }
}
