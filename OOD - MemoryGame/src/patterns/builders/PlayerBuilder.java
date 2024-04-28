package patterns.builders;

import model.Board;
import model.Player;

// General interface for all player types
public interface PlayerBuilder {
    PlayerBuilder setName(String name);
    PlayerBuilder setBoard(Board board);
    Player build();
}

