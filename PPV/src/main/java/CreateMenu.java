import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreateMenu {

    private final AnchorPane root;
    private final Stage stage;

    public CreateMenu() {
        root = new AnchorPane();
        Text creationMenu = new Text();
        creationMenu.setText("Создание");
        creationMenu.setFont(new Font(20));
        creationMenu.setWrappingWidth(200);
        creationMenu.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(creationMenu, 5d);
        AnchorPane.setLeftAnchor(creationMenu, 350d);

        Line line = new Line();
        line.setStartX(800);
        line.setStartY(30);
        line.setEndX(0);
        line.setEndY(30);
        line.setFill(Color.BLACK);

        Button recipeButton = new Button("Рецепт");
        recipeButton.setMinHeight(30);
        recipeButton.setMinWidth(200);
        AnchorPane.setLeftAnchor(recipeButton, 100d);
        AnchorPane.setTopAnchor(recipeButton, 70d);
        recipeButton.setOnAction(event -> customizeCreationFieldsForRecipe());

        Button categoryButton = new Button("Категория");
        categoryButton.setMinHeight(30);
        categoryButton.setMinWidth(200);
        AnchorPane.setLeftAnchor(categoryButton, 450d);
        AnchorPane.setTopAnchor(categoryButton, 70d);
        categoryButton.setOnAction(event -> customizeCreationFieldsForCategory());

        root.getChildren().addAll(creationMenu, line, recipeButton, categoryButton);
        Scene scene = new Scene(root,800,600);
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void customizeCreationFieldsForRecipe() {
        TextField nameField = new TextField();
        nameField.setText("Наберите название");
        nameField.setMinSize(300d,10d);
        AnchorPane.setLeftAnchor(nameField, 250d);
        AnchorPane.setTopAnchor(nameField, 150d);

        TextField descriptionField = new TextField();
        descriptionField.setText("Наберите описание");
        descriptionField.setMinSize(300d,10d);
        AnchorPane.setLeftAnchor(descriptionField, 250d);
        AnchorPane.setTopAnchor(descriptionField, 200d);

        TextField ingredientsField = new TextField();
        ingredientsField.setText("Перечислите ингридиенты(\"и1\",\"и2\",...,\"иХ\")");
        ingredientsField.setMinSize(300d,10d);
        AnchorPane.setLeftAnchor(ingredientsField, 250d);
        AnchorPane.setTopAnchor(ingredientsField, 250d);

        ChoiceBox<String> categorySelect = new ChoiceBox<>(
                FXCollections.observableArrayList());
        categorySelect.setValue("Текущая категория:\"выбранная категория\"");
        categorySelect.setMinSize(300d, 10d);
        AnchorPane.setLeftAnchor(categorySelect, 250d);
        AnchorPane.setTopAnchor(categorySelect, 300d);

        Button createButton = new Button("Создать");
        createButton.setMinHeight(30);
        createButton.setMinWidth(200);
        AnchorPane.setLeftAnchor(createButton, 300d);
        AnchorPane.setTopAnchor(createButton, 350d);

        root.getChildren().addAll(nameField, descriptionField, ingredientsField, categorySelect, createButton);

        createButton.setOnAction(event -> {
            String categoryName = categorySelect.getSelectionModel().getSelectedItem();
            Category category = paramCookBook.getCategories().stream()
                    .filter(cat -> cat.getName().equals(categoryName))
                    .findFirst()
                    .orElse(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(String.format("Не существует категории с именем %s", nameField.getText()));
                        alert.showAndWait();
                    });
            List<String> ingredients = Arrays.stream(ingredientsField.getText().split(","))
                    .collect(Collectors.toList());
            Recipe recipe = new Recipe(nameField.getText(), ingredients, label, descriptionField.getText());
            category.getRecipes().add(recipe);
        });

        shutdown();
    }

    public void customizeCreationFieldsForCategory() {
        TextField nameField = new TextField();
        nameField.setText("Наберите название");
        nameField.setMinSize(300d,10d);
        AnchorPane.setLeftAnchor(nameField, 250d);
        AnchorPane.setTopAnchor(nameField, 150d);

        Button createButton = new Button("Создать");
        createButton.setMinHeight(30);
        createButton.setMinWidth(200);
        AnchorPane.setLeftAnchor(createButton, 300d);
        AnchorPane.setTopAnchor(createButton, 200d);

        createButton.setOnAction(event -> {
            Category category = new Category();
            category.setName(nameField.getText());
            category.setRecipes(new ArrayList<>());
            paramCookBook.getCategories().add(category);
            shutdown();
        });
    }

    public void shutdown() {
        stage.close();
    }
}
