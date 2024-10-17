package pro.sky.java.TreeCategories.service;

import org.springframework.stereotype.Service;
import pro.sky.java.TreeCategories.model.MyTree;
import pro.sky.java.TreeCategories.repository.MyTreeRepository;

import java.util.ArrayList;

@Service
public class MyTreeService {
    private final MyTreeRepository repository;

    public MyTreeService(MyTreeRepository repository) {
        this.repository = repository;
    }

    public MyTree addParent(String user, String parent) {
        return repository.save(new MyTree(user, parent, 1));
    }
    public String addChild(String user,String parent,String child){
        ArrayList<MyTree> list = new ArrayList<>(repository.findMyTreeByParentAndUser(parent,user));
        if (!list.isEmpty()){
             repository.save(new MyTree(user,parent,child,list.get(0).getLevel()));
            return "Элемент добавлен";
        }
        else return  "не найдена категория";

    }
}
