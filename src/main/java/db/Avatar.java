package db;

import javax.persistence.*;
import java.util.Base64;

@Entity
public class Avatar {
    private int id;
    private String name;
    private byte[] data;
    private Recipe recipe;
    private String base64Image;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Column(nullable = false,columnDefinition="MEDIUMBLOB ")
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @OneToOne(mappedBy = "avatar")
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Transient
    public String getBase64Image() {
        base64Image= Base64.getEncoder().encodeToString(this.data);
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
