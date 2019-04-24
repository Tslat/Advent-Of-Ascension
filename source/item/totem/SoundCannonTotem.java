package net.tslat.aoa3.item.totem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.misc.EntityLottoIdol;

public class SoundCannonTotem extends TotemItem {
	public SoundCannonTotem() {
		super("SoundCannonTotem", "sound_cannon_totem");
	}

	@Override
	protected void summonTotemEntity(World world, EntityPlayer player, BlockPos pos) {
		ItemStack majorPrize = ItemStack.EMPTY;

		switch (itemRand.nextInt(5)) {
			case 0:
				majorPrize = new ItemStack(WeaponRegister.blasterBeatSoundCannon);
				break;
			case 1:
				majorPrize = new ItemStack(WeaponRegister.blasterElectroSoundCannon);
				break;
			case 2:
				majorPrize = new ItemStack(WeaponRegister.blasterStepSoundCannon);
				break;
			case 3:
				majorPrize = new ItemStack(WeaponRegister.blasterSynthSoundCannon);
				break;
			case 4:
				majorPrize = new ItemStack(WeaponRegister.blasterVibeSoundCannon);
				break;
		}

		EntityLottoIdol idolEntity = new EntityLottoIdol(world, majorPrize, new ItemStack(ItemRegister.tokensMysterium));

		idolEntity.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
		world.spawnEntity(idolEntity);
	}
}
