import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

/**
 * Maxim Saffarini -19980925
 * Emil Alic - 19980129
 */

public class Main extends Application {
    RadioButton radio1 = new RadioButton("Namn");
    RadioButton radio2 = new RadioButton("Värde");
    private ArrayList<Vardesak> vardeSaker = new ArrayList<>();
    private ArrayList<Aktie> aktier = new ArrayList<>();
    private ComboBox<String> boxen;
    private Stage window;
    private Scene scene1, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;


        BorderPane root = new BorderPane();
        ///////// Top panel ////////////
        BorderPane uppe = new BorderPane();
        Label vardesaker = new Label("Värdesaker");
        vardesaker.setAlignment(Pos.CENTER);
        uppe.setCenter(vardesaker);
        root.setTop(uppe);
        vardesaker.setFont(Font.font("Verdana", FontWeight.BOLD, 30));


        ///////// Left panel ////////////
        VBox left = new VBox();
        Label sortering = new Label("Sortering");
        sortering.setAlignment(Pos.CENTER);
        ToggleGroup group = new ToggleGroup();
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio1.setSelected(true);
        left.getChildren().addAll(sortering, radio1, radio2);
        root.setLeft(left);
        sortering.setFont(Font.font("Verdana", FontWeight.BOLD, 17));


        ///////// Bottom panel ////////////
        BorderPane nere = new BorderPane();
        FlowPane flow = new FlowPane();
        Label ny = new Label("Ny: ");
        Button visa = new Button("Visa");
        Button borskrasch = new Button("Börskrasch");
        visa.setAlignment(Pos.CENTER);
        ny.setAlignment(Pos.CENTER);

        ObservableList<String> options = FXCollections.observableArrayList(
                "Smycke", "Aktie", "Apparat");
        boxen = new ComboBox<>(options);
        boxen.setPromptText("Välj värdesak:");
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(boxen);
        boxen.setOnAction(new BoxHanterare());
        root.setBottom(vBox);

        flow.getChildren().addAll(ny, vBox, visa, borskrasch);
        flow.setHgap(15);
        flow.setAlignment(Pos.CENTER);
        nere.setCenter(flow);
        root.setBottom(nere);
        ny.setFont(Font.font("Verdana", 20));


        ///////// center ///////////
        TextArea display = new TextArea();
        //display.appendText("Hello ");
        display.setEditable(false);
        root.setCenter(display);
        scene1 = new Scene(root, 600, 400);

        visa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                display.clear();
                sorteraIgen();
                for (Vardesak sak : vardeSaker)
                    display.appendText(sak.toString() + "\n");

            }
        });




        borskrasch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Vardesak x : vardeSaker) {
                    if(x instanceof Aktie)
                        ((Aktie)x).setKurs(0.0);
                }
            }
        });

        visaAllaRegistreradeSaker();
        window.setScene(scene1);
        window.setTitle("Sakregister");
        window.show();
    }


    private void sortNamn() {
        Collections.sort(vardeSaker, new Comparator<Vardesak>() {
            public int compare(Vardesak result1, Vardesak result2) {
                return result1.getNamn().compareTo(result2.getNamn());
            }
        });
    }

    private void sortVarde() {
        Collections.sort(vardeSaker, new Comparator<Vardesak>() {
            public int compare(Vardesak result1, Vardesak result2) {
                return Double.compare(result1.getRealVarde(), (result2.getRealVarde()));
            }
        });
    }

    private void sorteraIgen() {
        if (radio1.isSelected()) {
            sortNamn();
        } else if (radio2.isSelected()) {
            sortVarde();
        }
    }


    private void NyttSmycke() {
        try {
            NyttSmycke newSmycke = new NyttSmycke();
            Optional<ButtonType> buttonType = newSmycke.showAndWait();
            if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                String namn = newSmycke.getNamn();
                int antalStenar = newSmycke.getStenar();
                if (namn.trim().matches("-?\\d+(\\.\\d+)?"))
                    throw new NumberFormatException();
                if (namn.trim().isEmpty()) {
                    Alert msg = new Alert(Alert.AlertType.ERROR, "Tomt namn!");
                    msg.showAndWait();
                    return;
                } else if (antalStenar < 0) {
                    Alert msg = new Alert(Alert.AlertType.ERROR, "Antal stenar kan inte vara negativ");
                    msg.showAndWait();
                    return;
                }
                boolean material = newSmycke.getGold();
                Smycke sakSmycke = new Smycke(namn, antalStenar, material);
                vardeSaker.add(sakSmycke);
                sorteraIgen();
            }
        } catch (NumberFormatException e) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setContentText("Fel inmatning!");
            msg.showAndWait();
        }
    }


    private void NyApparat() {
        try {
            NyApparat newApparat = new NyApparat();
            Optional<ButtonType> buttonType1 = newApparat.showAndWait();
            if (buttonType1.isPresent() && buttonType1.get() == ButtonType.OK) {
                String namn = newApparat.getNamn();
                double inkopspris = newApparat.getPris();
                if (namn.trim().matches("-?\\d+(\\.\\d+)?"))
                    throw new NumberFormatException();
                if (namn.trim().isEmpty()) {
                    Alert msg = new Alert(Alert.AlertType.ERROR, "Tomt namn!");
                    msg.showAndWait();
                    return;
                } else if (inkopspris < 0) {
                    Alert msg = new Alert(Alert.AlertType.ERROR, "Kan inte vara negativ!");
                    msg.showAndWait();
                    return;
                }
                int slitage = newApparat.getSlitage();
                Apparat sakApparat = new Apparat(namn, inkopspris, slitage);
                vardeSaker.add(sakApparat);
                sorteraIgen();
            }
        } catch (NumberFormatException e) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setContentText("Fel inmatning!");
            msg.showAndWait();
        }
    }


    private void NyAktie() {
        try {
            NyAktie newAktie = new NyAktie();
            Optional<ButtonType> buttonType2 = newAktie.showAndWait();
            if (buttonType2.isPresent() && buttonType2.get() == ButtonType.OK) {
                String namn = newAktie.getNamn();
                int antal = newAktie.getAntal();
                if (namn.trim().matches("-?\\d+(\\.\\d+)?"))
                    throw new NumberFormatException();
                if (namn.trim().isEmpty()) {
                    Alert msg = new Alert(Alert.AlertType.ERROR, "Tomt namn!");
                    msg.showAndWait();
                    return;
                } else if (antal < 0) {
                    Alert msg = new Alert(Alert.AlertType.ERROR, "Kan inte vara negativ!");
                    msg.showAndWait();
                    return;
                }
                double kurs = newAktie.getPris();
                Aktie sakAktie = new Aktie(namn, antal, kurs);
                vardeSaker.add(sakAktie);
                aktier.add(sakAktie);
                sorteraIgen();
            }
        } catch (NumberFormatException e) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setContentText("Fel inmatning!");
            msg.showAndWait();
        }
    }


    private void visaAllaRegistreradeSaker() {
        vardeSaker.add(new Smycke("Swarovski", 10, true));
        vardeSaker.add(new Smycke("Pandora", 20, false));
        vardeSaker.add(new Smycke("Emmma Israelsson", 12, true));
        vardeSaker.add(new Smycke("Hermes", 1, false));


        vardeSaker.add(new Aktie("Apple", 100, 2000));
        aktier.add(new Aktie("Apple", 100, 2000));

        vardeSaker.add(new Aktie("Spotify", 50, 1200));
        aktier.add(new Aktie("Spotify", 100, 2000));

        vardeSaker.add(new Aktie("Samsung", 180, 100));
        aktier.add(new Aktie("Samsung", 100, 2000));


        vardeSaker.add(new Apparat("MacBook", 25000, 1));
        vardeSaker.add(new Apparat("iPhone XS Max", 15000, 2));
        vardeSaker.add(new Apparat("Huawei", 10000, 9));
        vardeSaker.add(new Apparat("Siemens", 1200, 10));
        vardeSaker.add(new Apparat("MSI", 100000, 11));


    }


    class BoxHanterare implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            String valj = boxen.getValue();
            switch (valj) {
                case "Smycke":
                    NyttSmycke();
                    break;
                case "Apparat":
                    NyApparat();
                    break;
                case "Aktie":
                    NyAktie();
                    break;
            }
        }
    }
}