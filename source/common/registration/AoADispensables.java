package net.tslat.aoa3.common.registration;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.projectile.thrown.*;

public final class AoADispensables {
	public static void registerDispenseBehaviours() {
		registerFluidDispensables();
		registerProjectileDispensables();
		registerMiscDispensables();
	}

	private static void registerProjectileDispensables() {
		DispenserBlock.registerBehavior(AoAWeapons.HELLFIRE.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new HellfireEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.GRENADE.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new GrenadeEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.CHAKRAM.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new ChakramEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.GOO_BALL.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new GooBallEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.RUNIC_BOMB.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new RunicBombEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.VULKRAM.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new VulkramEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.SLICE_STAR.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new SliceStarEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.HARDENED_PARAPIRANHA.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new HardenedParapiranhaEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});
	}

	private static void registerMiscDispensables() {
	}

	private static void registerFluidDispensables() {
		DefaultDispenseItemBehavior fluidDispenser = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultBehaviour = new DefaultDispenseItemBehavior();

			@Override
			protected ItemStack execute(BlockSource source, ItemStack stack) {
				BucketItem bucket = (BucketItem)stack.getItem();
				BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
				Level world = source.getLevel();

				if (bucket.emptyContents(null, world, pos, null)) {
					bucket.checkExtraContent(null, world, stack, pos);

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
