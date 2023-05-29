package screbber.restaurant.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import screbber.restaurant.exceptions.OrderNotFoundException;

import java.security.DigestException;

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


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
        LOGGER.error("Order not found {} ", ex.getMessage());

        return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DigestException.class)
    public ResponseEntity<String> handleDishNotFoundException(DigestException ex) {
        LOGGER.error("dish not found {} ", ex.getMessage());

        return new ResponseEntity<>("dish not found", HttpStatus.NOT_FOUND);
    }
}
