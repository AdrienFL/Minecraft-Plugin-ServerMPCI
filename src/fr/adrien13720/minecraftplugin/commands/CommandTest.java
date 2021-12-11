package fr.adrien13720.minecraftplugin.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandTest implements TabExecutor {

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
					
					case("message"):
						if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1])) && Bukkit.getPlayer(args[1]) != null) {
							Player player1 = Bukkit.getPlayer(args[1]);
							bc.delete(0, 9 + Bukkit.getPlayer(args[1]).getName().length());
							player1.chat(bc.toString());
						}
						else {
							player.sendMessage("§cLa commande s'utilise comme /alert message <joueur> <message>");
						}
						break;
						
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
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String cmd2, String[] args){
		List<String> autoCompletes = new ArrayList<>();
		if(cmd.getName().equalsIgnoreCase("alert")) {
			if(args.length == 1) {
				autoCompletes.add("magic");
				autoCompletes.add("bold");
				autoCompletes.add("gold");
				autoCompletes.add("red");
				autoCompletes.add("message");
			}
		}
		return autoCompletes;

	}

}
