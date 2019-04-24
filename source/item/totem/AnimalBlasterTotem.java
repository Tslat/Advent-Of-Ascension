package net.tslat.aoa3.item.totem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.misc.EntityLottoIdol;

public class AnimalBlasterTotem extends TotemItem {
	public AnimalBlasterTotem() {
		super("AnimalBlasterTotem", "animal_blaster_totem");
	}

	@Override
	protected void summonTotemEntity(World world, EntityPlayer player, BlockPos pos) {
		ItemStack majorPrize = ItemStack.EMPTY;

		switch (itemRand.nextInt(8)) {
			case 0:
				majorPrize = new ItemStack(WeaponRegister.blasterPenguinBlaster);
				break;
			case 1:
				majorPrize = new ItemStack(WeaponRegister.blasterBearBlaster);
				break;
			case 2:
				majorPrize = new ItemStack(WeaponRegister.blasterDragonDestroyer);
				break;
			case 3:
				majorPrize = new ItemStack(WeaponRegister.blasterDeerDetonator);
				break;
			case 4:
				majorPrize = new ItemStack(WeaponRegister.blasterBeeBlaster);
				break;
			case 5:
				majorPrize = new ItemStack(WeaponRegister.blasterFishFryer);
				break;
			case 6:
				majorPrize = new ItemStack(WeaponRegister.blasterHoundHoncho);
				break;
			case 7:
				majorPrize = new ItemStack(WeaponRegister.blasterCamelCannon);
				break;
		}

		EntityLottoIdol idolEntity = new EntityLottoIdol(world, majorPrize, new ItemStack(ItemRegister.tokensMysterium));

		idolEntity.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
		world.spawnEntity(idolEntity);
	}
}
