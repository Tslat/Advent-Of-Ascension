package net.tslat.aoa3.common.registration;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.command.argument.AoAResourceArgument;
import net.tslat.aoa3.command.argument.AoASkillArgument;

import java.util.function.Supplier;

public final class AoAArgumentTypes {
	public static void init() {}

	public static final RegistryObject<SingletonArgumentInfo<AoASkillArgument>> AOA_SKILL = register("aoa_skill", AoASkillArgument.class, () -> SingletonArgumentInfo.contextAware(AoASkillArgument::skill));
	public static final RegistryObject<SingletonArgumentInfo<AoAResourceArgument>> AOA_RESOURCE = register("aoa_resource", AoAResourceArgument.class, () -> SingletonArgumentInfo.contextAware(AoAResourceArgument::resource));

	private static <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>, I extends ArgumentTypeInfo<A, T>> RegistryObject<I> register(String id, Class<A> argumentClass, Supplier<I> argumentType) {
		ArgumentTypeInfos.registerByClass(argumentClass, argumentType.get());
		return AoARegistries.ARGUMENT_TYPES.register(id, argumentType);
	}
}
