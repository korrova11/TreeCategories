package pro.sky.java.TreeCategories.service;

import org.springframework.stereotype.Service;
import pro.sky.java.TreeCategories.model.MyTree;
import pro.sky.java.TreeCategories.repository.MyTreeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class MyTreeService {
    private final MyTreeRepository repository;

    public MyTreeService(MyTreeRepository repository) {
        this.repository = repository;
    }

    /**
     * метод добавляет в дерево новый корневой элемент
     *
     * @param user
     * @param parent
     * @return оповещение о выполненном действии
     */
    public String addParent(String user, String parent) {
        ArrayList<MyTree> list = new ArrayList(repository.findMyTreeByUserAndLevel(user, 1));

        if (list.isEmpty() || list.stream().noneMatch(m -> m.getParent().equals(parent))) {
            repository.save(new MyTree(user, parent, 1));
            return "Корневой элемент добавлен";
        } else return "Такой элемент уже есть";
    }

    /**
     * метод добавляет в список child новую запись
     *
     * @param user
     * @param parent
     * @param child
     * @return если запись добавлена - метод возвращает оповещение об этом, если нет -
     * то метод оповещает, что категория не найдена
     */
    public String addChild(String user, String parent, String child) {
        Optional<MyTree> myTreeOptional = repository.findMyTreeByParentAndUser(parent, user);
        if (myTreeOptional.isPresent()) {
            MyTree parent1 = myTreeOptional.get();
            parent1.getChild().add(new MyTree(user, child, parent1.getLevel() + 1));
            repository.save(parent1);
            return "Элемент добавлен";
        } else return "не найдена категория";

    }

    /**
     * метод делает  дерево в структурированном виде в виде строки
     *
     * @param myTree
     * @param str1
     * @return
     */
    public String toStr(MyTree myTree, StringBuilder str1) {

        str1.append(" ".repeat(myTree.getLevel())).append("-").append(myTree.getParent()).append('\n');
        if (!myTree.getChild().isEmpty()) {
            myTree.getChild().stream().forEach(c ->
                    toStr(c, str1));
        }

        return str1.toString();

    }
}
