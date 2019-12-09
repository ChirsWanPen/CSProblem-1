package CSproblem.work;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Win extends JFrame implements Action{
	private static final long serialVersionUID = 1L;
	//相关变量
	JTextField in, out1, out2;
	JLabel exp, expRes, calRes;
	JButton yes, clr, prefix, infix, suffix;
	String read = "", ans1 = ""; double ans2 = 0;
	//构造函数以及窗口初始化
	public Win(){
		//初始化
		setTitle("表达式的求值");
		in = new JTextField(15);
		out1 = new JTextField(20);
		out2 = new JTextField(20);
		exp = new JLabel("表达式：");
		expRes = new JLabel("表达式结果：");
		calRes = new JLabel("计算结果：");
		yes = new JButton("确定");
		clr = new JButton("清空");
		prefix = new JButton("前缀式");
		infix = new JButton("中缀式");
		suffix = new JButton("后缀式");
		out1.setEditable(false);
		out2.setEditable(false);
		//设置点击监听
		yes.addActionListener(this);
		clr.addActionListener(this);
		prefix.addActionListener(this);
		infix.addActionListener(this);
		suffix.addActionListener(this);
		//设置位置大小
		setBounds(600, 280, 700, 500);
		exp.setBounds(100, 13, 100, 100);
		in.setBounds(150, 50, 300, 25);
		expRes.setBounds(350, 100, 100, 100);
		out1.setBounds(350, 170, 300, 70);
		calRes.setBounds(350, 200, 100, 100);
		out2.setBounds(350, 270, 300, 70);
		yes.setBounds(480, 50, 70, 25);
		clr.setBounds(580, 50, 70, 25);
		prefix.setBounds(100, 130, 200, 35);
		infix.setBounds(100, 230, 200, 35);
		suffix.setBounds(100, 330, 200, 35);
		//加入窗体
		add(exp); add(in); add(yes); add(clr);
		add(prefix); add(expRes); add(out1);
		add(infix); add(calRes); add(out2);
		add(suffix);
		//设置窗体
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//计算表达式与获得结果
	BinaryTree t = new BinaryTree();
	public void initTree(){
		t.source = read;
		t.insuffixConvertToSuffix();
		t.buildTree();
		t.prefix(t.rt, t.prefixStr);
		t.infix(t.rt, t.infixStr);
		t.suffix(t.rt, t.suffixStr);
	}
	public void getAnswer(String way){
		if(way.equals("prefix")){
			ans1 = t.prefixStr;
			ans2 = t.ansNum;
		}
		else if(way.equals("infix")){
			ans1 = t.infixStr;
			ans2 = t.ansNum;
		}
		else if(way.equals("suffix")){
			ans1 = t.suffixStr;
			ans2 = t.ansNum;
		}
	}
	//添加按钮的监听事件
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="确定"){
			read = in.getText();
			if(read.equals("")){
				JOptionPane.showMessageDialog(null, "请输入表达式！", "提示", JOptionPane.WARNING_MESSAGE);
			}
			else{
				initTree();
			}
		}
		if(e.getActionCommand()=="清空"){
			in.setText("");
			out1.setText("");
			out2.setText("");
		}
		if(e.getActionCommand()=="前缀式"){
			read = in.getText();
			if(read.equals("")){
				JOptionPane.showMessageDialog(null, "请输入表达式！", "提示信息", JOptionPane.WARNING_MESSAGE);
			}
			else if(ans1.equals("null")){
				JOptionPane.showMessageDialog(null, "输入的表达式有误！！", "提示信息", JOptionPane.WARNING_MESSAGE);
			}
			else{
				getAnswer("prefix");
				out1.setText(ans1);
				out2.setText(ans2+"");
			}
		}
		if(e.getActionCommand()=="中缀式"){
			read = in.getText();
			if(read.equals("")){
				JOptionPane.showMessageDialog(null, "请输入表达式！", "提示信息", JOptionPane.WARNING_MESSAGE);
			}
			else if(ans1.equals("null")){
				JOptionPane.showMessageDialog(null, "输入的表达式有误！！", "提示信息", JOptionPane.WARNING_MESSAGE);
			}
			else{
				getAnswer("infix");
				out1.setText(ans1);
				out2.setText(ans2+"");
			}
		}
		if(e.getActionCommand()=="后缀式"){
			read = in.getText();
			if(read.equals("")){
				JOptionPane.showMessageDialog(null, "请输入表达式！", "提示信息", JOptionPane.WARNING_MESSAGE);
			}
			else if(ans1.equals("null")){
				JOptionPane.showMessageDialog(null, "输入的表达式有误！！", "提示信息", JOptionPane.WARNING_MESSAGE);
			}
			else{
				getAnswer("suffix");
				out1.setText(ans1);
				out2.setText(ans2+"");
			}
		}
	}
	@Override
	public Object getValue(String arg0) { return null; }
	@Override
	public void putValue(String arg0, Object arg1) { }
}