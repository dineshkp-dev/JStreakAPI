package com.streakapi.crm.queryStreak;

import java.io.IOException;

import com.streakapi.crm.exceptions.NoValidObjectsReturned;
import com.streakapi.crm.queryStreak.resources.streakObjects.User;

public class TestQueryStreakAPI {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String userKey = "d7997013a9ca426c9ef2b8e03d21d6f7";
		String pipelineKey = "agxzfm1haWxmb29nYWVyOwsSDE9yZ2FuaXphdGlvbiIUcmFkaGlrYWNockBnbWFpbC5jb20MCxIIV29ya2Zsb3cYgICAwOOpuwoM";
		String boxKey1 = "agxzfm1haWxmb29nYWVyMwsSDE9yZ2FuaXphdGlvbiIUcmFkaGlrYWNockBnbWFpbC5jb20MCxIEQ2FzZRiB8PITDA";
		
//		String base_url = "https://www.streak.com"; // /search"; //
//		HttpEntity entity = null;
		StreakAPIImpl streakAPI = new StreakAPIImpl(userKey);
//		QueryStreakAPI otherUserDetails = new OtherUser(userKey);
//		QueryStreakAPI getPipelines = new Pipelines(userKey);
//		QueryStreakAPI getPipeline = new Pipeline(userKey);
//		QueryStreakAPI getBoxes = new Boxes(userKey);
//		StreakAPI getBox = new Box(userKey);
//		PostStreakAPI editBox = new EditBox(userKey);

//		entity = userDetails.getUser();
//		((User)userDetails).getUser();
		try {
			try {
				User user = streakAPI.getCurrentUser();
			} catch (NoValidObjectsReturned e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		((Pipelines)getPipelines).getPipelines();
//		((OtherUser) otherUserDetails).getOtherUser(userKey);
//		((Boxes)getBoxes).getBoxes();
//		((Box)getBox).getBox(boxKey1);
//		((EditBox)editBox).editBox(boxKey1);
		
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("name", "NewBoxHere");
//		jsonObj.put("notes", "Adding content from API");
//		jsonObj.put("notes", "Adding content from API");
//		jsonObj.put("stageKey", 5100);
//		
//		System.out.println(jsonObj.toString());
		
		
	}
}
