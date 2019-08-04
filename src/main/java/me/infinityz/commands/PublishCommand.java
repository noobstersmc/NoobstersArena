package me.infinityz.commands;

import me.infinityz.NoobstersArenaLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PublishCommand implements CommandExecutor {
    private NoobstersArenaLobby instance;

    public PublishCommand(NoobstersArenaLobby instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("publish")){
            if(args.length > 0){
                StringBuilder stringBuilder = new StringBuilder();
                for (String arg : args) {
                    if(arg.equals(args[0]))continue;
                    stringBuilder.append(arg).append(" ");
                }
                instance.getQueueManager().getRedisPub().publish(args[0], stringBuilder.toString());
                return true;
            }
            commandSender.sendMessage("Not enough args");
            return true;
        }
        return false;
    }
}
