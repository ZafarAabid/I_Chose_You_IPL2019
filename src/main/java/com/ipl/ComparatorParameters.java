package com.ipl;

import java.util.Comparator;

public class ComparatorParameters {

    public enum Parameter{
        AVERAGE{
            public Comparator getComparator(){
                return  Comparator.<IplBatsmanData,String>comparing(census -> census.playersAvg);
            }
        };
        public Comparator getComparator(){ return null; }
    }

    public static Comparator getComparator(Parameter parameter) {
            return parameter.getComparator();
        }
}
