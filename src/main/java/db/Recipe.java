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
    private Integer portions;
    private String description;
    private List<Ingredient> ingredients;
    private RecipeCategory category;
    private CuisineNationality cuisineNationality;
    private Post post;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable =false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable =false)
    public Integer getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(Integer prepareTime) {
        this.prepareTime = prepareTime;
    }

    @Column(nullable =false)
    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Column(nullable =false)
    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    @Column(nullable =false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="ingredient_id",referencedColumnName = "id")
    )
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    public RecipeCategory getCategory() {
        return category;
    }

    public void setCategory(RecipeCategory category) {
        this.category = category;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cuisine_nationality_id")
    public CuisineNationality getCuisineNationality() {
        return cuisineNationality;
    }

    public void setCuisineNationality(CuisineNationality cuisineNationality) {
        this.cuisineNationality = cuisineNationality;
    }

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "recipe")
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
