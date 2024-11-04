package pro.sky.java.TreeCategories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.TreeCategories.model.MyTree;

import java.util.List;
import java.util.Optional;

public interface MyTreeRepository  extends JpaRepository<MyTree, Long> {
    Optional<MyTree> findMyTreeByUser1AndParent(String user,String parent);
    List<MyTree> findMyTreeByUser1AndLevel(String user,int level);
    List<MyTree> findMyTreeByUser1(String user);
    }

