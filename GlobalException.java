package com.lpu.Momento;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue, WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(
                ue.getMessage(),
                req.getDescription(false),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(PostException.class) 
    public ResponseEntity<ErrorDetails> PostExceptionHandler(PostException ue, WebRequest req) {
    	ErrorDetails errorDetails = new ErrorDetails(
                ue.getMessage(),
                req.getDescription(false),
                LocalDateTime.now()
        );
    	return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    	
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me) {
        ErrorDetails errorDetails = new ErrorDetails(
                me.getBindingResult().getFieldError().getDefaultMessage(),
                "Validation error",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception e, WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                req.getDescription(false),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ErrorDetails> CommentExceptionHandler(CommentException ue, WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(
                ue.getMessage(),
                req.getDescription(false),
                LocalDateTime.now()
        );
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(StoryException.class)
    public ResponseEntity<ErrorDetails> StoryExceptionHandler(StoryException ue, WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(
                ue.getMessage(),
                req.getDescription(false),   
                LocalDateTime.now()
        );
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    
}
