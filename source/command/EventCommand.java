package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.server.command.EnumArgument;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.util.StringUtil;

import javax.annotation.Nullable;

public class EventCommand implements Command<CommandSource> {
	private static final EventCommand CMD = new EventCommand();

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("events").executes(CMD);

		builder.then(Commands.literal("list").requires(command -> command.hasPermissionLevel(0))
				.executes(EventCommand::listEvents));
		builder.then(Commands.literal("check").requires(command -> command.hasPermissionLevel(0))
				.executes((context) -> checkEvents(context, null))
				.then(Commands.argument("event", EnumArgument.enumArgument(OverworldEvents.Event.class))
					.executes((context) -> checkEvents(context, context.getArgument("event", OverworldEvents.Event.class)))));
		builder.then(Commands.literal("start").requires(command -> command.hasPermissionLevel(4))
				.then(Commands.argument("event", EnumArgument.enumArgument(OverworldEvents.Event.class))
					.executes(EventCommand::startEvent)));
		builder.then(Commands.literal("stop").requires(command -> command.hasPermissionLevel(4))
				.then(Commands.argument("event", EnumArgument.enumArgument(OverworldEvents.Event.class))
						.executes(EventCommand::stopEvent)));

		return builder;
	}

	private static int listEvents(CommandContext<CommandSource> cmd) {
		AoACommand.feedback(cmd.getSource(), "Event", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(cmd.getSource(), "Event", "command.aoa.event.list", AoACommand.CommandFeedbackType.SUCCESS);

		StringBuilder builder = new StringBuilder();

		for (OverworldEvents.Event ev : OverworldEvents.Event.values()) {
			builder.append(", ");
			builder.append(StringUtil.toTitleCase(ev.toString()));
		}

		AoACommand.feedback(cmd.getSource(), "Event", builder.substring(2), AoACommand.CommandFeedbackType.INFO);

		return 1;
	}

	private static int checkEvents(CommandContext<CommandSource> cmd, @Nullable OverworldEvents.Event checkEvent) {
		AoACommand.feedback(cmd.getSource(), "Event", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(cmd.getSource(), "Event", "command.aoa.event.check", AoACommand.CommandFeedbackType.SUCCESS);

		if (checkEvent == null) {
			StringBuilder builder = new StringBuilder();

			for (OverworldEvents.Event ev : OverworldEvents.Event.values()) {
				if (OverworldEvents.isEventActive(ev)) {
					builder.append(", ");
					builder.append(StringUtil.toTitleCase(ev.toString()));
				}
			}

			if (builder.length() == 0)
				builder.append("   ");

			AoACommand.feedback(cmd.getSource(), "Event", builder.substring(2), AoACommand.CommandFeedbackType.INFO);

			return builder.length();
		}
		else {
			AoACommand.feedback(cmd.getSource(), "Event", OverworldEvents.isEventActive(checkEvent) ? "command.aoa.event.check.active" : "command.aoa.event.check.inactive", AoACommand.CommandFeedbackType.INFO, StringUtil.toTitleCase(checkEvent.toString()));

			return 1;
		}
	}

	private static int startEvent(CommandContext<CommandSource> cmd) {
		OverworldEvents.Event ev = cmd.getArgument("event", OverworldEvents.Event.class);

		AoACommand.feedback(cmd.getSource(), "Event", "command.aoa.event.starting", AoACommand.CommandFeedbackType.SUCCESS, StringUtil.toTitleCase(ev.toString()));

		if (OverworldEvents.isEventActive(ev)) {
			AoACommand.error(cmd.getSource(), "Event", "command.aoa.event.starting.fail", StringUtil.toTitleCase(ev.toString()));
		}
		else {
			OverworldEvents.activateEvent(cmd.getSource().getServer().getWorld(DimensionType.OVERWORLD), ev);
		}

		return 1;
	}

	private static int stopEvent(CommandContext<CommandSource> cmd) {
		OverworldEvents.Event ev = cmd.getArgument("event", OverworldEvents.Event.class);

		AoACommand.feedback(cmd.getSource(), "Event", "command.aoa.event.stopping", AoACommand.CommandFeedbackType.SUCCESS, StringUtil.toTitleCase(ev.toString()));

		if (OverworldEvents.isEventActive(ev)) {
			OverworldEvents.deactivateEvent(cmd.getSource().getServer().getWorld(DimensionType.OVERWORLD), ev);
		}
		else {
			AoACommand.error(cmd.getSource(), "Event", "command.aoa.event.stopping.fail", StringUtil.toTitleCase(ev.toString()));
		}

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSource> context) {
		AoACommand.feedback(context.getSource(), "Event", "command.aoa.event.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}
}
