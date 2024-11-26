package pro.sky.java.TreeCategories.command.withArgs;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.sky.java.TreeCategories.bot.TelegramBot;
import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.model.MyTree;
import pro.sky.java.TreeCategories.repository.MyTreeRepository;
import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;
import pro.sky.java.TreeCategories.service.SendBotMessageServiceImpl;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public abstract class AbstractCommandWithArgsTest {
    protected TelegramBot bot = Mockito.mock(TelegramBot.class);
    protected MyTreeRepository repository = Mockito.mock(MyTreeRepository.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(bot);
    protected MyTreeService myTreeService = new MyTreeService(repository);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommandTest() throws TelegramApiException, IOException {

        Long chatId = 1234567824356L;
        String arg1 = "аргумент1";
        MyTree myTree = new MyTree(chatId,arg1,1);
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName()+" " +arg1);
        Mockito.when(repository.save(any(MyTree.class))).thenReturn(myTree);
        Mockito.when( repository.findMyTreeByChatAndName(chatId, arg1))
                .thenReturn(Optional.of(myTree));
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);


        getCommand().execute(update);


        Mockito.verify(bot).execute(sendMessage);
    }
}