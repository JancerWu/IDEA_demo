package hw;

import java.util.*;


class Solution {


    private static String calculate(String expression) {
        char[] exp = expression.toCharArray();
        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Character> symbols = new ArrayDeque<>();
        int pre = 0;
        for (char c : exp) {
            if (Character.isDigit(c)) {
                pre = pre * 10 + c - '0';
                continue;
            }
            numbers.push(pre);
            pre = 0;
            while (!symbols.isEmpty() && (symbols.peek() == '*' || symbols.peek() == '/'
                    || ((c == '+' || c == '-') && (symbols.peek() == '+' || symbols.peek() == '-')))) {
                String error = getString(numbers, symbols);
                if (error != null) return error;
            }
            symbols.push(c);
        }
        numbers.push(pre);
        while (numbers.size() > 1) {
            String error = getString(numbers, symbols);
            if (error != null) return error;
        }
        return String.valueOf(numbers.pop());
    }

    private static String getString(Deque<Integer> numbers, Deque<Character> symbols) {
        int b = numbers.pop(), a = numbers.pop();
        char symbol = symbols.pop();
        if (symbol == '/' && b == 0) return "error";
        else if (symbol == '/') numbers.push(a / b);
        else if (symbol == '*') numbers.push(a * b);
        else if (symbol == '+') numbers.push(a + b);
        else numbers.push(a - b);
        return null;
    }

    private static char[] getNTimesCharacter(int nValue, String[] strings) {
        int[][] cnt = new int[strings.length][128];
        for (int i = 0; i < strings.length; i++) {
            for (char c : strings[i].toCharArray()) {
                cnt[i][c]++;
            }
        }
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            if (check(cnt, i, nValue)) {
                list.add((char) i);
            }
        }

        Collections.sort(list);
        char[] ans = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private static boolean check(int[][] cnt, int i, int nValue) {
        for (int i1 = 0; i1 < cnt.length; i1++) {
            if (cnt[i1][i] < nValue) return false;
        }
        return true;
    }


    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = nums[nums.length - 1] - nums[0];
        while (left < right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for (int i = 1; i < nums.length - 1; i++) {
                if (nums[i] - nums[i - 1] <= mid) {
                    i++;
                    cnt++;
                }
            }
            if (cnt >= p) right = mid;
            else left = mid + 1;
        }
        return left;


    }


    public long[] distance(int[] nums) {
        Map<Integer, long[]> total = new HashMap<>();
        Map<Integer, long[]> cur = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            long[] ares = total.getOrDefault(num, new long[]{0, 0});
            ares[0] += 1;
            ares[1] += i;
            total.put(num, ares);
        }

        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            long[] sum = total.get(num);
            if (sum[0] == 1) {
                ans[i] = 0;
                continue;
            }
            long[] now = cur.getOrDefault(num, new long[]{0, 0});
            ans[i] = (now[0] * i - now[1]) + (sum[1] - now[1]) - (sum[0] - now[0]) * i;
            now[0] += 1;
            now[1] += i;
            cur.put(num, now);
        }

        return ans;
    }

    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (check(nums[i][i])) ans = Math.max(ans, nums[i][i]);
        }
        for (int i = 0; i < n; i++) {
            if (check(nums[i][n - 1 - i])) ans = Math.max(ans, nums[i][n - 1 - i]);
        }
        return ans;

    }

    public boolean check(int num) {
        if (num == 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }


    private static int getCountOfSubString(String input) {
        // TODO 在此补充你的代码
        char[] s = input.toCharArray();
        int ans = 0, n = s.length;
        Set<Character> set = new HashSet<>();
        set.add('/');
        for (int l = 0, r = 0; r < n; r++) {
            while (set.contains(s[r])) {
                set.remove(s[l++]);
            }
            ans += set.size();
            set.add(s[r]);
        }
        return ans;
    }


    static class RegCallOperate {
        int type = -1;
        String number = null;

        RegCallOperate(int type, String number) {
            this.type = type;
            this.number = number;
        }
    }


    private static String calling(String status, List<RegCallOperate> regCallForwardNums) {
        String[] parts = status.split(" ");
        String userStatus = parts[1];
        Map<String, String> converts = new HashMap<>();
        for (RegCallOperate reg : regCallForwardNums) {
            int type = reg.type;
            if (type == 0) return reg.number;
            else if (type == 1) converts.put("busy", reg.number);
            else if (type == 2) converts.put("no-response", reg.number);
            else if (type == 3) converts.put("unreachable", reg.number);
            else converts.put("default", reg.number);
        }
        if (converts.containsKey(userStatus)) return converts.get(userStatus);
        else if (converts.containsKey("default")) return converts.get("default");
        else if (userStatus.equals("idle")) return "success";
        else return "failure";
    }


    private static int calcExpression(String expression) {
        Deque<Integer> stack = new ArrayDeque<>();
        String[] parts = expression.split(",");
        for (String part : parts) {
            if (part.length() == 1 && !Character.isDigit(part.charAt(0))) {
                // 运算符
                char symbol = part.charAt(0);
                int b = stack.pop(), a = stack.pop(), tmp = 0;
                if (symbol == '+') tmp = a + b;
                else if (symbol == '-') tmp = a - b;
                else if (symbol == '*') tmp = a * b;
                else tmp = a / b;
                stack.push(tmp);
            } else stack.push(Integer.parseInt(part));
        }
        return stack.pop();
    }


    // 待实现函数，在此函数中填入答题代码
    private static int binaryToDecimal(String binaryString) {
        char[] s = binaryString.toCharArray();
        int ans = 1;
        // 负数
        if (s.length == 32 && binaryString.charAt(0) == 1) {
            // 从右往左找到第一个1，然后左边的位置反转
            ans = -1;
            int firstOnePos = 31;
            while (s[firstOnePos] != '1') firstOnePos--;
            for (int i = firstOnePos - 1; i >= 0; i--) {
                if (s[i] == '0') s[i] = '1';
                else s[i] = '0';
            }
        }
        return ans * helper(s);
    }

    private static int helper(char[] s) {
        int ans = 0;
        for (int i = 0; i < s.length; i++) {
            ans = ans * 2 + s[i] - '0';
        }
        return ans;
    }


    private static int getMaxSendNumber(int cap, int[] billLen, int[] billPrior) {
        int n = billLen.length;
        Map<Integer, List<Integer>> prior2billLen = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int prior = billPrior[i];
            List<Integer> list = prior2billLen.getOrDefault(prior, new ArrayList<>());
            list.add(billLen[i]);
            prior2billLen.put(prior, list);
        }
        int ans = 0;
        for (int i = 0; i <= 30; i++) {
            if (!prior2billLen.containsKey(i)) continue;
            List<Integer> list = prior2billLen.get(i);
            Collections.sort(list);
            for (int len : list) {
                if (cap - len >= 0) {
                    cap -= len;
                    ans++;
                } else {
                    return ans;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {

        System.out.println(calculate("-200*0/1+9"));
        List<Integer> list = new ArrayList<>();
        boolean contains = list.contains(1);

    }


    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);


        return new int[0];

    }


}
