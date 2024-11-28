package pro.sky.java.TreeCategories.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

/**
 * AddElement {@link Command}.
 */
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
        Long chat = update.getMessage().getChatId();
        String[] command = message.split(" ");
        if (command.length == 2) {
            String answer = myTreeService.addCategory(chat, command
                    [1]);
            sendBotMessageService.sendMessage(chat, answer);

        } else if (command.length == 3) {
            String answer = myTreeService.addChild(chat, command
                    [1], command[2]);
            sendBotMessageService.sendMessage(chat, answer);
        } else sendBotMessageService.sendMessage(chat,
                "некорректная команда");
    }

}

