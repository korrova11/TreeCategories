package pro.sky.java.TreeCategories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.TreeCategories.model.MyTree;

import java.util.Collection;
import java.util.List;

public interface MyTreeRepository  extends JpaRepository<MyTree, Long> {
    Collection<MyTree> findMyTreeByParentAndUser(String parent,String User);
    }

