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
        },
        BATTING_WITH_BOWLING_AVERAGE{
            public Comparator getComparator() {
                return Comparator.<IplPlayersDAO, Double>comparing(census -> census.rating).reversed();
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
        },
        ECONOMY {
            public Comparator getComparator() {
                return Comparator.<IplPlayersDAO, Double>comparing(census -> census.playersEcon).reversed();
            }
        },
        STRIKE_RATE_WITHW4W5 {
            public Comparator getComparator() {
                return Comparator.<IplPlayersDAO, Double>comparing(census -> census.playersStrikeRate).thenComparing(iplPlayersDAO -> iplPlayersDAO.players4w).thenComparing(iplPlayersDAO -> iplPlayersDAO.players5w).reversed();
            }
        },
        BOWLING_AVG{
            public Comparator getComparator() {
                return Comparator.<IplPlayersDAO, Double>comparing(census -> census.playersBwolingAvg).reversed();
            }
        },
        ALL_ROUNDER{
            public Comparator getComparator() {
                return Comparator.<IplPlayersDAO, Double>comparing(census -> census.rating).reversed();
            }
        }

        ;
        public abstract Comparator getComparator();
    }

    public static Comparator getComparator(SortingParamters parameter) {
        return parameter.getComparator();
    }
}
