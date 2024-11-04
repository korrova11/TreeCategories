package pro.sky.java.TreeCategories.command;


public enum CommandName {
    START("/start"),
    VIEW_TREE("/viewTree"),
    HELP("/help"),
    ADD_ELEMENT("/addElement <название элемента>"),
    ADD_CHILD("/addElement <родительский элемент> <дочерний элемент>"),
    REMOVE_ELEMENT("/removeElement <название элемента>"),
    NO("");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
