package com.mmuca.expLab.domain.collections;

import java.util.*;

public class ForwardingHashSet<E> implements Cloneable, Iterable<E> {
    protected HashSet<E> set;

    public ForwardingHashSet(){
         set = new HashSet<E>();
    }

    public ForwardingHashSet(Collection<? extends E> c){
        set = new HashSet<E>(c);
    }

    public ForwardingHashSet(int initialCapacity, float loadFactor) {
        set = new HashSet<E>(initialCapacity,loadFactor);
    }

    public ForwardingHashSet(int initialCapacity) {
        set = new HashSet<E>(initialCapacity);
    }

    public Iterator<E> iterator() {
        return set.iterator();
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public boolean contains(Object o) {
        return set.contains(o);
    }

    public boolean add(E e) {
        return set.add(e);
    }

    public boolean remove(Object o) {
        return set.remove(o);
    }

    public void clear() {
        set.clear();
    }

    public ForwardingHashSet<E> clone() {
       try{
           return (ForwardingHashSet<E>)super.clone();
       }
       catch(CloneNotSupportedException e){
           throw new InternalError();
       }
    }







}
