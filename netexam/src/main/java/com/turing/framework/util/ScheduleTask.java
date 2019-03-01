package com.turing.framework.util;

import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
* @author 赵刚
* @date 2018年6月13日 上午10:38:03
* @desc 定时计划任务
*/
@Component
@EnableScheduling
public class ScheduleTask {

	
	/**
	 * 每天凌晨1点清理一次无用的数据
	 */
	@Scheduled(cron="0 0 1 * * *")
	public void clearPjno() {
		//清理昨天的票据编号集合中的信息
		Singleton singleton = Singleton.getInstance();
		LinkedList<String> list = singleton.getPjnos();
		String now = DateFormatUtils.format(new Date(), "yyyyMMdd");//20180613
		for (int i = list.size()-1; i >=0; i--) {
			if(list.get(i).indexOf(now) == -1 ) {
				list.remove( list.get(i) );
			}
		}
	}
}
