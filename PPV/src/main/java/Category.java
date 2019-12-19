import javafx.scene.control.Alert;
import lombok.Data;

import java.util.List;

@Data
public class Category {

    private char name;
    private List<Recipe> recipes;

    public void createRecipe() {
        recipeCreator.craeteRecipe(paramRecipeName, paramIngridients, paramDescription, paramLabel);
    }

    public void editRecipe() {
        Recipe recipe = recipes.stream()
                .filter(recipe -> recipe.getName().equals(paramRecipeName))
                .findFirst()
                .orElse((recip) -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует рецепта с именем %s", recip.getName()));
                    alert.showAndWait();
                });
        recipe.setIngredientsList(updateParamIngridients);
        recipe.setName(updateParamName);
        recipe.setRecipeDescription(updateParamDescription);
        recipe.setLabelSelected(updateParamLabel);
    }

    public void deleteRecipe() {
        Recipe recipe = recipes.stream()
                .filter(recipe -> recipe.getName().equals(paramRecipeName))
                .findFirst()
                .orElse((recip) -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует рецепта с именем  %s", recip.getName()));
                    alert.showAndWait();
                });
        recipes.remove(recipe);
    }

    public int findRecipe() {
        Recipe recipe = recipes.stream()
                .filter(recipe -> recipe.getName().equals(paramRecipeName))
                .findFirst()
                .orElse((recip) -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует рецепта с именем  %s", recip.getName()));
                    alert.showAndWait();
                });
    }

    public void readInformation() {
        super.toString();
    }

}
