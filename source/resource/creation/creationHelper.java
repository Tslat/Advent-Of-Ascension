package net.nevermine.resource.creation;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.container.PlayerContainer;

import static net.nevermine.container.PlayerContainer.Skills.Augury;

public class creationHelper implements IExtendedEntityProperties {
	private static int creation;
	private static int regenDelay;
	private final EntityPlayer player;
	private static final String NAME = "creationBar";

	public creationHelper(final EntityPlayer player) {
		this.player = player;
		player.getDataWatcher().addObject(ConfigurationHelper.dataW4, 0.0f);
	}

	public void saveNBTData(final NBTTagCompound n) {
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat("Value", player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW4));
		n.setTag("creationBar", tag);
	}

	public static void addProperties(final EntityPlayer player) {
		player.registerExtendedProperties("creationBar", new creationHelper(player));
	}

	public static creationHelper getProperties(final EntityPlayer player) {
		return (creationHelper)player.getExtendedProperties("creationBar");
	}

	public void loadNBTData(final NBTTagCompound n) {
		final NBTTagCompound tag = (NBTTagCompound)n.getTag("creationBar");
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW4, tag.getFloat("Value"));
	}

	private int getAugury() {
		return PlayerContainer.getProperties(player).getLevel(Augury);
	}

	public float getMax() {
		float max;
		if (getAugury() >= 0 && getAugury() < 30) {
			max = 6000.0f;
		}
		else if (getAugury() >= 30 && getAugury() < 60) {
			max = 7200.0f;
		}
		else if (getAugury() >= 60 && getAugury() < 85) {
			max = 8400.0f;
		}
		else {
			max = 9600.0f;
		}
		return max;
	}

	public void updateAllBars() {
		if (!player.worldObj.isRemote) {
			if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW4) < 0.0f) {
				player.getDataWatcher().updateObject(ConfigurationHelper.dataW4, 0.0f);
			}
			float max;
			if (getAugury() >= 0 && getAugury() < 30) {
				max = 6000.0f;
			}
			else if (getAugury() >= 30 && getAugury() < 60) {
				max = 7200.0f;
			}
			else if (getAugury() >= 60 && getAugury() < 85) {
				max = 8400.0f;
			}
			else {
				max = 9600.0f;
			}
			if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW4) != max) {
				regen(1.0f);
			}
			else {
				regen(0.0f);
			}
			if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW4) >= max) {
				player.getDataWatcher().updateObject(ConfigurationHelper.dataW4, max);
			}
		}
	}

	public boolean useBar(final float amount) {
		if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW4) < amount) {
			return false;
		}
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW4, player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW4) - amount);
		return true;
	}

	public void regen(final float amount) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW4, player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW4) + amount);
	}

	public float getBarValue() {
		return player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW4);
	}

	public int getBarInt() {
		return (int)Math.floor(player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW4) / 600.0f);
	}

	public void setBarValue(final float i) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW4, i);
	}

	public void removeBarValue(final float i) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW4, player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW4) - i);
	}

	@SubscribeEvent
	public void init(final Entity entity, final World world) {
	}

	static {
		creationHelper.creation = 0;
		creationHelper.regenDelay = 0;
	}
}
