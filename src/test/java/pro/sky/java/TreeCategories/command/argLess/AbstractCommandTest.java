package pro.sky.java.TreeCategories.command.argLess;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.sky.java.TreeCategories.bot.TelegramBot;
import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.repository.CategoryRepository;
import pro.sky.java.TreeCategories.service.CategoryService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;
import pro.sky.java.TreeCategories.service.SendBotMessageServiceImpl;

import java.io.IOException;


abstract class AbstractCommandTest {

    protected TelegramBot bot = Mockito.mock(TelegramBot.class);
    protected CategoryRepository repository = Mockito.mock(CategoryRepository.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(bot);
    protected  CategoryService categoryService = new CategoryService(repository);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommandTest() throws TelegramApiException, IOException {

        Long chatId = 1234567824356L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);


        getCommand().execute(update);


        Mockito.verify(bot).execute(sendMessage);
    }
}
