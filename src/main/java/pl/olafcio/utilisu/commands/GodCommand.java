package pl.olafcio.utilisu.commands;

import pl.olafcio.avoid.mods.annotation_processor.AutoCommand;
import pl.olafcio.avoid.net.chat.component.Colors;
import pl.olafcio.avoid.net.chat.component.Components;
import pl.olafcio.avoid.net.command.Command;
import pl.olafcio.avoid.net.command.annotation.PermissionLevel;
import pl.olafcio.avoid.net.command.annotation.Syntax;
import pl.olafcio.avoid.net.command.handling.Usage;
import pl.olafcio.avoid.net.player.Player;

@AutoCommand
public class GodCommand extends Command {
    @Syntax("/god")
    @PermissionLevel(value = "utilisu.god", level = PermissionLevel.Enum.GAMEMASTERS)
    public void god(Usage input) {
        if (input.getExecutor() instanceof Player player) {
            if (player.isInvulnerable()) {
                player.setInvulnerable(false);
                player.sendMessage(Components.literal("Disabled god mode.")
                                             .color(Colors.GOLD));
            } else {
                player.setInvulnerable(true);
                player.sendMessage(Components.literal("Enabled god mode.")
                                             .color(Colors.GOLD));
            }
        } else {
            input.getExecutor().sendMessage(Components.literal("Can only toggle invulnerability of players")
                                                      .color(Colors.RED));
        }
    }

    @Syntax("/god <player>")
    @PermissionLevel(value = "utilisu.god.others", level = PermissionLevel.Enum.GAMEMASTERS)
    public void godPlayer(Usage input) {
        var player = input.getArgument("player", Player.class);

        if (player.isInvulnerable()) {
            player.setInvulnerable(false);
            input.getExecutor().sendMessage(Components.literal("Disabled god mode for ")
                                                      .color(Colors.GOLD)
                                                      .append(Components.literal(player.getName())
                                                                                       .color(Colors.YELLOW))
                                                      .append(Components.literal(".")
                                                                        .color(Colors.GOLD)));
        } else {
            player.setInvulnerable(true);
            input.getExecutor().sendMessage(Components.literal("Enabled god mode for ")
                                                      .color(Colors.GOLD)
                                                      .append(Components.literal(player.getName())
                                                                        .color(Colors.YELLOW))
                                                      .append(Components.literal(".")
                                                                        .color(Colors.GOLD)));
        }
    }
}
