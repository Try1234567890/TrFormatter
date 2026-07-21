package com.github.jsf.dynamic_placeholders.phases.resolution.parser.types;

import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.containers.ListType;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.containers.MapType;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.numbers.*;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.text.CharType;
import com.github.jsf.dynamic_placeholders.phases.resolution.parser.types.text.StringType;
import com.github.utilities.registries.CollectionRegistry;
import com.github.utilities.validators.Preconditions;

import java.util.Collection;

public class ParameterTypes extends CollectionRegistry<ParameterType<?>> {
    private ParameterTypes() {
        super();
        register(new ListType());
        register(new MapType());

        register(new ByteType());
        register(new ShortType());
        register(new IntegerType());
        register(new LongType());
        register(new FloatType());
        register(new DoubleType());

        register(new BooleanType());
        register(new CharType());

        //register(new StringType());
    }

    private record Holder() {
        private static final ParameterTypes INSTANCE = new ParameterTypes();
    }

    private static ParameterTypes getInstance() {
        return Holder.INSTANCE;
    }

    public static void newType(ParameterType<?> type) {
        getInstance().register(Preconditions.parameterNotNull(type, "type"));
    }

    public static Collection<ParameterType<?>> all() {
        return getInstance().asCollection();
    }
}

















