package com.ipl;

import java.util.Comparator;

public class BowlingComparatorParameter {

    public enum Parameter{
        AVERAGE{
            public Comparator getComparator(){
                return  Comparator.<IplBatsmanData,Double>comparing(census -> census.playersAvg).reversed();
            }
        };
        public Comparator getComparator(){ return null; }
    }

    public static Comparator getComparator(ComparatorParameters.BattingParameter battingParameter) {
        return battingParameter.getComparator();
    }
}
