package pro.sky.java.TreeCategories.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import pro.sky.java.TreeCategories.service.SendBotMessageService;

/**
 * Unknown {@link Command}.
 */
@Component
public class UnknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE = "Не понимаю вас \uD83D\uDE1F, напишите /help, чтобы узнать что я понимаю.";

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(),
                UNKNOWN_MESSAGE);
    }
}
