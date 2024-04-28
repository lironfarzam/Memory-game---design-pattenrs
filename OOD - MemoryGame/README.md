# Memory Card Game Project

## Table of Contents
- [Overview](#overview)
- [System Requirements](#system-requirements)
- [Compilation and Running the Game](#compilation-and-running-the-game)
- [Gameplay Instructions](#gameplay-instructions)
- [Architectural Overview and Project Structure](#architectural-overview-and-project-structure)
  - [Project Directory Structure](#project-directory-structure)
  - [Explanation of Responsibilities](#explanation-of-responsibilities)
- [Initialization and Game Mechanics Overview](#initialization-and-game-mechanics-overview)
  - [Initialization Process](#initialization-process)
  - [Game Tools and Object Creation](#game-tools-and-object-creation)
  - [Gameplay Mechanics](#gameplay-mechanics)
  - [Game Progression and State Transitions](#game-progression-and-state-transitions)
- [Game Configuration: Difficulty and Scoring](#game-configuration-difficulty-and-scoring)
  - [Difficulty Settings](#difficulty-settings)
  - [Scoring Options](#scoring-options)
  - [Expanding Configuration Options](#expanding-configuration-options)
- [Design Patterns Utilized](#design-patterns-utilized)
  - [Singleton Design Pattern](#singleton-design-pattern)
  - [Factory Design Pattern](#factory-design-pattern)
  - [Command Design Pattern](#command-design-pattern)
  - [Observer Design Pattern](#observer-design-pattern)
  - [Strategy Design Pattern](#strategy-design-pattern)
  - [Mediator Design Pattern](#mediator-design-pattern)
  - [Memento Design Pattern](#memento-design-pattern)
  - [State Design Pattern](#state-design-pattern)
  - [Builder Design Pattern](#builder-design-pattern)
  - [Prototype Design Pattern](#prototype-design-pattern)
  - [Decorator Design Pattern](#decorator-design-pattern)
- [Future Extensions](#future-extensions)

## Overview
This Memory Card Game is an interactive console-based application that simulates a simple card matching game suitable for one or more players, including AI opponents. Players turn over pairs of cards to find matching sets based on symbols, striving to remember card positions as the game progresses. The goal is to match more pairs of cards than your opponent.

The game has been designed using a variety of design patterns to demonstrate object-oriented programming principles, ensure maintainability, and facilitate future enhancements.

## System Requirements
- Java 11 or later
- A command line interface (CLI) such as Terminal on macOS or CMD on Windows

## Compilation and Running the Game
To compile and run the Memory Card Game, follow these steps:

1. **Compilation:**
   Navigate to the source directory where all `.java` files are located and compile the Java files using the following command:
   ```bash
   javac -d . *.java
   ```

2. **Running the Game:**
   After compilation, run the game using the Java command. Make sure to be in the root directory where the `app.Main` class file is located:
   ```bash
   java app.Main
   ```

## Gameplay Instructions
- The game starts by asking the user to enter the number of human and computer players.
- Players take turns choosing two cards to flip during each turn.
- If a pair matches, the player earns points and takes another turn.
- The game continues until all pairs are matched.
- The player with the most points at the end of the game wins.

## Architectural Overview and Project Structure

### Project Directory Structure
```
src/
    app/
        Main.java         - Entry point of the application, handles game initialization and main loop.
    core/
        Game.java         - Manages game logic, interactions, and enforces game rules.
        GameManager.java  - Singleton class that controls the overall management of the game lifecycle.
        GameConfigurator.java - Handles initial game setup and configuration based on user input.
        GameStateManager.java - Manages the different states of the game (e.g., initializing, playing, game over).
        CommandLineInterface.java - Provides CLI-based interaction for the game setup and ongoing input during the game.
    model/
        Card.java         - Represents a single card, including its state and properties.
        Board.java        - Manages the game board setup and operations like shuffling and dealing cards.
        Player.java       - Abstract definition for a player, extended by specific types of players.
        ComputerPlayer.java - Defines the AI player with strategies based on difficulty level.
        HumanPlayer.java  - Defines a human player's interactions via console.
        TurnResult.java   - Represents the outcome of a player's turn, including score changes and card flips.
    ui/
        GameUI.java       - Manages all user interface elements and interactions, updating according to

 game state changes.
    patterns/
        builders/
            PlayerBuilder.java       - Interface for building player objects.
            HumanPlayerBuilder.java  - Constructs a human player.
            ComputerPlayerBuilder.java - Constructs an AI player with specified difficulty.
            CardBuilder.java         - Builds card objects with specific attributes.
        decorators/
            CardDecorator.java       - Base class for decorating card objects.
            BonusScoringCardDecorator.java - Adds scoring features to cards.
        observer/
            UIObserver.java          - Updates the user interface based on state changes.
            GameObserver.java        - Interface for objects that need to be notified of game events.
            ScoreObserver.java       - Observes score changes and updates relevant displays.
        memento/
            Memento.java             - Stores state snapshots for undo functionality.
            Caretaker.java           - Manages memento objects to enable undo operations.
        state/
            card/
                CardState.java       - Interface for card states (face up, face down, matched).
                FaceUpState.java      - Handles behavior for a card that is face up.
                FaceDownState.java    - Handles behavior for a card that is face down.
                MatchedState.java     - Handles behavior for a card that has been matched.
            game/
                GameState.java        - Interface for game states.
                InitializingState.java - State during game setup.
                PlayingState.java     - State during the main gameplay.
                GameOverState.java    - State when the game has ended.
        command/
            Command.java             - Interface for commands in the command pattern.
            FlipCommand.java         - Concrete command to flip cards.
        mediator/
            BoardMediator.java       - Coordinates interactions between game components.
        factory/
            CardFactory.java         - Factory for creating card objects.
            PlayerFactory.java       - Factory for creating player objects.
        strategy/
            difficulty/
                DifficultyStrategy.java - Strategy pattern interface for AI difficulty levels.
                EasyStrategy.java     - Easy level AI behavior.
                MediumStrategy.java   - Medium level AI behavior.
                HardStrategy.java     - Hard level AI behavior.
            match/
                MatchStrategy.java    - Strategy for determining if two cards match.
                SymbolMatchStrategy.java - Matches cards based on symbols.
                ColorMatchStrategy.java - Matches cards based on color.
                FullMatchStrategy.java - Matches cards based on multiple attributes.
            score/
                ScoreStrategy.java    - Strategy for calculating scores.
                SimpleScoreStrategy.java - Simple scoring logic.
                TimeBasedScoreStrategy.java - Scores based on speed of match.
                PenaltyScoreStrategy.java - Adds penalties for incorrect matches.
```

### Explanation of Responsibilities
- **`app`**: Contains the main driver of the application, setting up and starting the game.
- **`core`**: Holds the central game logic and state management, ensuring the game progresses orderly and according to rules.
- **`model`**: Defines the essential data structures and domain logic for game entities like cards, players, and the board.
- **`ui`**: Responsible for all user interactions, ensuring the player receives all necessary game information and can provide input through a user-friendly interface.
- **`patterns`**: Implements various design patterns to solve common software design problems or introduce specific functionalities in a flexible and reusable manner. Each sub-package under `patterns` corresponds to a particular design pattern applied in the project.

## Initialization and Game Mechanics Overview

### Initialization Process
The initialization of the Memory Card Game begins in the `app.Main` class, which serves as the entry point. Here, the `CommandLineInterface` is instantiated to interact with the user, gathering necessary configurations such as the number of players, board size, and computer player difficulty. These settings are passed to the `GameManager` class, which acts as a singleton to ensure that only one instance of the game management process exists throughout the application's lifecycle.

The `GameManager` sets up the game environment by creating an instance of `Game`, which includes initializing the `Board` and `Player` objects. The `Board` is set up using a `CardFactory`, which populates the board with pairs of matched cards shuffled into a random order to begin the game. Players are created through the `PlayerFactory`, utilizing builder patterns to construct either human or computer players based on the initial configuration.

### Game Tools and Object Creation
- **Factories and Builders:** Player and card objects are created using factory and builder design patterns, respectively, providing flexibility in the configuration and instantiation processes. This approach allows for easy adjustments to the instantiation process and encapsulates the creation logic.
- **Mediator and Command Patterns:** The `BoardMediator` facilitates communication between various components of the game, such as the board and players, without them needing to hold direct references to each other. Actions like flipping cards are handled via the command pattern, encapsulating the action logic in `FlipCommand` objects that can be executed or undone.

### Gameplay Mechanics
Once initialized, the game enters its main loop, managed by the `GameStateManager`. This manager controls the transition between various

 states of the game:
- **InitializingState:** Sets up the game environment.
- **PlayingState:** Manages the gameplay where players take turns playing according to the rules defined.
- **GameOverState:** Concludes the game and displays the final results.

Each player's turn is processed in the `Game` class, where the game checks for card matches and updates scores accordingly. Matches are determined based on the strategy pattern, which can vary depending on the game's difficulty settings. This pattern allows different matching rules and scoring systems to be easily interchanged and adjusted.

### Game Progression and State Transitions
The game progresses by cycling through players, allowing each to make a move by selecting two cards to flip. The outcome of these moves can affect the game state by triggering transitions managed by the `GameStateManager`. For instance, if all cards are matched, the game transitions to the `GameOverState`.

- **Observer Pattern:** Changes in the game state are communicated to the UI components through the observer pattern. Classes like `UIObserver` update the game's visual representation in response to state changes, ensuring the player interface reflects the current game status.

This structured approach to handling game initialization, object creation, and gameplay mechanics not only organizes the flow of the game effectively but also enhances maintainability and scalability of the application. By separating concerns and utilizing design patterns, the game architecture supports extensions such as adding new features or modifying existing behaviors with minimal impact on other parts of the system.

## Game Configuration: Difficulty and Scoring

### Difficulty Settings
The difficulty levels for computer players (Easy, Medium, and Hard) are managed in the `GameManager` class. These settings influence the AI's decision-making algorithms, impacting how challenging the game is for human players. The selected difficulty level is applied during the initialization of computer players in the `Game` class, specifically through the use of different `DifficultyStrategy` instances.

### Scoring Options
Scoring mechanisms in the game are flexible and implemented using the Strategy pattern. This design choice allows for easy swapping of scoring rules, enhancing gameplay variation. Currently available strategies include simple point accumulation, penalties for incorrect matches, and time-based scoring that rewards quick matches. These strategies are defined in the `ScoreStrategy` interface, and specific implementations are selected and applied within the `GameManager` during game setup.

### Expanding Configuration Options
To add new difficulty or scoring options, developers need to follow these steps:

1. **Define New Strategies:** Create new classes that implement the `DifficultyStrategy` or `ScoreStrategy` interfaces. Each new class should encapsulate the unique behavior of the new game rule.

2. **Integrate Strategies:** Introduce these new strategy classes in the `GameManager`. This involves adding them to the methods responsible for setting up and initializing games. For instance, add new case statements in the switch-case block that determines which strategy to apply based on user selections or configuration.

3. **Update Configuration Interfaces:** Modify the `CommandLineInterface` or any other user interface components to include options for selecting these new strategies. This ensures that players can choose them when starting a new game.

4. **Testing:** Thoroughly test the new strategies to ensure they integrate seamlessly with existing game logic and that they behave as expected across different game scenarios.

By following these steps, new difficulty levels or scoring rules can be easily added, making the game more versatile and appealing to a broader audience. This approach not only maintains the modular nature of the game architecture but also enhances its extendibility.

## Design Patterns Utilized

### Singleton Design Pattern

#### Overview of the Singleton Pattern
The Singleton design pattern ensures that a class has only one instance and provides a global point of access to it. This pattern is particularly useful when exactly one object is needed to coordinate actions across the system. The Singleton pattern is implemented by making the class's constructor private, providing a static method that returns a reference to the instance, and creating a static member in the class that holds the instance.

#### Implementation in the Memory Card Game
In the Memory Card Game, the `GameManager` class is implemented as a Singleton to manage the state and flow of the entire game. This ensures that there is only one game manager instance controlling the game logic, enforcing consistency and preventing issues such as multiple game loops or conflicting game states.

**Key aspects of the Singleton usage in the project include:**
- **Centralized Management:** The `GameManager` centrally controls the game setup, progression, and termination, coordinating all major actions throughout the game's lifecycle.
- **Consistency:** By using the Singleton pattern, we ensure that all parts of the application interact with the same game manager instance, maintaining consistency in the game's state and behavior.
- **Controlled Access:** The Singleton pattern restricts the instantiation of the `GameManager`, providing controlled access to the game's management functionalities through its static instance.

This design is critical for the game as it centralizes control and coordination of the complex interactions between different components of the game such as the UI, game state management, and player actions.

### Factory Design Pattern

####

 Overview
The Factory pattern provides a way to encapsulate object creation. It allows for the creation of objects without specifying the exact class to be created, supporting flexibility and decoupling by delegating instantiation to specialized factory classes.

#### Implementation in the Memory Card Game
In the Memory Card Game, the Factory pattern is employed mainly for player and card creation:

- **Player Factory:** Creates `Player` objects dynamically, with the type (human or computer) determined by game settings. Utilizes `PlayerBuilder` and `ComputerPlayerBuilder` for specific configurations.

- **Card Factory:** Manages the creation of matching card pairs with consistent properties, ensuring all cards are correctly paired and configured for gameplay.

**Benefits in the project:**
- Allows dynamic instantiation based on game configurations, enhancing game flexibility.
- Encapsulates object creation complexity, simplifying the main game logic.
- Facilitates easy expansion with new player types or card features while adhering to the Open/Closed principle.

### Command Design Pattern

#### Overview
The Command pattern encapsulates a request as an object, allowing for parameterization of clients with different requests, queueing or logging of requests, and support for undoable operations. It provides the means to separate the responsibility of issuing commands from the objects that execute them, enhancing flexibility in command execution.

#### Implementation in the Memory Card Game
In the Memory Card Game, the Command pattern is utilized to manage the actions associated with card manipulation, specifically flipping cards:

- **Flip Command:** Encapsulates the action of flipping a card, allowing for execution and potential undoing of this action. This makes it possible to reverse the flip operation, providing an undo functionality that is crucial for player moves within the game.

**Benefits in the project:**
- Enables the separation of the command execution logic from the rest of the game logic, allowing for cleaner and more manageable code.
- Supports undo operations, which are essential for allowing players to revert actions, adding strategic depth to the game.
- Increases the flexibility of command execution and extension, facilitating the addition of new commands without altering existing code.

### Observer Design Pattern

#### Overview
The Observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods. It is mainly used to implement distributed event-handling systems, where one object's state changes can be broadcast to other, dependent objects.

#### Implementation in the Memory Card Game
In the Memory Card Game, the Observer pattern is applied to manage updates to the user interface (UI) and game state information. The pattern is crucial for ensuring that the game's display is updated in real-time as the game state changes:
- **Game as Subject:** The game acts as the subject that holds crucial state information. It notifies observers about changes like game start, player turns, scores, and game end.
- **UI and Score Observers:** Observers such as `UIObserver` and `ScoreObserver` react to notifications by updating the game UI and displaying updated scores, respectively.

**Benefits in the project:**
- **Decoupling:** The game logic is decoupled from the user interface, which means changes in the game state management do not directly affect how the state is displayed or managed on the UI.
- **Real-time Updates:** The UI updates are handled in real-time, ensuring that all players have the latest view of the game board and scores, enhancing user experience.
- **Extensibility:** New types of observers can easily be added without altering the existing game logic, making the system more extensible and maintainable. For instance, adding a new observer to log game steps for debugging or analysis purposes would be straightforward.
- **Maintainability:** The Observer pattern helps keep the code more organized and focused on their responsibilities, which in turn makes the system easier to manage and maintain.

### Strategy Design Pattern

#### Overview
The Strategy pattern is a behavioral design pattern that enables selecting an algorithm at runtime. Instead of implementing a single algorithm directly, code receives run-time instructions as to which in a family of algorithms to use. This pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

#### Implementation in the Memory Card Game
In the Memory Card Game, the Strategy pattern is extensively used to handle varying complexities and behaviors without altering the classes that use the algorithms. Different strategies are implemented for game difficulty, card matching rules, and scoring:
- **Difficulty Strategies:** Different algorithms for computer player behavior are encapsulated as strategies like `EasyStrategy`, `MediumStrategy`, and `HardStrategy`. Each strategy affects how the computer player chooses cards to flip, providing different levels of challenge for the player.
- **Match Strategies:** The game can be configured with different card matching strategies such as `SymbolMatchStrategy`, `ColorMatchStrategy`, and `FullMatchStrategy`, which determine how two cards are considered a match.
- **Score Strategies:** Sc

oring can also be varied using strategies like `SimpleScoreStrategy`, `TimeBasedScoreStrategy`, and `PenaltyScoreStrategy` to provide different scoring mechanisms based on game dynamics and player actions.

**Benefits in the project:**
- **Flexibility in Game Features:** The Strategy pattern allows the game to easily switch between different rules and behaviors without changing the core game logic. This flexibility is essential for creating a customizable and extendable game experience.
- **Easy to Extend:** New strategies can be added for any aspect of the game (difficulty, matching, scoring) without modifying existing code, adhering to the open/closed principle.
- **Decoupling of Algorithm Implementation:** Clients that use strategies are decoupled from the implementation details of each strategy. This separation of concerns ensures that adding new strategies or changing existing ones has minimal impact on the clients.
- **Improved Testability:** Each strategy can be independently tested from the clients that use it, improving the testability of the code.

Overall, the Strategy pattern provides a robust mechanism for encapsulating varying algorithms, enhancing the game’s ability to adapt to different requirements and player preferences.

### Mediator Design Pattern

#### Overview
The Mediator pattern is a behavioral design pattern that provides a unified interface to a set of interfaces in a subsystem. This pattern defines an object that encapsulates how a set of objects interact, promoting loose coupling by keeping objects from referring to each other explicitly. It allows for the interaction between objects to be encapsulated within a mediator object.

#### Implementation in the Memory Card Game
In the Memory Card Game, the Mediator pattern is utilized through the `BoardMediator` class, which acts as the central coordinator for interactions between different components such as the game board, players, and the UI.

- **Board and Players Interaction:** `BoardMediator` manages interactions between the board and the players. It handles the updating of scores when players make moves, ensuring that the game logic related to player actions is centrally managed.
- **Communication with UI:** The mediator updates the game UI in response to changes in the game state, such as score updates or changes in the game board. This separation ensures that the game logic is not directly tied to the UI code.
- **Handling Undo Operations:** The mediator also facilitates the undo functionality by managing the interactions required to revert actions, such as unflipping cards and adjusting scores accordingly.

**Benefits in the project:**
- **Centralized Control:** The mediator centralizes complex communications and control logic between various components, making the system easier to understand and maintain.
- **Reduced Coupling:** By reducing the direct communication between various components of the system (such as players and the game board), the Mediator pattern decreases the coupling and increases the component reusability.
- **Simplified Object Protocols:** The interaction logic is abstracted into the mediator, simplifying the protocols that objects must follow and reducing dependencies.
- **Enhanced Flexibility:** Changes to the mediation logic or the introduction of new components involve modifications only within the mediator rather than across various components.

The use of the Mediator pattern in the Memory Card Game effectively organizes the management of game logic and interactions, leading to cleaner, more manageable code and a clear separation between the game mechanics and user interface.

### Memento Design Pattern

#### Overview
The Memento pattern is a behavioral design pattern that provides the ability to restore an object to its previous state (undo via rollback). It does this without revealing the details of its implementation. The pattern uses three main actors: the originator, the caretaker, and the memento. The originator is the object whose state needs to be saved and restored. The memento holds the state of the originator. The caretaker manages the memento but does not modify it.

#### Implementation in the Memory Card Game
In the Memory Card Game, the Memento pattern is utilized to implement the undo functionality, which allows players to revert to a previous state if they wish to undo a move. This feature is crucial for providing a forgiving user experience, allowing players to correct mistakes or reconsider their strategies.

- **Memento Class:** This class holds the state of a specific game moment, particularly capturing the state of matched cards and their respective scores before an action changes the game state.
- **Caretaker Class:** Manages the mementos. It maintains a stack of mementos to track the history of game states. When an undo request is made, the caretaker retrieves the most recent memento from the stack and restores the game state to that captured moment.
- **Game and Player Classes as Originators:** These classes generate mementos before performing actions that alter the state. When an action is taken that can be undone, the game or player object creates a memento and hands it over to the caretaker.

**Benefits in the project:**
- **Undo Functionality:** The primary benefit of implementing the Memento pattern in this game is to provide robust undo functionality, allowing players to retract moves without complexity or risk of corrupting the game state

.
- **Preservation of Encapsulation:** Memento allows the internal state of an object to be saved and restored without exposing its internal structure. The state is stored externally but remains encapsulated within the memento, ensuring that the object's integrity is maintained.
- **Ease of Restoration:** It simplifies the restoration process since the object restoring its state works with a previously saved memento, ensuring that the restoration is consistent with the object's history.
- **Enhanced Game Experience:** By enabling undo, the game becomes more user-friendly and flexible, encouraging experimentation and learning from past moves without penalty.

By using the Memento pattern, the Memory Card Game efficiently handles state restoration, allowing users to easily and safely navigate through their play history. This functionality is essential for enhancing user engagement and satisfaction with the game experience.

### State Design Pattern

#### Overview
The State pattern is a behavioral design pattern that allows an object to change its behavior when its internal state changes. This pattern is used to encapsulate varying behavior for the same object, based on its internal state. This can be a cleaner way for an object to change its behavior at runtime without resorting to large monolithic conditional statements.

#### Implementation in the Memory Card Game
In the Memory Card Game, the State pattern is used to manage the state of the cards and the game's overall state. This approach simplifies the management of different stages and conditions of gameplay, such as flipping cards or changing game phases (initializing, playing, waiting for players, and game over).

- **Card State Handling:**
  - **CardState Interface:** This interface defines common actions (like flipping a card) and behaviors that can vary depending on the state of the card.
  - **Concrete States:** `FaceUpState`, `FaceDownState`, and `MatchedState` are specific states that implement the `CardState` interface, providing specific behaviors for different card states, such as showing a card face up, face down, or handling matched cards.
  
- **Game State Management:**
  - **GameState Interface:** Similar to `CardState`, this interface defines the protocol for game state classes.
  - **Concrete States:** Classes like `InitializingState`, `PlayingState`, `WaitingForPlayerState`, and `GameOverState` implement the `GameState` interface and encapsulate the behavior associated with each possible phase of the game.

**Benefits in the project:**
- **Localized State-Specific Behavior:** Each state object encapsulates the operations and transitions of a particular state of either a card or the game, leading to a more organized and modular structure.
- **Simplified State Transitions:** Changing the state of the game or a card involves switching the state object associated with it. This abstracts away the complexities of state-dependent operations.
- **Extensibility:** New states can be added by simply creating new classes that implement the `GameState` or `CardState` interfaces, without modifying existing classes. This makes extending the game mechanics straightforward.
- **Separation of Concerns:** Decouples state-specific behavior from the main classes, keeping the code for conditions and state transitions clean and easy to manage.

**Use in the project:**
- The state pattern allows for an efficient way of handling different stages of the game and different conditions of cards. For example, transitioning from showing all cards face down at the start to handling actions during gameplay, and finally to the game over state, is managed seamlessly with the State pattern. Each state class handles its specific tasks and transitions to the next appropriate state, streamlining the game flow.

Overall, the State pattern is crucial in the Memory Card Game for managing the flow and rules of the game dynamically based on the game's and the cards' states, thereby supporting complexity management and future game enhancements.

### Builder Design Pattern

#### Overview
The Builder pattern is a creational design pattern that provides a flexible solution to various object creation problems in object-oriented programming. The key idea behind the Builder pattern is to separate the construction of a complex object from its representation. This allows the same construction process to create different representations.

#### Implementation in the Memory Card Game
In the Memory Card Game, the Builder pattern is used to construct complex objects such as `Player` and `Card` objects. This is particularly useful in scenarios where these objects have several attributes that need to be set during creation.

- **Player and Card Construction:**
  - **PlayerBuilder Interface and its Concrete Classes:** This interface defines methods for step-by-step construction of `Player` objects, including setting a name, setting a board, and eventually building the final `Player` object. Concrete classes like `HumanPlayerBuilder` and `ComputerPlayerBuilder` provide specific implementations for creating human and computer players, respectively.
  - **CardBuilder Class:** Manages the construction of `Card` objects. It allows setting properties such as ID, number, symbol, and color step by step and then building the final `Card` object.

**Benefits in the project:**
- **Control Over Construction Process:** The Builder pattern provides control over the

 construction process of complex objects. It facilitates a clear process by allowing the step-by-step setting of properties.
- **Immutability:** Once all necessary properties are set and the object is built, the resulting object can be made immutable and cannot be altered. This is beneficial for maintaining the integrity of game elements like cards and players.
- **Fluent Interfaces:** Often, builders are implemented with fluent interfaces, allowing for method chaining (e.g., `new CardBuilder().setId(1).setSymbol("Ace").setColor("Black").build();`). This makes the client code more readable and easy to write.
- **Separation of Construction and Representation:** Separates the object construction from its class implementation, allowing different representations of objects to be created using the same construction process. This is especially useful in games where different types of players or card setups might be needed.

**Use in the project:**
- In the Memory Card Game, builders abstract the complexity involved in creating `Player` and `Card` objects with different configurations. For instance, a computer player can be configured with different difficulty levels using a specific strategy pattern for decision-making, and this configuration is neatly encapsulated by the `ComputerPlayerBuilder`.

Overall, the Builder pattern in the Memory Card Game simplifies the instantiation of complex objects, ensuring that the objects are created in a controlled and orderly fashion. This makes the system easier to scale and modify, as new types of players or cards can be introduced with minimal changes to the existing codebase.

### Prototype Design Pattern

#### Overview
The Prototype pattern is a creational design pattern used when the type of objects to create is determined by a prototypical instance, which is cloned to produce new objects. This pattern is particularly useful when creating many instances of a complex object is costly or complicated.

#### Implementation in the Memory Card Game
In the Memory Card Game, the Prototype pattern is used primarily for the creation of `Card` objects. Given that each card can be considered as an instance with specific attributes that could be duplicated, using the Prototype pattern simplifies the process.

- **Card Cloning:**
  - The `Card` class in the game includes cloning capabilities, where each card can create a copy of itself. This is particularly useful when setting up the game board with pairs of matching cards.
  
**Benefits in the project:**
- **Efficiency:** Cloning is generally more efficient in terms of both time and memory than creating new objects from scratch, especially when the objects involved are complex.
- **Simplicity:** Using prototypes simplifies the code when creating duplicates of existing objects. Instead of needing to know the details of how to construct a new object, the system can simply ask an existing object to clone itself.
- **Dynamic Configuration:** The Prototype pattern supports adding and removing products at runtime. For a card game, this means the game could potentially introduce new types of cards without changing the underlying codebase that manages them.

**Use in the project:**
- Each `Card` object in the Memory Card Game can clone itself to ensure that pairs of cards are identical in terms of visual representation and properties, but distinct in terms of their identity (object instance).
- The game utilizes a `CardFactory` that manages the cloning process to ensure that the board setup is populated correctly with pairs of cards. This factory makes use of the Prototype pattern to efficiently manage the creation and setup of cards on the game board.

Overall, the Prototype pattern helps manage the complexity and performance implications of creating numerous card objects in the Memory Card Game. It encapsulates the logic for replicating card properties while ensuring that each card remains a unique instance, thus simplifying the board setup process and enhancing performance.

### Decorator Design Pattern

#### Overview
The Decorator pattern is a structural design pattern that allows behavior to be added to individual objects, either statically or dynamically, without affecting the behavior of other objects from the same class. This pattern is useful for adding functionalities to objects without altering their structure by wrapping them in more useful objects.

#### Implementation in the Memory Card Game
In the Memory Card Game, the Decorator pattern can be used to enhance or modify the properties of `Card` objects without changing the card class itself. This could include adding new behaviors like scoring bonuses or special effects that are triggered under certain conditions.

- **Card Enhancement:**
  - `CardDecorator` acts as a base class for decorations, providing a wrapper around a `Card` object.
  - `BonusScoringCardDecorator` extends `CardDecorator` to enhance cards by providing additional scoring bonuses when they are matched.

**Benefits in the project:**
- **Flexibility:** Allows for dynamic addition of new behaviors and can be removed as easily as they are added. This is much more flexible than inheriting from a class.
- **Functionality Extension:** It provides a flexible alternative to subclassing for extending functionality.
- **Variability:** Individual objects can be configured with varying behaviors to meet specific needs without creating a proliferation of subclasses.

**Use in the project:**
- Decorators in the Memory Card

 Game can dynamically alter the display or scoring characteristics of a card. For example, during special events or game modes, some cards might temporarily gain new effects or scoring rules.
- `BonusScoringCardDecorator` could be used during a special game round to increase the points awarded for matching certain cards, thereby introducing a strategic element to choosing which cards to match.

Overall, the Decorator pattern allows the Memory Card Game to maintain a clean and flexible architecture by enabling the dynamic extension of card behaviors. It helps avoid an explosion of subclass varieties while keeping the system adaptable to changing requirements, such as introducing temporary effects or behaviors based on gameplay context.

### Future Extensions

To expand the game or add new features, consider the following approaches:

- **Adding New Player Types:** Implement new `Player` subclasses to introduce different player behaviors or strategies.
- **Enhancing AI Difficulty:** Develop more sophisticated AI strategies using the Strategy pattern to increase the challenge.
- **Multiplayer Support:** Extend the game to support networked multiplayer functionality, requiring adjustments to how players interact with the game state.
- **Graphical User Interface:** Transition from a CLI to a graphical user interface (GUI) to enhance user interaction and appeal.
