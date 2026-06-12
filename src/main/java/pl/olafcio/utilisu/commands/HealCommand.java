package pl.olafcio.utilisu.commands;

import pl.olafcio.avoid.mods.annotation_processor.AutoCommand;
import pl.olafcio.avoid.net.chat.component.Colors;
import pl.olafcio.avoid.net.chat.component.Components;
import pl.olafcio.avoid.net.command.Command;
import pl.olafcio.avoid.net.command.annotation.Syntax;
import pl.olafcio.avoid.net.command.handling.Usage;
import pl.olafcio.avoid.net.player.Player;

@AutoCommand
public class HealCommand extends Command {
    @Syntax("/heal")
    public void heal(Usage input) {
        if (input.getExecutor() instanceof Player player) {
            player.updateHealth(player.getMaxHealth());
            player.updateFoodLevel(20);
            player.updateFoodSaturation(5F);

            if (player.isOnFire())
                player.extinguishFire();

            player.sendMessage(Components.literal("Healed.")
                                         .color(Colors.GOLD));
        } else {
            input.getExecutor().sendMessage(Components.literal("Can only heal players")
                                                      .color(Colors.RED));
        }
    }

    @Syntax("/heal <player>")
    public void healPlayer(Usage input) {
        var player = input.getArgument("player", Player.class);

        player.updateHealth(player.getMaxHealth());
        player.updateFoodLevel(20);
        player.updateFoodSaturation(5F);

        if (player.isOnFire())
            player.extinguishFire();

        input.getExecutor().sendMessage(
                Components.literal("Healed ")
                          .color(Colors.GOLD)
                          .append(Components.literal(player.getName())
                                            .color(Colors.YELLOW))
                          .append(Components.literal(".")
                                            .color(Colors.GOLD))
        );
    }
}
