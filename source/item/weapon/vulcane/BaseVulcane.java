package net.nevermine.item.weapon.vulcane;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.placement.EntityHunter;
import net.nevermine.skill.vulcanism.vulcanismHelper;

import java.util.Random;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;
import static net.nevermine.container.PlayerContainer.Skills.Vulcanism;

public abstract class BaseVulcane extends Item {
	private Random rand = new Random();
	private int canShootTick;
	private String sound;
	private float mult;
	private float modify;
	private float dmg;
	private int level;
	private int required;

	public BaseVulcane(final String effect, final int uses, final float damage, final int need) {
		canShootTick = 0;
		mult = 1.0f;
		sound = effect;
		setFull3D();
		setMaxDamage(uses);
		maxStackSize = 1;
		dmg = damage;
		required = need;
		setCreativeTab(Weaponizer.VulcanesTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public EnumAction getItemUseAction(final ItemStack par1ItemStack) {
		return EnumAction.none;
	}

	public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
		if (player.worldObj.isRemote)
			return stack;

		PlayerContainer cont = PlayerContainer.getProperties(player);

		if (!cont.revengeActive())
			return stack;

		if (!(cont.getLevel(Vulcanism) >= required)) {
			player.addChatMessage(StringUtil.getLocaleWithArguments("message.feedback.item.vulcane.fail", Integer.toString(required)));
		}

		float multiplier = vulcanismHelper.getVulcaneDamageMultiplier(cont.getLevel(Vulcanism));

		if (vulcanismHelper.isWearingVulcanismArmor(player))
			multiplier = 3.0f;

		if (cont.getRevengeTarget() != null) {
			EntityMob mob = cont.getRevengeTarget();

			if (mob instanceof EntityHunter) {
				if (cont.getLevel(Hunter) > ((EntityHunter)mob).getLevReq()) {
					if (mob.getHealth() > dmg * multiplier) {
						mob.setHealth(mob.getHealth() - dmg * multiplier);
						mob.attackEntityFrom(DamageSource.causeIndirectMagicDamage(mob, player), 0.0f);
					}
					else {
						mob.attackEntityFrom(DamageSource.causeIndirectMagicDamage(mob, player), dmg * multiplier);
					}

					fireGun(world, stack, player, multiplier, mob);
					cont.addExperience(cont.getExpRequired(Vulcanism) / vulcanismHelper.getExpDenominator(cont.getLevel(Vulcanism)), Vulcanism);
				}
			}
			else {
				if (mob.getHealth() > dmg * multiplier) {
					mob.setHealth(mob.getHealth() - dmg * multiplier);
					mob.attackEntityFrom(DamageSource.causeIndirectMagicDamage(mob, player), 0.0f);
				}
				else {
					mob.attackEntityFrom(DamageSource.causeIndirectMagicDamage(mob, player), dmg * multiplier);
				}

				fireGun(world, stack, player, multiplier, mob);
				cont.addExperience(cont.getExpRequired(Vulcanism) / vulcanismHelper.getExpDenominator(cont.getLevel(Vulcanism)), Vulcanism);
			}
		}

		stack.damageItem(1, player);
		cont.revengeEnacted();
		player.worldObj.playSoundAtEntity(player, "nevermine:" + sound, 1.0f, 1.0f);
		return stack;
	}

	public abstract void fireGun(final World p0, final ItemStack p1, final EntityPlayer p2, final float p3, final EntityMob p4);
}
