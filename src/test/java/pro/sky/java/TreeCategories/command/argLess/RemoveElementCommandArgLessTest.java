package pro.sky.java.TreeCategories.command.argLess;

import org.junit.jupiter.api.DisplayName;
import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.command.RemoveElementCommand;

import static pro.sky.java.TreeCategories.command.CommandName.REMOVE_ELEMENT;

@DisplayName("Unit-level testing for RemoveElementArgLessCommand")
public class RemoveElementCommandArgLessTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return REMOVE_ELEMENT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return "Не введена категория!";
    }

    @Override
    Command getCommand() {
        return new RemoveElementCommand(sendBotMessageService,myTreeService);
    }
}
