package pro.sky.java.TreeCategories.command;


public enum CommandName {
    START("/start"),
    VIEW_TREE("/viewtree"),
    HELP("/help"),
    ADD_ELEMENT("/addelement"),
    REMOVE_ELEMENT("/removeelement"),
    NO("nocommand"),
    UPLOAD("/upload");


    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
