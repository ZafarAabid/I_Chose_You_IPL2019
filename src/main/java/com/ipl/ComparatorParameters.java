package com.ipl;

import java.util.Comparator;

public class ComparatorParameters {

    public enum BattingParameter implements SortingParamters {
        AVERAGE {
            public Comparator getComparator() {
                return Comparator.<IplPlayersDAO, Double>comparing(census -> census.playersBattingAvg).reversed();
            }
        }, STRIKE_RATE {
            public Comparator getComparator() {
                Comparator<IplPlayersDAO> comp = Comparator.comparing(iplPlayersDAO -> iplPlayersDAO.playersStrikeRate, Comparator.reverseOrder());
            return comp;
            }
        }, STRIKE_RATE_BASED_ON_6s4s {
            public Comparator getComparator() {
                return Comparator.<IplPlayersDAO, Double>comparing(census -> census.playersStrikeRate).reversed();
            }

        }, MAX_RUNS {
            public Comparator getComparator() {
                return Comparator.<IplPlayersDAO, Double>comparing(census -> census.playersRun).reversed();
            }
        };
        public abstract Comparator getComparator();
    }

    public enum BowlingParameter implements SortingParamters {
        AVERAGE {
            public Comparator getComparator() {
                return Comparator.<IplPlayersDAO, Double>comparing(census -> census.playersBwolingAvg).reversed();
            }
        },
        STRIKE_RATE {
            public Comparator getComparator() {
                return Comparator.<IplPlayersDAO, Double>comparing(census -> census.playersBowlingStrikeRate).reversed();
            }
        };
        public abstract Comparator getComparator();
    }

    public static Comparator getComparator(SortingParamters parameter) {
        return parameter.getComparator();
    }
}
