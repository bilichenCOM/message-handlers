package ua.edu.kneu.libraries.handlers.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TelegramDispatcherMessageHandler implements TelegramMessageHandler {

    private List<TelegramChainMessageHandler> handlers;

    public TelegramDispatcherMessageHandler() {
        handlers = new ArrayList<>();
    }

    @Override
    public Optional<SendMessage> handleMessage(Message msg) {
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

    public void addHandler(TelegramChainMessageHandler handler) {
        handlers.add(handler);
    }

    public List<TelegramChainMessageHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<TelegramChainMessageHandler> handlers) {
        this.handlers = handlers;
    }
}
