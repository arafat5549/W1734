package jdbc;

import java.util.HashMap;

import com.ssf.model.SysUser;

public class CacheUtils {

	private HashMap<String, Object> cache = new HashMap<>();
	
	//缓存 键的选择
	//缓存命中
	//缓存更新策略
	public Object getCache(String key){
		Object obj = cache.get(key);
		if(obj==null){
			SysUser user=DBUtils.queryBean(key, SysUser.class);
			cache.put(key, user);
		}
		return obj;
	}
}
