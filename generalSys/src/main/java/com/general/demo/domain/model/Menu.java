package com.general.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.general.demo.ex.FatalException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
public class Menu {

    private final List<Business> businesses = new ArrayList<>();
    public Menu addBusiness(@NotNull String id, @NotNull String url, @NotNull String name,@NotNull String icon) throws FatalException {
        businesses.add(new Business(id,url,name,icon,new ArrayList()));
        return this;
    }

    public Menu addFunc(@NotNull String id, @NotNull String url, @NotNull String name,@NotNull String icon) throws FatalException {
        Func func = new Func(id,url,name,icon);
        businesses.stream()
            .filter(e->id.equals(e.getId()))
            .findFirst()
            .orElseThrow(()->new FatalException("対象データなし"))
            .addFunc(func);
        return this;
    }

    @Data
    @AllArgsConstructor
    class Business{
        private final String id;
        private final String url;
        private final String name;
        private final String icon;
        private final List<Func> funcs;
        public Business addFunc(Func f) {
            funcs.add(f);
            return this;
        }
    }

    @Data
    @AllArgsConstructor
    class Func{
        private String id;
        private String url;
        private String name;
        private String icon;
    }
}
