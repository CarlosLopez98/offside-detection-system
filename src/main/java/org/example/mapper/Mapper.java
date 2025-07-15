package org.example.mapper;

public interface Mapper<M, D> {
    D toDrawable(M e);
    M toElement(D d);
}
