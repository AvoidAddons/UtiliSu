package pl.olafcio.utilisu.commands;

import pl.olafcio.avoid.mods.annotation_processor.AutoCommand;
import pl.olafcio.avoid.net.chat.component.Colors;
import pl.olafcio.avoid.net.chat.component.Components;
import pl.olafcio.avoid.net.command.Command;
import pl.olafcio.avoid.net.command.annotation.Syntax;
import pl.olafcio.avoid.net.command.handling.Usage;
import pl.olafcio.avoid.net.player.Player;

@AutoCommand
public class ExtinguishCommand extends Command {
    @Syntax("/extinguish")
    public void extinguish(Usage input) {
        if (input.getExecutor() instanceof Player player) {
            if (player.isOnFire())
                player.extinguishFire();

            player.sendMessage(Components.literal("Extinguished.")
                                         .color(Colors.GOLD));
        } else {
            input.getExecutor().sendMessage(Components.literal("Can only extinguish players")
                                                      .color(Colors.RED));
        }
    }

    @Syntax("/extinguish <player>")
    public void extinguishPlayer(Usage input) {
        var player = input.getArgument("player", Player.class);
        if (player.isOnFire())
            player.extinguishFire();

        input.getExecutor().sendMessage(
                Components.literal("Extinguished ")
                          .color(Colors.GOLD)
                          .append(Components.literal(player.getName())
                                            .color(Colors.YELLOW))
                          .append(Components.literal(".")
                                            .color(Colors.GOLD))
        );
    }
}
