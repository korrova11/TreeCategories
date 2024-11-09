package pro.sky.java.TreeCategories.command;

import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

import java.util.HashMap;
import java.util.Map;

import static pro.sky.java.TreeCategories.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {

    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {

        commandMap = new HashMap<>();
        commandMap.put(START.getCommandName(), new StartCommand(sendBotMessageService));
        commandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        //commandMap.put(ADDCHILD.getCommandName(), new AddChildCommand(sendBotMessageService));
        commandMap.put(ADDELEMENT.getCommandName(), new AddElementCommand(sendBotMessageService));
        commandMap.put(REMOVEELEMENT.getCommandName(), new RemoveElementCommand(sendBotMessageService));
        commandMap.put(VIEWTREE.getCommandName(), new ViewTreeCommand(sendBotMessageService));
        commandMap.put(NO.getCommandName(), new NoCommand(sendBotMessageService));


        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier,unknownCommand);
    }

}
