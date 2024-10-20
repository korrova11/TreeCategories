package pro.sky.java.TreeCategories.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommandName {
    START("/start"),
    VIEW_TREE("/viewTree"),
    HELP("/help"),
    ADD_ELEMENT("/addElement <название элемента>"),
    ADD_CHILD("/addElement <родительский элемент> <дочерний элемент>"),
    REMOVE_ELEMENT("/removeElement <название элемента>"),
    NO("");

    private final String commandName;
}
