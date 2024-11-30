package pro.sky.java.TreeCategories.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

import static pro.sky.java.TreeCategories.command.CommandName.*;

/**
 * Help {@link Command}.
 */
@Component
public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE =
         """
                    ✨<b>Доcтупные команды</b>✨

                    /start - начать работу со мной
                    /addElement <em>название_элемента</em> - добавить корневой элемент
                    /addElement <em>родительский_элемент дочерний_элемент</em> - добавить дочерний элемент к существующему элементу
                    /removeElement <em>название_ элемента</em> - удалить элемент со всеми дочерними элементами
                    /viewTree - отобразить дерево
                    /upload - загрузить и сохранить в БД таблицу excel дерева категорий
                    /download - предоставляет таблицу excel дерева категорий
                    /help - получить список комманд в работе с ботом
                    """;

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), HELP_MESSAGE);
    }
}