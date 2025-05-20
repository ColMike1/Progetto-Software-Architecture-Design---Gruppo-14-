import com.example.State.Stato;
import javafx.scene.input.MouseEvent;

public class StatoFinto implements Stato {
    boolean premuto = false;

    @Override
    public void onMousePressed(MouseEvent event) {
        premuto = true;
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        // non ci serve per il test
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        // non ci serve per il test
    }
}
