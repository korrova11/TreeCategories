package pro.sky.java.TreeCategories.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

public class AddElementCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    protected MyTreeService myTreeService;

    public AddElementCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    //  @Autowired
    // public void setMyTreeService(MyTreeService myTreeService) {
    //     this.myTreeService = myTreeService;
    //  }

    @Override
    public void execute(Update update) {

     //   myTreeService.addCategory(update.getMessage().getText().t)
    }
}
