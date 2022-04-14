package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
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
		return AoASounds.ITEM_GUN_SNIPER_HEAVY_FIRE_LONG.get();
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
		Registry.ITEM.getTagOrEmpty(Tags.Items.GEMS).forEach(holder -> gemDrops.add(holder.unwrap().right().get().getDefaultInstance()));

		//gemDrops.add(new ItemStack(Items.DIAMOND));
		//gemDrops.add(new ItemStack(Items.EMERALD));
		//gemDrops.add(new ItemStack(AoAItems.SAPPHIRE.get()));
		//gemDrops.add(new ItemStack(AoAItems.JADE.get()));
		//gemDrops.add(new ItemStack(AoAItems.AMETHYST.get()));

		populated = true;
	}

	public static void addGemDrop(final ItemStack stack) {
		if (!populated)
			populateGemDrops();

		gemDrops.add(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
