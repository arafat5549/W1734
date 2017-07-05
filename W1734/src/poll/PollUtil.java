package poll;

/**
 * 抽奖系统
 * 
 * @author wyy
 *
 */
public class PollUtil {

	//单例模式
	private PollUtil(){};
	private static PollUtil instance;
	public static PollUtil getInstance(){
		if(instance == null){
			instance = new PollUtil();
		}
		return instance;
	}
	public static void main(String[] args) {
		Reward reward1 = new Reward(1,"鞋子1",0.05);
		Reward reward2 = new Reward(1,"鞋子1",0.05);
		System.out.println("PollUtil:"+reward1.equals(reward2));
		
		System.out.println("a".compareTo("b"));
	}
	
	//固定抽奖#奖品池固定
	public void stablePoll(){
		int total = 10;//总共10个奖品
		
		Reward reward1 = new Reward(1,"鞋子1",0.05);
		Reward reward2 = new Reward(2,"鞋子2",0.05);
		Reward reward3 = new Reward(3,"鞋子3",0.05);
		Reward reward4 = new Reward(4,"鞋子4",0.05);
		Reward reward5 = new Reward(5,"鞋子5",0.05);
		Reward reward6 = new Reward(6,"鞋子6",0.05);
		Reward reward7 = new Reward(7,"鞋子7",0.05);
		Reward reward8 = new Reward(8,"鞋子8",0.05);
		
		Reward reward9 = new Reward(9,"三等奖",0.1);
		Reward reward10 = new Reward(10,"谢谢参与",0.5);
		
	}
	//随机生成奖品 随机产生概率
	public void randomPoll(){
		//谢谢参与-1份       50%
		//鼓励奖-4份           10%    
		//三等奖-2份           4%
		//二等奖-1份           1%
		//一等奖-1份           0.9%
		//特等奖-1份           0.1%
		
		double rate = Math.random();
		if(rate < 0.5){
			//谢谢参与-1份       50%
		}
		else if(rate >= 0.5 && rate < 0.9){
			//再从四个里面随机抽一个
		}
		else if(rate >= 0.9 && rate < 0.98){
			
		}
		else if(rate >= 0.98 && rate < 0.99){
			
		}
		else if(rate >= 0.99 && rate < 0.999){
			
		}
		else{
			
		}
	}
}
