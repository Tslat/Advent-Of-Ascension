package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.WikiSearchPacket;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.StringUtil;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class WikiCommand implements Command<CommandSource> {
	private static final WikiCommand CMD = new WikiCommand();

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("wiki").executes(CMD);

		builder.then(Commands.argument("search", StringArgumentType.greedyString()).requires(command -> command.hasPermission(0))
				.executes(WikiCommand::sendPacket));

		return builder;
	}

	@OnlyIn(Dist.CLIENT)
	public static void handleSearchRequest(String search) {
		String baseUrl = "?";

		if (search.equalsIgnoreCase("random"))
			search = "Special:Random";

		try {
			String testRootUrl = "https://adventofascension.gamepedia.com/" + StringUtil.toTitleCase(URLEncoder.encode(search, "UTF-8").replace("+", " ")).replace(" ", "_");
			baseUrl = "https://adventofascension.gamepedia.com/index.php?search=" + URLEncoder.encode(search, "UTF-8") + "&title=Special:Search&go=Go";

			HttpURLConnection connection = (HttpURLConnection)new URL(testRootUrl).openConnection();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
				baseUrl = testRootUrl;
		}
		catch (UnsupportedEncodingException | MalformedURLException e) {
			if (AoAConfig.CLIENT.doVerboseDebugging.get())
				e.printStackTrace();
		}
		catch (IOException ex) {
			Minecraft.getInstance().player.sendMessage(AoACommand.getCmdPrefix("Wiki").append(new TranslationTextComponent("command.aoa.wiki.connectionFail").setStyle(Style.EMPTY.applyFormat(AoACommand.CommandFeedbackType.ERROR.getColour()))), Util.NIL_UUID);
		}

		if (search.equals("Special:Random"))
			search = "???";

		IFormattableTextComponent responseComponent = getComponentFromKeys("command.aoa.wiki.response", baseUrl, StringUtil.toSentenceCase(search));
		Minecraft.getInstance().player.sendMessage(AoACommand.getCmdPrefix("Wiki").append(responseComponent != null ? responseComponent.setStyle(Style.EMPTY.applyFormat(TextFormatting.GRAY)) : new TranslationTextComponent("command.aoawiki.response", baseUrl)), Util.NIL_UUID);
	}

	@Nullable
	private static IFormattableTextComponent getComponentFromKeys(String langKey, String url, String pageTitle) {
		return ITextComponent.Serializer.fromJson("{\"translate\":\"" + langKey + "\",\"with\":[{\"text\":\"" + pageTitle + "\",\"color\":\"red\",\"underlined\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + url + "\"}}]}");
	}

	private static int sendPacket(CommandContext<CommandSource> cmd) throws CommandSyntaxException {
		AoAPackets.messagePlayer((ServerPlayerEntity)cmd.getSource().getEntityOrException(), new WikiSearchPacket(StringArgumentType.getString(cmd, "search")));

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSource> context) {
		AoACommand.feedback(context.getSource(), "Wiki", "command.aoa.wiki.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}
}
