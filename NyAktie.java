import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


/**
 * Maxim Saffarini -19980925
 * Emil Alic - 19980129
 */
public class NyAktie extends Alert {
    private TextField namnFalt = new TextField();
    private TextField antalFalt = new TextField();
    private TextField prisFalt = new TextField();

    public NyAktie() {
        super(AlertType.CONFIRMATION);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(10);
        grid.addRow(0, new Label("Namn:"), namnFalt);
        grid.addRow(1, new Label("Antal:"), antalFalt);
        grid.addRow(2, new Label("Pris:"), prisFalt);
        setTitle("Ny Aktie");
        setHeaderText(null);
        getDialogPane().setContent(grid);

    }
    public String getNamn() {
        return namnFalt.getText();
    }

    public int getAntal() {
        return Integer.parseInt(antalFalt.getText());
    }

    public String getAntalFalt() {
        return antalFalt.getText();
    }

    public Double getPris() {
        return Double.parseDouble(prisFalt.getText());
    }

    public String getPrisFalt() {
        return prisFalt.getText();
    }

}