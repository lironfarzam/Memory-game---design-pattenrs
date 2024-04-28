package patterns.memento;

import java.util.Stack;

/**
 * Caretaker class manages the mementos to enable undo or redo operations.
 */
public class Caretaker {
    private Stack<Memento> mementos = new Stack<>();

    /**
     * Saves the memento of the game state.
     * @param memento The memento object to be saved.
     */
    public void saveMemento(Memento memento) {
        mementos.push(memento);
    }

    /**
     * Restores the most recently saved game state.
     * @return The last saved memento or null if no memento exists.
     */
    public Memento restoreMemento() {
        return mementos.isEmpty() ? null : mementos.pop();
    }

    /**
     * Gets the number of stored mementos which can be useful for debugging or UI elements that display undo stack size.
     * @return The number of mementos in the stack.
     */
    public int getMementosCount() {
        return mementos.size();
    }
}
