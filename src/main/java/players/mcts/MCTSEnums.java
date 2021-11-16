package players.mcts;

public class MCTSEnums {

    public enum Strategies {
        RANDOM, MAST, CLASS
    }

    public enum Information {
        Closed_Loop, Open_Loop, Information_Set
    }

    public enum MASTType {
        Rollout, Tree, Both
    }

    public enum SelectionPolicy {
        ROBUST, SIMPLE
    }

    public enum TreePolicy {
        UCB, EXP3, AlphaGo, RegretMatching, UCB_Tuned
    }

    public enum OpponentTreePolicy {
        SelfOnly, Paranoid, MaxN, MultiTree
    }

}
