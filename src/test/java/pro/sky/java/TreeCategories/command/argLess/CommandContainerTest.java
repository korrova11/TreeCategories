package pro.sky.java.TreeCategories.command.argLess;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.command.CommandContainer;
import pro.sky.java.TreeCategories.command.CommandName;
import pro.sky.java.TreeCategories.command.UnknownCommand;
import pro.sky.java.TreeCategories.service.CategoryService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

import java.util.Arrays;

@DisplayName("Unit-level testing for CommandContainer")
class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        commandContainer = new CommandContainer(sendBotMessageService, categoryService);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {

        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {

        String unknownCommand = "/fgjhdfgdfg";

        Command command = commandContainer.retrieveCommand(unknownCommand);

        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}
