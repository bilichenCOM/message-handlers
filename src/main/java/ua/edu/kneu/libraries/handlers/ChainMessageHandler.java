package ua.edu.kneu.libraries.handlers;

public interface ChainMessageHandler<T, R, U> extends MessageHandler<T, R> {

    default U andThen(U other) {
        throw new UnsupportedOperationException("chaining operation is not implemented!");
    }
}
