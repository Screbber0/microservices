package screbber.SpringSecurity.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionsHandler.class);

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<String> handlePlaylistNotFoundException(JWTVerificationException ex) {
        LOGGER.error("Invalid or expired JWT token {}", ex.getMessage());

        return new ResponseEntity<>("Invalid or expired JWT token", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        LOGGER.error("User not found {}", ex.getMessage());

        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
