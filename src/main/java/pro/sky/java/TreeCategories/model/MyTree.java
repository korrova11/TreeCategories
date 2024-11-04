package pro.sky.java.TreeCategories.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class MyTree {
    @Id
    @GeneratedValue
    private Long id;

    private String user1;
    private String parent;
    private List<String> child;
    private int level;

    public MyTree(String user, String parent, int level) {
        this.user1 = user;
        this.parent = parent;
        this.level = level;
        this.child = new ArrayList<>();
    }


}
