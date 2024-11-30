package pro.sky.java.TreeCategories.command;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import pro.sky.java.TreeCategories.service.SendBotMessageService;

/**
 * Start {@link Command}.
 */
@Component
@Scope("prototype")
public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = "Тебя приветствует бот, который поможет тебе создать дерево категорий";


    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;

    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), START_MESSAGE);
    }
}
