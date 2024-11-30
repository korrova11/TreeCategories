package pro.sky.java.TreeCategories.command;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.sky.java.TreeCategories.service.CategoryService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

import java.io.File;
import java.io.IOException;
@Component
public class DownLoadCommand  implements Command{
    private final SendBotMessageService sendBotMessageService;

    private final CategoryService categoryService;

    public DownLoadCommand(SendBotMessageService sendBotMessageService, CategoryService categoryService) {
        this.sendBotMessageService = sendBotMessageService;
        this.categoryService = categoryService;
    }

    @Override
    public void execute(Update update) throws IOException, TelegramApiException {
        Long chat = update.getMessage().getChatId();
        if (!categoryService.isCategory(chat)){
            sendBotMessageService.sendMessage(chat,"У вас еще нет дерева категорий!");}
        else{
        File file = File.createTempFile("treeCategories", ".xlsx");
       sendBotMessageService.sendDocument(chat,
               categoryService.createExcel(chat,file));}
    }
}
