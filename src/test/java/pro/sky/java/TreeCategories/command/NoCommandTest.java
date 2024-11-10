package pro.sky.java.TreeCategories.command;

import org.junit.jupiter.api.DisplayName;
import pro.sky.java.TreeCategories.command.AbstractCommandTest;
import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.command.NoCommand;

import static pro.sky.java.TreeCategories.command.CommandName.NO;
import static pro.sky.java.TreeCategories.command.NoCommand.NO_MESSAGE;

@DisplayName("Unit-level testing for NoCommand")
public class NoCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}
