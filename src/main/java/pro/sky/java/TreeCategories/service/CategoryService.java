package pro.sky.java.TreeCategories.service;

import jakarta.transaction.Transactional;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pro.sky.java.TreeCategories.model.Category;
import pro.sky.java.TreeCategories.repository.CategoryRepository;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
@Service
@Transactional
public class CategoryService {
    private final CategoryRepository repository;
    private final String ADD = "категория добавлена";
    private final String ADD_IS_PRESENT = "такая категория уже есть!";
    private final String TITLE = "Дерево категорий";


    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * метод добавляет в дерево новый корневой элемент
     *
     * @param chat
     * @param name
     * @return оповещение о выполненном действии
     */

    public String addCategory(Long chat, String name) {
        Optional<Category> categoryOptional = repository.findCategoryByChatAndName(chat, name);
        if (categoryOptional.isPresent()) return ADD_IS_PRESENT;
        Optional<Category> categoryOptional1 = repository
                .findCategoryByChatAndName(chat, TITLE);
        if (categoryOptional1.isEmpty()) {
            Category category = repository.save(new Category(chat, TITLE, 1));
            repository.save(new Category(chat, category, name, 2));
            return ADD;
        }
        repository.save(new Category(chat, categoryOptional1.get(), name, 2));
        return ADD;
    }


    /**
     * метод добавляет нового потомка к уже имеющейся категории
     *
     * @param chat
     * @param parent
     * @param child
     * @return если запись добавлена - метод возвращает оповещение об этом, если нет -
     * то метод оповещает, что категория не найдена или такая категория уже есть
     */
    public String addChild(Long chat, String parent, String child) {
        Optional<Category> categoryOptional = repository.findCategoryByChatAndName(chat, parent);
        Optional<Category> categoryOptional1 = repository.findCategoryByChatAndName(chat, child);
        if (categoryOptional1.isPresent()) return ADD_IS_PRESENT;
        if (categoryOptional.isEmpty()) return "не найдена категория родителя " + parent;
        Category categoryParent = categoryOptional.get();
        Category category = new Category(chat, categoryParent, child, categoryParent.getLevel() + 1);
        repository.save(category);
        return ADD;

    }

    /**
     * метод делает  дерево в структурированном виде
     *
     * @param category
     * @param str
     * @return метод возвращает структуру дерева
     */

    public String toStr(Category category, StringBuilder str) {

        str.append("  ".repeat(category.getLevel())).append("-")
                .append(category.getName()).append('\n');
        if (!category.getChildren().isEmpty()) {
            category.getChildren().stream().forEach(c ->
                    toStr(c, str));
        }

        return str.toString();

    }

    /**
     * рекурмивный метод,  создает строку в excel таблице
     *
     * @param sheet
     * @param category
     */

    private void builderTree(XSSFSheet sheet, Category category) {

        XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);

        int level = category.getLevel();
        for (int j = 0; j < level; j++) {
            row.createCell(j).setCellValue("_");
        }
        row.createCell(level).setCellValue(category.getName());
        if (!category.getChildren().isEmpty()) {
            category.getChildren().stream().forEach(c ->
                    builderTree(sheet, c));
        }
    }

    /**
     * метод находит дерево по владельцу и вызывает метод  toStr
     *
     * @param chat
     * @return возвращает дерево в структурированном виде
     */

    public String toStringCategoryByUser(Long chat) {
        Optional<Category> categoryOptional = repository.findCategoryByChatAndName(chat, TITLE);
        if (categoryOptional.isPresent())
            return toStr(categoryOptional.get(), new StringBuilder());
        else return "У Вас еще нет дерева категорий!";
    }

    /**
     * метод удаляет категорию со всеми дочерними категориями
     *
     * @param chat
     * @param name
     * @return оповещение о результате выполнения команды
     */
    public String removeCategory(Long chat, String name) {
        Optional<Category> categoryOptional = repository.findCategoryByChatAndName(chat, name);
        if (categoryOptional.isEmpty()) return "категория " + name + " не найдена!";

        repository.delete(categoryOptional.get());
        return "категория удалена";
    }

    /**
     * запись данных в БД из exel таблицы с ячейками :
     * parent, name
     *
     * @param file
     * @param chat
     * @throws IOException
     */
    public String uploadExel(File file, Long chat) {

        XSSFWorkbook myExcelBook = null;
        try {
            myExcelBook = new XSSFWorkbook(new FileInputStream(String.valueOf(file)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        XSSFSheet sheet = myExcelBook.getSheetAt(0);
        String str = "";
        Iterator rowIter = sheet.rowIterator();
        while (rowIter.hasNext()) {
            XSSFRow row = (XSSFRow) rowIter.next();
            String cell0 = row.getCell(0).getRichStringCellValue().getString();
            String cell1 = row.getCell(1).getRichStringCellValue().getString();
            if (cell0.equals("_")) {
                str = addCategory(chat, cell1);

            } else str = addChild(chat, cell0, cell1);

        }
        return str;

    }

    /**
     * Метод создает excel документ дерева категорий
     *
     * @param chat
     * @param file
     * @return
     * @throws IOException
     */
    public File createExcel(Long chat, File file) throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        Optional<Category> categoryOptional = repository.findCategoryByChatAndName(chat, TITLE);
        if (categoryOptional.isPresent()) {
            XSSFSheet sheet = book.createSheet("Дерево категорий");
            Category category = categoryOptional.get();
            builderTree(sheet, category);

            book.write(new FileOutputStream(file));

            return file;
        } else return file;

    }

    public boolean isCategory(Long chat) {
        Optional<Category> categoryOptional = repository.findCategoryByChatAndName(chat, TITLE);
        return categoryOptional.isPresent();
    }
}


