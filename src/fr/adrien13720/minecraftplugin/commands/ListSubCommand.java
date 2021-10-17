package fr.adrien13720.minecraftplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class ListSubCommand extends SubCommand { // liste les factions existantes
	public boolean onCommand(Player player, Command command, String[] args) {
		player.sendMessage("Liste des Factions : " + CommandFaction.teamnames);
		return true;
	}
		
}



