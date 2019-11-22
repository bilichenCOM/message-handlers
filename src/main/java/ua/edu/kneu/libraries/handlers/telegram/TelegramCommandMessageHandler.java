package ua.edu.kneu.libraries.handlers.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ua.edu.kneu.libraries.handlers.CommandMessageHandler;

import java.util.List;

public abstract class TelegramCommandMessageHandler extends CommandMessageHandler<Message, SendMessage> implements TelegramChainMessageHandler {

    public TelegramCommandMessageHandler(List<String> commands) {
        super(commands);
    }
}
