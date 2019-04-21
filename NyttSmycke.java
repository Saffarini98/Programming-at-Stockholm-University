import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Maxim Saffarini -19980925
 * Emil Alic - 19980129
 */
public class NyttSmycke extends Alert {
    private TextField namnFalt = new TextField();
    private TextField stenFalt = new TextField();
    private CheckBox cbox = new CheckBox();


    public NyttSmycke() {
        super(AlertType.CONFIRMATION);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(10);
        grid.addRow(0, new Label("Namn:"), namnFalt);
        grid.addRow(1, new Label("Stenar:"), stenFalt);
        grid.addRow(2, new Label("Av guld: "), cbox);
        setTitle("Nytt smycke");
        setHeaderText(null);
        getDialogPane().setContent(grid);
    }
    public String getNamn() {
        return namnFalt.getText();
    }

    public String getStenFalt() {
        return stenFalt.getText();
    }

    public int getStenar() {
        return Integer.parseInt(stenFalt.getText());
    }

    public boolean getGold() {
        return cbox.isSelected();

    }
}
