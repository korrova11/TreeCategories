package pro.sky.java.TreeCategories.command.argLess;

import org.junit.jupiter.api.DisplayName;
import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.command.UnknownCommand;

import static pro.sky.java.TreeCategories.command.UnknownCommand.UNKNOWN_MESSAGE;

@DisplayName("Unit-level testing for UnknownCommand")
public class UnknownCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return "/fdgdfgdfgdbd";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}
