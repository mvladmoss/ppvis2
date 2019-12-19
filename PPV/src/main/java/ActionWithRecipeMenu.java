import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ActionWithRecipeMenu {

    private Stage stage;


    public void getRecipeInformation() {
        new Application() {
            @Override
            public void start(Stage primaryStage) {
                AnchorPane pane = new AnchorPane();

                TextField recipeDescription = new TextField();
                recipeDescription.setMinSize(100d, 100d);
                recipeDescription.setText(paramRecipe.getDescription());

                TextField ingredients = new TextField();
                ingredients.setMinSize(100d, 100d);
                ingredients.setText(paramRecipe.getIngredients());

                stage = new Stage();
                stage.setScene(new Scene(pane, 200, 200));
                Button button = new Button("Ага");
                button.setOnAction(event -> stage.close());
                stage.showAndWait();
            }
        };
    }

    public void changeSelectedStatus() {
        paramRecipe.setLabelSelected(paramRecipe.isLabelSelected ? false : true);
    }

    public void edit() {
        new Application() {
            @Override
            public void start(Stage primaryStage) {
                AnchorPane pane = new AnchorPane();

                Button changeStatusButton = new Button("Изменить статус\"Избранное\"");
                changeStatusButton.setOnAction(event -> {
                    changeSelectedStatus();
                });

                TextField selected = new TextField();
                selected.setMinSize(100d, 100d);
                selected.setText(paramRecipe.isLabelSelected());

                TextField nameField = new TextField();
                nameField.setMinSize(100d, 100d);
                nameField.setText("Введите новое название");

                TextField descriptionField = new TextField();
                nameField.setMinSize(100d, 100d);
                nameField.setText("Введите новое описание");

                TextField ingredientsField = new TextField();
                ingredientsField.setText("Перечислите новый список ингридиентов(\"и1\",\"и2\",...,\"иХ\")");
                ingredientsField.setMinSize(100d, 100d);

                Button finishButton = new Button("Готово");
                finishButton.setOnAction(event -> {
                    paramRecipe.setName(nameField.getText());
                    paramRecipe.setDescription(descriptionField.getText());
                    paramRecipe.setIngredients(Arrays.stream(ingredientsField.getText()
                            .split(",")).collect(Collectors.toList()));
                });

                stage = new Stage();
                stage.setScene(new Scene(pane, 200, 200));
                Button button = new Button("Ага");
                button.setOnAction(event -> stage.close());
                stage.showAndWait();
            }
        };
    }

    public void delete() {
        new Application() {
            @Override
            public void start(Stage primaryStage) {
                AnchorPane pane = new AnchorPane();

                Text confirmation = new Text();
                confirmation.setText("Вы уверены, что хотите удалить рецепт?");
                AnchorPane.setLeftAnchor(confirmation, 100d);
                AnchorPane.setTopAnchor(confirmation, 100d);

                stage = new Stage();
                stage.setScene(new Scene(pane, 200, 200));

                Button button = new Button("Подтвердить");
                button.setOnAction(event -> {
                    paramCategort.getRecipes().remove(paramRecipe);
                    stage.close();
                });
                stage.showAndWait();
            }
        };
    }

    public void shutDown() {
        stage.close();
    }

}
