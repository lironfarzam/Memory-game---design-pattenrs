package patterns.decorators;

import model.Card;

/**
 * Specific CardDecorator that adds a scoring bonus behavior to the card.
 */
class BonusScoringCardDecorator extends CardDecorator {
    public BonusScoringCardDecorator(Card decoratedCard) {
        super(decoratedCard);
    }

    // Add the scoring bonus effect to the card's visual representation
    @Override
    public String display() {
        addScoringBonusEffect(); // Then add additional effect
        return super.display(); // Display the underlying card's visual representation first
        
    }

    // Add the scoring bonus effect to the card's behavior
    private void addScoringBonusEffect() {
        System.out.println("This card carries a scoring bonus!");
    }
}