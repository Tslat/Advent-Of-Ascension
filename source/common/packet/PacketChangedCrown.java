package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.handlers.PlayerCrownHandler;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;

public class PacketChangedCrown implements IMessage {
	private String crownChoice;

	public PacketChangedCrown() {}

	public PacketChangedCrown(@Nonnull final Enums.PlayerCrownTypes.ChooseableCrownTypes crownChoice) {
		this.crownChoice = crownChoice.toString();
	}

	public void fromBytes(final ByteBuf buffer) {
		crownChoice = ByteBufUtils.readUTF8String(buffer);
	}

	public void toBytes(final ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, crownChoice);
	}

	public static class Handler implements IMessageHandler<PacketChangedCrown, IMessage> {
		public IMessage onMessage(final PacketChangedCrown msg, final MessageContext ctx) {
			Enums.PlayerCrownTypes preferredCrown = Enums.PlayerCrownTypes.Donator;

			try {
				preferredCrown = Enums.PlayerCrownTypes.ChooseableCrownTypes.valueOf(msg.crownChoice).toBaseType();
			}
			catch (IllegalArgumentException e) {
				if (ConfigurationUtil.MainConfig.doVerboseDebugging) {
					AdventOfAscension.logMessage(Level.WARN, "Error parsing crown info from client: " + msg.crownChoice);
					e.printStackTrace();
				}
			}

			PlayerCrownHandler.syncNewCrownChoice(ctx.getServerHandler().player.getGameProfile().getId(), preferredCrown);

			return null;
		}
	}
}
