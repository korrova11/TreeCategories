package pro.sky.java.TreeCategories.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import pro.sky.java.TreeCategories.model.MyTree;
import pro.sky.java.TreeCategories.repository.MyTreeRepository;

import java.util.ArrayList;
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
     * @param chat
     * @param name
     * @param Level
     * @return оповещение о выполненном действии
     */
    public String addCategory(Long chat, String name, int Level) {
        Optional<MyTree> myTreeOptional = repository.findMyTreeByChatAndName(chat, name);

        if (myTreeOptional.isEmpty()) {
            repository.save(new MyTree(chat, name, Level));

            return "Корневой элемент добавлен";
        } else return "Такой элемент уже есть";
       /* public void add(Node parentNode, Node node) {
            node.setParent(parentNode);
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(node);
            session.getTransaction().commit();
        }*/
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
        if (myTreeOptional1.isPresent()) return "такая категория уже есть!";
        if (myTreeOptional.isEmpty()) return "не найдена категория родителя";
        MyTree myTreeParent = myTreeOptional.get();
        MyTree myTree = new MyTree(chat, myTreeParent, child, myTreeParent.getLevel() + 1);
        repository.save(myTree);
        return "категория добавлена";

    }

/**
 * метод делает  дерево в структурированном виде

 *
 * @param myTree
 * @param str1
 * @return метод возвращает структуру дерева
 */

    public String toStr(MyTree myTree, StringBuilder str1) {

        str1.append(" ".repeat(myTree.getLevel())).append("-").append(myTree.getName()).append('\n');
        if (!myTree.getChildren().isEmpty()) {
            myTree.getChildren().stream().forEach(c ->
                    toStr(c, str1));
        }

        return str1.toString();

    }
    public String toStringTreebyUser(Long chat){
        MyTree myTree = repository.findMyTreeByChatAndName(chat,"Ваше дерево").get();
        return toStr(myTree,new StringBuilder());
    }
}

