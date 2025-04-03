package com.tcars.maincarsservice.util.mapper;

public interface Mapper<T, S> {

    T mapToModel(S s);

    S mapToDto(T t);

}
