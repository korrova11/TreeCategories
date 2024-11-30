package pro.sky.java.TreeCategories.command.withArgs;

import pro.sky.java.TreeCategories.command.AddElementCommand;
import pro.sky.java.TreeCategories.command.Command;

import static pro.sky.java.TreeCategories.command.CommandName.ADD_ELEMENT;

public class AddElementCommandWithArgsTest extends AbstractCommandWithArgsTest{
    @Override
    String getCommandName() {
        return ADD_ELEMENT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return "категория добавлена";
    }

    @Override
    Command getCommand() {
        return new AddElementCommand(sendBotMessageService,categoryService);
    }

}
