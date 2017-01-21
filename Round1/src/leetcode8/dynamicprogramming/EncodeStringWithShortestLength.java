package leetcode8.dynamicprogramming;
/**
 * 471. Given a non-empty string, encode the string such that its encoded length is the shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Note:
k will be a positive integer and encoded string will not be empty or have extra space.
You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
Example 1:

Input: "aaa"
Output: "aaa"
Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
Example 2:

Input: "aaaaa"
Output: "5[a]"
Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
Example 3:

Input: "aaaaaaaaaa"
Output: "10[a]"
Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
Example 4:

Input: "aabcaabcd"
Output: "2[aabc]d"
Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
Example 5:

Input: "abbbabbbcabbbabbbc"
Output: "2[2[abbb]c]"
Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
 */
public class EncodeStringWithShortestLength {
    // http://www.dongcoder.com/detail-313364.html
    public String encode(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        String[][] hash = new String[s.length()][s.length()];
        
        for (int len = 0; len < s.length(); len++) {
            for (int i = 0; i + len < s.length(); i++) {
                int j = i + len;
                
                String substr = s.substring(i, j + 1);
                hash[i][j] = substr;
                if (substr.length() <= 4) {
                    continue;
                }
                
                for (int k = i; k < j; k++) {
                    if (hash[i][k].length() + hash[k + 1][j].length() < hash[i][j].length()) {
                        hash[i][j] = hash[i][k] + hash[k + 1][j];
                    }
                }
                
                // Check if substr has repeat pattern
                for (int k = i; k < j; k++) {
                    String repeat = s.substring(i, k + 1);
                    if (substr.length() % repeat.length() == 0 && substr.replaceAll(repeat, "").length() == 0) {
                        String ss = substr.length() / repeat.length() + "[" + hash[i][k] + "]"; // Must from hash[i][k]
                        if (ss.length() < hash[i][j].length()) {
                            hash[i][j] = ss;
                        }
                    }
                }
            }
        }
        
        return hash[0][s.length() - 1];
    }
}
