package net.nevermine.item.weapon.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.event.player.Ticker;
import net.nevermine.projectiles.energy.EntityIroMineShot;

import java.util.List;
import java.util.Random;

public class IroMiner extends BaseEnergy {
	private final float dmg = 22.0f;
	private Random rand;
	private int canShootTick;
	private String sound;
	private int rof;
	private int Energycost;
	private int newcost;

	public IroMiner(final int consumeChance, final String effect, final int uses, final int fireRate, final int cost) {
		super(consumeChance, effect, uses, fireRate, cost);
		rand = new Random();
		canShootTick = 0;
		sound = effect;
		rof = fireRate;
		Energycost = cost;
	}

	@Override
	public void fireAncient(final World world, final ItemStack stack, final EntityPlayer player) {
		if (!player.worldObj.isRemote) {
			player.worldObj.spawnEntityInWorld(new EntityIroMineShot(player.worldObj, player, dmg));
			stack.damageItem(1, player);
			canShootTick = Ticker.tick + rof;
		}
		if (rand.nextInt(4) == 3) {
			player.addVelocity(player.motionX * 2.0 * rand.nextInt(4), 1.0, player.motionZ * 2.0 * rand.nextInt(4));
			player.velocityChanged = true;
		}
	}

	/*@Override
	public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
		if (Ticker.tick >= canShootTick) {
			if (ArmorUtil.isGhoulishArmor(player)) {
				newcost = Energycost / 2 + Energycost % 2;
			}
			else {
				newcost = Energycost;
			}
			if (player.capabilities.isCreativeMode || energyHelper.getProperties(player).useBar(newcost)) {
				player.worldObj.playSoundAtEntity(player, "nevermine:" + sound, 1.0f, 1.0f);
				fireAncient(world, stack, player);
			}
		}
		if (canShootTick >= 100000) {
			canShootTick = 0;
		}
		return stack;
	}*/

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", EnumChatFormatting.DARK_RED, Integer.toString((int)dmg)));
		list.add(StringUtil.getColourLocaleString("item.IroMiner.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getLocaleString("items.description.speed.moderate"));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.energy.cost", EnumChatFormatting.LIGHT_PURPLE, "30"));
	}
}
