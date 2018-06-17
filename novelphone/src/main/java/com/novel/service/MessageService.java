package com.novel.service;

import java.util.List;

import com.novel.bean.Message;
import com.novel.until.Msg;

public interface MessageService {
int addMessage(Message message);

List<Message> getMessageCount(Integer userid, Integer state);


Msg deleteMessage(String ids, Integer saveid);

Msg updateStateMessage(Integer id, Integer saveid);
}
