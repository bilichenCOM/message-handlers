package ua.edu.kneu.libraries.handlers;

import java.util.List;

public abstract class CommandMessageHandler<T, R> implements MessageHandler<T, R> {

    protected List<String> commands;

    public CommandMessageHandler(List<String> commands) {
        this.commands = commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
