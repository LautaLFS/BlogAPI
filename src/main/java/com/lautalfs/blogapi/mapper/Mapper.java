package com.lautalfs.blogapi.mapper;

import org.springframework.lang.NonNull;

public interface Mapper<D, E>{

    D toDto(@NonNull E e);

    E toEntity(@NonNull D d);

    E toUpdate(@NonNull D d, E e);
}
