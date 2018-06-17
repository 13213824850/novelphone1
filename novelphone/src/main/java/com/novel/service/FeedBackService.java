package com.novel.service;

import com.novel.bean.Feedback;
import com.novel.until.Msg;

public interface FeedBackService {

	Msg addFeedBack(Feedback feedback);

	Msg getFeedBack(String feedbacktype, Integer pn);

	Msg updateFeedbackstate(Integer id);

}
