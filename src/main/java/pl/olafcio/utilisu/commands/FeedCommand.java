package pl.olafcio.utilisu.commands;

import pl.olafcio.avoid.mods.annotation_processor.AutoCommand;
import pl.olafcio.avoid.net.chat.component.Colors;
import pl.olafcio.avoid.net.chat.component.Components;
import pl.olafcio.avoid.net.command.Command;
import pl.olafcio.avoid.net.command.annotation.Syntax;
import pl.olafcio.avoid.net.command.handling.Usage;
import pl.olafcio.avoid.net.player.Player;

@AutoCommand
public class FeedCommand extends Command {
    @Syntax("/feed")
    public void feed(Usage input) {
        if (input.getExecutor() instanceof Player player) {
            player.updateFoodLevel(20);
            player.updateFoodSaturation(5F);

            player.sendMessage(Components.literal("Fed.")
                                         .color(Colors.GOLD));
        } else {
            input.getExecutor().sendMessage(Components.literal("Can only feed players")
                                                      .color(Colors.RED));
        }
    }
}
