package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.tslat.aoa3.util.PlayerUtil;

public class PortalResetCommand implements Command<CommandSource> {
	private static final PortalResetCommand CMD = new PortalResetCommand();

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("portalreset").executes(CMD);

		builder.then(Commands.argument("player", EntityArgument.player()).requires(command -> command.hasPermission(0))
				.executes(context -> resetPortals(context, EntityArgument.getPlayer(context, "player"))));

		return builder;
	}

	private static int resetPortals(CommandContext<CommandSource> cmd, Entity entity) throws CommandSyntaxException {
		ServerPlayerEntity pl = (ServerPlayerEntity)entity;

		if (pl != cmd.getSource().getEntity() && !cmd.getSource().hasPermission(4))
			throw AoACommand.NO_PERMISSION_EXCEPTION.create();

		AoACommand.feedback(cmd.getSource(), "PortalReset", "--", AoACommand.CommandFeedbackType.INFO);
		PlayerUtil.getAdventPlayer(pl).flushPortalReturnLocations();
		AoACommand.feedback(cmd.getSource(), "PortalReset", "command.aoa.portalreset.success", AoACommand.CommandFeedbackType.SUCCESS);

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSource> context) {
		AoACommand.feedback(context.getSource(), "PortalReset", "command.aoa.portalreset.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}
}
