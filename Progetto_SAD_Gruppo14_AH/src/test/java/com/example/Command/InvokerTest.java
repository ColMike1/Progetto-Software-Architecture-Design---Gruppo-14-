package com.example.Command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class InvokerTest {

    private Invoker invoker;

    @BeforeEach
    void setUp() {
        // Recuperiamo l'istanza reale del singleton
        invoker = Invoker.getInstance();
        // Svuotiamo lo stack prima di ogni test per isolare i test
        invoker.svuotaStack();
    }

    @Test
    void testExecuteCommandUndoable() {
        // Creo un comando undoable
        Command commandMock = mock(Command.class);
        when(commandMock.canExecute()).thenReturn(true);
        when(commandMock.isUndoable()).thenReturn(true);

        invoker.executeCommand(commandMock);

        verify(commandMock).execute();
        // Lo stack ora contiene 1 comando → quindi undo() lo chiamerà
        invoker.undo();
        verify(commandMock).undo();
    }

    @Test
    void testExecuteCommandNonUndoable() {
        // Creo un comando non undoable
        Command commandMock = mock(Command.class);
        when(commandMock.canExecute()).thenReturn(true);
        when(commandMock.isUndoable()).thenReturn(false);

        invoker.executeCommand(commandMock);

        verify(commandMock).execute();

        // Adesso undo() non deve chiamare nulla perché lo stack è vuoto
        invoker.undo();
        verify(commandMock, never()).undo();
    }

    @Test
    void testUndoOnEmptyStack() {
        // Non carico nulla nello stack
        invoker.undo(); // Deve semplicemente non lanciare eccezioni
    }

    @Test
    void testSvuotaStack() {
        // Creo un comando undoable
        Command commandMock = mock(Command.class);
        when(commandMock.canExecute()).thenReturn(true);
        when(commandMock.isUndoable()).thenReturn(true);

        invoker.executeCommand(commandMock);
        invoker.svuotaStack();

        // Dopo aver svuotato, undo non dovrebbe più richiamare undo()
        invoker.undo();
        verify(commandMock, never()).undo();
    }
}
