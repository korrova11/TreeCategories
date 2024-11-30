package pro.sky.java.TreeCategories.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.CategoryService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;
/**
 * ViewTree {@link Command}.
 */
@Component
public class ViewTreeCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    private final CategoryService categoryService;

    public ViewTreeCommand(SendBotMessageService sendBotMessageService, CategoryService categoryService) {
        this.sendBotMessageService = sendBotMessageService;
        this.categoryService =categoryService;
    }

    @Override
    public void execute(Update update) {
        Long chat = update.getMessage().getChatId();
        String answer =categoryService.toStringCategoryByUser(chat);
        sendBotMessageService.sendMessage(chat, answer);
    }
}
