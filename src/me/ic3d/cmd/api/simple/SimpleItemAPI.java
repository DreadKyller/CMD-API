package me.ic3d.cmd.api.simple;

import java.util.HashMap;
import java.util.List;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

@SuppressWarnings("rawtypes")
public class SimpleItemAPI extends PlayerListener{
	
	private HashMap<CMDListener, String> registered;
	
	public SimpleItemAPI(){
	}
	
	public boolean register(CMDListener c, String text){
		boolean ret = true;
		if(registered.containsKey(c)){
			ret=false;
		}
		registered.put(c, text);
		return ret;
	}
	
	public void onPlayerChat(PlayerChatEvent event){
		String chat = event.getMessage();
		for(CMDListener cmd: registered.keySet()){
			
			char[] chars = registered.get(cmd).toCharArray(); 
			
			String sub = chat.substring(0, chars.length);
			
			if(sub.equalsIgnoreCase(registered.get(cmd))){
				event.setCancelled(true);
				String[] agg = chat.split(" ");
				String comd = agg[0].substring(1, agg[0].toCharArray().length);
				String usage=agg[0].substring(0, 1);
				String[] content = new String[agg.length-1];
				for(int i=1;i<agg.length;i++){
					content[i-1]=agg[i];
				}
				cmd.onCustomCommand(event.getPlayer() ,usage, comd, content);
			}
		}
	}
	
}
