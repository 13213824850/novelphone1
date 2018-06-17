package com.novel.until;

public class Until {
public static String getNovelType(String type) {
	String novelType = null;
	switch (type) {
	case "玄幻奇幻": novelType = "xhqh";
		break;
	case "武侠仙侠":novelType = "wxxx";
		break;
	case "都市言情":novelType = "dsyq";
		break;
	case "历史军事":novelType = "lsjs";
		break;
	case "科幻灵异":novelType = "khly";
		break;
	case "网游竞技":novelType = "wyjj";
		break;
	case "女生频道":novelType = "nspd";
		break;

	}
	return novelType;
}
}
