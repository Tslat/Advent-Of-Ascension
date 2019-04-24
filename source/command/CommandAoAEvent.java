package net.tslat.aoa3.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.Collections;
import java.util.List;

public class CommandAoAEvent extends CommandBase {
	private static final TextComponentString commandPrefix = new TextComponentString(TextFormatting.DARK_RED + "[AoA" + TextFormatting.GOLD + "Event" + TextFormatting.DARK_RED + "] ");

	@Override
	public String getName() {
		return "aoaevent";
	}

	@Override
	public List<String> getAliases() {
		return Collections.<String>singletonList("aoaevents");
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "command.aoaevent.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			messageSender(sender, Enums.CommandFeedbackType.INFO, "command.aoaevent.desc");

			return;
		}

		StringBuilder builder;
		String eventName;
		Enums.CreatureEvents event;

		switch (args[0]) {
			case "list":
				messageSender(sender, Enums.CommandFeedbackType.INFO, "--");
				messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaevent.list");

				builder = new StringBuilder();

				for (Enums.CreatureEvents ev : Enums.CreatureEvents.values()) {
					builder.append(", ");
					builder.append(ev.toString().toLowerCase());
				}

				sender.sendMessage(new TextComponentString(builder.toString().substring(2)).setStyle(new Style().setColor(TextFormatting.GRAY)));
				break;
			case "check":
				messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaevent.check");

				builder = new StringBuilder();

				for (Enums.CreatureEvents ev : Enums.CreatureEvents.values()) {
					if (OverworldEvents.isEventActive(ev)) {
						builder.append(", ");
						builder.append(StringUtil.capitaliseAllWords(ev.name().toLowerCase().replace("_", " ")));
					}
				}

				if (builder.length() == 0)
					builder.append("--");

				sender.sendMessage(new TextComponentString(builder.toString().substring(2)).setStyle(new Style().setColor(TextFormatting.GRAY)));
				break;
			case "start":
				if (args.length < 2) {
					messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaevent.start.usage");

					return;
				}

				builder = new StringBuilder();

				for (int i = 1; i < args.length; i++) {
					builder.append("_");
					builder.append(args[i]);
				}

				eventName = builder.toString().substring(1);

				try {
					event = Enums.CreatureEvents.valueOf(eventName.toUpperCase());
				}
				catch (IllegalArgumentException e) {
					messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoaevent.notFound", eventName.toLowerCase());

					return;
				}

				messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaevent.starting", StringUtil.capitaliseAllWords(eventName));
				OverworldEvents.activateEvent(sender.getEntityWorld(), event);
				break;
			case "stop":
				if (args.length < 2) {
					messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaevent.stop.usage");

					return;
				}

				builder = new StringBuilder();

				for (int i = 1; i < args.length; i++) {
					builder.append("_");
					builder.append(args[i]);
				}

				eventName = builder.toString().substring(1);

				try {
					event = Enums.CreatureEvents.valueOf(eventName.toUpperCase());
				}
				catch (IllegalArgumentException e) {
					messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoaevent.notFound", eventName.toLowerCase());

					return;
				}

				messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaevent.stopping", StringUtil.capitaliseAllWords(eventName));
				OverworldEvents.deactivateEvent(sender.getEntityWorld(), event);
				break;
			default:
				messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaevent.usage");
				break;
		}
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 4;
	}

	private void messageSender(ICommandSender sender, Enums.CommandFeedbackType type, String langKey, String... args) {
		sender.sendMessage(commandPrefix.createCopy().appendSibling(StringUtil.getLocaleWithArguments(langKey, args).setStyle(new Style().setColor(type.getColour()))));
	}
}
