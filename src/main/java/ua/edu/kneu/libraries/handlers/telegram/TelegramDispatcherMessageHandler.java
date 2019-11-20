package ua.edu.kneu.libraries.handlers.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TelegramDispatcherMessageHandler implements TelegramMessageHandler {

    private List<TelegramChainMessageHandler> handlers;
    private Predicate<Message> messageFilter;

    public TelegramDispatcherMessageHandler() {
        handlers = new ArrayList<>();
        messageFilter = msg -> true;
    }

    @Override
    public Optional<SendMessage> handleMessage(Message msg) {
        if (!filterMessage(msg)) {
            return Optional.empty();
        }

        TelegramChainMessageHandler combinedMessageHandler = handlers.stream()
                .reduce(TelegramChainMessageHandler::andThen)
                .orElseThrow(() -> new IllegalStateException("there are no message handlers!"));

        Optional<SendMessage> optionalMessage = combinedMessageHandler.handleMessage(msg);
        if (!optionalMessage.isPresent()) {
            return Optional.empty();
        }
        SendMessage message = optionalMessage.get();
        if (message.getChatId() == null) {
            message.setChatId(msg.getChatId());
        }

        return Optional.of(message);
    }

    private boolean filterMessage(Message msg) {
        return messageFilter.test(msg);
    }

    public void addHandler(TelegramChainMessageHandler handler) {
        handlers.add(handler);
    }

    public List<TelegramChainMessageHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<TelegramChainMessageHandler> handlers) {
        this.handlers = handlers;
    }

    public void setMessageFilter(Predicate<Message> messageFilter) {
        this.messageFilter = messageFilter;
    }
}
