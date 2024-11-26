package pro.sky.java.TreeCategories.command.withArgs;

import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.command.RemoveElementCommand;

import static pro.sky.java.TreeCategories.command.CommandName.REMOVE_ELEMENT;

public class RemoveElementCommandWithArgTest extends AbstractCommandWithArgsTest {
    @Override
    String getCommandName() {
        return REMOVE_ELEMENT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return "категория удалена";
    }

    @Override
    Command getCommand() {
        return new RemoveElementCommand(sendBotMessageService, myTreeService);
    }
}
