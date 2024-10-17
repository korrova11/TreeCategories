package pro.sky.java.TreeCategories.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@NoArgsConstructor
@Data
public class MyTree {
    @Id
    @GeneratedValue
    private Long id;
    private String user;
    private String parent;
    private String child;
    private int level;

    public MyTree(String user, String parent, int level) {
        this.user = user;
        this.parent = parent;
        this.level = level;
    }

    public MyTree(String user, String parent, String child, int level) {
        this.user = user;
        this.parent = parent;
        this.child = child;
        this.level = level;
    }
}
