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


public class NyApparat extends Alert {
    private TextField namnFalt = new TextField();
    private TextField prisFalt = new TextField();
    private TextField skickFalt = new TextField();


    public NyApparat() {
        super(AlertType.CONFIRMATION);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(10);
        grid.addRow(0, new Label("Namn:"), namnFalt);
        grid.addRow(1, new Label("Pris:"), prisFalt);
        grid.addRow(2, new Label("Skick:"), skickFalt);
        setTitle("Ny Apparat");
        setHeaderText(null);
        getDialogPane().setContent(grid);


    }
    public String getNamn() {
        return namnFalt.getText();
    }

    public double getPris() {
        return Double.parseDouble(prisFalt.getText());
    }

    public String getPrisFalt() {
        return prisFalt.getText();
    }

    public int getSlitage() {
        return Integer.parseInt(skickFalt.getText());
    }

    public String getSlitageFalt() {
        return skickFalt.getText();
    }

}



