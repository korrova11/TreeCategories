package pro.sky.java.TreeCategories.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pro.sky.java.TreeCategories.command.Command;
import pro.sky.java.TreeCategories.model.MyTree;
import pro.sky.java.TreeCategories.repository.MyTreeRepository;

import java.util.Optional;

@Service
@Transactional
public class MyTreeService implements MyTreeServiceApi{
    private final MyTreeRepository repository;
    private final String ADD = "категория добавлена";
    private final String ADD_IS_PRESENT = "такая категория уже есть!";

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
    @Override
    public String addCategory(Long chat, String name) {
        Optional<MyTree> myTreeOptional = repository.findMyTreeByChatAndName(chat, name);
        if (myTreeOptional.isPresent()) return ADD_IS_PRESENT;
        Optional<MyTree> myTreeOptional1 = repository
                .findMyTreeByChatAndName(chat, "Ваше дерево");
        if (myTreeOptional1.isEmpty()) {
            MyTree myTree = repository.save(new MyTree(chat, "Ваше дерево", 1));
            repository.save(new MyTree(chat, myTree, name, 2));
            return ADD;
        }
        repository.save(new MyTree(chat, myTreeOptional1.get(), name, 2));
        return ADD;
    }

       /* public void add(Node parentNode, Node node) {
            node.setParent(parentNode);
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(node);
            session.getTransaction().commit();
        }*/


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
        if (myTreeOptional.isEmpty()) return "не найдена категория родителя";
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

        str.append(" ".repeat(myTree.getLevel())).append("-").append(myTree.getName()).append('\n');
        if (!myTree.getChildren().isEmpty()) {
            myTree.getChildren().stream().forEach(c ->
                    toStr(c, str));
        }

        return str.toString();

    }

    /**
     * метод находит дерево по владельцу и вызывает метод  toStr
     * @param chat
     * @return возвращает дерево в структурированном виде
     */

    public String toStringMyTreeByUser(Long chat) {
        Optional<MyTree> myTreeOptional = repository.findMyTreeByChatAndName(chat, "Ваше дерево");
        if (myTreeOptional.isPresent())
            return toStr(myTreeOptional.get(), new StringBuilder());
        else return "У Вас еще нет дерева категорий!";
    }

    /**
     * рекурсивный метод удаления категории, начиная с коллекции children
     * @param myTree
     */
    public void removeChildren(MyTree myTree){
        if (myTree.getChildren().isEmpty()) repository.delete(myTree);
        else myTree.getChildren().stream().forEach(m-> removeChildren(m));
    }

    /**
     * метод удаляет категорию со всеми дочерними категориями
     * @param chat
     * @param name
     * @return оповещение о результате выполнения команды
     */
    public String removeMyTreeCategory(Long chat, String name){
       Optional<MyTree>  myTreeOptional = repository.findMyTreeByChatAndName(chat,name);
       if (myTreeOptional.isEmpty()) return "категория не найдена";
       removeChildren(myTreeOptional.get());
       return "категория удалена";
    }

}

