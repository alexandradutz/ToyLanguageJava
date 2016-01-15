package ViewsGUI;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class DeserializeController {
    private Controller ctrl;
    public TextArea preview;

    public void init(Controller ctrl)throws ClassNotFoundException, IOException {
        this.ctrl = ctrl;
        this.ctrl.getRepo().deserialize();
        preview.appendText(ctrl.getRepo().getPrgList().toString());
    }

    public void actionBtnBack(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}

