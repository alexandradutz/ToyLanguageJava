package ViewsGUI;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import repository.EmptyRepository;

import java.io.IOException;


public class OneStepExecController {
    private Controller ctrl;
    public TextArea preview;

    public void init(Controller ctrl) throws InterruptedException, EmptyRepository, IOException{
        this.ctrl = ctrl;
        this.ctrl.oneStep();
        preview.appendText(ctrl.getPrgList().toString());
    }
    public void actionBtnBack(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void actionBtnOneStep(ActionEvent event) throws InterruptedException, EmptyRepository, IOException{
        this.ctrl.oneStep();
        this.preview.setText(ctrl.getPrgList().toString());
    }

}