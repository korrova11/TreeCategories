package pro.sky.java.TreeCategories.command.withBadArgs;

import pro.sky.java.TreeCategories.command.AddElementCommand;
import pro.sky.java.TreeCategories.command.Command;

import static pro.sky.java.TreeCategories.command.CommandName.ADD_ELEMENT;

public class AddElementCommandWithBadArgsTest extends AbstractCommandWithBadArgsTest {
    @Override
    String getCommandName() {
        return ADD_ELEMENT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return "не найдена категория родителя аргумент1";
    }

    @Override
    Command getCommand() {
        return new AddElementCommand(sendBotMessageService, myTreeService);
    }

}
