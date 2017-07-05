package lesson;

/**
 * 数学类
 * 
 * Math
 * 
 * @author wyy
 *
 */
public class W20170705MathDemo {

	public static void main(String[] args) {
		//Math.PI
		//取两者中的较大者
		System.out.println(Math.max(10, 9));
		//取两者中的较小者
		System.out.println(Math.min(1, 3));
		//绝对值
		System.out.println(Math.abs(-1));
		//计算n次方
		System.out.println(Math.pow(2, 3));
		//开平方
		System.out.println(Math.sqrt(8));
		//向上取整
		System.out.println(Math.ceil(8.1));
		//向下取整
		System.out.println(Math.floor(8.1));
		//四舍五入
		System.out.println(Math.round(8.7));
		//随机数 [0-1)
		System.out.println(Math.random());
		//三角函数@平面距离
		//角度degress和弧度randian小数值
		//Math.toDegrees(angrad)  //angrad * 180.0 / PI;
		System.out.println(Math.sin(Math.toRadians(90)));
		//自然对数 log
		
	}
	
}
