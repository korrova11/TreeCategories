package pro.sky.java.TreeCategories.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.CategoryService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

/**
 * AddElement {@link Command}.
 */
@Component
public class AddElementCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    private final CategoryService categoryService;

    public AddElementCommand(SendBotMessageService sendBotMessageService, CategoryService categoryService) {
        this.sendBotMessageService = sendBotMessageService;
        this.categoryService = categoryService;
    }

    @Override
    public void execute(Update update) {
        String message = update.getMessage().getText().trim();
        Long chat = update.getMessage().getChatId();
        String[] command = message.split(" ");
        if (command.length == 2) {
            String answer = categoryService.addCategory(chat, command
                    [1]);
            sendBotMessageService.sendMessage(chat, answer);

        } else if (command.length == 3) {
            String answer = categoryService.addChild(chat, command
                    [1], command[2]);
            sendBotMessageService.sendMessage(chat, answer);
        } else sendBotMessageService.sendMessage(chat,
                "некорректная команда");
    }

}

