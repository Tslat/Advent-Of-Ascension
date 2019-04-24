package net.nevermine.resource.rage;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.skill.butchery.butcheryHelper;

public class rageHelper implements IExtendedEntityProperties {
	private static int ap;
	private final EntityPlayer player;
	private static final String NAME = "ThermalBar";

	public rageHelper(final EntityPlayer player) {
		this.player = player;
		player.getDataWatcher().addObject(ConfigurationHelper.dataW2, 100.0f);
	}

	public void saveNBTData(final NBTTagCompound n) {
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat("Value", player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW2));
		n.setTag("ThermalBar", tag);
	}

	public static void addProperties(final EntityPlayer player) {
		player.registerExtendedProperties("ThermalBar", new rageHelper(player));
	}

	public static rageHelper getProperties(final EntityPlayer player) {
		return (rageHelper)player.getExtendedProperties("ThermalBar");
	}

	public int getBarInt() {
		return (int)Math.floor(player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW2) / 2.0f);
	}

	public void loadNBTData(final NBTTagCompound n) {
		final NBTTagCompound tag = (NBTTagCompound)n.getTag("ThermalBar");
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW2, tag.getFloat("Value"));
	}

	public void updateAllBars() {
		if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW2) < 0.0f) {
			player.getDataWatcher().updateObject(ConfigurationHelper.dataW2, 0.0f);
		}
		if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW2) != 200.0f) {
			regen(butcheryHelper.getTickRegen(player));
		}
		else {
			regen(0.0f);
		}
		if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW2) >= 200.0f) {
			player.getDataWatcher().updateObject(ConfigurationHelper.dataW2, 200.0f);
		}
	}

	public void regen(final float amount) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW2, (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW2) + amount));
	}

	public float getBarValue() {
		return player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW2);
	}

	public float getRageValue() {
		return player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW2) / 2.0f;
	}

	public void setBarValue(final float i) {
		player.getDataWatcher().updateObject(ConfigurationHelper.dataW2, i);
	}

	public void removeBarValue(final float i) {
		if (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW2) > i) {
			player.getDataWatcher().updateObject(ConfigurationHelper.dataW2, (player.getDataWatcher().getWatchableObjectFloat(ConfigurationHelper.dataW2) - i));
		}
		else {
			player.getDataWatcher().updateObject(ConfigurationHelper.dataW2, 0.0f);
		}
	}

	@SubscribeEvent
	public void init(final Entity entity, final World world) {
	}

	static {
		rageHelper.ap = 0;
	}
}
