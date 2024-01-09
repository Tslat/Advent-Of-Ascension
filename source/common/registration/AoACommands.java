package net.tslat.aoa3.common.registration;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.command.AoACommand;
import net.tslat.aoa3.command.argument.AoAResourceArgument;
import net.tslat.aoa3.command.argument.AoASkillArgument;

import java.util.function.Supplier;

public final class AoACommands {
	public static void init() {
		NeoForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RegisterCommandsEvent.class, AoACommands::registerCommands);
	}

	public static final DeferredHolder<ArgumentTypeInfo<?, ?>, SingletonArgumentInfo<AoASkillArgument>> AOA_SKILL = register("aoa_skill", AoASkillArgument.class, () -> SingletonArgumentInfo.contextAware(AoASkillArgument::skill));
	public static final DeferredHolder<ArgumentTypeInfo<?, ?>, SingletonArgumentInfo<AoAResourceArgument>> AOA_RESOURCE = register("aoa_resource", AoAResourceArgument.class, () -> SingletonArgumentInfo.contextAware(AoAResourceArgument::resource));

	private static void registerCommands(final RegisterCommandsEvent ev) {
		AoACommand.registerSubCommands(ev.getDispatcher(), ev.getBuildContext());
	}

	private static <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>, I extends ArgumentTypeInfo<A, T>> DeferredHolder<ArgumentTypeInfo<?, ?>, I> register(String id, Class<A> argumentClass, Supplier<I> argumentType) {
		ArgumentTypeInfos.registerByClass(argumentClass, argumentType.get());

		return AoARegistries.ARGUMENT_TYPES.register(id, argumentType);
	}
}
