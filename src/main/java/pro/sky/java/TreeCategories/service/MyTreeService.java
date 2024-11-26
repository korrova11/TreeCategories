package pro.sky.java.TreeCategories.service;

import jakarta.transaction.Transactional;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;


import pro.sky.java.TreeCategories.model.MyTree;
import pro.sky.java.TreeCategories.repository.MyTreeRepository;

import java.io.*;
import java.util.Iterator;
import java.util.Optional;

@Service
@Transactional
public class MyTreeService {
    private final MyTreeRepository repository;
    private final String ADD = "категория добавлена";
    private final String ADD_IS_PRESENT = "такая категория уже есть!";
    private final  String TITLE = "Дерево категорий";


    public MyTreeService(MyTreeRepository repository) {
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
        Optional<MyTree> myTreeOptional = repository.findMyTreeByChatAndName(chat, name);
        if (myTreeOptional.isPresent()) return ADD_IS_PRESENT;
        Optional<MyTree> myTreeOptional1 = repository
                .findMyTreeByChatAndName(chat, TITLE);
        if (myTreeOptional1.isEmpty()) {
            MyTree myTree = repository.save(new MyTree(chat, TITLE, 1));
            repository.save(new MyTree(chat, myTree, name, 2));
            return ADD;
        }
        repository.save(new MyTree(chat, myTreeOptional1.get(), name, 2));
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
        Optional<MyTree> myTreeOptional = repository.findMyTreeByChatAndName(chat, parent);
        Optional<MyTree> myTreeOptional1 = repository.findMyTreeByChatAndName(chat, child);
        if (myTreeOptional1.isPresent()) return ADD_IS_PRESENT;
        if (myTreeOptional.isEmpty()) return "не найдена категория родителя " + parent;
        MyTree myTreeParent = myTreeOptional.get();
        MyTree myTree = new MyTree(chat, myTreeParent, child, myTreeParent.getLevel() + 1);
        repository.save(myTree);
        return ADD;

    }

    /**
     * метод делает  дерево в структурированном виде
     *
     * @param myTree
     * @param str
     * @return метод возвращает структуру дерева
     */

    public String toStr(MyTree myTree, StringBuilder str) {

        str.append("  ".repeat(myTree.getLevel())).append("-")
                .append(myTree.getName()).append('\n');
        if (!myTree.getChildren().isEmpty()) {
            myTree.getChildren().stream().forEach(c ->
                    toStr(c, str));
        }

        return str.toString();

    }

    /**
     * рекурмивный метод,  создает строку в excel таблице
     * @param sheet
     * @param myTree
     */

    private void builderTree(XSSFSheet sheet, MyTree myTree) {

        XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);

        int level = myTree.getLevel();
        for (int j = 0; j < level; j++) {
            row.createCell(j).setCellValue("_");
        }
        row.createCell(level).setCellValue(myTree.getName());
        if (!myTree.getChildren().isEmpty()) {
            myTree.getChildren().stream().forEach(c ->
                    builderTree(sheet, c));
        }
    }

    /**
     * метод находит дерево по владельцу и вызывает метод  toStr
     *
     * @param chat
     * @return возвращает дерево в структурированном виде
     */

    public String toStringMyTreeByUser(Long chat) {
        Optional<MyTree> myTreeOptional = repository.findMyTreeByChatAndName(chat, TITLE);
        if (myTreeOptional.isPresent())
            return toStr(myTreeOptional.get(), new StringBuilder());
        else return "У Вас еще нет дерева категорий!";
    }

    /**
     * метод удаляет категорию со всеми дочерними категориями
     *
     * @param chat
     * @param name
     * @return оповещение о результате выполнения команды
     */
    public String removeMyTreeCategory(Long chat, String name) {
        Optional<MyTree> myTreeOptional = repository.findMyTreeByChatAndName(chat, name);
        if (myTreeOptional.isEmpty()) return "категория " + name + " не найдена!";

        repository.delete(myTreeOptional.get());
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
        Optional<MyTree> myTreeOptional = repository.findMyTreeByChatAndName(chat, TITLE);
        if (myTreeOptional.isPresent()) {
            XSSFSheet sheet = book.createSheet("Дерево категорий");
            MyTree myTree = myTreeOptional.get();
            builderTree(sheet, myTree);

            book.write(new FileOutputStream(file));

            return file;
        } else return file;

    }
    public boolean isMyTree(Long chat){
        Optional<MyTree> myTreeOptional = repository.findMyTreeByChatAndName(chat,TITLE);
        return myTreeOptional.isPresent();
    }
}




