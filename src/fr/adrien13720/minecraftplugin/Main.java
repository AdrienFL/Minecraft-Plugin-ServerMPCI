package fr.adrien13720.minecraftplugin;

import org.bukkit.plugin.java.JavaPlugin;

import fr.adrien13720.minecraftplugin.commands.CommandTest;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		System.out.println("Le plugin démarre");
		getCommand("test").setExecutor(new CommandTest());
		getCommand("alert").setExecutor(new CommandTest());
	}
	@Override
	public void onDisable() {
		System.out.println("Le plugin s'arrête");


	}


}

