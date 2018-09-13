package com.srini.albumservice.model;

import java.util.ArrayList;
import java.util.Collection;

public class Photos extends ArrayList<Photo> {

    public Photos() {

    }

    public Photos(Collection<? extends Photo> c) { super(c); }
}
