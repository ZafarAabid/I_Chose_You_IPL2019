package com.ipl;

import java.util.Comparator;

public class ComparatorParameters {

    public enum Parameter{
        AVERAGE{
            public Comparator getComparator(){
                return  Comparator.<IplBatsmanData,String>comparing(census -> census.playersAvg);
            }
        },STRIKE_RATE{
            public Comparator getComparator(){
                return  Comparator.<IplBatsmanData,Double>comparing(census -> census.playersStrikeRate);
            }
        },STRIKE_RATE_BASED_ON_6s4s{
            public Comparator getComparator(){
                return  Comparator.<IplBatsmanData,Double>comparing(census -> census.playersStrikeRate);
            }
        };
        public Comparator getComparator(){ return null; }
    }

    public static Comparator getComparator(Parameter parameter) {
            return parameter.getComparator();
        }
}
