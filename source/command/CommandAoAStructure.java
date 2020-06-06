package net.tslat.aoa3.command;

import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.structure.AoAStructure;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.StringUtil;
import org.apache.commons.lang3.math.NumberUtils;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandAoAStructure extends CommandBase {
	private static final TextComponentString commandPrefix = new TextComponentString(TextFormatting.DARK_RED + "[AoA" + TextFormatting.GOLD + "Structure" + TextFormatting.DARK_RED + "] ");

	@Override
	public String getName() {
		return "aoastructure";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "command.aoastructure.usage";
	}

	@Override
	public List<String> getAliases() {
		return Collections.<String>singletonList("aoastructures");
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			messageSender(sender, Enums.CommandFeedbackType.INFO, "command.aoastructure.desc");

			return;
		}

		if (args[0].equalsIgnoreCase("list")) {
			int pageIndex = 1;
			int pageCount = StructuresHandler.getStructureListPageCount();

			if (args.length > 1 && NumberUtils.isCreatable(args[1])) {
				try {
					pageIndex = MathHelper.clamp(Integer.parseInt(args[1]), 1, pageCount);
				}
				catch (Exception e) {
					messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.invalidArgument", args[1]);

					return;
				}
			}

			messageSender(sender, Enums.CommandFeedbackType.INFO, "--");
			messageSender(sender, Enums.CommandFeedbackType.INFO, "command.aoastructure.list", StructuresHandler.getStructuresList(pageIndex));
			messageSender(sender, Enums.CommandFeedbackType.INFO, "");
			messageSender(sender, Enums.CommandFeedbackType.INFO, "command.aoastructure.page", String.valueOf(pageIndex), String.valueOf(pageCount));

			return;
		}

		if (args.length < 2 || (args.length < 4 && !args[1].equalsIgnoreCase("here"))) {
			messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoastructure.usage");

			return;
		}

		AoAStructure structure = StructuresHandler.getStructure(args[0]);

		if (structure != StructuresHandler.EMPTY_STRUCTURE) {
			BlockPos generationPos = evaluatePosition(sender, args);

			if (!sender.getEntityWorld().isChunkGeneratedAt(generationPos.getX(),generationPos.getZ()))
				sender.getEntityWorld().getChunkProvider().provideChunk(generationPos.getX() >> 4, generationPos.getZ() >> 4);

			StructuresHandler.generateStructure(structure, sender.getEntityWorld(), null, generationPos);
			messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoastructure.success", args[0], String.valueOf(generationPos.getX()), String.valueOf(generationPos.getY()), String.valueOf(generationPos.getZ()));
		}
		else {
			messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoastructure.invalid", args[0]);
		}
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 4;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
		return StructuresHandler.autoCompleteStructureName(args[args.length - 1]);
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	private BlockPos evaluatePosition(ICommandSender sender, String[] args) throws CommandException {
		if (args[1].equals("here")) {
			if (sender instanceof EntityPlayerMP) {
				return sender.getPosition();
			}
			else {
				throw new WrongUsageException("command.aoa.noConsole", "Here");
			}
		}
		else if (args.length >= 4) {
			int posX = 0;
			int posY = 0;
			int posZ = 0;

			for (int i = 1; i < 4; i++) {
				int coord;
				boolean relative = false;
				String s = args[i];

				if (s.startsWith("~")) {
					s = s.substring(1);
					relative = true;
				}

				try {
					if (args[i].length() == 1 && relative) {
						coord = 0;
					}
					else {
						coord = Integer.parseInt(s);
					}
				}
				catch (Exception e) {
					throw new WrongUsageException("command.aoa.invalidArgument", args[i]);
				}

				if (relative && !(sender instanceof EntityPlayerMP))
					throw new WrongUsageException("command.aoa.noConsole", "~");

				switch (i) {
					case 1:
						posX = relative ? (int)((EntityPlayerMP)sender).posX + coord : coord;
						break;
					case 2:
						posY = relative ? (int)((EntityPlayerMP)sender).posY + coord : coord;
						break;
					case 3:
						posZ = relative ? (int)((EntityPlayerMP)sender).posZ + coord : coord;
						break;
				}
			}

			return new BlockPos(posX, posY, posZ);
		}
		else {
			throw new WrongUsageException("command.aoastructure.usage");
		}
	}

	private void messageSender(ICommandSender sender, Enums.CommandFeedbackType type, String langKey, String... args) {
		sender.sendMessage(commandPrefix.createCopy().appendSibling(StringUtil.getLocaleWithArguments(langKey, args).setStyle(new Style().setColor(type.getColour()))));
	}
}
