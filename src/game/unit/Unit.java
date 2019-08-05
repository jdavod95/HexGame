package game.unit;

import java.util.ArrayList;
import java.util.List;

import game.core.HexTile;
import game.items.Armor;
import game.items.Weapon;
import game.match.Player;
import game.match.StatModifier;

public abstract class Unit {
	private final static int BASE_HEALTH = 1;
	private final static int BASE_SPEED = 3;
	private final static int BASE_DEFENSE = 0;
	private final static double BASE_EVASION = 0.1;
	
	private final List<StatModifier> modifiers;
	
	private int healthLevel;
	private int speedLevel;
	private int defenseLevel;
	private double evasionLevel;

	private Weapon hand1;
	private Weapon hand2;
	private Armor armor;
	private SkillSet skills;
	
	private boolean incapacitated;

	private HexTile onTile;
	private Player owner;
	private String texName;
	
	public Unit(Player owner, HexTile onTile, Weapon hand1, Weapon hand2, Armor armor, String texName) {
		this.onTile = onTile;
		this.texName = texName;
		this.owner = owner;
		this.hand1 = hand1;
		this.hand2 = hand2;
		this.armor = armor;
		this.skills = new SkillSet(hand1, hand2);
		
		incapacitated = false;

		modifiers = new ArrayList<>();
		modifiers.add(hand1.getMods());
		modifiers.add(hand2.getMods());
		modifiers.add(armor.getMods());

		initStat();
		applyModifiers();
	}

	public void addStatMod(StatModifier mod) {
		modifiers.add(mod);
		applyModifiers();
	}
	
	private void applyModifiers() {
		initStat();
		for(StatModifier mod : modifiers) {
			healthLevel += mod.getHealth();
			speedLevel += mod.getSpeed();
			evasionLevel += mod.getEvasion();
			defenseLevel += mod.getDefense();
		}
	}

	public String getTexName() {
		return texName;
	}

	public Player getOwner() {
		return owner;
	}
	
	public boolean isOwner(Player player) {
		return owner.equals(player);
	}
	
	public boolean isIncapacitated() {
		return incapacitated;
	}

	private void initStat() {
		healthLevel = BASE_HEALTH;
		defenseLevel = BASE_DEFENSE;
		speedLevel = BASE_SPEED;
		evasionLevel = BASE_EVASION;
	}

	public int getSpeed() {
		return speedLevel;
	}

	public HexTile getOnTile() {
		return onTile;
	}

	public void setOnTile(HexTile hexTile) {
		onTile = hexTile;
	}

	public Skill getSkill(int slot) {
		switch(slot) {
			case 0: return hand1.getFirstSkill();
			default: return hand2.getSecondSkill();
		}
	}
}
