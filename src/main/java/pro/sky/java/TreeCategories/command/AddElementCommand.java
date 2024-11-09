package pro.sky.java.TreeCategories.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.MyTreeService;
import pro.sky.java.TreeCategories.service.MyTreeServiceApi;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

public class AddElementCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    protected MyTreeService myTreeService;

    public AddElementCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

   @Autowired
    public void setMyTreeService(MyTreeService myTreeService) {
        this.myTreeService = myTreeService;
    }

    @Override
    public void execute(Update update) {
        String message = update.getMessage().getText().trim();

            String []  command = message.split(" ");
            if (command.length==2){
                String answer = myTreeService.addCategory(update.getMessage().getChatId(),command
                [1]);
                sendBotMessageService.sendMessage(update.getMessage().getChatId(), answer);}
                else sendBotMessageService.sendMessage(update.getMessage().getChatId(),
                    "не введена категория!");
            }


    }

