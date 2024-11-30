package pro.sky.java.TreeCategories.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public enum CommandName {
    START("/start"),
    VIEW_TREE("/viewtree"),
    HELP("/help"),
    ADD_ELEMENT("/addelement"),
    REMOVE_ELEMENT("/removeelement"),
    NO("nocommand"),
    DOWN_LOAD("/download"),
    UPLOAD("/upload");


    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }


    public String getCommandName() {
        return commandName;
    }
}
