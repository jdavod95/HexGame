package game.match;

public class StatModifier {
	public static final StatModifier EMPTY = new StatModifier(0, 0, 0, 0);

	private int health;
	private int speed;
	private int defense;
	private double evasion;
	
	public StatModifier(int health, int speed, int defense, double evasion) {
		this.health = health;
		this.speed = speed;
		this.defense = defense;
		this.evasion = evasion;
	}

	public int getHealth() {
		return health;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDefense() {
		return defense;
	}

	public double getEvasion() {
		return evasion;
	}
	
	
}
