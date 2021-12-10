package fr.adrien13720.minecraftplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandInfo implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		System.out.println(CommandFaction.diamondcount);
		System.out.println(CommandFaction.teamdiamondcount);
		return true;
	}

}
