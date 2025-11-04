package it.unibo.inner.test.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public  class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    
    private List<T> totElements = null;

    public IterableWithPolicyImpl(T[] elements) {
        totElements = new ArrayList<>();
        for(T e : elements) {
            this.totElements.add(e);
        }
        
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new IteratorImpl();
        return iterator;
    }

    private class IteratorImpl implements Iterator<T> {

        int counter = 0;       
        
        @Override
        public T next() {
            
            
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            
            T result = totElements.get(counter);
            counter++;
            return result;            
            
            
        }

        @Override
        public boolean hasNext() {
            boolean result = false;

            if(counter < totElements.size()) {
                result = true;
            }else {
                result = false;
            }
            return result;            
        }
        
    }
}
