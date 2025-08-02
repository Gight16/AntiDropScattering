package me.deaglewolf.antidropscattering.commands;

import me.deaglewolf.antidropscattering.AntiDropScattering;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ADSCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender.hasPermission("ads.admin")) {
            if (args.length > 0 && args[0].equals("toggle")) {
                boolean b = args.length > 1 ? parseToggle(args[1]) : !AntiDropScattering.getInstance().pluginIsEnabled();

                AntiDropScattering.getInstance().toggle(b);

                sender.sendMessage("AntiDropScattering toggled to " + (b ? "on" : "off"));

                return true;
            } else {
                sender.sendMessage("/ads toggle [on/off]");
            }
        } else {
            sender.sendMessage("You don't have permission for this.");
        }

        return false;
    }

    public boolean parseToggle(@Nullable String s) {
        if (s == null)
            return !AntiDropScattering.getInstance().pluginIsEnabled();

        if (s.equals("on"))
            return true;

        if (s.equals("off"))
            return false;

        return Boolean.parseBoolean(s);
    }
}