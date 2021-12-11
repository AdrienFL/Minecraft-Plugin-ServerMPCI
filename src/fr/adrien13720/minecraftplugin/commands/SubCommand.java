package fr.adrien13720.minecraftplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public abstract class SubCommand {
	boolean onCommand(Player player, Command command, String[] args) {
		
		return true;
	}
}
