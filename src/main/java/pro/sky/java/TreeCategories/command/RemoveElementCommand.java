package pro.sky.java.TreeCategories.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

public class RemoveElementCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public RemoveElementCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public void execute(Update update) {

    }
}
