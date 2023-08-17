package com.runjing.learn_runjing.utils.copy;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author forestSpringH
 * @date 2023/6/23
 * @project learn_runjing
 */
public class BeanCopyHandler {
    private static final MapperFactory MAPPER_FACTORY;
    private static final MapperFacade MAPPER_FACADE;

    static {
        MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();
        MAPPER_FACADE = MAPPER_FACTORY.getMapperFacade();

        ServiceLoader<CopyInterface> serviceLoader = ServiceLoader.load(CopyInterface.class);
        Stream<CopyInterface> stream = StreamSupport.stream(serviceLoader.spliterator(), true);
        stream.filter(Objects::nonNull).forEach(element -> element.register(MAPPER_FACTORY));
    }

    public static <S,T> T map(S source, Class<T> targetClass){
        return MAPPER_FACADE.map(source,targetClass);
    }

    public static <S,T> List<T> mapAsList(Iterable<S> source, Class<T> targetClass){
        return MAPPER_FACADE.mapAsList(source,targetClass);
    }

    public static <S,T> List<T> mapAsList(List<S> source, Class<T> targetClass){
        S[] sourceObjects = (S[]) source.stream().toArray();
        return MAPPER_FACADE.mapAsList(sourceObjects,targetClass);
    }
}
