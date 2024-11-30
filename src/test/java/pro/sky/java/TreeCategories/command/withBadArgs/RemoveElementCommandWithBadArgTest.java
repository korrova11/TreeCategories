package pro.sky.java.TreeCategories.command.withBadArgs;

import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.command.RemoveElementCommand;

import static pro.sky.java.TreeCategories.command.CommandName.REMOVE_ELEMENT;

public class RemoveElementCommandWithBadArgTest extends AbstractCommandWithBadArgsTest {
    @Override
    String getCommandName() {
        return REMOVE_ELEMENT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return "категория аргумент1 не найдена!";
    }

    @Override
    Command getCommand() {
        return new RemoveElementCommand(sendBotMessageService, categoryService);
    }
}
