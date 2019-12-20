package com.ipl;

import java.util.Comparator;

public class ComparatorParameters {

    public enum BattingParameter {
        AVERAGE{
            @Override
            public Comparator getComparator() {
                return  Comparator.<IplBatsmanData,Double>comparing(census -> census.playersAvg).reversed();
            }
        },STRIKE_RATE{
            @Override
            public Comparator getComparator() {
                return  Comparator.<IplBatsmanData,Double>comparing(census -> census.playersStrikeRate).reversed();
            }
        },STRIKE_RATE_BASED_ON_6s4s{
            @Override
            public Comparator getComparator() {
                return  Comparator.<IplBatsmanData,Double>comparing(census -> census.playersStrikeRate).reversed();
            }

        },MAX_RUNS{
            @Override
            public Comparator getComparator() {
                return  Comparator.<IplBatsmanData,Double>comparing(census -> census.playersRun).reversed();
            }
        };
        public abstract Comparator getComparator();
    }

    public static Comparator getComparator(BattingParameter battingParameter) {
            return battingParameter.getComparator();
        }
}
