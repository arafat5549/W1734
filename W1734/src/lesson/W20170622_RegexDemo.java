package lesson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * 
 * 1.什么是正则表达式?有什么作用?
 * 2.基础语法
 * 3.正则表达式分组
 * 4.贪婪匹配greed
 * 5.?=断言 只匹配一个位置  (?=中国)人  只有中国人中的人字才会被匹配
 * 6.常用的正则表达式的编写?查看ValidatorUtils
 * 7.正则的最佳实践:
 *  - 复杂正则拆分：涉及到逻辑判断不要全部用正则来做
 *  - 判断某些字符都要有 一般使用用?=断言匹配  //regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,10}"
 * @author wyy
 *
 */
public class W20170622_RegexDemo {
	
	// ? 0-1
	// [0-9]  范围:
	// ^ 字符串开头  $字符串结束
	// + 一个到多个
	// * 0个到多个
	// [\u4e00-\u9fa5]
	// {m,n}  {6,10}
	
	public static void main(String[] args) {
		//0.是否是中文
		//regex_isChinese();
		//1.是否为小写字母
		//regex_isLetterLower();
		//2.是否合法用户名称。用户注册。只能包含英文字母、数字、"-"和"_"
		//regex_isUserName();
		//分组
		//regex_isGroup();
		//regex_greedMatch();
		//4.贪婪匹配问题
		//regex_greedMatch();
		//5.复杂正则
		//regex_complicate();
		
		//一些注意事项:
		//a.字符串拆分split怎么进行优化
//		Pattern pattern = Pattern.compile(",");
//		String s[] = {"a,b,c","","",""};
//		for (String string : s) {
//			string.split(",");//每一次执行都要编译一次正则
//			pattern.split(string);//只会编译一次正则
//		}
		
		//regex_isEmail();
		regex_isGreed();
	}
	/**
	 * 分组
	 * 
	 */
	private static void regex_isGroup()
	{
		String input = "img:(abc.png)";
		Pattern pattern = null;// 正则表达式
		Matcher m = null;// 操作符表达式
		//pattern = Pattern.compile("img:\\(.+\\)");
		pattern = Pattern.compile("img:\\((.+)\\)");
		m = pattern.matcher(input);
		
		if(m.find())
		{
			System.out.println(m.group()+","+m.group(1));
		}
		
		System.out.println("分组:"+m.matches());
	}
	/**
	 * 验证手机号 13 | 15 | 18 开头的11位数字
	 */
	private static void regex_isPhone(){
		String input = "中文";
		Pattern pattern = null;// 正则表达式
		Matcher m = null;// 操作符表达式
		pattern = Pattern.compile("^1[358][0-9]{9}$");
		//"[131518]"代表131518六个数中取一个
		//另一种写法   (13|15|18)[0-9]{9}
		m = pattern.matcher(input);
		System.out.println("是否是中文:"+m.matches());
	}
	/**
	 * 是否合法用户名称。
	 * 1.用户注册。只能包含英文字母、数字、"-"和"_"
	 * 2.长度在6位到10位之间
	 * 
	 */
	private static void regex_isUserName() {
		String input = "abc-asa";
		Pattern pattern = null;// 正则表达式
		Matcher m = null;// 操作符表达式
		//1.用户注册。只能包含英文字母、数字、"-"和"_"
		//pattern = Pattern.compile("^[a-zA-Z0-9-_]+$");
		//2.长度在6位到10位之间
		pattern = Pattern.compile("^[\\u4E00-\\u9FA5a-zA-Z0-9-_]{5,8}$");
		
		m = pattern.matcher(input);
		System.out.println("是否合法用户名称:"+m.matches());
	}
	/**
	 * 是否是中文
	 */
	private static void regex_isChinese(){
		String input = "中文";
		Pattern pattern = null;// 正则表达式
		Matcher m = null;// 操作符表达式
		pattern = Pattern.compile("^[\\u4E00-\\u9FA5]+$");
		m = pattern.matcher(input);
		System.out.println("是否是中文:"+m.matches());
	}
	/**
	 * 是否为小写字母
	 */
	private static void regex_isLetterLower(){
		String input = "aaaasdas";
		Pattern pattern = null;// 正则表达式
		Matcher m = null;// 操作符表达式
		
		pattern = Pattern.compile("^[a-z]+$");
		m = pattern.matcher(input);
		System.out.println("是否为小写字母:"+m.matches());
	}
	/**
	 * 贪婪匹配问题
	 */
	private static void regex_greedMatch(){
		Pattern pattern = null;// 正则表达式
		Matcher m = null;// 操作符表达式
		//String input  = "background-image: url(../img/custom-header-bg.jpg);";
		String input = "background-image: url(../img/custom-header-bg.jpg) url(abc.jpg);";
		//pattern = Pattern.compile("url\\((.+)\\)");
		pattern = Pattern.compile("url\\((.+?)\\)");
		m = pattern.matcher(input);
		while(m.find())
		{
			System.out.println(m.group(1));
		}
		
		System.out.println("分组:"+m.matches());
	}
	/**
	 * 复杂正则
	 * 注册密码要求: 
	 * //要求0: 字符必须为 数字，小写字母，大写字母
	 * //要求1：小写字母 大写字母都要有 
	 * //要求2：六位到十位之间  {6,10}
	 */
	private static void regex_complicate(){
		//String regex = "[0-9][a-z][A-Z]";
		
		String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,10}";//"[0-9][a-z][A-Z]" ;
		String input = "asggdgA1231";
		System.out.println(input.matches(regex));
		
		regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,10}";
		System.out.println(input.matches(regex));		
	}
	
	
	/**
	 * 是否是邮箱地址   xxxx@xxx.xxx 
		 1.必须以com和org结尾
		 2.@之前的部分长度不能超过10
		 3.必须为数字字母和下划线不能以下划线开头
	 */
	private static void regex_isEmail(){
		String input = "zzz122@xxx.org";
		String regex = "";
		//判断邮件地址
		//regex = ".+@.+\\..+";
	
		//必须以com和org结尾
		regex = "^.+@.+\\.(com|org)$";
		//@之前的部分长度不能超过10
		regex = "^.{1,10}@.+\\.(com|org)$";
		//必须为数字字母和下划线
		regex = "^[A-Za-z0-9_]{1,10}@.+\\.(com|org)$";
		//不能以下划线开头
		regex = "^[A-Za-z0-9][A-Za-z0-9_]{0,9}@.+\\.(com|org)$";
		System.out.println(input.matches(regex));
	}
	/**
	 * 贪婪匹配问题
	 * 一般做字符串提取的时候 就不加 ^ $
	 */
	private static void regex_isGreed(){
		String input = "link:url(a.png) url(b.png) img(c.png)";
		String regex = "url\\(.+\\)";
		regex = "url\\((.+)\\)"; //添加分组
		regex = "url\\((.+?)\\)";//只会匹配一次
		
		Pattern pattern = Pattern.compile(regex);// 正则表达式
		Matcher m = pattern.matcher(input);// 操作符表达式
		while(m.find()){
			System.out.println(m.group(1));
		}
		//url(.+)
	}
	
	
}
