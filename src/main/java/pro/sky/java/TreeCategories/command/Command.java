package pro.sky.java.TreeCategories.command;

import com.fasterxml.jackson.core.SerializableString;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface Command  {
    /**
     * Main method, which is executing command logic.
     * @param update provided {@link Update} object with all
     *               the needed data for command
     */
    void execute(Update update);

   }
