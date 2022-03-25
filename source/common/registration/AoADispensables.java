package net.tslat.aoa3.common.registration;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.*;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.projectile.thrown.*;

public final class AoADispensables {
	public static void registerDispenseBehaviours() {
		registerFluidDispensables();
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

		DispenserBlock.registerBehavior(AoAWeapons.HARDENED_PARAPIRANHA.get(), new ProjectileDispenseBehavior() {
			@Override
			protected ProjectileEntity getProjectile(World world, IPosition dispenserPos, ItemStack stack) {
				return new HardenedParapiranhaEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});
	}

	private static void registerMiscDispensables() {
	}

	private static void registerFluidDispensables() {
		IDispenseItemBehavior fluidDispenser = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultBehaviour = new DefaultDispenseItemBehavior();

			@Override
			protected ItemStack execute(IBlockSource source, ItemStack stack) {
				BucketItem bucket = (BucketItem)stack.getItem();
				BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
				World world = source.getLevel();

				if (bucket.emptyBucket(null, world, pos, null)) {
					bucket.checkExtraContent(world, stack, pos);

					return new ItemStack(Items.BUCKET);
				}
				else {
					return this.defaultBehaviour.dispense(source, stack);
				}
			}
		};

		DispenserBlock.registerBehavior(ForgeRegistries.ITEMS.getValue(new ResourceLocation(AdventOfAscension.MOD_ID, "candied_water_bucket")), fluidDispenser);
		DispenserBlock.registerBehavior(ForgeRegistries.ITEMS.getValue(new ResourceLocation(AdventOfAscension.MOD_ID, "toxic_waste_bucket")), fluidDispenser);
		DispenserBlock.registerBehavior(ForgeRegistries.ITEMS.getValue(new ResourceLocation(AdventOfAscension.MOD_ID, "clear_water_bucket")), fluidDispenser);
	}
}
