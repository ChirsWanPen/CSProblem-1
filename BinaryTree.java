package CSproblem.work;

class node{
    public int flag = 0;    //标记是否有值
    public double w = 0;
    public char ch = '@';
    node(){}
    node Lson;
    node Rson;
}

public class BinaryTree{
    node rt;
    String source = "";    //源字符串
    double ansNum = 0;    //求值结果
    String prefixStr = "";    //前缀表达式
    String infixStr = "";    //中缀表达式
    String suffixStr = "";    //后缀表达式
    String opt = "+-*/()";    //操作符数组
    String cmpr[]={    //操作符比较数组
        ">><<<>",
        ">><<<>",
        ">>>><>",
        ">>>><>",
        "<<<<<+",
        ">>>>$>"
    };
    //中缀式转后缀式
    public void insuffixConvertToSuffix(){
        String ans = "";
        int len = source.length();System.out.println(source);
        char st[] = new char[1000]; int top = 0;
        for(int i = 0; i < len; i++){
        	if(source.charAt(i) == '('){
        		st[top++] = source.charAt(i);
        		continue;
        	}
        	if(source.charAt(i) == ')'){
        		while(st[top-1] != '('){
        			ans += st[top-1];
        			top--;
        			ans += " ";
        		}
        		top--;
        		continue;
        	}
        	if(isOpt(source.charAt(i))){
        		while(top > 0 && cmpr[getIdx(st[top-1])].charAt(getIdx(source.charAt(i))) != '<'){
        			ans += st[top-1];
                    top--;
                    ans += " ";
        		}
        		st[top++] = source.charAt(i);
        	}
        	else{
        		while(i<len && !isOpt(source.charAt(i))){
        			ans += source.charAt(i);
        			i++;
                }
                ans += " ";
        		i--;
        	}
        }
        while(top > 0){
        	ans += st[top-1];
        	top--;
        	ans += " ";
        }
        suffixStr = ans;
    }
    //根据后缀表达式建立二叉树
    public void buildTree(){

    }
    //求前缀表达式
    public void prefix(node rt, String s){
        if(rt.flag == 1){
            if(rt.ch == '@'){
                s = s+(rt.w+"");
            }
            else{
                s = s+rt.ch;
            }
            prefix(rt.Lson, s);
            prefix(rt.Rson, s);
        }
    }
    //求中缀表达式
    public void infix(node rt, String s){
        if(rt.flag == 1){
            infix(rt.Lson, s);
            if(rt.ch == '@'){
                s = s+(rt.w+"");
            }
            else{
                s = s+rt.ch;
            }
            infix(rt.Rson, s);
        }
    }
    //求后缀表达式
    public void suffix(node rt, String s){
        if(rt.flag == 1){
            suffix(rt.Lson, s);
            suffix(rt.Rson, s);
            if(rt.ch == '@'){
                s = s+(rt.w+"");
            }
            else{
                s = s+rt.ch;
            }
        }
    }
    //判断是不是运算符
    boolean isOpt(char ch){
        for(int i = 0; i < opt.length(); i++){
            if(ch == opt.charAt(i))
                return true;
        }
        return false;
    }
    //对两个操作符进行比较大小
    char cmp(char opt1, char opt2){
        return cmpr[getIdx(opt1)].charAt(getIdx(opt2));
    }
    //完成一个运算
    double calc(double num1, char opt, double num2){
        double ans = 0;
        if(opt == '+') ans = num1+num2;
        if(opt == '-') ans = num1-num2;
        if(opt == '*') ans = num1*num2;
        if(opt == '/') ans = num1/num2;
        return ans;
    }
    //得到运算符的下标
    int getIdx(char ch){
        for(int i = 0; i < opt.length(); i++){
            if(opt.charAt(i) == ch)
                return i;
        }
        return -1;                                                                                                                                                                                                                                                                                                                                                   
    }
}