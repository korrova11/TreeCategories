package pro.sky.java.TreeCategories.command;


public enum CommandName {
    START("/start"),
    VIEWTREE("/viewTree"),
    HELP("/help"),
    ADDELEMENT("/addelement"),
    //ADDCHILD("/addElement"),
    REMOVEELEMENT("/remove"),
    NO("nocommand");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
