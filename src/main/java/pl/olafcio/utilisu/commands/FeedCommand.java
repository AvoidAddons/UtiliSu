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
public class FeedCommand extends Command {
    @Syntax("/feed")
    @PermissionLevel(value = "utilisu.feed", level = PermissionLevel.Enum.GAMEMASTERS)
    public void feed(Usage input) {
        if (input.getExecutor() instanceof Player player) {
            player.updateFoodLevel(20);

            player.sendMessage(Components.literal("Fed.")
                                         .color(Colors.GOLD));
        } else {
            input.getExecutor().sendMessage(Components.literal("Can only feed players")
                                                      .color(Colors.RED));
        }
    }

    @Syntax("/feed <player>")
    @PermissionLevel(value = "utilisu.feed.others", level = PermissionLevel.Enum.GAMEMASTERS)
    public void feedPlayer(Usage input) {
        var player = input.getArgument("player", Player.class);

        player.updateFoodLevel(20);

        input.getExecutor().sendMessage(
                Components.literal("Fed ")
                          .color(Colors.GOLD)
                          .append(Components.literal(player.getName())
                                            .color(Colors.YELLOW))
                          .append(Components.literal(".")
                                            .color(Colors.GOLD))
        );
    }
}
