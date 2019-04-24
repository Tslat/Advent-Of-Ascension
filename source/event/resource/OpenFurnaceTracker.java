package net.nevermine.event.resource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class OpenFurnaceTracker implements IExtendedEntityProperties {
	private final EntityPlayer player;
	public static final String NAME = "OpenFurnaceTracker";
	public int lastStackSize;
	public int lastMeta;
	public Item lastItem;

	public OpenFurnaceTracker(final EntityPlayer player) {
		this.player = player;
	}

	public void saveNBTData(final NBTTagCompound n) {
	}

	public void loadNBTData(final NBTTagCompound n) {
	}

	public static void addProperties(final EntityPlayer player) {
		player.registerExtendedProperties("OpenFurnaceTracker", new OpenFurnaceTracker(player));
	}

	public static OpenFurnaceTracker getProperties(final EntityPlayer player) {
		return (OpenFurnaceTracker)player.getExtendedProperties("OpenFurnaceTracker");
	}

	public Item getLast() {
		return lastItem;
	}

	public void SetLast(final Item i) {
		lastItem = i;
	}

	public void init(final Entity entity, final World world) {
	}
}
