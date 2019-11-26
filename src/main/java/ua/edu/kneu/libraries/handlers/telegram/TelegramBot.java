package ua.edu.kneu.libraries.handlers.telegram;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;
import java.util.function.Predicate;

public class TelegramBot extends TelegramLongPollingBot {

    private String botUserName;

    private String botToken;

    private TelegramDispatcherMessageHandler dispatcher;

    private Predicate<Message> messageFilter;

    private final Logger logger = Logger.getLogger(TelegramLongPollingBot.class);

    public TelegramBot(String botUserName, String botToken) {
        this.botUserName = botUserName;
        this.botToken = botToken;
        this.messageFilter = new TelegramMessageDefaultFilterPredicate(botUserName);
        this.dispatcher = new TelegramDispatcherMessageHandler();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message msg = update.getMessage();
            if (!messageFilter.test(msg)) {
                return;
            }

            Optional<SendMessage> responseMsg = dispatcher.handleMessage(msg);
            if (responseMsg.isPresent()) {
                try {
                    execute(responseMsg.get());
                } catch (TelegramApiException e) {
                    logger.error("Some problems sending message!");
                }
            }
        }
    }

    public TelegramDispatcherMessageHandler getDispatcher() {
        return dispatcher;
    }

    public void setDispatcherMessageHandler(TelegramDispatcherMessageHandler dispatcherMessageHandler) {
        dispatcher = dispatcherMessageHandler;
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
}
