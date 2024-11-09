package pro.sky.java.TreeCategories.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

public class AddElementCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    private final MyTreeService myTreeService;

    public AddElementCommand(SendBotMessageService sendBotMessageService, MyTreeService myTreeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.myTreeService = myTreeService;
    }

    @Override
    public void execute(Update update) {
        String message = update.getMessage().getText().trim();

        String[] command = message.split(" ");
        if (command.length == 2) {
            String answer = myTreeService.addCategory(update.getMessage().getChatId(), command
                    [1]);
            sendBotMessageService.sendMessage(update.getMessage().getChatId(), answer);

        }
        else if (command.length == 3){
            String answer = myTreeService.addChild(update.getMessage().getChatId(), command
                    [1],command[2]);
            sendBotMessageService.sendMessage(update.getMessage().getChatId(), answer);
        }
        else sendBotMessageService.sendMessage(update.getMessage().getChatId(),
                "некорректная команда");
    }


}

