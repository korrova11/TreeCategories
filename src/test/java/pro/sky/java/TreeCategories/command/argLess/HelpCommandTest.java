package pro.sky.java.TreeCategories.command.argLess;

import org.junit.jupiter.api.DisplayName;
import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.command.HelpCommand;

import static pro.sky.java.TreeCategories.command.CommandName.HELP;
import static pro.sky.java.TreeCategories.command.HelpCommand.HELP_MESSAGE;

@DisplayName("Unit-level testing for HelpCommand")
public class HelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}
