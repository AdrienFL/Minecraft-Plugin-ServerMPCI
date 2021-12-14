package fr.adrien13720.minecraftplugin.commands;



import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { // Commande de TP au spawn
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Location spawn = new Location(player.getWorld(), -256.5, 80 , 1580, 180f, -1.8f); //A changer quand on aura les coords du spawn
			player.sendMessage("�eVous avez �t� tp au spawn");
			player.teleport(spawn);
			
		}
		return false;
	}

}
