package a00754887.assignment2.data;

/**
 * this is the enum class for card types, there are four types of card, amex,
 * debit, mastercard, and visa
 * 
 * @author AoAo_Feng
 * 
 */
public enum CardType {
	AMEX("AMEX"), DEBIT("DEBIT"), MASTERCARD("MASTERCARD"), VISA("VISA");

	private String cardType;

	/**
	 * constructor
	 * 
	 * @param cardType
	 */
	private CardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * return the CardType object by index number
	 * 
	 * @param index
	 * @return cardtype
	 */
	public static CardType getCard(int index) {
		switch (index) {
		case 1:
			return AMEX;
		case 2:
			return DEBIT;
		case 3:
			return MASTERCARD;
		case 4:
			return VISA;
		}
		return null;
	}

	/**
	 * return the index of the card given the name
	 * 
	 * @param name
	 * @return index
	 */
	public static int getCardIndexByName(String name) {
		if (name.compareToIgnoreCase("AMEX") == 0) {
			return 1;
		} else if (name.compareToIgnoreCase("DEBIT") == 0) {
			return 2;
		} else if (name.compareToIgnoreCase("MASTERCARD") == 0) {
			return 3;
		} else if (name.compareToIgnoreCase("VISA") == 0) {
			return 4;
		}
		return 0;
	}

	/**
	 * return the type of the card
	 * 
	 * @return
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * return a string array of all card types
	 * 
	 * @return
	 */
	public static String[] getAllCardTypes() {
		String[] cardTypes = { AMEX.getCardType(), DEBIT.getCardType(),
				MASTERCARD.getCardType(), VISA.getCardType() };
		return cardTypes;
	}

}
