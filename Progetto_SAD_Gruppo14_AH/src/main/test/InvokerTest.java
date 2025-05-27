import com.example.Command.Invoker;
import com.example.Command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvokerTest {

    @Test
    void testSingleton() {
        Invoker i1 = Invoker.getInstance();
        Invoker i2 = Invoker.getInstance();
        assertSame(i1, i2, "Invoker deve essere singleton");
    }

    @Test
    void testExecuteCommandChiamaExecute() {
        class ComandoFinto implements Command {
            boolean eseguito = false;

            @Override
            public void execute() {
                eseguito = true;
            }
            @Override
            public void undo() {}
        }

        ComandoFinto cmd = new ComandoFinto();

        Invoker invoker = Invoker.getInstance();
        invoker.executeCommand(cmd);

        assertTrue(cmd.eseguito, "Il comando deve essere eseguito");
    }
}
