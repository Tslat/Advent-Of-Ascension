package net.tslat.aoa3.common.registration;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.IProjectile;
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
		DispenserBlock.registerDispenseBehavior(AoAWeapons.HELLFIRE.get(), new ProjectileDispenseBehavior() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition dispenserPos, ItemStack stack) {
				return new HellfireEntity(world, dispenserPos.getX(), dispenserPos.getY(), dispenserPos.getZ());
			}
		});

		DispenserBlock.registerDispenseBehavior(AoAWeapons.GRENADE.get(), new ProjectileDispenseBehavior() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition dispenserPos, ItemStack stack) {
				return new GrenadeEntity(world, dispenserPos.getX(), dispenserPos.getY(), dispenserPos.getZ());
			}
		});

		DispenserBlock.registerDispenseBehavior(AoAWeapons.CHAKRAM.get(), new ProjectileDispenseBehavior() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition dispenserPos, ItemStack stack) {
				return new ChakramEntity(world, dispenserPos.getX(), dispenserPos.getY(), dispenserPos.getZ());
			}
		});

		DispenserBlock.registerDispenseBehavior(AoAWeapons.GOO_BALL.get(), new ProjectileDispenseBehavior() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition dispenserPos, ItemStack stack) {
				return new GooBallEntity(world, dispenserPos.getX(), dispenserPos.getY(), dispenserPos.getZ());
			}
		});

		DispenserBlock.registerDispenseBehavior(AoAWeapons.RUNIC_BOMB.get(), new ProjectileDispenseBehavior() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition dispenserPos, ItemStack stack) {
				return new RunicBombEntity(world, dispenserPos.getX(), dispenserPos.getY(), dispenserPos.getZ());
			}
		});

		DispenserBlock.registerDispenseBehavior(AoAWeapons.VULKRAM.get(), new ProjectileDispenseBehavior() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition dispenserPos, ItemStack stack) {
				return new VulkramEntity(world, dispenserPos.getX(), dispenserPos.getY(), dispenserPos.getZ());
			}
		});

		DispenserBlock.registerDispenseBehavior(AoAWeapons.SLICE_STAR.get(), new ProjectileDispenseBehavior() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition dispenserPos, ItemStack stack) {
				return new SliceStarEntity(world, dispenserPos.getX(), dispenserPos.getY(), dispenserPos.getZ());
			}
		});
	}

	private static void registerMiscDispensables() {
		DispenserBlock.registerDispenseBehavior(AoAItems.FRAGMENTED_ANIMA_STONE.get(), new OptionalDispenseBehavior() {
			@Override
			protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
				successful = true;

				World world = source.getWorld();
				BlockPos pos = source.getBlockPos().offset(source.getBlockState().get(DispenserBlock.FACING));

				if (BoneMealItem.applyBonemeal(stack, world, pos)) {
					if (!world.isRemote)
						world.playEvent(2005, pos, 0);
				}
				else {
					successful = false;
				}

				return stack;
			}
		});
	}
}
