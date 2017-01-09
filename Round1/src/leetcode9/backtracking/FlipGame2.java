package leetcode9.backtracking;

import java.util.HashMap;
import java.util.Map;

/**
 * 294. You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
 */
public class FlipGame2 {
    public boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        return canWin(s, map);
    }
    
    boolean canWin(String s, Map<String, Boolean> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) { // Can also use (s.charAt(i) == '+' && s.charAt(i + 1) == '+')
                String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!canWin(opponent, map)) { // Opponent can't win
                    map.put(s, true); // With this string, I can win
                    return true;
                }
            }
        }
        
        map.put(s, false);
        return false;
    }
}
