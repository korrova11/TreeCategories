package pro.sky.java.TreeCategories.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

public class RemoveElementCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final MyTreeService myTreeService;
    public RemoveElementCommand(SendBotMessageService sendBotMessageService, MyTreeService myTreeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.myTreeService = myTreeService;
    }
    @Override
    public void execute(Update update) {
        String message = update.getMessage().getText().trim();

        String[] command = message.split(" ");
        String answer = myTreeService.removeMyTreeCategory(update.getMessage().getChatId(),command[1]);
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), answer);
    }
}
