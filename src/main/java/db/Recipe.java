package db;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Recipe {
    private int id;
    private String title;
    private Integer prepareTime;
    private Integer cookingTime;
    private Integer servings;
    private String description;
    private List<Ingredient> ingredients;
    private List<Instruction> instructions;
    private RecipeCategory category;
    private Post post;
    private byte[] image;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public Integer getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(Integer prepareTime) {
        this.prepareTime = prepareTime;
    }

    @Column(nullable = false)
    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Column(nullable = false)
    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer portions) {
        this.servings = portions;
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "recipe",cascade = {CascadeType.ALL})
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @OneToMany(mappedBy = "recipe",cascade = {CascadeType.ALL})
    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public RecipeCategory getCategory() {
        return category;
    }

    public void setCategory(RecipeCategory category) {
        this.category = category;
    }

    @OneToOne(mappedBy = "recipe", fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Column
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
