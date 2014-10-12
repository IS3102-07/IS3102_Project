package EntityManager;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RawIngredientEntity extends ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private String name;
    private String category;
    private String description;

    public RawIngredientEntity() {
        super.setIsDeleted(false);
    }

    public RawIngredientEntity(String SKU, String name, String category, String description, Integer _length, Integer width, Integer height) {
        super(SKU, _length, width, height);
        this.name = name;
        super.setName(name);
        this.category = category;
        this.description = description;
        super.setType("Raw Ingredient");
        super.setIsDeleted(false);
    }

    public Boolean getIsDeleted() {
        return super.getIsDeleted();
    }

    public void setIsDeleted(Boolean isDeleted) {
        super.setIsDeleted(isDeleted);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
