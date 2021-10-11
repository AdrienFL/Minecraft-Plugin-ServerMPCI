package fr.adrien13720.minecraftplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		System.out.println("Le plugin démarre");
		
	}
	@Override
	public void onDisable() {
		System.out.println("Le plugin s'arrête");
	
	
	}


}
