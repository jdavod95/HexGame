package game.match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import game.core.HexTileActions;
import game.core.screen.battleField.GameField;
import game.unit.Unit;

public class Combat {
	private Player attacker;
	private Player defender;
	private Player winner = null;
	
	private List<Unit> allUnits;
	private List<Unit> activeUnits;
	private List<Unit> restingUnits;
	
	private int roundCounter;
	private Unit selectedUnit;
	private static boolean playerActionTaken = false;
	
	public Combat(Player player1, Player player2) {
		this.attacker = player1;
		this.defender = player2;
		allUnits  = new ArrayList<>();
		
		for (Unit unit : player1.getGroup()) 
			allUnits.add(unit);
		
		for (Unit unit : player2.getGroup()) 
			allUnits.add(unit);

		roundCounter = 0;
		activeUnits = new ArrayList<>(allUnits);
		restingUnits = new ArrayList<>();
		sortActiveUnits();
	}

	public void cycle() {
		/* 1 round lasts until when there are no active units,
		 * or a player is defeated
		 * 
		 * round{
		 * 	unit apply effects
		 * 	unit takes action
		 * 		movement
		 * 		skill use
		 * 	apply target unit mods
		 *  sortActiveUnits
		 * }
		 * 
		 * */
		selectedUnit = activeUnits.get(activeUnits.size() - 1);
		HexTileActions.setSelectedTile(selectedUnit.getOnTile());
		HexTileActions.unitActionSelect("MOVE");
		if(playerActionTaken) {
			playerActionTaken = false;
			GameField.clearHighlight();
			endTurn();
		}
	}
	private void endTurn() {
		restingUnits.add(activeUnits.remove(activeUnits.size() - 1));
		if(activeUnits.size() == 0)
			nextRound();
		winner = checkWinCondition();
		if(winner != null)
			gameOver(winner);
	}

	private void nextRound() {
		roundCounter++;
		activeUnits = new ArrayList<>(restingUnits);
		restingUnits.clear();
		sortActiveUnits();
	}

	public void sortActiveUnits() {
		// puts slowest units first
		activeUnits.sort(new Comparator<Unit>() {
			int result = 0;
			@Override
			public int compare(Unit o1, Unit o2) {
				if(o1.getSpeed() < o2.getSpeed())
					result = -1;
				else if (o1.getSpeed() > o2.getSpeed())
					result = 1;
				else {
					if(o1.isOwner(attacker))
						result = -1;
					else
						result = 1;
				}
				return result;
			}});
	}

	private void gameOver(Player winner) {
		
	}

	public void initCombat() {
		
	}
	
	private Player checkWinCondition() {
		Player winner = null;
		for(Unit unit : allUnits)
			if(!unit.isIncapacitated() && winner == null)
				winner = unit.getOwner();
			else if(!unit.isIncapacitated() && !unit.isOwner(winner)) {
				winner = null;
				break;
			}
		return winner;
	}
	
	public static void takenPlayerAction() {
		playerActionTaken = true;
	}
}
