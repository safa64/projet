package com.example.projectmanagement.resource;

import com.example.projectmanagement.DTO.ResponseAuth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class MaxUploadSizeExceededExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseAuth> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        String errorMessage = "Maximum upload size exceeded";
        return ResponseEntity.badRequest().body(new ResponseAuth(errorMessage));
    }
}
