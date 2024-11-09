package pro.sky.java.TreeCategories.command;


public enum CommandName {
    START("/start"),
    VIEWTREE("/viewtree"),
    HELP("/help"),
    ADDELEMENT("/addelement"),
    //ADDCHILD("/addElement"),
    REMOVEELEMENT("/removeelement"),
    NO("nocommand");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
