package fr.adrien13720.minecraftplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandTest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if (cmd.getName().equalsIgnoreCase("test")) {
				player.sendMessage(ChatColor.RED + "Bravo, tu as reussi le test");
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("alert")) {
				//alert --> pas d'arguments
				if (args.length == 0) {
					player.sendMessage("la commande est : /alert <message>");
				}
				//alert <texte texte texte>
				if (args.length == 1) {
					StringBuilder bc = new StringBuilder();
					for(String part : args) {
						bc.append(part + " ");
					Bukkit.broadcastMessage(bc.toString());
					
					}
				}
				return true;
			}
		}
		return false;
	}

}
