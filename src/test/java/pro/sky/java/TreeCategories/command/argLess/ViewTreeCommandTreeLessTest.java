package pro.sky.java.TreeCategories.command.argLess;

import org.junit.jupiter.api.DisplayName;
import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.command.ViewTreeCommand;

import static pro.sky.java.TreeCategories.command.CommandName.VIEW_TREE;

@DisplayName("Unit-level testing for ViewTreeCommandTreeLess")
public class ViewTreeCommandTreeLessTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return VIEW_TREE.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return "У Вас еще нет дерева категорий!";
    }

    @Override
    Command getCommand() {
        return new ViewTreeCommand(sendBotMessageService,categoryService);
    }
}
