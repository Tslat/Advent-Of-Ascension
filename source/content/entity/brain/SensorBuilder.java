package net.tslat.aoa3.content.entity.brain;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.LivingEntity;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class SensorBuilder<E extends LivingEntity> extends ObjectArrayList<ExtendedSensor<E>> {
    public static <E extends LivingEntity> SensorBuilder<E> with(List<ExtendedSensor<E>> parentList, ExtendedSensor<E>... additional) {
        return new SensorBuilder<>(parentList).withAll(additional);
    }

    public static <E extends LivingEntity> SensorBuilder<E> with(ExtendedSensor<E>... additional) {
        return new SensorBuilder<E>().withAll(additional);
    }

    public static <E extends LivingEntity> SensorBuilder<E> with(List<ExtendedSensor<E>> parentList) {
        return new SensorBuilder<>(parentList);
    }

    private SensorBuilder(Collection<ExtendedSensor<E>> wrapping) {
        super(wrapping);
    }

    private SensorBuilder() {
        super();
    }

    public SensorBuilder<E> with(ExtendedSensor<E> sensor) {
        super.add(sensor);

        return this;
    }

    public SensorBuilder<E> withAll(ExtendedSensor<E>... sensors) {
        super.addAll(List.of(sensors));

        return this;
    }

    public <S extends ExtendedSensor<E>> SensorBuilder<E> replaceFirst(Class<? extends ExtendedSensor<E>> replacing, S replacement) {
        return replaceNthInstance(replacing, 0, replacement);
    }

    public <S extends ExtendedSensor<E>> SensorBuilder<E> replaceLast(Class<? extends ExtendedSensor<E>> replacing, S replacement) {
        int index = lastIndexOf(replacing);

        if (index >= 0)
            set(index, replacement);

        return this;
    }

    public <S extends ExtendedSensor<E>> SensorBuilder<E> replaceNthInstance(Class<? extends ExtendedSensor<E>> replacing, int ordinal, S replacement) {
        int index = indexOf(replacing);

        while (index >= 0 && ordinal-- > 0) {
            index = indexOf(replacing, index + 1);
        }

        if (index >= 0 && ordinal < 0)
            set(index, replacement);

        return this;
    }

    public <S extends ExtendedSensor<E>> SensorBuilder<E> insertAfterFirst(Class<? extends ExtendedSensor<E>> replacing, S replacement) {
        return insertAfterNthInstance(replacing, 0, replacement);
    }

    public <S extends ExtendedSensor<E>> SensorBuilder<E> insertAfterLast(Class<? extends ExtendedSensor<E>> replacing, S replacement) {
        int index = lastIndexOf(replacing);

        if (index >= 0)
            add(index + 1, replacement);

        return this;
    }

    public <S extends ExtendedSensor<E>> SensorBuilder<E> insertAfterNthInstance(Class<? extends ExtendedSensor<E>> replacing, int ordinal, S replacement) {
        int index = indexOf(replacing);

        while (index >= 0 && ordinal-- > 0) {
            index = indexOf(replacing, index + 1);
        }

        if (index >= 0 && ordinal < 0)
            add(index + 1, replacement);

        return this;
    }

    public <S extends ExtendedSensor<E>> SensorBuilder<E> insertBeforeFirst(Class<? extends ExtendedSensor<E>> replacing, S replacement) {
        return insertBeforeNthInstance(replacing, 0, replacement);
    }

    public <S extends ExtendedSensor<E>> SensorBuilder<E> insertBeforeLast(Class<? extends ExtendedSensor<E>> replacing, S replacement) {
        int index = lastIndexOf(replacing);

        if (index >= 0)
            add(index, replacement);

        return this;
    }

    public <S extends ExtendedSensor<E>> SensorBuilder<E> insertBeforeNthInstance(Class<? extends ExtendedSensor<E>> replacing, int ordinal, S replacement) {
        int index = indexOf(replacing);

        while (index >= 0 && ordinal-- > 0) {
            index = indexOf(replacing, index + 1);
        }

        if (index >= 0 && ordinal < 0)
            add(index, replacement);

        return this;
    }

    public int indexOf(Predicate<ExtendedSensor<E>> predicate) {
        for (int i = 0; i < size(); i++) {
            if (predicate.test(get(i)))
                return i;
        }

        return -1;
    }

    public int indexOf(Class<? extends ExtendedSensor<E>> sensorClass) {
        return indexOf(sensorClass, 0);
    }

    public int indexOf(Class<? extends ExtendedSensor<E>> sensorClass, int starting) {
        for (int i = starting; i < size(); i++) {
            if (get(i).getClass() == sensorClass)
                return i;
        }

        return -1;
    }

    public int lastIndexOf(Class<? extends ExtendedSensor<E>> sensorClass) {
        for (int i = size() - 1; i >= 0; i--) {
            if (get(i).getClass() == sensorClass)
                return i;
        }

        return -1;
    }
}
