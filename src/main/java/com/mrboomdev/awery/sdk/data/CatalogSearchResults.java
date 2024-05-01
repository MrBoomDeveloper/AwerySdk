package com.mrboomdev.awery.sdk.data;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface CatalogSearchResults<T> extends List<T> {

    boolean hasNextPage();

    abstract class Impl<T> extends ArrayList<T> implements CatalogSearchResults<T> {
        public Impl(Collection<T> collection) {
            super(collection);
        }

        public Impl() {}
    }

    @NotNull
    @Contract("_, _ -> new")
    static <T> CatalogSearchResults<T> of(Collection<T> list, boolean hasNextPage) {
        return new CatalogSearchResults.Impl<>(list) {

            @Override
            public boolean hasNextPage() {
                return hasNextPage;
            }
        };
    }
}