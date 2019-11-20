package ua.edu.kneu.libraries.handlers.telegram;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

public class TelegramBot extends TelegramLongPollingBot {

    private String botUserName;

    private String botToken;

    private TelegramDispatcherMessageHandler dispatcher;

    private final Logger logger = Logger.getLogger(TelegramLongPollingBot.class);

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Optional<SendMessage> message = dispatcher.handleMessage(update.getMessage());
            if (message.isPresent()) {
                try {
                    execute(message.get());
                } catch (TelegramApiException e) {
                    logger.error("Some problems sending message!");
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public void setDispatcherMessageHandler(TelegramDispatcherMessageHandler dispatcherMessageHandler) {
        dispatcher = dispatcherMessageHandler;
    }
}
