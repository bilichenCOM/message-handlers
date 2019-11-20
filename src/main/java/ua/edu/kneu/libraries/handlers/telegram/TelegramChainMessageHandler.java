package ua.edu.kneu.libraries.handlers.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ua.edu.kneu.libraries.handlers.ChainMessageHandler;

import java.util.Optional;

public interface TelegramChainMessageHandler extends ChainMessageHandler<Message, SendMessage, TelegramChainMessageHandler>,
        TelegramMessageHandler {

    @Override
    default TelegramChainMessageHandler andThen(TelegramChainMessageHandler other) {
        return msg -> {
            Optional<SendMessage> previous = handleMessage(msg);
            Optional<SendMessage> next = other.handleMessage(msg);
            if (previous.isPresent()) {
                if (next.isPresent()) {
                    SendMessage prev = previous.get();
                    SendMessage nxt = next.get();
                    SendMessage after = new SendMessage();
                    after.setChatId(nxt.getChatId() != null ? nxt.getChatId() : prev.getChatId());
                    after.setReplyToMessageId(nxt.getReplyToMessageId() != null ? nxt.getReplyToMessageId() : prev.getReplyToMessageId());
                    after.setText(prev.getText() + nxt.getText());
                    return Optional.of(after);
                }
                return previous;
            }
            return next;
        };
    }
}
