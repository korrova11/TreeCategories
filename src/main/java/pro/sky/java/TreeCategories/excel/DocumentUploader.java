package pro.sky.java.TreeCategories.excel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Document;
import pro.sky.java.TreeCategories.bot.TelegramBot;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Component
@RequiredArgsConstructor
public class DocumentUploader {

    private final TelegramBot bot;

    //-----------------API START-----------------

    /**
     * Принимает документ {@link Document} из апдейта, сохраняет его во временный файл и возвращает
     * @param doc {@link Document} из апдейта
     * @return загруженный файл {@link File}
     * @author ezuykow
     */
    public File upload(Document doc) {
        try {
            org.telegram.telegrambots.meta.api.objects.File file = bot.execute(new GetFile(doc.getFileId()));
            return downloadFile(new URL(file.getFilePath()), newTempFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //-----------------API END-------------------

    private File downloadFile(URL fileURL, File tempFile) throws IOException {
        try (ReadableByteChannel rbc = Channels.newChannel(fileURL.openStream());
             FileOutputStream fos = new FileOutputStream(tempFile))
        {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            return tempFile;
        }
    }

    private File newTempFile() throws IOException {
        return File.createTempFile("tempCategories", ".xlsx");
    }
}
