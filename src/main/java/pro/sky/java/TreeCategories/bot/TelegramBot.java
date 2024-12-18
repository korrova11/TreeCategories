package pro.sky.java.TreeCategories.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.springframework.beans.factory.annotation.Value;
import pro.sky.java.TreeCategories.command.CommandContainer;
import pro.sky.java.TreeCategories.service.CategoryService;
import pro.sky.java.TreeCategories.service.SendBotMessageServiceImpl;


import static pro.sky.java.TreeCategories.command.CommandName.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;
    private final CommandContainer commandContainer;

    @Autowired
    public TelegramBot(CategoryService categoryService) {

        this.commandContainer =
                new CommandContainer(new SendBotMessageServiceImpl(this),
                        categoryService);
    }


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().hasDocument()) {

            commandContainer.retrieveCommand(UPLOAD.getCommandName()).execute(update);

        }
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();


                commandContainer.retrieveCommand(commandIdentifier).execute(update);

            } else {

                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);

            }
        }
    }


    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
