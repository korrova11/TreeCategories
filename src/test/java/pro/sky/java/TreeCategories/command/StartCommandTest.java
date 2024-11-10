package pro.sky.java.TreeCategories.command;

import org.junit.jupiter.api.DisplayName;

import static pro.sky.java.TreeCategories.command.CommandName.START;
import static pro.sky.java.TreeCategories.command.StartCommand.START_MESSAGE;

@DisplayName("Unit-level testing for StartCommand")
class StartCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService);
    }
}