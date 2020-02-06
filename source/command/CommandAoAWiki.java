package net.tslat.aoa3.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.*;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CommandAoAWiki extends CommandBase {
	private static final TextComponentString commandPrefix = new TextComponentString(TextFormatting.DARK_RED + "[AoA" + TextFormatting.GOLD + "Wiki" + TextFormatting.DARK_RED + "] ");

	@Override
	public String getName() {
		return "aoawiki";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "command.aoawiki.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			messageSender(sender, Enums.CommandFeedbackType.INFO, "command.aoawiki.desc");

			return;
		}

		if (sender.getEntityWorld().isRemote) {
			String baseUrl = "?";
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < args.length; i++) {
				builder.append(args[i]);
				builder.append(" ");
			}

			String searchQuery = builder.toString().trim();
			String currentLang = FMLCommonHandler.instance().getCurrentLanguage();

			if (searchQuery.equalsIgnoreCase("random"))
				searchQuery = "Special:Random";

			try {
				if (currentLang.equals("zh_cn") || currentLang.equals("zh_tw")) {
					baseUrl = "https://adventofascension-zh.gamepedia.com/index.php?search=" + URLEncoder.encode(searchQuery, "UTF-8") + "&title=Special:Search&go=Go";
				}
				else {
					baseUrl = "https://adventofascension.gamepedia.com/index.php?search=" + URLEncoder.encode(searchQuery, "UTF-8") + "&title=Special:Search&go=Go";
				}
			}
			catch (UnsupportedEncodingException e) {
				if (ConfigurationUtil.MainConfig.doVerboseDebugging)
					e.printStackTrace();
			}

			if (searchQuery.equals("Special:Random"))
				searchQuery = "???";

			ITextComponent responseComponent = getComponentFromKeys("command.aoawiki.response", baseUrl, StringUtil.capitaliseFirstLetter(searchQuery));

			sender.sendMessage(commandPrefix.createCopy().appendSibling(responseComponent != null ? responseComponent : new TextComponentTranslation("command.aoawiki.response", baseUrl)));
		}
	}

	@Nullable
	private ITextComponent getComponentFromKeys(String langKey, String url, String pageTitle) {
		return ITextComponent.Serializer.jsonToComponent("{\"translate\":\"" + langKey + "\",\"with\":[{\"text\":\"" + pageTitle + "\",\"color\":\"red\",\"underlined\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + url + "\"}}]}");
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	private void messageSender(ICommandSender sender, Enums.CommandFeedbackType type, String langKey, String... args) {
		sender.sendMessage(commandPrefix.createCopy().appendSibling(StringUtil.getLocaleWithArguments(langKey, args).setStyle(new Style().setColor(type.getColour()))));
	}
}
