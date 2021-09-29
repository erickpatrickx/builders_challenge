package br.com.devchallenge.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Trata BadRequestException
 * @author erick.oliveira
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
public BadRequestException(String message) {
    super(message);
 }
}