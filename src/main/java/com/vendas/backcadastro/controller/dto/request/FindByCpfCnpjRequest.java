package com.vendas.backcadastro.controller.dto.request;

import jakarta.validation.constraints.AssertTrue;

import java.util.HashMap;
import java.util.Map;

public record FindByCpfCnpjRequest(
        String cpf,
        String cnpj) {

    @AssertTrue(message = "O `cpf` deve conter apenas números.")
    public boolean isValidCpf() {
        if (cpf == null || cpf.isEmpty()) {
            return true; // Permite CPF vazio se não for obrigatório por outras validações
        }
        return cpf.matches("^\\d+$");
    }

    @AssertTrue(message = "O `cnpj` deve conter apenas números.")
    public boolean isValidCnpj() {
        if (cnpj == null || cnpj.isEmpty()) {
            return true; // Permite CNPJ vazio se não for obrigatório por outras validações
        }
        return cnpj.matches("^\\d+$");
    }

    @AssertTrue(message = "Deve ser informado apenas o CPF ou apenas o CNPJ.")
    public boolean isOnlyCpfOrCnpjProvided() {
        boolean cpfProvided = cpf != null && !cpf.isEmpty();
        boolean cnpjProvided = cnpj != null && !cnpj.isEmpty();
        return (cpfProvided && !cnpjProvided) || (!cpfProvided && cnpjProvided);
    }

    public Map<String, String> getCpfCnpj() {
        Map<String, String> resultado = new HashMap<>();
        if(cpf != null) {
            resultado.put("doc", "cpf");
            resultado.put("numero", cpf);
            return resultado;
        }

        resultado.put("doc", "cnpj");
        resultado.put("numero", cnpj);

        return resultado;
    }
}
