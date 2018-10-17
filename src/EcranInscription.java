
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import javax.xml.bind.DatatypeConverter;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class EcranInscription extends Scene {
    public EcranInscription(Group root) {
        super(root);
    }

    public static Scene create() {
        return new EcranInscription(affichage());
    }

    public static Group affichage() {

        Label prenomtexte = new Label();
        prenomtexte.setText("Prénom");
        prenomtexte.setTranslateX(175);
        TextField prenom = new TextField();
        prenom.setTranslateX(175);
        prenom.setTranslateY(20);
        prenom.setPromptText("Veuillez entrer votre prénom");

        Label nomtexte = new Label();
        nomtexte.setText("Nom de famille");
        nomtexte.setTranslateX(175);
        nomtexte.setTranslateY(50);
        TextField nom = new TextField();
        nom.setTranslateX(175);
        nom.setTranslateY(70);
        nom.setPromptText("Nom de famille");

        Label usertexte = new Label();
        usertexte.setText("Nom d'utilisateur");
        usertexte.setTranslateX(175);
        usertexte.setTranslateY(100);
        TextField user = new TextField();
        user.setTranslateX(175);
        user.setTranslateY(120);
        user.setPromptText("Nom d'utilisateur");

        Label motdepasse = new Label();
        motdepasse.setText("Mot de passe");
        motdepasse.setTranslateX(175);
        motdepasse.setTranslateY(150);
        PasswordField pass = new PasswordField();
        pass.setTranslateX(175);
        pass.setTranslateY(170);
        pass.setPromptText("Mot de passe");

        Label conp = new Label();
        conp.setText("Confirmer le mot de passe");
        conp.setTranslateX(175);
        conp.setTranslateY(200);
        PasswordField pass2 = new PasswordField();
        pass2.setTranslateX(175);
        pass2.setTranslateY(220);
        pass2.setPromptText("Mot de passe");

        Label genre = new Label();
        genre.setText("Genre");
        genre.setTranslateX(175);
        genre.setTranslateY(250);

        ToggleGroup groupe = new ToggleGroup();

        RadioButton sexeM = new RadioButton("Homme");
        sexeM.setToggleGroup(groupe);
        sexeM.setTranslateX(175);
        sexeM.setTranslateY(270);

        RadioButton sexeF = new RadioButton("Femme");
        sexeF.setToggleGroup(groupe);
        sexeF.setTranslateX(240);
        sexeF.setTranslateY(270);

        RadioButton sexess = new RadioButton("Autre");
        sexess.setToggleGroup(groupe);
        sexess.setTranslateX(305);
        sexess.setTranslateY(270);

        Label age = new Label();
        age.setText("Âge");
        age.setTranslateX(175);
        age.setTranslateY(300);

        Spinner spinner = new Spinner(18, 100, 18);
        spinner.setTranslateX(175);
        spinner.setTranslateY(320);

        CheckBox cond = new CheckBox("J'accepte les conditions d'utilisation");
        cond.setTranslateX(175);
        cond.setTranslateY(350);

        Label faich = new Label();
        faich.setVisible(false);
        faich.setTextFill(Color.RED);
        faich.setTranslateX(175);
        faich.setTranslateY(400);

        Button effacer = new Button("Effacer");
        effacer.setTranslateY(375);
        effacer.setTranslateX(225);
        effacer.setOnAction(event -> {
            prenom.textProperty().set("");
            nom.textProperty().set("");
            user.textProperty().set("");
            groupe.selectToggle(null);
            pass.textProperty().set("");
            pass2.textProperty().set("");
            cond.setSelected(false);
            spinner.getValueFactory().setValue(18);


        });

        Button retour = new Button("Retour");
        retour.setTranslateY(375);
        retour.setTranslateX(285);
        retour.setOnAction(event -> {
            Main.setScene(EcranAccueille.create());
        });



        Button inscrire = new Button("S'inscrire");
        inscrire.setTranslateY(375);
        inscrire.setTranslateX(155);
        inscrire.setOnAction(event -> {
            if (prenom.textProperty().isEmpty().get()) {
                faich.setVisible(true);
                faich.setText("Prénom manquant");
            }
            else if (nom.textProperty().isEmpty().get()) {
                faich.setVisible(true);
                faich.setText("nom manquant");
            }
            else if (user.textProperty().isEmpty().get()) {
                faich.setVisible(true);
                faich.setText("Nom d'utilisateur manquant");
            }
            else if(groupe.getSelectedToggle()==null)
            {
                faich.setVisible(true);
                faich.setText("Cochez un genre");
            }
            else if(!cond.isSelected()){
                faich.setVisible(true);
                faich.setText("Acceptez les conditions");
            }
            else if(!pass.textProperty().get().equals(pass2.textProperty().get())){
                faich.setVisible(true);
                faich.setText("Les mots de passe ne concordent pas");
            }
            else{
                Utilisateur nouvo= new Utilisateur();
                nouvo.setPrenom(prenom.textProperty().get());
                nouvo.setNom(nom.textProperty().get());
                nouvo.setUsername(user.textProperty().get());
                try{
                nouvo.setMotdepasse(hash(pass.textProperty().get()));}
                catch (NoSuchAlgorithmException io){}
                if (groupe.getSelectedToggle()==groupe.getToggles().get(0)){
                    nouvo.setGenre("M");
                }
                else if (groupe.getSelectedToggle()==groupe.getToggles().get(1)){
                    nouvo.setGenre("F");
                }
                else {
                    nouvo.setGenre("A");
                }
                nouvo.setAge(spinner.getValue().toString());

               ArrayList<String> titt=new ArrayList<>();
                titt.add(nouvo.getPrenom());
                titt.add(nouvo.getNom());
                titt.add(nouvo.getUsername());
                try { titt.add(hash(pass.textProperty().get())); }
                catch (NoSuchAlgorithmException e) { }
                titt.add(nouvo.getGenre());
                titt.add(nouvo.getAge());
                String csv=titt.stream().collect(Collectors.joining(","));
                Main.listtext.add(csv);
                titt.clear();

                Log log=new Log();
                for(int i=0;i<Main.listtext.size();i++){
                log.writeData(Main.listtext.get(i));}

                log.saveData();
                Main.setScene(EcranAccueille.create());

            }
        });


        return new Group(retour,effacer,faich, inscrire, prenom, nom, prenomtexte, nomtexte, user, usertexte, pass, motdepasse, conp, pass2, genre, sexeF, sexeM, sexess, age, spinner, cond);


    }
    public static String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();

        return sha256;
    }
}
