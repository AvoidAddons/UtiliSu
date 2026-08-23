package pl.olafcio.utilisu.commands;

import pl.olafcio.avoid.mods.annotation_processor.AutoCommand;
import pl.olafcio.avoid.net.chat.component.Colors;
import pl.olafcio.avoid.net.chat.component.Components;
import pl.olafcio.avoid.net.command.Command;
import pl.olafcio.avoid.net.command.annotation.PermissionLevel;
import pl.olafcio.avoid.net.command.annotation.Syntax;
import pl.olafcio.avoid.net.command.handling.Usage;
import pl.olafcio.avoid.net.id.Identification;
import pl.olafcio.avoid.net.server.Server;

import java.util.concurrent.atomic.AtomicInteger;

@AutoCommand
public class KillallCommand extends Command {
    @Syntax("/killall <text>")
    @PermissionLevel(value = "utilisu.killall", level = PermissionLevel.Enum.GAMEMASTERS)
    public void heal(Usage input) {
        var type = input.getArgument("text", String.class);
        var id = Identification.of(type);

        var num = new AtomicInteger();

        Server.getWorlds().forEach(world -> {
            world.eachEntity(entity -> {
                if (entity.type().getID().equals(id)) {
                    entity.damage(Integer.MAX_VALUE);
                    num.getAndIncrement();
                }
            });
        });

        input.getExecutor().sendMessage(
                Components.literal("Killed ")
                          .color(Colors.GOLD)
                          .append(Components.literal(num.toString())
                                            .color(Colors.RED))
                          .append(Components.literal(" entities.")
                                            .color(Colors.GOLD))
        );
    }
}
