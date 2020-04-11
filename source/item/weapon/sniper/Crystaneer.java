package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Crystaneer extends BaseSniper {
	private static final ArrayList<ItemStack> gemDrops = new ArrayList<ItemStack>(5);
	private static boolean populated = false;

	public Crystaneer(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("Crystaneer");
		setRegistryName("aoa3:crystaneer");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunSniper;
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.CRYSTAL;
	}

	@Override
	protected void doImpactEffect(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target instanceof EntityLivingBase && ((EntityLivingBase)target).getHealth() <= 0 && itemRand.nextInt(5) == 0) {
			if (!populated)
				populateGemDrops();

			target.entityDropItem(gemDrops.get(gemDrops.size() - 1), 0f);
		}
	}

	private static void populateGemDrops() {
		gemDrops.add(new ItemStack(Items.DIAMOND));
		gemDrops.add(new ItemStack(Items.EMERALD));
		gemDrops.add(new ItemStack(ItemRegister.gemSapphire));
		gemDrops.add(new ItemStack(ItemRegister.gemJade));
		gemDrops.add(new ItemStack(ItemRegister.gemAmethyst));

		populated = true;
	}

	public static void addGemDrop(final ItemStack stack) {
		if (!populated)
			populateGemDrops();

		gemDrops.add(stack);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.Crystaneer.desc.1", Enums.ItemDescriptionType.UNIQUE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
