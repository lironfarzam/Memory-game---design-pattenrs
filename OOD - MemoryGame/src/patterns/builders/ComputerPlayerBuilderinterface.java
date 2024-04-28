package patterns.builders;


// Extended interface for computer players
public interface ComputerPlayerBuilderinterface extends PlayerBuilder {
    ComputerPlayerBuilder setDifficulty(int difficulty);
}