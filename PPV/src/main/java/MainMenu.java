import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu {

    private final static int START_WINDOW_WIDTH = 800;
    private final static int START_WINDOW_HEIGHT = 600;
    private Stage primaryStage;

    public MainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;
        AnchorPane anchorPane = new AnchorPane();

        Text mainMenu = new Text();
        mainMenu.setText("Главное Меню");
        mainMenu.setFont(new Font(20));
        mainMenu.setWrappingWidth(200);
        mainMenu.setFill(Color.BLACK);

        Line line = new Line();
        line.setStartX(800);
        line.setStartY(30);
        line.setEndX(0);
        line.setEndY(30);
        line.setFill(Color.BLACK);

        Button createButton = new Button("Создать");
        createButton.setMinHeight(50);
        createButton.setMinWidth(100);
        AnchorPane.setTopAnchor(createButton, 140d);
        AnchorPane.setLeftAnchor(createButton, 140d);
        createButton.setOnAction(event -> create());

        Button findButton = new Button("Найти");
        findButton.setMinHeight(50);
        findButton.setMinWidth(100);
        AnchorPane.setTopAnchor(findButton, 140d);
        AnchorPane.setLeftAnchor(findButton, 540d);
        findButton.setOnAction(event -> find());

        Button exitButton = new Button("exit");
        exitButton.setMinHeight(50);
        exitButton.setMinWidth(100);
        AnchorPane.setTopAnchor(exitButton, 360d);
        AnchorPane.setLeftAnchor(exitButton, 350d);
        exitButton.setOnAction(event -> exit());

        anchorPane.getChildren().addAll(mainMenu, line, createButton, findButton, exitButton);
        Scene scene = new Scene(anchorPane, START_WINDOW_WIDTH, START_WINDOW_HEIGHT);
        AnchorPane.setTopAnchor(mainMenu, 5d);
        AnchorPane.setLeftAnchor(mainMenu, 350d);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void create() {
        new CreateMenu();
    }

    public void exit() {
        primaryStage.close();
    }

    public void find() {
        new SearchMenu();
    }


}
