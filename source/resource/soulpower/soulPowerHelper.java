package net.nevermine.resource.soulpower;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.container.PlayerContainer;

import static net.nevermine.container.PlayerContainer.Skills.Augury;

public class soulPowerHelper implements IExtendedEntityProperties {
	private static int ap;
	private static int regenDelay;
	private final EntityPlayer player;
	private static final String NAME = "soulStreamBar";

	public soulPowerHelper(final EntityPlayer player) {
		this.player = player;
		player.getDataWatcher().addObject(ConfigurationHelper.dataW3, 0.0f);
	}

	public void saveNBTData(final NBTTagCompound n) {
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat("Value", player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW3));
		n.setTag("soulStreamBar", tag);
	}

	public static void addProperties(final EntityPlayer player) {
		player.registerExtendedProperties("soulStreamBar", new soulPowerHelper(player));
	}

	public static soulPowerHelper getProperties(final EntityPlayer player) {
		return (soulPowerHelper)player.getExtendedProperties("soulStreamBar");
	}

	public void loadNBTData(final NBTTagCompound n) {
		final NBTTagCompound tag = (NBTTagCompound)n.getTag("soulStreamBar");
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW3, tag.getFloat("Value"));
	}

	private int getAugury() {
		return PlayerContainer.getProperties(player).getLevel(Augury);
	}

	public void updateAllBars() {
		if (!player.worldObj.isRemote) {
			if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW3) < 0.0f) {
				player.getDataWatcher().updateObject(ConfigurationHelper.dataW3, 0.0f);
			}
			int augury = getAugury();

			float max;
			if (augury < 35) {
				max = 20000.0f;
			}
			else if (augury < 70) {
				max = 26000.0f;
			}
			else if (augury < 90) {
				max = 32000.0f;
			}
			else {
				max = 40000.0f;
			}

			if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW3) != max) {
				regen(1.0f);
			}
			else {
				regen(0.0f);
			}
			if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW3) >= max) {
				player.getDataWatcher().updateObject(ConfigurationHelper.dataW3, max);
			}
		}
	}

	public boolean useBar(final float amount) {
		if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW3) < amount) {
			return false;
		}
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW3, (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW3) - amount));
		return true;
	}

	public void regen(final float amount) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW3, (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW3) + amount));
	}

	public float getBarValue() {
		return player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW3);
	}

	public int getBarInt() {
		return (int)Math.floor(player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW3) / 2000.0f);
	}

	public void setBarValue(final float i) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW3, i);
	}

	public void removeBarValue(final float i) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW3, (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW3) - i));
	}

	@SubscribeEvent
	public void init(final Entity entity, final World world) {
	}

	static {
		soulPowerHelper.ap = 0;
		soulPowerHelper.regenDelay = 0;
	}
}
