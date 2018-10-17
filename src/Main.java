import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static Stage stage =new Stage();
    static ArrayList<Utilisateur> list= new ArrayList();
    static ArrayList<String>listtext=new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage = stage;
        try{
            ObjectInput entré = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream("save.cvs"))) {
            };

            listtext= (ArrayList)entré.readObject();
            entré.close();


        }   catch (IOException e){
        }
        catch (ClassNotFoundException ya){
        }

        primaryStage=stage;

        Scene accueil = EcranAccueille.create();
        primaryStage.setWidth(500);
        primaryStage.setHeight(700);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setScene((accueil));
        primaryStage.show();

    }
    public static void setScene(Scene scene){
        stage.setScene(scene);
    }




}
