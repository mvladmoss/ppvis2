import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class Recipe {

    private char name;
    private char ingredientsList;
    private boolean labelSelected;
    private char recipeDescription;

    public void setName() {
        this.name = argumentParameterName;
    }

    public void setIngredientsList() {
        this.ingredientsList = argumentParameterIngridientList;
    }

    public void setRecipeDescription() {
        this.recipeDescription = argumentParameterRecipeDescription;
    }

    public void setLabelSelected() {
        this.labelSelected = argumentParameterlabelSelected;
    }

    public void displayInformation() {
        super.toString();
    }

}
