import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ActionWithCategoryMenu {

    private Stage stage;

    public void getRecipesInCategory() {
        Category category = cookBook.getCategories().stream()
                .filter(category -> category.getName().equals(paramName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", paramName));
                    alert.showAndWait();
                });
        new Application() {

            @Override
            public void start(Stage primaryStage) {
                AnchorPane pane = new AnchorPane();
                TextField categoryRecipes = new TextField();
                categoryRecipes.setMinSize(100d, 100d);
                AnchorPane.setLeftAnchor(categoryRecipes, 100d);
                AnchorPane.setTopAnchor(categoryRecipes, 100d);
                categoryRecipes.setText(category.getRecipes().toString());

                stage = new Stage();
                stage.setScene(new Scene(pane, 200, 200));

                Button button = new Button("Ага");
                button.setOnAction(event -> stage.close());
                stage.showAndWait();
            }
        };
    }

    public void edit() {
        Category category = new CookBook().getCategories().stream()
                .filter(category -> category.getName().equals(paramName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", paramName));
                    alert.showAndWait();
                });

        new Application() {
            @Override
            public void start(Stage primaryStage) throws Exception {
                AnchorPane pane = new AnchorPane();

                TextField newCategoryName = new TextField();
                newCategoryName.setText("Введите новое имя");
                newCategoryName.setMinSize(100d, 100d);
                AnchorPane.setLeftAnchor(newCategoryName, 100d);
                AnchorPane.setTopAnchor(newCategoryName, 100d);

                stage = new Stage();
                stage.setScene(new Scene(pane, 200, 200));

                Button button = new Button("Подтвердить");
                button.setOnAction(event -> {
                    category.setName(newCategoryName.getText());
                    stage.close();
                });
                stage.showAndWait();
            }
        };
    }

    public void delete() {
        Category category = cookBook.getCategories().stream()
                .filter(category -> category.getName().equals(paramName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", paramName));
                    alert.showAndWait();
                });

        new Application() {
            @Override
            public void start(Stage primaryStage) {
                AnchorPane pane = new AnchorPane();

                Text confirmation = new Text();
                confirmation.setText("Вы уверены, что хотите удалить категорию?");
                AnchorPane.setLeftAnchor(confirmation, 100d);
                AnchorPane.setTopAnchor(confirmation, 100d);

                stage = new Stage();
                stage.setScene(new Scene(pane, 200, 200));

                Button button = new Button("Подтвердить");
                button.setOnAction(event -> {
                    cookBook.getCategories().remove(category);
                    stage.close();
                });
                stage.showAndWait();
            }
        };
    }

    public void chooseCategory() {
        Category category = cookBook.getCategories().stream()
                .filter(category -> category.getName().equals(paramName))
                .findFirst()
                .orElse(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(String.format("Не существует категории с именем %s", paramName));
                    alert.showAndWait();
                });
        cookBook.setWorkCategory(category);
    }

    public void shutDown() {
        stage.close();
    }

}
