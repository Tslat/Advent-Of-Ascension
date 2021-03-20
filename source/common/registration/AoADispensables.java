package net.tslat.aoa3.common.registration;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectile.thrown.*;

public final class AoADispensables {
	public static void registerDispenseBehaviours() {
		registerProjectileDispensables();
		registerMiscDispensables();
	}

	private static void registerProjectileDispensables() {
		DispenserBlock.registerBehavior(AoAWeapons.HELLFIRE.get(), new ProjectileDispenseBehavior() {
			@Override
			protected ProjectileEntity getProjectile(World world, IPosition dispenserPos, ItemStack stack) {
				return new HellfireEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.GRENADE.get(), new ProjectileDispenseBehavior() {
			@Override
			protected ProjectileEntity getProjectile(World world, IPosition dispenserPos, ItemStack stack) {
				return new GrenadeEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.CHAKRAM.get(), new ProjectileDispenseBehavior() {
			@Override
			protected ProjectileEntity getProjectile(World world, IPosition dispenserPos, ItemStack stack) {
				return new ChakramEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.GOO_BALL.get(), new ProjectileDispenseBehavior() {
			@Override
			protected ProjectileEntity getProjectile(World world, IPosition dispenserPos, ItemStack stack) {
				return new GooBallEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.RUNIC_BOMB.get(), new ProjectileDispenseBehavior() {
			@Override
			protected ProjectileEntity getProjectile(World world, IPosition dispenserPos, ItemStack stack) {
				return new RunicBombEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.VULKRAM.get(), new ProjectileDispenseBehavior() {
			@Override
			protected ProjectileEntity getProjectile(World world, IPosition dispenserPos, ItemStack stack) {
				return new VulkramEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.SLICE_STAR.get(), new ProjectileDispenseBehavior() {
			@Override
			protected ProjectileEntity getProjectile(World world, IPosition dispenserPos, ItemStack stack) {
				return new SliceStarEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});
	}

	private static void registerMiscDispensables() {
		DispenserBlock.registerBehavior(AoAItems.FRAGMENTED_ANIMA_STONE.get(), new OptionalDispenseBehavior() {
			@Override
			protected ItemStack execute(IBlockSource source, ItemStack stack) {
				setSuccess(true);

				World world = source.getLevel();
				BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));

				if (BoneMealItem.growCrop(stack, world, pos)) {
					if (!world.isClientSide)
						world.levelEvent(2005, pos, 0);
				}
				else {
					setSuccess(false);
				}

				return stack;
			}
		});
	}
}
