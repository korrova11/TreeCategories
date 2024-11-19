package pro.sky.java.TreeCategories.service;

import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;

/**
 * Service for sending messages via telegram-bot.
 */
public interface SendBotMessageService {
    /**
     * Send message via telegram-bot.
     * @param chatId provided chatId in which messages would be sent.
     * @param message provided message to be sent.
     */

    void sendMessage(Long chatId,String message);
    public File upload(Document doc) throws TelegramApiException;
    public String getFilePath(Document doc) throws TelegramApiException;

  // public java.io.File uploadFile(Document doc) throws IOException, TelegramApiException;
}
