package CSproblem.work;

class node{
    //public int flag = 0;    //标记是否有值
    public double w = 0;
    public char ch = '@';
    node Lson = null;
    node Rson = null;
    node(){w = 0; ch = '@'; }
    node(double b, char c){w = b; ch = c; }
}

public class BinaryTree{
    node rt; int cnt = 0;
    String source = "";    //源字符串
    double ansNum = 0;    //求值结果
    String prefixStr = "";    //前缀表达式
    String infixStr = "";    //中缀表达式
    String suffixStr = "";    //后缀表达式
    String expAnswer[] = new String[100];    //表达式结果数组，用来暂存
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
        int len = source.length();
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
    	node st[] = new node[1000]; int top = 0;
    	for(int i = 0; i < 1000; i++) st[i] = new node();
    	int oldSpace = 0;
    	int nowIdx = 0;
    	while(nowIdx < suffixStr.length()){
    		for(; nowIdx < suffixStr.length() && suffixStr.charAt(nowIdx) != ' '; nowIdx++);
        	String str = suffixStr.substring(oldSpace, nowIdx);
    		if(isOpt(str.charAt(0))){
    			node tmp = new node(0, str.charAt(0));
    			tmp.Rson = st[--top];
    			tmp.Lson = st[--top];
    			st[top++] = tmp;
    		}
    		else{
    			double temp = Double.valueOf(str);
    			st[top++] = new node(temp, (char)'@');
    		}
    		oldSpace = nowIdx + 1;
        	nowIdx++;
    	}
    	rt = st[0];
    	suffixStr = "";
    }
    //根据后缀表达式求值
    public void calcAnswer(){
        double st[] = new double[1000]; int top = 0;
        int oldSpace = 0;
        int nowIdx = 0;
        while(nowIdx < suffixStr.length()){
        	for(; nowIdx < suffixStr.length() && suffixStr.charAt(nowIdx) != ' '; nowIdx++);
        	String str = suffixStr.substring(oldSpace, nowIdx);
        	if(isOpt(str.charAt(0))){
        		double a = st[--top];
        		double b = st[--top];
        		st[top++] = calc(a, str.charAt(0), b);
        	}
        	else{
        		st[top++] = Double.valueOf(str);
        	}
        	oldSpace = nowIdx + 1;
        	nowIdx++;
        }
        ansNum = st[0];
    }
    //求前缀表达式
    public void prefix(node rt, String s[]){
        if(rt != null){
            if(rt.ch == '@'){
            	s[cnt++] = rt.w+"";
            }
            else{
                s[cnt++] = rt.ch+"";
            }
            prefix(rt.Lson, s);
            prefix(rt.Rson, s);
        }
    }
    //求中缀表达式
    public void infix(node rt, String s[]){
    	if(rt != null){
            infix(rt.Lson, s);
            if(rt.ch == '@'){
            	s[cnt++] = rt.w+"";
            }
            else{
                s[cnt++] = rt.ch+"";
            }
            infix(rt.Rson, s);
        }
    }
    //求后缀表达式
    public void suffix(node rt, String s[]){
    	if(rt != null){
            suffix(rt.Lson, s);
            suffix(rt.Rson, s);
            if(rt.ch == '@'){
            	s[cnt++] = rt.w+"";
            }
            else{
                s[cnt++] = rt.ch+"";
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
