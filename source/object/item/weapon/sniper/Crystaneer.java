package net.tslat.aoa3.object.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Crystaneer extends BaseSniper {
	private static final ArrayList<ItemStack> gemDrops = new ArrayList<ItemStack>(5);
	private static boolean populated = false;

	public Crystaneer(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SNIPER_FIRE.get();
	}

	@Override
	public ResourceLocation getScopeTexture(ItemStack stack) {
		return SCOPE_4;
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target instanceof LivingEntity && ((LivingEntity)target).getHealth() <= 0 && RandomUtil.oneInNChance(5)) {
			if (!populated)
				populateGemDrops();

			target.spawnAtLocation(RandomUtil.getRandomSelection(gemDrops), 0f);
		}
	}

	private static void populateGemDrops() {
		gemDrops.add(new ItemStack(Items.DIAMOND));
		gemDrops.add(new ItemStack(Items.EMERALD));
		gemDrops.add(new ItemStack(AoAItems.SAPPHIRE.get()));
		gemDrops.add(new ItemStack(AoAItems.JADE.get()));
		gemDrops.add(new ItemStack(AoAItems.AMETHYST.get()));

		populated = true;
	}

	public static void addGemDrop(final ItemStack stack) {
		if (!populated)
			populateGemDrops();

		gemDrops.add(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
