package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.WikiSearchPacket;
import net.tslat.aoa3.util.StringUtil;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikiCommand implements Command<CommandSourceStack> {
	private static final WikiCommand CMD = new WikiCommand();

	public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {
		LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("wiki").executes(CMD);

		builder.then(Commands.argument("search", StringArgumentType.greedyString()).requires(command -> command.hasPermission(0))
				.executes(WikiCommand::sendPacket));

		return builder;
	}

	public static void handleSearchRequest(String search) {
		String targetUrl = "?";
		String pageTitle = StringUtil.toTitleCase(search);

		if (search.equalsIgnoreCase("random"))
			search = "Special:Random";

		try {
			targetUrl = "https://adventofascension.gamepedia.com/index.php?search=" + URLEncoder.encode(search, StandardCharsets.UTF_8) + "&title=Special:Search&go=Go";
			HttpURLConnection connection = (HttpURLConnection)new URL("https://adventofascension.gamepedia.com/" + StringUtil.toTitleCase(URLEncoder.encode(search, StandardCharsets.UTF_8).replace("+", " ")).replace(" ", "_")).openConnection();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				targetUrl = connection.getURL().toString();
				int titleIndex = targetUrl.indexOf("wiki/");

				if (titleIndex >= 0)
					pageTitle = StringUtil.toTitleCase(targetUrl.substring(titleIndex + 5));
			}
		}
		catch (UnsupportedEncodingException | MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException ex) {
			ClientOperations.getPlayer().sendSystemMessage(AoACommand.getCmdPrefix("Wiki").append(Component.translatable("command.aoa.wiki.connectionFail").setStyle(Style.EMPTY.applyFormat(AoACommand.CommandFeedbackType.ERROR.getColour()))));
		}

		if (search.equals("Special:Random"))
			pageTitle = "???";

		MutableComponent responseComponent = getComponentFromKeys("command.aoa.wiki.response", targetUrl, pageTitle);
		ClientOperations.getPlayer().sendSystemMessage(AoACommand.getCmdPrefix("Wiki").append(responseComponent != null ? responseComponent.setStyle(Style.EMPTY.applyFormat(ChatFormatting.GRAY)) : Component.translatable("command.aoawiki.response", targetUrl)));
	}

	@Nullable
	private static MutableComponent getComponentFromKeys(String langKey, String url, String pageTitle) {
		return MutableComponent.Serializer.fromJson("{\"translate\":\"" + langKey + "\",\"with\":[{\"text\":\"" + pageTitle + "\",\"color\":\"red\",\"underlined\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + url + "\"}}]}");
	}

	private static int sendPacket(CommandContext<CommandSourceStack> cmd) throws CommandSyntaxException {
		AoANetworking.sendToPlayer((ServerPlayer)cmd.getSource().getEntityOrException(), new WikiSearchPacket(StringArgumentType.getString(cmd, "search")));

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) {
		AoACommand.feedback(context.getSource(), "Wiki", "command.aoa.wiki.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}
}