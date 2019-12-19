import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class SearchMenu {

    private final AnchorPane root;
    private final Stage stage;

    public SearchMenu() {
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
        recipeButton.setOnAction(event -> customizeFieldsForRecipeSearch());

        Button categoryButton = new Button("Категория");
        categoryButton.setMinHeight(30);
        categoryButton.setMinWidth(200);
        AnchorPane.setLeftAnchor(categoryButton, 450d);
        AnchorPane.setTopAnchor(categoryButton, 70d);
        categoryButton.setOnAction(event -> customizeFieldsForCategorySearch());

        root.getChildren().addAll(creationMenu, line, recipeButton, categoryButton);
        Scene scene = new Scene(root, 800, 600);
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void customizeFieldsForCategorySearch() {
        TextField nameField = new TextField();
        nameField.setText("Наберите название");
        nameField.setMinSize(300d, 10d);
        AnchorPane.setLeftAnchor(nameField, 250d);
        AnchorPane.setTopAnchor(nameField, 150d);

        Button findButton = new Button("Найти");
        findButton.setMinHeight(30);
        findButton.setMinWidth(200);
        AnchorPane.setLeftAnchor(findButton, 50d);
        AnchorPane.setTopAnchor(findButton, 500d);

        Button generalSearchButton = new Button("Общий поиск");
        generalSearchButton.setMinHeight(30);
        generalSearchButton.setMinWidth(200);
        AnchorPane.setLeftAnchor(generalSearchButton, 50d);
        AnchorPane.setTopAnchor(generalSearchButton, 500d);
        generalSearchButton.setOnAction(event -> {
            Category category = paramCookBook.getCategories().stream()
                    .filter(cat -> cat.getName().equals(nameField.getText()))
                    .findFirst()
                    .orElse(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(String.format("Не существует категории с именем %s", nameField.getText()));
                        alert.showAndWait();
                    });
            startPointSearch(category);
            stage.close();
        });

        TextField selectedRecipes = new TextField("Избранные рецепты");
        selectedRecipes.setMinSize(300d, 10d);
        AnchorPane.setLeftAnchor(selectedRecipes, 250d);
        AnchorPane.setTopAnchor(selectedRecipes, 500d);

        TextField existItems = new TextField("Список имеющегося");
        existItems.setMinSize(300d, 10d);
        AnchorPane.setLeftAnchor(existItems, 350d);
        AnchorPane.setTopAnchor(existItems, 500d);

        findButton.setOnAction(event -> {
            Category category = paramCookBook.getCategories().stream()
                    .filter(cat -> cat.getName().equals(nameField.getText()))
                    .findFirst()
                    .orElse(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(String.format("Не существует категории с именем %s", nameField.getText()));
                        alert.showAndWait();
                    });

            List<Recipe> selectRecipes = category.getRecipes().stream()
                    .filter(Recipe::isLabelSelected)
                    .collect(Collectors.toList());
            selectedRecipes.setText(selectRecipes.toString());
            existItems.setText(category.getRecipes().toString());
        });

    }

    public void customizeFieldsForRecipeSearch() {
        TextField nameField = new TextField();
        nameField.setText("Наберите название");
        nameField.setMinSize(300d, 10d);
        AnchorPane.setLeftAnchor(nameField, 250d);
        AnchorPane.setTopAnchor(nameField, 150d);

        TextField categoryField = new TextField();
        nameField.setText("Наберите название категории");
        nameField.setMinSize(300d, 10d);
        AnchorPane.setLeftAnchor(nameField, 350d);
        AnchorPane.setTopAnchor(nameField, 150d);

        Button generalSearchButton = new Button("Общий поиск");
        generalSearchButton.setMinHeight(30);
        generalSearchButton.setMinWidth(200);
        AnchorPane.setLeftAnchor(generalSearchButton, 50d);
        AnchorPane.setTopAnchor(generalSearchButton, 500d);
        generalSearchButton.setOnAction(event -> {
            Recipe recipe = paramCookBook.getCategories().stream()
                    .filter(cat -> cat.getName().equals(categoryField.getText()))
                    .findFirst().getRecipes().stream()
                    .filter(recipe -> recipe.getName().equals(nameField.getText()))
                    .orElse(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(String.format("Не существует рецепта с именем %s", nameField.getText()));
                        alert.showAndWait();
                    });
            startPointSearch(recipe);
            stage.close();
        });
    }

    public void startPointSearch(Category category) {
        new javafx.application.Application() {
            private AnchorPane pane;

            @Override
            public void start(Stage primaryStage) {
                pane = new AnchorPane();
                Text creationMenu = new Text();
                creationMenu.setText(String.valueOf(category.getName()));
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

                Button editButton = new Button("Редактировать");
                editButton.setOnAction(event -> {
                    new ActionWithCategoryMenu().edit();
                    shutDown();
                });

                Button recipesButton = new Button("Просмотр рецептов");
                editButton.setOnAction(event -> {
                    displayList();
                    shutDown();

                });

                Button chooseButton = new Button("Выбрать как основную");
                editButton.setOnAction(event -> {
                    new ActionWithCategoryMenu().chooseCategory();
                    shutDown();
                });

                Button deleteButton = new Button("Удалить");
                editButton.setOnAction(event -> {
                    new ActionWithCategoryMenu().delete();
                    shutDown();
                });

                pane.getChildren().addAll(editButton, recipesButton, chooseButton, deleteButton);
                Scene scene = new Scene(root, 800, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        };
    }

    public void startPointSearch(Recipe recipe) {
        new javafx.application.Application() {
            private AnchorPane pane;

            @Override
            public void start(Stage primaryStage) {
                pane = new AnchorPane();
                Text creationMenu = new Text();
                creationMenu.setText(String.valueOf(recipe.getName()));
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

                Button getButton = new Button("Получение информации");
                getButton.setOnAction(event -> {
                    new ActionWithRecipeMenu().getRecipeInformation();
                    shutDown();
                });

                Button editButton = new Button("Редактировать");
                editButton.setOnAction(event -> {
                    new ActionWithRecipeMenu().edit();
                    shutDown();
                });

                Button deleteButton = new Button("Удалить");
                editButton.setOnAction(event -> {
                    new ActionWithRecipeMenu().delete();
                    shutDown();
                });

                pane.getChildren().addAll(getButton, editButton, deleteButton);
                Scene scene = new Scene(root, 800, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        };
    }

    public void displayList() {
        new ActionWithRecipeMenu().getRecipeInformation();
    }

    public void displaySelectRecepts() {
        new Application() {
            @Override
            public void start(Stage primaryStage) throws Exception {
                AnchorPane pane = new AnchorPane();

                Text selectedRecipesField = new Text();
                selectedRecipesField.setFont(new Font(20));
                selectedRecipesField.setWrappingWidth(200);
                selectedRecipesField.setFill(Color.BLACK);

                List<Recipe> selectedRecipes = paramCategory.getRecipes().stream()
                        .filter(Recipe::isLabelSelected)
                        .collect(Collectors.toList());

                selectedRecipesField.setText(selectedRecipesField.getText());
                pane.getChildren().addAll(selectedRecipes);
                Scene scene = new Scene(root, 800, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        };
    }

    public void shutDown() {
        stage.close();
    }
}
