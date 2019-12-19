import javafx.scene.control.Alert;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CookBook {

    private char name;
    private char workCategory;
    private List<Category> categories;

    public void createCategories() {
        new CategoryCreator().create();
    }

    public void editCategory() {
        Category category = categories.stream()
                .filter(cat -> cat.getName().equals(paramName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", paramName));
                    alert.showAndWait();
                });
        category.setName(newName);
        category.setRecipes(newRecipes);
    }

    public void deleteCategory() {
        Category category = categories.stream()
                .filter(cat -> cat.getName().equals(paramName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", paramName));
                    alert.showAndWait();
                });
        categories.remove(category);
    }

    public void displayCategory() {
        Category category = categories.stream()
                .filter(cat -> cat.getName().equals(paramName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", paramName));
                    alert.showAndWait();
                });
        return category;
    }

    public void selectedList() {
        Category category = categories.stream()
                .filter(recipe -> recipe.getName().equals(categoryParamName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", categoryParamName));
                    alert.showAndWait();
                });
        return category.getRecipes().stream()
                .filter(Recipe::isLabelSelected)
                .collect(Collectors.toList());
    }

    public void findRecipe() {
        Category category = categories.stream()
                .filter(cat -> cat.getName().equals(categoryParamName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", categoryParamName));
                    alert.showAndWait();
                });
        return category.getRecipes().stream()
                .filter(cat -> cat.getName().equals(recipeParamName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует рецепта с именем %s", recipeParamName));
                    alert.showAndWait();
                });
    }

    public void findCategory() {
        return categories.stream()
                .filter(cat -> cat.getName().equals(paramName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", paramName));
                    alert.showAndWait();
                });
    }

    public void chooseWorkCategoty() {
        Category category = categories.stream()
                .filter(cat -> cat.getName().equals(paramName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", paramName));
                    alert.showAndWait();
                });
        workCategory = category.getName();
    }

}
