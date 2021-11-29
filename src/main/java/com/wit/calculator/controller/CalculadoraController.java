package com.wit.calculator.controller;

import com.wit.calculator.constantes.RabbitmqConstantes;
import com.wit.calculator.dto.CalculadoraDto;
import com.wit.calculator.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "api")
public class CalculadoraController {

    @Autowired
    private RabbitmqService raabbitmqService;

    @PostMapping(value = "calculadora")
    private ResponseEntity calcular(@Valid @RequestBody CalculadoraDto calculadoraDto) {
        Double result = calculadoraDto.Resultado();
        //System.out.println(result);
        this.raabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_CALCULADORA, calculadoraDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
