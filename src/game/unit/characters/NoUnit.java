package game.unit.characters;

import game.core.HexTile;
import game.items.Armor;
import game.items.Weapon;
import game.match.Player;
import game.unit.Unit;

public class NoUnit extends Unit{

	public NoUnit(HexTile onTile) {
		super(Player.EMPTY, onTile, Weapon.EMPTY, Weapon.EMPTY, Armor.EMPTY, "");
	}

}
