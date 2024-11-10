package pro.sky.java.TreeCategories.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

/**
 * RemoveElement {@link Command}.
 */
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
        Long chat = update.getMessage().getChatId();
        String answer;
        if (command.length == 1) {answer = "Не введена категория!";}
        else {
        answer = myTreeService.removeMyTreeCategory(chat, command[1]);}
        sendBotMessageService.sendMessage(chat, answer);
    }
}
