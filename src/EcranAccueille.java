

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class EcranAccueille extends Scene {

    public static Scene create(){
        return new EcranAccueille(affichage());
    }
    public static Group affichage(){

        boolean afficher=false;

        Label username=new Label();
        username.setText("Nom d'utilisateur");
        username.setTranslateX(175);
        username.setTranslateY(150);

        Label faich = new Label();
        faich.setText("La connexion a échoué");
        faich.setVisible(false);
        faich.setTextFill(Color.RED);
        faich.setTranslateX(175);
        faich.setTranslateY(400);

        Label motdepasse=new Label();
        motdepasse.setText("Mot de passe");
        motdepasse.setTranslateX(175);
        motdepasse.setTranslateY(200);
        TextField usernametext = new TextField();
        usernametext.setTranslateX(175);
        usernametext.setTranslateY(170);
        usernametext.setPromptText("Veuillez entrer votre nom d'utilisateur");
        PasswordField motdepassetext = new PasswordField();
        motdepassetext.setTranslateX(175);
        motdepassetext.setTranslateY(220);
        motdepassetext.setPromptText("Veuillez entrer votre mot de passe");

        Button connecter= new Button("Se connecter");
        connecter.setTranslateY(250);
        connecter.setTranslateX(150);
        connecter.setOnAction(event -> {
          for(int i=0;i<Main.listtext.size();i++){

            String [] acomparer= Main.listtext.get(i).split(",");
            if(usernametext.textProperty().get().equals(acomparer[2]))
            {
                try {String conf= (hash(motdepassetext.textProperty().get()));

                    if(conf.equals(acomparer[3])){

                        Main.setScene(Ecranloading.create());
                    }

                }
                catch (NoSuchAlgorithmException e) { }


            }
            if(usernametext.textProperty().get()!=(acomparer[2])){
                i++;
            }






        }faich.setVisible(true);});


        Button inscrire= new Button("S'inscrire");
        inscrire.setTranslateY(250);
        inscrire.setTranslateX(250);
        inscrire.setOnAction(event -> {
            Main.setScene(EcranInscription.create());
    });


        return new Group(username,usernametext,motdepasse,motdepassetext,connecter,inscrire,faich);
    }
    private EcranAccueille(Group root) {
        super(root);
    }
    public static String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();

        return sha256;}
}
