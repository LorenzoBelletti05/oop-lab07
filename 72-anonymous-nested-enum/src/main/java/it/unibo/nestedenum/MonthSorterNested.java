package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {
        JANUARY("January", 31), 
        FEBRUARY("February", 28), 
        MARCH("March", 31), 
        APRIL("April", 30), 
        MAY("May", 31), 
        JUNE("June", 30), 
        JULY("July", 31), 
        AUGUST("August", 31), 
        SEPTEMBER("September", 30), 
        OCTOBER("October", 31), 
        NOVEMBER("November", 30), 
        DECEMBER("December", 31);

        private final Integer dayInAMonth;
        private final String corrispectiveName;

        //enum costructor
        Month(final String str, final Integer numDay) {
            this.corrispectiveName = str;
            this.dayInAMonth = numDay;
        }

        public Integer getDays() {
            return this.dayInAMonth;
        }


        static Month fromString(String inputStr) { 

            String lowerInputStr = "";            
            Month mthFound = null;
            Integer maxLenghtMonth = 9; //the longest name lenght
            Integer countCopy = 0;

            //check if input is null
            if(inputStr == null) {
                throw new IllegalArgumentException("Input can not be null");
            }           
            
            //convert input in lower case
            lowerInputStr = inputStr.toLowerCase();           
                      
            //check lenght and iterate all month
            if(lowerInputStr.length() > maxLenghtMonth) {
                    throw new IllegalArgumentException("The input is too long, does not mach any Month");
            }else {            
                for(Month mth : Month.values()) {
                      
                    if(mth.corrispectiveName.toLowerCase().startsWith(lowerInputStr)) {
                        mthFound = mth;
                        countCopy++;
                    }                    
                }
            }

            //check variable for ambiguity
            if(countCopy > 1) {
                throw new IllegalArgumentException("The input is too ambigus, can find the right month");
            }else if(countCopy == 0) {
                throw new IllegalArgumentException("No month has been found");
            }                
            return mthFound;                
        }
    }
    

    
    //create a new SortByDate and return it
    @Override
    public Comparator<String> sortByDays() {
        
        MonthSorterNested.SortByDate elem = new SortByDate();
        return elem;

    }

    //create a new sortByOrder and return it
    @Override
    public Comparator<String> sortByOrder() {
        MonthSorterNested.SortByMonthOrder elem = new SortByMonthOrder();
        return elem;
    }

    
    private static class SortByMonthOrder implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            
            Month a1 = Month.fromString(o1);
            Month a2 = Month.fromString(o2);
            int result = 0;            
            
            if(a1.ordinal() > a2.ordinal()) {
                result = 1;
            }else if(a1.ordinal() < a2.ordinal()) {
                result = -1;
            }else if(a1.ordinal() == a2.ordinal()) {
                result = 0;
            }            
            return result;         
        }
    }



    private static class SortByDate implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            
            Month a1 = Month.fromString(o1);
            Month a2 = Month.fromString(o2);
            int result = 0;

            if(a1.getDays() > a2.getDays()) {
                result = 1;
            }else if(a1.getDays() < a2.getDays()) {
                result = -1;
            }else if(a1.getDays() == a2.getDays()) {
                result = 0;
            }            
            return result;  

            
        }


    }
}
