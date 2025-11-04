package it.unibo.inner.test.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public  class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    
    private List<T> totElements = null;
    private Predicate<T> iterationPolicyPredicate;
    
    /**
     * The 1-ary constructor call the 2-ary constructor giving, the array T and using an anonymous class
     * which implements the override of the method test. 
     * @param elements a generic array.
     */
    public IterableWithPolicyImpl(T[] elements) {
        
        this(elements, new Predicate<T>() {
            
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
        
    }

    /**
     * The 2-ary constructor which save in the local List<T> 
     * all the elements of the first parameter given. 
     * Setting also the local Predictate filed.
     * @param elements a generic array.
     * @param iterationPolicy a predictate which return always true, no matter the T elment given.         
     */
    public IterableWithPolicyImpl(T[] elements, Predicate<T> iterationPolicy) {
        totElements = new ArrayList<>();
        for(T e : elements) {
            this.totElements.add(e);
        }
        this.setIterationPolicy(iterationPolicy); //save the value of the predictate in the filed using a set method
    }

    
    /**
     * This method set the general Predictate filed with the value 
     * passed from the 2-ary constructor.
     * @param iterationPolicyPredicate      
     */
    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        iterationPolicyPredicate = filter;
        
    }



    /**
     * Methed that create an istance of Iterator
     * @return iterator istance created right before
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new IteratorImpl();
        return iterator;
    }

    private class IteratorImpl implements Iterator<T> {

        int counter = 0;       




        /**
         * Implementation of method next() from Iterator<T>.
         * Give an exception if the List<T> has been already visited or there's no element.
         * Otherwise takes the next element.
         * @return The next element in the List<T> field, based on the field counter.
         */
        @Override
        public T next() {             
            
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            
            T result = totElements.get(counter);
            counter++;
            
            return result;             
        }


        /**
         * Implementation of method hasNext() from Iterator<T>.
         * Before return it check (with the Predicate<T> filter) in a while costruct:
         * - the presence of elements in the array
         * - the validity of the element given to the method test() of the istance of Predicate<T> based on the result of it.
         * @return the boolean value of the presence or not of value inside the List<T> field
         */
        @Override
        public boolean hasNext() {          
            while (counter < totElements.size() && !iterationPolicyPredicate.test(totElements.get(counter))) {
                counter++;
            }

            return counter < totElements.size();
        }
        
    }
}
