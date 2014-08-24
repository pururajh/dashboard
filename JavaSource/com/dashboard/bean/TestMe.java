package com.dashboard.bean;

import java.sql.Timestamp;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

public class TestMe  {

	public static void main(String[] args) {
		FacebookClient client = new DefaultFacebookClient("CAACEdEose0cBADZCkNP22ZAMkSZCcsyo0gfxhtZAZCZAuuYZCmzwBFq2k6zR8n33FxbtRtaHKAWhjRQIYR8YwZBES3xX2gJZARh5PLAKiOVjZC6ldYahNaHBWJ7dl29hbsDWPu17mHfTjCzgiSxo6ZAo2U7AVEP61P1IAZBZBOmQtwAZBGGnMDspXwzJnpV8XR4dUXQp6Mzu48dkg1OO4dZC7KIPlXiyUrB2IukHlQZD");
		FacebookType publishMessageResponse =  client.publish("me/feed", FacebookType.class, Parameter.with("message", "First Message from java"));
		System.out.println("Published message ID: " + publishMessageResponse.getId());
		
		/**Create event is not possible for regular user using Graph Api explorer*/
		/*Timestamp starttime = new Timestamp(System.currentTimeMillis());
		System.out.println( "start time-->"+starttime);
		Timestamp endTime = new Timestamp(System.currentTimeMillis()+(2 * 3600)) ;
		System.out.println( "end time-->"+endTime);
		FacebookType publishEventResponse = client.publish("me/events", FacebookType.class,  Parameter.with("name", "Party"), Parameter.with("start_time", starttime),    Parameter.with("end_time", endTime));
		System.out.println("Published event ID: " + publishEventResponse.getId());*/
	   
	}

}
