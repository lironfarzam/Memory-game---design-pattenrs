package patterns.command;

/**
 * The Command interface declares a method for executing a specific action.
 */
public interface Command {
    void execute();
    void undo();  // Not all commands may need undo functionality
}
