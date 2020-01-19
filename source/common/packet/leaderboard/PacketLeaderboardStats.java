package net.tslat.aoa3.common.packet.leaderboard;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.leaderboard.Leaderboard;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PacketLeaderboardStats implements IMessage {
	public int skillId;
	public int firstRank;
	public int byteArraySize;
	public ArrayList<Leaderboard.TrimmedLeaderboardEntry> entries;

	public PacketLeaderboardStats() {}

	public PacketLeaderboardStats(@Nullable Enums.Skills skill, int topRank, ArrayList<Leaderboard.TrimmedLeaderboardEntry> entries) {
		this.skillId = (skill == null ? -1 : skill.id);
		this.firstRank = topRank;
		this.entries = entries;
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.skillId = buffer.readInt();
		this.firstRank = buffer.readInt();
		this.byteArraySize = buffer.readInt();
		byte[] byteArray = new byte[this.byteArraySize];

		buffer.readBytes(new byte[buffer.readableBytes()]);

		ByteArrayInputStream arrayStreamIn = new ByteArrayInputStream(byteArray);
		ObjectInputStream objectIn = null;
		ArrayList<Leaderboard.TrimmedLeaderboardEntry> readEntries = null;

		try {
			objectIn = new ObjectInputStream(arrayStreamIn);
			readEntries = (ArrayList<Leaderboard.TrimmedLeaderboardEntry>)objectIn.readObject();
		}
		catch (Exception e) {
			if (ConfigurationUtil.MainConfig.doVerboseDebugging)
				AdventOfAscension.logMessage(Level.WARN, "Unable to deserialize packet for leaderboard stats, skipping");
		}
		finally {
			this.entries = readEntries;

			try {
				arrayStreamIn.close();
			}
			catch (Exception e) {}
		}
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(skillId);
		buffer.writeInt(firstRank);

		byte[] entriesByteArray = null;
		ByteArrayOutputStream byteStreamOut = new ByteArrayOutputStream();
		ObjectOutputStream objectOut = null;

		try {
			objectOut = new ObjectOutputStream(byteStreamOut);
			objectOut.writeObject(entries);
			objectOut.flush();

			entriesByteArray = byteStreamOut.toByteArray();
		}
		catch (Exception e) {
			if (ConfigurationUtil.MainConfig.doVerboseDebugging)
				AdventOfAscension.logMessage(Level.WARN, "Failed to serialize packet for leaderboard stats, skipping");

			return;
		}
		finally {
			if (entriesByteArray != null) {
				buffer.writeInt(entriesByteArray.length);
				buffer.writeBytes(entriesByteArray);
			}

			try {
				byteStreamOut.close();
			}
			catch (Exception e) {}
		}
	}

	public static class Handler implements IMessageHandler<PacketLeaderboardStats, IMessage> {
		public IMessage onMessage(final PacketLeaderboardStats msg, final MessageContext ctx) {
			AdventOfAscension.proxy.handleLeaderboardData(msg);

			return null;
		}
	}

	/*private int skillId;
	private int arraySize;
	private ArrayList<NBTTagCompound> entries;

	public PacketLeaderboardStats() {}

	public PacketLeaderboardStats(Enums.Skills skill, ArrayList<NBTTagCompound> entries) {
		this.skillId = skill.id	;
		this.arraySize = entries.leaderboardCapacity();
		this.entries = entries;
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		skillId = buffer.readInt();
		arraySize = buffer.readInt();

		ArrayList<NBTTagCompound> entries = new ArrayList<NBTTagCompound>(arraySize);

		for (int i = 0; i < arraySize; i++) {
			NBTTagCompound tag = ByteBufUtils.readTag(buffer);

			entries.add(tag);
		}
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(skillId);
		buffer.writeInt(arraySize);

		for (NBTTagCompound tag : entries) {
			ByteBufUtils.writeTag(buffer, tag);
		}
	}

	public static class Handler implements IMessageHandler<PacketLeaderboardStats, IMessage> {
		public IMessage onMessage(final PacketLeaderboardStats msg, final MessageContext ctx) {
			AdventOfAscension.proxy.handleLeaderboardData(msg);

			return null;
		}
	}*/
}
