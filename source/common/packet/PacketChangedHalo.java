package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.handlers.PlayerHaloHandler;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;

public class PacketChangedHalo implements IMessage {
	private String haloChoice;

	public PacketChangedHalo() {}

	public PacketChangedHalo(@Nonnull final Enums.PlayerHaloTypes.ChoosableHaloTypes haloChoice) {
		this.haloChoice = haloChoice.toString();
	}

	public void fromBytes(final ByteBuf buffer) {
		haloChoice = ByteBufUtils.readUTF8String(buffer);
	}

	public void toBytes(final ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, haloChoice);
	}

	public static class Handler implements IMessageHandler<PacketChangedHalo, IMessage> {
		public IMessage onMessage(final PacketChangedHalo msg, final MessageContext ctx) {
			Enums.PlayerHaloTypes preferredHalo = Enums.PlayerHaloTypes.Donator;

			try {
				preferredHalo = Enums.PlayerHaloTypes.ChoosableHaloTypes.valueOf(msg.haloChoice).toBaseType();
			}
			catch (IllegalArgumentException e) {
				if (ConfigurationUtil.MainConfig.doVerboseDebugging) {
					AdventOfAscension.logMessage(Level.WARN, "Error parsing halo info from client: " + msg.haloChoice);
					e.printStackTrace();
				}
			}

			PlayerHaloHandler.syncNewHaloChoice(ctx.getServerHandler().player.getGameProfile().getId(), preferredHalo);

			return null;
		}
	}
}
