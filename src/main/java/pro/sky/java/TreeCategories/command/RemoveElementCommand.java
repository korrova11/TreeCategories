package pro.sky.java.TreeCategories.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.CategoryService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

/**
 * RemoveElement {@link Command}.
 */
@Component
public class RemoveElementCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final CategoryService categoryService;

    public RemoveElementCommand(SendBotMessageService sendBotMessageService, CategoryService categoryService) {
        this.sendBotMessageService = sendBotMessageService;
        this.categoryService = categoryService;
    }

    @Override
    public void execute(Update update) {
        String message = update.getMessage().getText().trim();

        String[] command = message.split(" ");
        Long chat = update.getMessage().getChatId();
        String answer;
        if (command.length == 1) {answer = "Не введена категория!";}
        else {
        answer = categoryService.removeCategory(chat, command[1]);}
        sendBotMessageService.sendMessage(chat, answer);
    }
}
