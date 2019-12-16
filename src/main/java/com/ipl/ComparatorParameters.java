package com.ipl;

import java.util.Comparator;

public class ComparatorParameters {

    public enum Parameter{
        AVERAGE{
            public Comparator getComparator(){
                return  Comparator.<IplBatsmanData,String>comparing(census -> census.playersAvg).reversed();
            }
        },STRIKE_RATE{
            public Comparator getComparator(){
                return  Comparator.<IplBatsmanData,Double>comparing(census -> census.playersStrikeRate).reversed();
            }
        },STRIKE_RATE_BASED_ON_6s4s{
            public Comparator getComparator(){
                return  Comparator.<IplBatsmanData,Double>comparing(census -> census.playersStrikeRate).reversed();
            }
        };
        public Comparator getComparator(){ return null; }
    }

    public static Comparator getComparator(Parameter parameter) {
            return parameter.getComparator();
        }
}
