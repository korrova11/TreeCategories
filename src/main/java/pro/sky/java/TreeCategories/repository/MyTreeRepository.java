package pro.sky.java.TreeCategories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.TreeCategories.model.MyTree;

import java.util.Optional;

public interface MyTreeRepository  extends JpaRepository<MyTree, Long> {

    Optional<MyTree> findMyTreeByChatAndName(Long chat, String name);

}
