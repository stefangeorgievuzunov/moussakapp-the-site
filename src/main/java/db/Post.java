package db;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {
    private int id;
    private User author;
    private Recipe recipe;
    private List<Comment> commnets;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id",referencedColumnName = "id")
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post")
    public List<Comment> getCommnets() {
        return commnets;
    }

    public void setCommnets(List<Comment> commnets) {
        this.commnets = commnets;
    }
}
