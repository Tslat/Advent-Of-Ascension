package net.tslat.aoa3.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.Collections;
import java.util.List;

public class CommandAoASkill extends CommandBase {
	private static final TextComponentString commandPrefix = new TextComponentString(TextFormatting.DARK_RED + "[AoA" + TextFormatting.GOLD + "Skill" + TextFormatting.DARK_RED + "] ");

	@Override
	public String getName() {
		return "aoaskill";
	}

	@Override
	public List<String> getAliases() {
		return Collections.<String>singletonList("aoaskills");
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "command.aoaskill.usage";
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return super.checkPermission(server, sender);
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			messageSender(sender, Enums.CommandFeedbackType.INFO, "command.aoaskill.desc");

			return;
		}

		EntityPlayer pl;
		float xp;
		Enums.Skills skill;
		int lvl;
		PlayerDataManager plData;

		switch (args[0]) {
			case "check":
				if (args.length < 3) {
					messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaskill.check.usage");

					return;
				}

				try {
					pl = getEntity(FMLCommonHandler.instance().getMinecraftServerInstance(), sender, args[1], EntityPlayer.class);
					skill = Enums.Skills.valueOf(args[2].toUpperCase());
				}
				catch (EntityNotFoundException e) {
					messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[1]);

					return;
				}
				catch (IllegalArgumentException e) {
					messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[2]);

					return;
				}

				plData = PlayerUtil.getAdventPlayer(pl);
				lvl = plData.stats().getLevelForDisplay(skill);

				sender.sendMessage(StringUtil.getColourLocaleWithArguments("command.aoaskill.check.player", TextFormatting.GRAY, pl.getDisplayNameString()));
				sender.sendMessage(StringUtil.getColourLocaleWithArguments("command.aoaskill.check.data", TextFormatting.GRAY, StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(lvl), String.valueOf(plData.stats().getExp(skill)), String.valueOf(PlayerUtil.getXpRequiredForNextLevel(lvl))));
				break;
			case "setlevel":
				switch (args.length) {
					case 1:
					case 2:
					default:
						messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaskill.setlevel.usage");
						break;
					case 3:
						try {
							lvl = MathHelper.clamp(Integer.parseInt(args[2]), 1, 1000);
							skill = Enums.Skills.valueOf(args[1].toUpperCase());

							if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
								pl = (EntityPlayer)sender.getCommandSenderEntity();
							}
							else {
								messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noConsole", "/aoaskill setlevel <skill> <level>");

								return;
							}
						}
						catch (NumberFormatException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.invalidArgument", args[2]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						PlayerUtil.getAdventPlayer(pl).stats().setLevel(skill, lvl);
						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.setlevel.success", pl.getDisplayNameString(), StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(lvl));
						break;
					case 4:
						try {
							lvl = MathHelper.clamp(Integer.parseInt(args[3]), 1, 1000);
							skill = Enums.Skills.valueOf(args[1].toUpperCase());
							pl = getEntity(FMLCommonHandler.instance().getMinecraftServerInstance(), sender, args[2], EntityPlayer.class);
						}
						catch (EntityNotFoundException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[2]);

							return;
						}
						catch (NumberFormatException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR,"command.aoa.invalidArgument", args[3]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						PlayerUtil.getAdventPlayer(pl).stats().setLevel(skill, lvl);
						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.setlevel.success", pl.getDisplayNameString(), StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(lvl));
						break;
				}
				break;
			case "addlevel":
				switch (args.length) {
					case 1:
					default:
						messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaskill.addlevel.usage");
						break;
					case 2:
						try {
							skill = Enums.Skills.valueOf(args[1].toUpperCase());

							if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
								pl = (EntityPlayer)sender.getCommandSenderEntity();
							}
							else {
								messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noConsole", "/aoaskill addlevel <skill>");

								return;
							}
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						plData = PlayerUtil.getAdventPlayer(pl);
						lvl = Math.min(1000, plData.stats().getLevelForDisplay(skill) + 1);

						plData.stats().setLevel(skill, lvl);
						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.addlevel.success", pl.getDisplayNameString(), StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(lvl));
						break;
					case 3:
						try {
							skill = Enums.Skills.valueOf(args[1].toUpperCase());
							pl = getEntity(FMLCommonHandler.instance().getMinecraftServerInstance(), sender, args[2], EntityPlayer.class);
						}
						catch (EntityNotFoundException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[2]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						plData = PlayerUtil.getAdventPlayer(pl);
						lvl = Math.min(1000, plData.stats().getLevelForDisplay(skill) + 1);

						plData.stats().setLevel(skill, lvl);
						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.addlevel.success", pl.getDisplayNameString(), StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(lvl));
						break;
				}
				break;
			case "removelevel":
				switch (args.length) {
					case 1:
					default:
						messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaskill.removelevel.usage");
						break;
					case 2:
						try {
							skill = Enums.Skills.valueOf(args[1].toUpperCase());

							if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
								pl = (EntityPlayer)sender.getCommandSenderEntity();
							}
							else {
								messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noConsole", "/aoaskill removelevel <skill>");

								return;
							}
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						plData = PlayerUtil.getAdventPlayer(pl);
						lvl = Math.max(1, plData.stats().getLevelForDisplay(skill) - 1);

						plData.stats().setLevel(skill, lvl);
						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.removelevel.success", pl.getDisplayNameString(), StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(lvl));
						break;
					case 3:
						try {
							skill = Enums.Skills.valueOf(args[1].toUpperCase());
							pl = getEntity(FMLCommonHandler.instance().getMinecraftServerInstance(), sender, args[2], EntityPlayer.class);
						}
						catch (EntityNotFoundException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[2]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						plData = PlayerUtil.getAdventPlayer(pl);
						lvl = Math.max(1, plData.stats().getLevelForDisplay(skill) - 1);

						plData.stats().setLevel(skill, lvl);
						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.removelevel.success", pl.getDisplayNameString(), StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(lvl));
						break;
				}
				break;
			case "addxp":
				switch (args.length) {
					case 1:
					case 2:
					default:
						messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaskill.addxp.usage");
						break;
					case 3:
						try {
							xp = MathHelper.clamp(Float.parseFloat(args[2]), 0, Integer.MAX_VALUE);
							skill = Enums.Skills.valueOf(args[1].toUpperCase());

							if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
								pl = (EntityPlayer)sender.getCommandSenderEntity();
							}
							else {
								messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noConsole", "/aoaskill addxp <skill> <xp>");

								return;
							}
						}
						catch (NumberFormatException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.invalidArgument", args[2]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.addxp.success", pl.getDisplayNameString(), StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(xp));
						PlayerUtil.getAdventPlayer(pl).stats().addXp(skill, xp, true);
						break;
					case 4:
						try {
							xp = MathHelper.clamp(Float.parseFloat(args[3]), 0, Integer.MAX_VALUE);
							skill = Enums.Skills.valueOf(args[1].toUpperCase());
							pl = getEntity(FMLCommonHandler.instance().getMinecraftServerInstance(), sender, args[2], EntityPlayer.class);
						}
						catch (EntityNotFoundException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[2]);

							return;
						}
						catch (NumberFormatException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR,"command.aoa.invalidArgument", args[3]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						PlayerUtil.getAdventPlayer(pl).stats().addXp(skill, xp, true);
						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.addxp.success", pl.getDisplayNameString(), StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(xp));
						break;
				}
				break;
			case "removexp":
				switch (args.length) {
					case 1:
					case 2:
					default:
						messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaskill.removexp.usage");
						break;
					case 3:
						try {
							xp = MathHelper.clamp(Float.parseFloat(args[2]), 0, Integer.MAX_VALUE);
							skill = Enums.Skills.valueOf(args[1].toUpperCase());

							if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
								pl = (EntityPlayer)sender.getCommandSenderEntity();
							}
							else {
								messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noConsole", "/aoaskill removexp <skill> <xp>");

								return;
							}
						}
						catch (NumberFormatException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.invalidArgument", args[2]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						PlayerUtil.getAdventPlayer(pl).stats().subtractXp(skill, xp);
						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.removexp.success", pl.getDisplayNameString(), StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(xp));
						break;
					case 4:
						try {
							xp = MathHelper.clamp(Float.parseFloat(args[3]), 0, Integer.MAX_VALUE);
							skill = Enums.Skills.valueOf(args[1].toUpperCase());
							pl = getEntity(FMLCommonHandler.instance().getMinecraftServerInstance(), sender, args[2], EntityPlayer.class);
						}
						catch (EntityNotFoundException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[2]);

							return;
						}
						catch (NumberFormatException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR,"command.aoa.invalidArgument", args[3]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						PlayerUtil.getAdventPlayer(pl).stats().subtractXp(skill, xp);
						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.removexp.success", pl.getDisplayNameString(), StringUtil.capitaliseFirstLetter(skill.toString()), String.valueOf(xp));
						break;
				}
				break;
			default:
				messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaskill.usage");
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
