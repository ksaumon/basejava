public class gf {

    public static void main(String[] args) {
        int [] nums = new int[] {2, 7, 11, 15};
        int target = 9;
        int result1;
        int result2;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] + nums[i + 1] == target) {
                result1 = i;
                result2 = i + 1;
                System.out.println("Выход: [" + result1 + ", " + result2 + "]");
                break;
            }
        }
    }

}
