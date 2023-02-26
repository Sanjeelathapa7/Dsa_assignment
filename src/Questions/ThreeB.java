package Questions;/*
b)
you are provided certain string and pattern, return true if pattern entirely matches
the string otherwise return false.
Note: if pattern contains char @ it matches entire sequence of characters and # matches any single character within string.
Input: String a=“tt”, pattern =”@”
Output: true
Input: String a=“ta”, pattern =”t”
Output: false
Input: String a=“ta”, pattern =”t#”
Output: true
 */


class ThreeB {
    public boolean solve(String str, String pattern, int a, int b){
        if(a<0&&b<0){
            return true;
        }if(a>=0&&b<0){
            return  false;
        }
        if(a<0&&b>=0){
            for(int i=0;i<=b; i++){
                if(pattern.charAt(i)!='@'){
                    return false;
                }
            }
        }

        if(str.charAt(a)==pattern.charAt(b)||pattern.charAt(b)=='#'){
            return solve(str,pattern,a-1,b-1);
        }
        else if(pattern.charAt(b)=='@'){
            return (solve(str,pattern,a-1,b)||solve(str,pattern,a,b-1));
        }

        else{
            return false;
        }
    }

    public static void main(String[] args) {

        ThreeB threeB = new ThreeB();
        String str ="sanji";
        String pattern="s@nji";
        System.out.println(threeB.solve(str,pattern,4,4));
    }
}