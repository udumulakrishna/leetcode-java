public class Solution {
    public ArrayList<Integer> solve(int a, int b, int c, int d) {
    
        ArrayList<Integer> ar = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        int x = a, y = b, z = c;
        int i = 0, j = 0, k = 0;

        while (ar.size() < d) {
            int min = Math.min(x, Math.min(y, z));

            if (!set.contains(min)) {
                ar.add(min);
                set.add(min);
            }

            if (min == x) {
                x = ar.get(i++) * a;
            } else if (min == y) {
                y = ar.get(j++) * b;
            } else {
                z = ar.get(k++) * c;
            }
        }

        return ar;
        
        
        
        
        
        // ArrayList list = new ArrayList();
        
        // list.add(1);
        // int P=0,Q=0,R=0;
        // int counter = 1, num=0;
        // while(counter == D){
        //     num = Math.min(A[P]*2, B[Q]*3, C[R]*5);
        //     list.add(num);
        //     if(A[P]*2 == num) P++;
            
        //     if(B[Q]*3 == num) Q++;
            
        //     if(C[R]*5 == num) R++;
        // }
        // list.remove(Integer.valueOf(1));
        
        // return list;
    }
}
