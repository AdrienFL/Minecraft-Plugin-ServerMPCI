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
			if (cmd.getName().equalsIgnoreCase("test")) { //commande de test
				player.sendMessage(ChatColor.RED + "test");
				return true;
			}
			
			if (cmd.getName().equalsIgnoreCase("alert")) { //commande pour Broadcast un message
				//alert --> pas d'arguments
				if (args.length == 0) {
					player.sendMessage("la commande est : /alert <message>");
				}
				//alert <text text text>
				if (args.length >= 1) {
					StringBuilder bc = new StringBuilder();
					for(String part : args) {
						bc.append(part + " ");
					}
					
					switch(args[0]) {
					
					case("magic"):
						bc.delete(0, 5);
						Bukkit.broadcastMessage("Annonce : "+ChatColor.MAGIC+bc.toString());
						break;
					
					case("bold"):
						bc.delete(0, 4);
						Bukkit.broadcastMessage("Annonce : "+ChatColor.BOLD+ bc.toString());
						break;
						
					case("red"):
						bc.delete(0, 3);
						Bukkit.broadcastMessage("Annonce : "+ChatColor.RED+ bc.toString());
						break;

					case("gold"):
						bc.delete(0, 4);
						Bukkit.broadcastMessage("Annonce : "+ChatColor.GOLD+ bc.toString());
						break;
						
					default:
						Bukkit.broadcastMessage("Annonce : "+ bc.toString());

					}

				}
				return true;
			}
		}
		return false;
	}
}
