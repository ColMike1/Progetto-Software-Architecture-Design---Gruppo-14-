import com.example.State.Stato;
import com.example.State.StatoManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatoManagerTest {

    @AfterEach
    void cleanup() {
        // Pulisci lo stato dopo ogni test per non influenzare gli altri
        StatoManager.getInstance().clearStato();
    }

    @Test
    void testSingletonUnicaIstanza() {
        StatoManager manager1 = StatoManager.getInstance();
        StatoManager manager2 = StatoManager.getInstance();

        assertSame(manager1, manager2, "Il singleton deve restituire sempre la stessa istanza");
    }

    @Test
    void testSetEGetStato() {
        Stato stato = new StatoFinto();
        StatoManager manager = StatoManager.getInstance();

        manager.setStato(stato);
        assertEquals(stato, manager.getStato(), "Lo stato deve essere quello impostato");
    }

    @Test
    void testClearStato() {
        StatoManager manager = StatoManager.getInstance();
        manager.setStato(new StatoFinto());

        manager.clearStato();

        assertNull(manager.getStato(), "Dopo il clear lo stato deve essere null");
    }

    @Test
    void testHasStato() {
        StatoManager manager = StatoManager.getInstance();

        assertFalse(manager.hasStato(), "Senza stato impostato, hasStato() deve restituire false");

        manager.setStato(new StatoFinto());
        assertTrue(manager.hasStato(), "Dopo aver impostato uno stato, hasStato() deve restituire true");
    }
}
