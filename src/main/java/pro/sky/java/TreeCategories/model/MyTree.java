package pro.sky.java.TreeCategories.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity

@NoArgsConstructor
@Data
@Getter
@Setter
public class MyTree {
    @Id
    @GeneratedValue
    private Long id;
    private String user;
    private String parent;
    private List<MyTree> child;
    private int level;

    public MyTree(String user, String parent, int level) {
        this.user = user;
        this.parent = parent;
        this.level = level;
        this.child =new ArrayList<>();
    }


}
