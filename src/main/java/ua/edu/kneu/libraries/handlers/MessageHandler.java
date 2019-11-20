package ua.edu.kneu.libraries.handlers;

import java.util.Optional;

@FunctionalInterface
public interface MessageHandler<T, R> {

    Optional<R> handleMessage(T msg);
}
