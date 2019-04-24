package net.nevermine.item.weapon.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.common.nevermine;
import net.nevermine.projectiles.energy.*;

import java.util.List;

public class SoundCannonBeat extends BaseEnergy {
	private final float dmg = 12.0f;

	public SoundCannonBeat(final int consumeChance, final String effect, final int uses, final int fireRate, final int cost) {
		super(consumeChance, effect, uses, fireRate, cost);
	}

	@Override
	public void fireAncient(final World world, final ItemStack stack, final EntityPlayer player) {
		switch (nevermine.rand.nextInt(5)) {
			case 0:
				player.worldObj.playSoundAtEntity(player, "nevermine:SoundCannonBeat1", 1.0f, 1.0f);

				if (!player.worldObj.isRemote)
					player.worldObj.spawnEntityInWorld(new EntitySoundCannon1(player.worldObj, player, dmg));
				break;
			case 1:
				player.worldObj.playSoundAtEntity(player, "nevermine:SoundCannonBeat2", 1.0f, 1.0f);

				if (!player.worldObj.isRemote)
					player.worldObj.spawnEntityInWorld(new EntitySoundCannon2(player.worldObj, player, dmg));
				break;
			case 2:
				player.worldObj.playSoundAtEntity(player, "nevermine:SoundCannonBeat3", 1.0f, 1.0f);

				if (!player.worldObj.isRemote)
					player.worldObj.spawnEntityInWorld(new EntitySoundCannon3(player.worldObj, player, dmg));
				break;
			case 3:
				player.worldObj.playSoundAtEntity(player, "nevermine:SoundCannonBeat4", 1.0f, 1.0f);

				if (!player.worldObj.isRemote)
					player.worldObj.spawnEntityInWorld(new EntitySoundCannon4(player.worldObj, player, dmg));
				break;
			case 4:
				player.worldObj.playSoundAtEntity(player, "nevermine:SoundCannonBeat5", 1.0f, 1.0f);

				if (!player.worldObj.isRemote)
					player.worldObj.spawnEntityInWorld(new EntitySoundCannon5(player.worldObj, player, dmg));
				break;
			default:
				break;
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getLocaleString("items.description.speed.veryFast"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.energy.cost", EnumChatFormatting.LIGHT_PURPLE, "8"));
	}
}
