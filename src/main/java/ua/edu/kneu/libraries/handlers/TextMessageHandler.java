package ua.edu.kneu.libraries.handlers;

import java.util.Arrays;
import java.util.List;

public abstract class TextMessageHandler<T, R> implements MessageHandler<T, R> {

    protected enum ConversationPattern {
        GREETINGS(Arrays.asList("hello", "hi", "привіт", "привет", "здаров", "здоров"),
                Arrays.asList("hello", "hi", "привіт", "привет")),
        ASKS_FOR_REMINDER(Arrays.asList("нагадування", "reminder", "напоминалка", "напоминание", "remind me"),
                Arrays.asList("створив нагадування", "я нагадаю Вам про це", "я запам'ятаю", "ви отримаєте повідомлення", "зробив")),
        PHRASES_FOR_REMINDER(null,
                Arrays.asList("нагадування:", "не забудьте:", "нагадую:")),
        UNKNOWN_REPLY(null,
                Arrays.asList("sorry, currently not so smart :(", "...", "я подумаю над цим", "не можу відповісти", "please, say it in another words)")),
        POSITIVE_REPLY(null,
                Arrays.asList("ok, I will do that", "виконую", "добре", "без проблем", "зробив")),
        MORNING_MESSAGES(null,
                Arrays.asList("Always get plenty of sleep, if you can.",
                        "Sometimes you eat the bear and sometimes the bear, well, he eats you.",
                        "You're here! The day just got better.",
                        "The mystery of life isn't a problem to solve, but a reality to experience. Frenk Herbert",
                        "You look nice today.",
                        "We’re all in this together.",
                        "Be cool. But also be warm.",
                        "Alright world, time to take you on!",
                        "Remember to get up & stretch once in a while."
                )),
        GRATITUDES(Arrays.asList("thank", "дякую", "спасиб", "спасіб", "вдячн"),
                Arrays.asList("U R welcome", "будь ласка", "це було не важко", "завжди радий")),
        HOWRU(Arrays.asList("справи", "діла", "дєла", "дела"),
                Arrays.asList("хороший день - я впорядку, поганий день - я впорядку. До чого ці питання?")),
        WIKI(Arrays.asList("що це", "що таке", "what is", "что такое", "что это", "wiki", "вики", "вікі"),
                Arrays.asList(""));
        private List<String> phrases;
        private List<String> answers;

        ConversationPattern(List<String> phrases, List<String> answers) {
            this.phrases = phrases;
            this.answers = answers;
        }

        public List<String> getPhrases() {
            return phrases;
        }

        public List<String> getAnswers() {
            return answers;
        }
    }
}
