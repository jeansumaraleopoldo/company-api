package com.company.sumpla.dtos;

public interface BaseRepresentationBuilder<E, D, B> {

    D toRepresentation(E entity);

    E fromRepresentation(D representation, B builder);
}
