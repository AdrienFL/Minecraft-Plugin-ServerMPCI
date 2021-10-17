package fr.adrien13720.minecraftplugin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class MinecraftPluginlisteners implements Listener {
	

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.sendTitle("§6Bienvenue", "§6sur le server §b" + player.getName(), 30, 50, 30); //Message d'entrée (au milieu de l'écran)
		
		//Change le message de join dans le chat
		switch(player.getName()) {
		 
			case "Adrien13720":
				event.setJoinMessage("§6Un énorme bg : §b" + player.getName() + "§2 est arrivé sur le server.");
				break;
			
			case "MagicZemmour":
				event.setJoinMessage("§4Le Z est dans la place " + player.getName());
				break;
			
			
			default:
				event.setJoinMessage("Bienvenue à toi " + player.getName());
			
		
		}
		
			
	}


	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();
		if(it == null) {
			return;
		}
		if(it.getType() == Material.COMPASS && it.getItemMeta().hasDisplayName()) {
			Inventory inv = Bukkit.createInventory(null, 18, "");
			player.openInventory(inv);
			
		}
		
	}
	
}
