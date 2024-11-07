package pro.sky.java.TreeCategories.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Data

public class MyTree implements Serializable {
    /*@Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String userChat;
    @NotNull
    private String name;

    private int level;
    @OneToMany(mappedBy = "name", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<MyTree> children = new ArrayList<>();*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "FK_PARENT_ID"))
    private MyTree parent;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<MyTree> children = new ArrayList<>();
    private int level;

    public MyTree(Long chat, MyTree parent, String name, int level) {
        this.chat = chat;
        this.parent = parent;
        this.name = name;
        this.level = level;
        // this.children = new ArrayList<>();
    }

    public MyTree(Long chat, String name, int level) {
        this.chat = chat;
        this.name = name;
        this.level = level;

    }


}
