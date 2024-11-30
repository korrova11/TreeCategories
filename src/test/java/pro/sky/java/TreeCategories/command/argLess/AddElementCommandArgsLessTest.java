package pro.sky.java.TreeCategories.command.argLess;

import org.junit.jupiter.api.DisplayName;
import pro.sky.java.TreeCategories.command.AddElementCommand;
import pro.sky.java.TreeCategories.command.Command;

import static pro.sky.java.TreeCategories.command.CommandName.ADD_ELEMENT;

@DisplayName("Unit-level testing for AddElementCommandArgsLess")
public class AddElementCommandArgsLessTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return ADD_ELEMENT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return "некорректная команда";
    }

    @Override
    Command getCommand() {
        return new AddElementCommand(sendBotMessageService,categoryService);
    }
}
