package poll;

//奖品类 -
//JavaBean对象
//私有化属性 公有化方法
public class Reward implements Comparable<Reward>{

	public Reward(){}
	
	public Reward(Integer id, String name){
		//super();
		this.id = id;
		this.name = name;
		this.rate = 0.0;
	}
	public Reward(Integer id, String name, Double rate) {
		//super();
		this.id = id;
		this.name = name;
		this.rate = rate;
	}


	private Integer id;
	private String name;
	private transient Double rate;//抽奖概率是每次随机生成的 不同商品组合会不一样
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
//	@Override
//	public boolean equals(Object obj) {
//		if(obj == null){
//			return false;
//		}
//		Reward r = (Reward)obj;
//		//System.out.println(id.intValue()+","+r.getId().intValue());
//		return id == r.getId();
//	}
//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		return super.hashCode();
//	}
	
	@Override
	public int compareTo(Reward o) {
		if(o ==null){
			return 1; //自己定义 null为最小
		}
		//根据你的业务来编码#按id大小
		return id.compareTo(o.getId());
	}

	@Override
	public String toString() {
		return "Reward [id=" + id + ", name=" + name + ", rate=" + rate + "]";
	}
	
	
}
