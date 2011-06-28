package me.ic3d.cmd.api.example;

import me.ic3d.cmd.api.simple.CMDListener;
import me.ic3d.cmd.api.simple.SimpleItemAPI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleItem extends JavaPlugin{
	
	SimpleItemAPI api = new SimpleItemAPI();
	
	@Override
	public void onDisable(){
	}

	@Override
	public void onEnable(){
		
		api.register(new APITest(), "$");
		
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_CHAT, api, Event.Priority.Monitor, this);
	}
	
	public class APITest extends CMDListener{

		@Override
		public void onCustomCommand(Player p, String usage, String cmd, String[] content){
			if(usage.equalsIgnoreCase("$")&&content.length<2){
				Material mat = Material.valueOf(cmd);
				if(mat==null){
					p.sendMessage(ChatColor.DARK_RED+"No Item Named "+cmd+" exists!");
					return;
				}
				int amount = 1;
				if(content.length==1){
					try{
						amount = Integer.parseInt(content[0]);
					}catch(Exception e){
					}
				}
				ItemStack item = new ItemStack(mat, amount);
				p.getInventory().addItem(item);
			}
		}
	}
	
}
