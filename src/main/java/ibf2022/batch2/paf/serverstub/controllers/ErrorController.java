package ibf2022.batch2.paf.serverstub.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ibf2022.batch2.paf.serverstub.exceptions.TransactionFailException;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestControllerAdvice
public class ErrorController {

    // Transfer failed return the following JSON object
	// { "message", "Error message" }
    @ExceptionHandler(TransactionFailException.class)
    public ResponseEntity<String> handleTransactionFail(TransactionFailException ex){

        JsonObject message = Json.createObjectBuilder().add("message", ex.getMessage()).build();
        return new ResponseEntity<>(message.toString(),HttpStatus.BAD_REQUEST);

    }

    
}
