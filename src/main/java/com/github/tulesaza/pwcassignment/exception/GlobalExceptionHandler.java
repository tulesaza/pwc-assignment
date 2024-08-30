package com.github.tulesaza.pwcassignment.exception;

import com.github.tulesaza.pwcassignment.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoLandCrossedException.class)
    public ResponseEntity<ErrorResponse> handleNoLandCrossedException(NoLandCrossedException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "No land crossed",
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
