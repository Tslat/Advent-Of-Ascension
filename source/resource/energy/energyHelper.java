package net.nevermine.resource.energy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.nevermine.assist.ConfigurationHelper;

public class energyHelper implements IExtendedEntityProperties {
	private static int ap;
	private final EntityPlayer player;
	private static final String NAME = "AncientPowerBar";

	public energyHelper(final EntityPlayer player) {
		this.player = player;
		player.getDataWatcher().addObject(ConfigurationHelper.dataW1, 0.0f);
	}

	public void saveNBTData(final NBTTagCompound n) {
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat("Value", player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW1));
		n.setTag("AncientPowerBar", tag);
	}

	public static void addProperties(final EntityPlayer player) {
		player.registerExtendedProperties("AncientPowerBar", new energyHelper(player));
	}

	public static energyHelper getProperties(final EntityPlayer player) {
		return (energyHelper)player.getExtendedProperties("AncientPowerBar");
	}

	public void loadNBTData(final NBTTagCompound n) {
		final NBTTagCompound tag = (NBTTagCompound)n.getTag("AncientPowerBar");
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW1, tag.getFloat("Value"));
	}

	public void updateAllBars() {
		if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW1) < 0.0f) {
			player.getDataWatcher().updateObject(ConfigurationHelper.dataW1, 0.0f);
		}
		if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW1) != 200.0f) {
			regen(0.32f);
		}
		else {
			regen(0.0f);
		}
		if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW1) >= 200.0f) {
			player.getDataWatcher().updateObject(ConfigurationHelper.dataW1, 200.0f);
		}
	}

	public boolean useBar(final float amount) {
		if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW1) < amount) {
			return false;
		}
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW1, (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW1) - amount));
		return true;
	}

	public void regen(final float amount) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW1, (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW1) + amount));
	}

	public float getBarValue() {
		return player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW1);
	}

	public int getBarInt() {
		return (int)Math.floor(player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW1));
	}

	public void setBarValue(final float i) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW1, i);
	}

	public void removeBarValue(final float i) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW1, (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW1) - i));
	}

	@SubscribeEvent
	public void init(final Entity entity, final World world) {
	}

	static {
		energyHelper.ap = 0;
	}
}
