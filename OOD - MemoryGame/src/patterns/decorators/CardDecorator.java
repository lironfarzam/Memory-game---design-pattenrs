package patterns.decorators;

import model.Card;

/**
 * Abstract decorator class for cards that follows the Decorator Pattern.
 */
public abstract class CardDecorator extends Card {
    protected Card decoratedCard;

    /**
     * Constructs a CardDecorator with a decorated card.
     * @param decoratedCard the card to decorate.
     */
    public CardDecorator(Card decoratedCard) {
        // Assuming there's no direct constructor in Card taking another Card, we manually clone or copy necessary attributes
        super(decoratedCard.getId(), decoratedCard.getNumber(), decoratedCard.getSymbol(), decoratedCard.getColor());
        this.decoratedCard = decoratedCard;
    }

    /**
     * Displays the card's visual representation.
     * @return the card's visual representation.
     */
    @Override
    public String display() {
        return decoratedCard.display();
    }

}
