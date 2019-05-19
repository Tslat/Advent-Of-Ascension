package net.tslat.aoa3.common.packet.leaderboard;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.logging.log4j.Level;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.SortedMap;

public class PacketIndividualLeaderboardStats implements IMessage {
	private String name;
	private int byteArraySize;
	private SortedMap<Enums.Skills, Integer> skillRankMap;

	public PacketIndividualLeaderboardStats() {}

	public PacketIndividualLeaderboardStats(String playerName, SortedMap<Enums.Skills, Integer> skillRankMap) {
		this.name = playerName;
		this.skillRankMap = skillRankMap;
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		this.name = ByteBufUtils.readUTF8String(buffer);
		this.byteArraySize = buffer.readInt();
		byte[] byteArray = new byte[this.byteArraySize];

		buffer.readBytes(new byte[buffer.readableBytes()]);

		ByteArrayInputStream arrayStreamIn = new ByteArrayInputStream(byteArray);
		ObjectInputStream objectIn = null;
		SortedMap<Enums.Skills, Integer> readEntries = null;

		try {
			objectIn = new ObjectInputStream(arrayStreamIn);
			readEntries = (SortedMap<Enums.Skills, Integer>)objectIn.readObject();
		}
		catch (Exception e) {
			if (ConfigurationUtil.MainConfig.doVerboseDebugging)
				AdventOfAscension.getLogger().log(Level.WARN, "Unable to deserialize packet for leaderboard stats, skipping");
		}
		finally {
			this.skillRankMap = readEntries;

			try {
				arrayStreamIn.close();
			}
			catch (Exception e) {}
		}
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, this.name);

		byte[] entriesByteArray = null;
		ByteArrayOutputStream byteStreamOut = new ByteArrayOutputStream();
		ObjectOutputStream objectOut = null;

		try {
			objectOut = new ObjectOutputStream(byteStreamOut);
			objectOut.writeObject(skillRankMap);
			objectOut.flush();

			entriesByteArray = byteStreamOut.toByteArray();
		}
		catch (Exception e) {
			if (ConfigurationUtil.MainConfig.doVerboseDebugging)
				AdventOfAscension.getLogger().log(Level.WARN, "Failed to serialize packet for leaderboard stats, skipping");

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
}
