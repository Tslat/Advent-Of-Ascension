package net.tslat.aoa3.hooks.tconstruct.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.hooks.tconstruct.traits.Traits;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class ModMoneybags extends ModifierTrait {
	public ModMoneybags() {
		super("moneybags", 0xFFCC00, 3, 0);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if (wasHit && !player.world.isRemote && (target.getHealth() <= 0 || target.isDead)) {
			int tier = Traits.getModifierStage(tool, this);

			for (float x = -0.5f; x <= 0.5f; x += 0.5f) {
				for (float z = -0.5f; z <= 0.5f; z += 0.5f) {
					if (random.nextInt(40) < tier * 12) {
						EntityItem coin = new EntityItem(player.world, target.posX, target.posY + target.height, target.posZ, new ItemStack(random.nextInt(30) < tier ? ItemRegister.SILVER_COIN : ItemRegister.COPPER_COIN));

						coin.setPickupDelay(10);
						coin.addVelocity(x, 0.5, z);
						coin.lifespan = 200;
						player.world.spawnEntity(coin);
					}
				}
			}
		}
	}
}
