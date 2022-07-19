package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.util.PlayerUtil;

public class CheckpointResetCommand implements Command<CommandSourceStack> {
	private static final CheckpointResetCommand CMD = new CheckpointResetCommand();

	public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {
		LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("checkpointreset").executes(CMD);

		builder.then(Commands.argument("player", EntityArgument.player()).requires(command -> command.hasPermission(0))
				.executes(context -> clearCheckpoint(context, EntityArgument.getPlayer(context, "player"))));

		return builder;
	}

	private static int clearCheckpoint(CommandContext<CommandSourceStack> cmd, Entity entity) throws CommandSyntaxException {
		ServerPlayer pl = (ServerPlayer)entity;

		if (pl != cmd.getSource().getEntity() && !cmd.getSource().hasPermission(4))
			throw AoACommand.NO_PERMISSION_EXCEPTION.create();

		AoACommand.feedback(cmd.getSource(), "CheckpointReset", "--", AoACommand.CommandFeedbackType.INFO);
		PlayerUtil.getAdventPlayer(pl).clearCheckpoint();
		AoACommand.feedback(cmd.getSource(), "CheckpointReset", "command.aoa.checkpointreset.success", AoACommand.CommandFeedbackType.SUCCESS);

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) {
		AoACommand.feedback(context.getSource(), "CheckpointReset", "command.aoa.checkpointreset.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}
}