import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import org.glo.giftw.view.StrategyImageExporter;

/**
 * Created by alexandra on 12/16/16.
 */
public class StrategyExporterController {
    private Dialog rootDialog;

    @FXML
    private DialogPane rootPane;

    @FXML
    private ImageView StrategyView;

    @FXML
    public void initialize()
    {
        System.out.println("initializeFieldEditor");

        rootDialog = new Dialog();
        rootDialog.setTitle("Exporter la stratégie");
        rootDialog.setDialogPane(rootPane);
        rootDialog.setResizable(true);

        StrategyImageExporter preview = new StrategyImageExporter();

        rootDialog.setResizable(true);
        rootDialog.setWidth(StrategyImageExporter.get);
    }
}
