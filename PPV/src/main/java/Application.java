import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    public void start(Stage primaryStage) {
        MainMenu mainMenu = new MainMenu(primaryStage);
        CookBook cookBook = new CookBook();
        CategoryCreator categoryCreator = new CategoryCreator();
        RecipeCreator recipeCreator = new RecipeCreator();
    }

}
