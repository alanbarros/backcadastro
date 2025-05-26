package com.vendas.backcadastro.controller;

import com.vendas.backcadastro.controller.dto.request.FindByCpfCnpjRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    @GetMapping("/")
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(
                "Oi mundo!",
                HttpStatus.OK);
    }

    @GetMapping("/from/cpf_cnpj")
    public ResponseEntity<?> getFromCpfCnpj(
            @Valid @ModelAttribute FindByCpfCnpjRequest request,
            BindingResult result
            ) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Map<String, String> cpfCnpj = request.getCpfCnpj();

        return new ResponseEntity<>(
                String.format("O %s: %s foi validado com sucesso",
                        cpfCnpj.get("doc"), cpfCnpj.get("numero")),
                HttpStatus.OK);
    }
}
