package guru.sfg.msscbeerservice.web.controller;

import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MVCExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException ex) {
    List<String> errorsList = new ArrayList<>(ex.getConstraintViolations().size());

    ex.getConstraintViolations()
        .forEach(constraintViolation -> errorsList.add(constraintViolation.toString()));

    return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<String>> validationErrorHandler(MethodArgumentNotValidException e) {
    List<String> errors = new ArrayList<>(e.getErrorCount());

    e.getFieldErrors()
        .forEach(
            fieldError ->
                errors.add(
                    fieldError.getObjectName()
                        + "."
                        + fieldError.getField()
                        + " : "
                        + fieldError.getDefaultMessage()));

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
