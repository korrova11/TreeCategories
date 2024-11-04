package pro.sky.java.TreeCategories.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

import static pro.sky.java.TreeCategories.command.CommandName.*;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("<b>Доступные команды</b>\n"

                    + "<b>Работа с ботом </b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - добавить корневой элемент\n"
                    + "%s - добавить дочерний элемент к существующему элементу\n"
                    + "%s - удалить элемент со всеми дочерними элементами\n"
                    + "%s - отобразить дерево\n"
                    + "%s - получить список комманд в работе с ботом\n",
            START.getCommandName(),ADD_ELEMENT.getCommandName(),ADD_CHILD.getCommandName(),
            REMOVE_ELEMENT.getCommandName(),VIEW_TREE.getCommandName(),HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}