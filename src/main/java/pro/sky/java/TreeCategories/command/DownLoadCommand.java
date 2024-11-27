package pro.sky.java.TreeCategories.command;


import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

import java.io.File;
import java.io.IOException;

public class DownLoadCommand  implements Command{
    private final SendBotMessageService sendBotMessageService;

    private final MyTreeService myTreeService;

    public DownLoadCommand(SendBotMessageService sendBotMessageService, MyTreeService myTreeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.myTreeService = myTreeService;
    }

    @Override
    public void execute(Update update) throws IOException, TelegramApiException {
        Long chat = update.getMessage().getChatId();
        if (!myTreeService.isMyTree(chat)){
            sendBotMessageService.sendMessage(chat,"У вас еще нет дерева категорий!");}
        else{
        File file = File.createTempFile("treeCategories", ".xlsx");
       sendBotMessageService.sendDocument(chat,
               myTreeService.createExcel(chat,file));}
    }
}
