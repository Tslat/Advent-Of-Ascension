package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.command.EnumArgument;
import net.tslat.aoa3.library.constant.ScreenImageEffect;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.StringUtil;

import java.util.Collection;

public class ScreenEffectCommand implements Command<CommandSourceStack> {
	private static final ScreenEffectCommand CMD = new ScreenEffectCommand();

	public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {
		LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("screeneffect").executes(CMD);

		builder.then(Commands.argument("players", EntityArgument.players()).requires(command -> command.hasPermission(1))
						.then(Commands.argument("effect", EnumArgument.enumArgument(ScreenImageEffect.Type.class))
								.then(Commands.argument("duration", IntegerArgumentType.integer(0, Integer.MAX_VALUE))
										.executes(cmd -> doScreenEffect(cmd, new ColourUtil.Colour(1f, 1f, 1f, 1f), 1f, false))
										.then(Commands.argument("red", IntegerArgumentType.integer(0, 255))
												.then(Commands.argument("green", IntegerArgumentType.integer(0, 255))
														.then(Commands.argument("blue", IntegerArgumentType.integer(0, 255))
																.then(Commands.argument("alpha", IntegerArgumentType.integer(0, 255))
																		.executes(cmd -> doScreenEffect(cmd, colourFromArgs(cmd), 1f, false))
																		.then(Commands.argument("scale", FloatArgumentType.floatArg(0, Float.MAX_VALUE))
																				.executes(cmd -> doScreenEffect(cmd, colourFromArgs(cmd), FloatArgumentType.getFloat(cmd, "scale"), false))
																				.then(Commands.argument("fullscreen", BoolArgumentType.bool())
																						.executes(cmd -> doScreenEffect(cmd, colourFromArgs(cmd), FloatArgumentType.getFloat(cmd, "scale"), BoolArgumentType.getBool(cmd, "fullscreen"))))))))))));

		return builder;
	}

	private static ColourUtil.Colour colourFromArgs(CommandContext<CommandSourceStack> cmd) {
		return new ColourUtil.Colour(IntegerArgumentType.getInteger(cmd, "red") / 255f, IntegerArgumentType.getInteger(cmd, "green") / 255f, IntegerArgumentType.getInteger(cmd, "blue") / 255f, IntegerArgumentType.getInteger(cmd, "alpha") / 255f);
	}

	private static int doScreenEffect(CommandContext<CommandSourceStack> cmd, ColourUtil.Colour colour, float scale, boolean fullscreen) throws CommandSyntaxException {
		final ScreenImageEffect effect = new ScreenImageEffect(cmd.getArgument("effect", ScreenImageEffect.Type.class))
				.duration(IntegerArgumentType.getInteger(cmd, "duration"))
				.fullscreen(fullscreen)
				.scaled(scale)
				.coloured(colour.packed());

		Collection<ServerPlayer> players = EntityArgument.getPlayers(cmd, "players");

		for (ServerPlayer pl : players) {
			effect.sendToPlayer(pl);
		}

		if (players.size() == 1) {
			AoACommand.feedback(cmd.getSource(), "ScreenEffect", "command.aoa.screeneffect.success.single", AoACommand.CommandFeedbackType.SUCCESS, Component.literal(StringUtil.toTitleCase(effect.getType().name())), players.iterator().next().getDisplayName(), Component.literal(NumberUtil.roundToNthDecimalPlace(effect.getDuration() / 20f, 1)));
		}
		else {
			AoACommand.feedback(cmd.getSource(), "ScreenEffect", "command.aoa.screeneffect.success.multiple", AoACommand.CommandFeedbackType.SUCCESS, Component.literal(StringUtil.toTitleCase(effect.getType().name())), Component.literal(String.valueOf(players.size())), Component.literal(NumberUtil.roundToNthDecimalPlace(effect.getDuration() / 20f, 1)));
		}

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) {
		AoACommand.feedback(context.getSource(), "ScreenEffect", "command.aoa.screeneffect.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}
}