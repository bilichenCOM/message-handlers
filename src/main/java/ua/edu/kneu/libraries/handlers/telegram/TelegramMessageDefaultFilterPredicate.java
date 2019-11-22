package ua.edu.kneu.libraries.handlers.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Predicate;

public class TelegramMessageDefaultFilterPredicate implements Predicate<Message> {

    private String botUserName;

    public TelegramMessageDefaultFilterPredicate(String botUserName) {
        this.botUserName = botUserName;
    }

    @Override
    public boolean test(Message message) {
        Predicate<Message> privateChat = msg -> msg.isUserMessage();
        Predicate<Message> hasBotName = msg -> msg.getText().contains(botUserName);

        return privateChat.or(hasBotName).test(message);
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }
}
