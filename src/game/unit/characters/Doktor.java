package game.unit.characters;

import game.core.HexTile;
import game.items.armors.LeatherArmor;
import game.items.weapons.Sword;
import game.match.Player;
import game.unit.Unit;

public class Doktor extends Unit{
	public Doktor(Player owner, HexTile onTile) {
		super(owner, onTile, new Sword(), new Sword(), new LeatherArmor(), "DOKTOR");
	}

}
