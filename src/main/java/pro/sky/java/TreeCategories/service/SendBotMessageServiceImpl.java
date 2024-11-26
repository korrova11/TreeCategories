package pro.sky.java.TreeCategories.service;

import jakarta.transaction.Transactional;

import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import pro.sky.java.TreeCategories.bot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


/**
 * Implementation of {@link SendBotMessageService} interface.
 */
@Service
@Transactional
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final TelegramBot bot;

    @Autowired
    public SendBotMessageServiceImpl(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void sendDocument(Long chatId, File file) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId.toString());
        sendDocument.setDocument(new InputFile(file));
        try {
            bot.execute(sendDocument);
        } catch (TelegramApiException e) {

            e.printStackTrace();
        }


    }


    @Override
    public File upload(Document doc) {

        String uploadedFileId = doc.getFileId();

        try {
            org.telegram.telegrambots.meta.api.objects.File file =
                    bot.execute(new GetFile(uploadedFileId));
            return downloadFile(new URL("https://api.telegram.org/file/bot" +
                            bot.getBotToken() + "/" + file.getFilePath()),
                    File.createTempFile("tempTree", ".xlsx"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private File downloadFile(URL fileURL, File tempFile) throws IOException {
        try (ReadableByteChannel rbc = Channels.newChannel(fileURL.openStream());
             FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            return tempFile;
        }
    }


    @Override
    public String getFilePath(Document doc) throws TelegramApiException {
        String uploadedFileId = doc.getFileId();
        org.telegram.telegrambots.meta.api.objects.File file = bot.execute(new GetFile(uploadedFileId));
        return file.getFilePath();
    }
}

