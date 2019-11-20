package ua.edu.kneu.libraries.handlers.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ua.edu.kneu.libraries.handlers.MessageHandler;

public interface TelegramMessageHandler extends MessageHandler<Message, SendMessage> {

}
