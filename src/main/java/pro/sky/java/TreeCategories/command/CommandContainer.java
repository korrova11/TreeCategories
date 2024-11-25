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

    public CommandContainer(SendBotMessageService sendBotMessageService, MyTreeService myTreeService) {

        commandMap = new HashMap<>();
        commandMap.put(START.getCommandName(), new StartCommand(sendBotMessageService));
        commandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        commandMap.put(ADD_ELEMENT.getCommandName(), new AddElementCommand(sendBotMessageService, myTreeService));
        commandMap.put(REMOVE_ELEMENT.getCommandName(), new RemoveElementCommand(sendBotMessageService, myTreeService));
        commandMap.put(VIEW_TREE.getCommandName(), new ViewTreeCommand(sendBotMessageService, myTreeService));
        commandMap.put(DOWN_LOAD.getCommandName(), new DownLoadCommand(sendBotMessageService, myTreeService));
        commandMap.put(UPLOAD.getCommandName(), new UploadCommand(sendBotMessageService, myTreeService));
        commandMap.put(NO.getCommandName(), new NoCommand(sendBotMessageService));


        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
