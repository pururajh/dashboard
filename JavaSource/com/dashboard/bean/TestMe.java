package com.dashboard.bean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.dashboard.constant.DashboardConstants;

public class TestMe {

	public static void main(String[] args) {
		Map<String, Integer> pieMap = new HashMap<String, Integer>();
		pieMap.put(DashboardConstants.GT15, 2);
		pieMap.put(DashboardConstants.IN_BETN_10_15, 3);
		pieMap.put(DashboardConstants.IN_BETN_5_10, 4);
		pieMap.put(DashboardConstants.LT5, 0);
		System.out.println("tcs Experience"+pieMap.get(DashboardConstants.IN_BETN_10_15));
		

	}

}
