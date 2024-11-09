package pro.sky.java.TreeCategories.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

public class ViewTreeCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    private final MyTreeService myTreeService;

    public ViewTreeCommand(SendBotMessageService sendBotMessageService, MyTreeService myTreeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.myTreeService = myTreeService;
    }

    @Override
    public void execute(Update update) {
        String answer = myTreeService.toStringMyTreeByUser(update.getMessage().getChatId());
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), answer);
    }
}
