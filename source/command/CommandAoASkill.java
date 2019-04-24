package net.tslat.aoa3.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

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
		AdventPlayerCapability cap;

		switch (args[0]) {
			case "check":
				if (args.length < 3) {
					messageSender(sender, Enums.CommandFeedbackType.WARN, "command.aoaskill.check.usage");

					return;
				}

				try {
					pl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(args[1]);
					skill = Enums.Skills.valueOf(args[2].toUpperCase());

					if (pl == null) {
						messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[1]);

						return;
					}
				}
				catch (IllegalArgumentException e) {
					messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[2]);

					return;
				}

				cap = PlayerUtil.getAdventPlayer(pl);
				lvl = cap.getDisplayLevel(skill);

				sender.sendMessage(StringUtil.getColourLocaleWithArguments("command.aoaskill.check.player", TextFormatting.GRAY, pl.getDisplayNameString()));
				sender.sendMessage(StringUtil.getColourLocaleWithArguments("command.aoaskill.check.data", TextFormatting.GRAY, StringUtil.toProperCasing(skill.toString()), String.valueOf(lvl), String.valueOf(cap.getExp(skill)), String.valueOf(cap.getXpReqForLevel(lvl + 1))));
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
							lvl = MathHelper.clamp(Integer.valueOf(args[2]), 1, 1000);
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

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.setlevel.success", pl.getDisplayNameString(), StringUtil.toProperCasing(skill.toString()), String.valueOf(lvl));
						PlayerUtil.getAdventPlayer(pl).setLevel(skill, lvl);
						break;
					case 4:
						try {
							lvl = MathHelper.clamp(Integer.valueOf(args[3]), 1, 1000);
							skill = Enums.Skills.valueOf(args[1].toUpperCase());
							pl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(args[2]);

							if (pl == null) {
								messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[2]);

								return;
							}
						}
						catch (NumberFormatException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR,"command.aoa.invalidArgument", args[3]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.setlevel.success", pl.getDisplayNameString(), StringUtil.toProperCasing(skill.toString()), String.valueOf(lvl));
						PlayerUtil.getAdventPlayer(pl).setLevel(skill, lvl);
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

						cap = PlayerUtil.getAdventPlayer(pl);
						lvl = Math.min(1000, cap.getDisplayLevel(skill) + 1);

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.addlevel.success", pl.getDisplayNameString(), StringUtil.toProperCasing(skill.toString()), String.valueOf(lvl));
						cap.setLevel(skill, lvl);
						break;
					case 3:
						try {
							skill = Enums.Skills.valueOf(args[1].toUpperCase());
							pl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(args[2]);

							if (pl == null) {
								messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[2]);

								return;
							}
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						cap = PlayerUtil.getAdventPlayer(pl);
						lvl = Math.min(1000, cap.getDisplayLevel(skill) + 1);

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.addlevel.success", pl.getDisplayNameString(), StringUtil.toProperCasing(skill.toString()), String.valueOf(lvl));
						cap.setLevel(skill, lvl);
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

						cap = PlayerUtil.getAdventPlayer(pl);
						lvl = Math.max(1, cap.getDisplayLevel(skill) - 1);

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.removelevel.success", pl.getDisplayNameString(), StringUtil.toProperCasing(skill.toString()), String.valueOf(lvl));
						cap.setLevel(skill, lvl);
						break;
					case 3:
						try {
							skill = Enums.Skills.valueOf(args[1].toUpperCase());
							pl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(args[2]);

							if (pl == null) {
								messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[2]);

								return;
							}
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						cap = PlayerUtil.getAdventPlayer(pl);
						lvl = Math.max(1, cap.getDisplayLevel(skill) - 1);

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.removelevel.success", pl.getDisplayNameString(), StringUtil.toProperCasing(skill.toString()), String.valueOf(lvl));
						cap.setLevel(skill, lvl);
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
							xp = MathHelper.clamp(Float.valueOf(args[2]), 0, Integer.MAX_VALUE);
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

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.addxp.success", pl.getDisplayNameString(), StringUtil.toProperCasing(skill.toString()), String.valueOf(xp));
						PlayerUtil.getAdventPlayer(pl).addXp(skill, xp, true);
						break;
					case 4:
						try {
							xp = MathHelper.clamp(Float.valueOf(args[3]), 0, Integer.MAX_VALUE);
							skill = Enums.Skills.valueOf(args[1].toUpperCase());
							pl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(args[2]);

							if (pl == null) {
								messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[2]);

								return;
							}
						}
						catch (NumberFormatException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR,"command.aoa.invalidArgument", args[3]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.addxp.success", pl.getDisplayNameString(), StringUtil.toProperCasing(skill.toString()), String.valueOf(xp));
						PlayerUtil.getAdventPlayer(pl).addXp(skill, xp, true);
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
							xp = MathHelper.clamp(Float.valueOf(args[2]), 0, Integer.MAX_VALUE);
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

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.removexp.success", pl.getDisplayNameString(), StringUtil.toProperCasing(skill.toString()), String.valueOf(xp));
						PlayerUtil.getAdventPlayer(pl).subtractXp(skill, xp);
						break;
					case 4:
						try {
							xp = MathHelper.clamp(Float.valueOf(args[3]), 0, Integer.MAX_VALUE);
							skill = Enums.Skills.valueOf(args[1].toUpperCase());
							pl = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(args[2]);

							if (pl == null) {
								messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noPlayer", args[2]);

								return;
							}
						}
						catch (NumberFormatException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR,"command.aoa.invalidArgument", args[3]);

							return;
						}
						catch (IllegalArgumentException e) {
							messageSender(sender, Enums.CommandFeedbackType.ERROR, "command.aoa.noSkill", args[1]);

							return;
						}

						messageSender(sender, Enums.CommandFeedbackType.SUCCESS, "command.aoaskill.removexp.success", pl.getDisplayNameString(), StringUtil.toProperCasing(skill.toString()), String.valueOf(xp));
						PlayerUtil.getAdventPlayer(pl).subtractXp(skill, xp);
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
