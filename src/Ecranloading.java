
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;


public class Ecranloading extends Scene {
    public Ecranloading(Group root) {
        super(root);
    }

    public static Scene create(){
        return new Ecranloading(affichage());
    }
    public static Group affichage(){

        ProgressIndicator pi=new ProgressIndicator();

        Label load=new Label("Chargement du contenu");
        load.setTranslateY(255);
        load.setTranslateX(175);

        pi.setTranslateX(205);
        pi.setTranslateY(175);
        pi.setProgress(pi.getProgress()+0);


        return new Group(pi,load);}


}