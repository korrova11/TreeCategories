package pro.sky.java.TreeCategories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.TreeCategories.model.MyTree;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MyTreeRepository  extends JpaRepository<MyTree, Long> {
    Optional<MyTree> findMyTreeByParentAndUser(String parent, String User);
    List<MyTree> findMyTreeByUserAndLevel(String user,int level);
    }

