package pro.sky.java.TreeCategories.command;


import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

import java.io.IOException;


public class UploadCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    private final MyTreeService myTreeService;


    public UploadCommand(SendBotMessageService sendBotMessageService, MyTreeService myTreeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.myTreeService = myTreeService;
    }

    @Override
    public void execute(Update update) throws TelegramApiException, IOException {

        Long chat = update.getMessage().getChatId();
        Document document = update.getMessage().getDocument();
        if (!update.getMessage().hasDocument()) {
            sendBotMessageService.sendMessage(chat, "прикрепите документ excel");
        }

        if (document.getMimeType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {

            try {
                sendBotMessageService.sendMessage(chat, myTreeService.uploadExel(sendBotMessageService.upload(document), chat));
                //  sendBotMessageService.sendMessage(chat,sendBotMessageService.getFilePath(document));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

        } else {
            sendBotMessageService.sendMessage(chat, "документ должен быть формата excel");
        }
    }

}





