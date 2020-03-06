package br.com.hpaiva.clubsupporterservice.interceptor;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeneralAdviceController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public DefaultErrorWrapper handleGenericException(final Exception exception) {
        final String stackTraceStr =
                Arrays.stream(exception.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.joining("\n"));

        log.error(
                "m=handleGenericException  message={} cause={} stackTrace={} class={}",
                exception.getMessage(),
                exception.getCause(),
                stackTraceStr,
                exception.getClass().toString());
        return new DefaultErrorWrapper(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public DefaultErrorWrapper handleNotFoundException(final NotFoundException exception) {
        log.error(
                "m=handleNotFoundException message={}",
                exception.getMessage());
        return new DefaultErrorWrapper(exception.getMessage());
    }
}
