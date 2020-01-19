package net.tslat.aoa3.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.Collections;
import java.util.List;

public class CommandAoAPortalReset extends CommandBase {
	private static final TextComponentString commandPrefix = new TextComponentString(TextFormatting.DARK_RED + "[AoA" + TextFormatting.GOLD + "PortalReset" + TextFormatting.DARK_RED + "] ");

	@Override
	public String getName() {
		return "aoaportalreset";
	}

	@Override
	public List<String> getAliases() {
		return Collections.<String>singletonList("aoaportalreset");
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "command.aoaportalreset.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			messageSender(sender, Enums.CommandFeedbackType.INFO, "command.aoaportalreset.desc");

			return;
		}

		if (!args[0].equalsIgnoreCase("yes"))
			throw new WrongUsageException("command.aoaportalreset.usage");

		if (sender instanceof EntityPlayerMP) {
			PlayerUtil.getAdventPlayer((EntityPlayer)sender).flushPortalReturnLocations();
			messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaportalreset.success");
		}
		else {
			throw new WrongUsageException("command.aoa.noConsole", "~");
		}
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	private void messageSender(ICommandSender sender, Enums.CommandFeedbackType type, String langKey, String... args) {
		sender.sendMessage(commandPrefix.createCopy().appendSibling(StringUtil.getLocaleWithArguments(langKey, args).setStyle(new Style().setColor(type.getColour()))));
	}
}
