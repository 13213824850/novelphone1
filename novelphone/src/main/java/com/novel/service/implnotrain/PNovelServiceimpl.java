package com.novel.service.implnotrain;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.prism.impl.Disposer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.novel.bean.Chapter;
import com.novel.bean.Content;
import com.novel.bean.Novel;
import com.novel.bean.NovelClock;
import com.novel.bean.NovelClockExample;
import com.novel.bean.NovelExample;
import com.novel.bean.NovelExample.Criteria;
import com.novel.bean.ReadRecord;
import com.novel.bean.ReadRecordExample;
import com.novel.mapper.ChapterMapper;
import com.novel.mapper.ContentMapper;
import com.novel.mapper.NovelClockMapper;
import com.novel.mapper.NovelMapper;
import com.novel.mapper.ReadRecordMapper;
import com.novel.service.PNovelService;
import com.novel.until.Msg;

@Service
public class PNovelServiceimpl implements PNovelService {
	@Value("${NovelState}")
	private String novelstate;
	@Autowired
	private ContentMapper contentMapper;
	@Autowired
	private ChapterMapper chapterMapper;
	// 搜索链接
	@Value("${searchUrl}")
	private String searchUrl;
	@Value("${chapterItem}")
	private String chapterItem;
	@Value("${contentUrl}")
	private String contentUrl;
	@Autowired
	private NovelMapper novelMapper;
	@Autowired
	private NovelClockMapper novelClockMapper;

	@Autowired
	private ReadRecordMapper readRecordMapper;

	// 默认false不开启更新
	private static boolean updateNovelStartAndEnd = true;

	private static CloseableHttpClient httpClient;
	static {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
		cm.setDefaultMaxPerRoute(50);
		httpClient = HttpClients.custom().setConnectionManager(cm).build();
	}

	public String getHtml(String url) {
		int conneccount = 0;
		String html = null;
		HttpGet get = new HttpGet(url);
		/*
		 * RequestConfig requestConfig = RequestConfig.custom()
		 * .setConnectTimeout(10000).setConnectionRequestTimeout(10000)
		 * .setSocketTimeout(10000).build(); get.setConfig(requestConfig);
		 */
		try {
			HttpEntity entity = httpClient.execute(get).getEntity();
			html = EntityUtils.toString(entity, "utf-8");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * if(html==null||html=="") { conneccount = conneccount+1; if(conneccount<=4) {
		 * html = getHtml(url); } }
		 */
		return html;

	}

	/*
	 * 本地服务搜索不到 进入第三方搜索模式
	 */
	public List<Novel> getNovels(String novelName) {

		String url = searchUrl + novelName;
		String html = (String) getHtml(url);
		if (html == null) {
			return null;
		}
		List<Novel> novels = new ArrayList<>();
		Document doc = Jsoup.parse(html);
		Elements listbook = doc.select(".result-list");// .book-img-text全部书籍
		Elements book = listbook.select(".result-item"); // .book-img-box单个书籍
		Elements imgs = book.select("img");// 图片链接
		Elements urls = book.select(".result-game-item-pic-link");// 小说链接
		// 书籍信息
		Elements info1 = listbook.select(".result-game-item-detail");// .book-mid-info
		Elements titles = info1.select("h3");// h4 a// 书籍tilte
		Elements intros = info1.select(".result-game-item-desc");// .intro书籍简介
		// 书籍详细介绍
		Elements info2 = book.select(".result-game-item-info");
		for (int i = 0; i < imgs.size(); i++) {
			String imgUrl = imgs.get(i).attr("src");

			String titleUrl = urls.get(i).attr("href");
			String title = titles.get(i).text();
			// 若搜索的内容简介为空
			Elements introscheck = info1.get(i).select(".result-game-item-desc");
			String bodyContent = intros.get(i).text();
			if (introscheck.size() == 0) {
				intros.add(i, null);
				bodyContent = null;
			}
			// 作者 类型 更新时间 最新章节
			Elements select = info2.get(i).select(".result-game-item-info-tag");
			String authName = select.get(0).text().split("：")[1];
			String type = select.get(1).text().split("：")[1];
			String[] updateTimea = select.get(2).text().split("：");
			String updateTime = null;
			if (updateTimea.length>=2) {
				updateTime = updateTimea[1];
			}
			String[] newChaptera = null;
			String newChapter = null;
			if(select.size()>=4) {
				newChaptera = select.get(3).text().split("：");
				if(newChaptera.length>=2) {
					newChapter = newChaptera[1];
				}
			}
			
			
			//String newChapterurl = select.get(3).select("a").attr("href");
			Novel novel = new Novel(0, title, authName, type, titleUrl, updateTime, newChapter, 0, imgUrl, bodyContent,
					0, "state", "www.qu.la/", null, 0, new Date(), new Date());
			novels.add(novel);
		}
		return novels;
	}

	/*
	 * 获取title章节目录
	 */
	public List<Chapter> getChapters(String url, int novelid) {

		String html = getHtml(url).toString();
		if (html == null) {
			return null;
		}
		Document doc = Jsoup.parse(html);
		Elements dls = doc.select("#chapterlist");// ("dd a");
		dls = dls.select("a");
		List<Chapter> chapters = new ArrayList<>();
		for (int i = 1; i < dls.size(); i++) {
			Element dl = dls.get(i);
			String chaptertext = dl.text();
			String chapterUrl = dl.attr("href");
			chapterUrl = contentUrl + dl.attr("href");
			Chapter chapter = new Chapter();
			chapter.setChaptertext(chaptertext);
			chapter.setNovelid(novelid);
			chapter.setSource(chapterUrl);
			chapter.setNum(i);// 设置当前第几章
			chapters.add(chapter);
		}
		return chapters;
	}

	/*
	 * 获取全本章节内容
	 */

	public void getContents(List<Chapter> chapters) {

		// 第一步判断需要多少线程 50章节开启一个线程
		int chaptersSize = chapters.size();
		int threadNum = 0;
		int threadNumChapters = 50;// 每个线程章节数
		if (chaptersSize % threadNumChapters == 0) {
			threadNum = chaptersSize / threadNumChapters;
		} else {
			threadNum = chaptersSize / threadNumChapters + 1;
		}
		// 开启线程
		for (int i = 0; i < threadNum; i++) {
			if (i == threadNum - 1) {
				MyThread myThread = new MyThread(chapters.subList(i * threadNumChapters, chapters.size()));
				Thread thread = new Thread(myThread);
				thread.start();
			} else {
				MyThread myThread = new MyThread(chapters.subList(i * threadNumChapters, (i + 1) * threadNumChapters));
				Thread thread = new Thread(myThread);
				thread.start();
			}
		}

	}

	public void getContentThread(List<Chapter> chapters) {

		int preid = 0;
		int nextid = 0;
		int id = 0;
		int novelid = 0;
		if (chapters.size() != 0) {
			novelid = chapters.get(0).getNovelid();
		}
		int againCount = 0;// 重新获取的次数
		List<Chapter> againGetChapter = new ArrayList<>();// 未获得再次获取的章节

		for (Chapter chapter : chapters) {
			String[] split = chapter.getSource().split("/");
			String idString = split[split.length - 1].split("\\.")[0];
			id = Integer.parseInt(idString);
			String url = chapter.getSource();
			String html = getHtml(url);
			if(html==null) {
				againGetChapter.add(chapter);
				continue;
			}
			Document doc = Jsoup.parse(html);
			Elements contenttext = doc.select("#chaptercontent");
			// 判断是否得到返回值 若没有则开始重新获取

			String pre = doc.select("#pt_prev").attr("href");
			if (pre == null || pre == "") {
				againGetChapter.add(chapter);
				continue;
			}
			String next = doc.select("#pt_next").attr("href");

			// 优化body
			String bodyold = contenttext.toString();
		    String body = changerBody(bodyold);

			String chaptertext = chapter.getChaptertext();
			if (pre.contains("/")) {
				Novel record = new Novel();
				record.setId(novelid);
				record.setContentid(id);
				novelMapper.updateByPrimaryKeySelective(record);
				preid = 0;
			} else {
				preid = Integer.parseInt(pre.split("\\.")[0]);
			}

			if (next.contains("/")) {
				Novel record = novelMapper.selectByPrimaryKey(novelid);
				record = GetNovelState(record);
				if (novelstate.equals(record.getState())) {
					NovelClock novelClock = new NovelClock();
					novelClock.setId(record.getId());
					novelClock.setChapterurl(chapter.getSource());
					novelClock.setClockstate(0);
					novelClock.setNum(chapter.getNum());
					novelClock.setCreatetime(new Date());
					novelClock.setUpdatetime(new Date());
					novelClock.setState(record.getState());
					novelClock.setTitleurl(record.getTitleurl());
					novelClockMapper.insert(novelClock);
				}
			} else {

				nextid = Integer.parseInt(next.split("\\.")[0]);
			}

			Content content = new Content(chaptertext, preid, nextid, 0, body);
			content.setId(id);
			content.setNum(chapter.getNum());
			content.setNovelid(novelid);
			chapter.setContentid(id);
			try {
				contentMapper.insert(content);
				chapterMapper.insert(chapter);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// 开始加重未完成的章节
		if (againGetChapter.size() != 0) {
			againCount = againCount + 1;
			// 获取次数超过4次则停止更新防止线程卡死
			if (againCount != 4) {
				getContentThread(againGetChapter);
				againGetChapter.isEmpty();
			} else {
				System.out.println(againGetChapter.size());
			}

		}
		againGetChapter.isEmpty();
	}

	public static String changerBody(String bodyold){
		String[] bodysplit = bodyold.split("<br>");
		String body = "";
		/*	for (String string : bodysplit) {
				if (string.contains("&nbsp;&nbsp;&nbsp;&nbsp") && !string.contains("<p")) {

					string = string.replace("&nbsp;", "");
					body = body.trim() + string.trim() + "<br>";
				}

			}*/
		//0除外只要偶数
		for (int i = 2; i <bodysplit.length ; i++) {
			if(i%2==0){
				String string = bodysplit[i].replace("&nbsp;", "");
				body = body.trim() + string.trim() + "<br>";
			}
		}
		return body;
	}

	@Override
	public Novel getNovel(Novel novel) {

		// 查询书库中有没有该书籍
		NovelExample example = new NovelExample();
		Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(novel.getTitle());
		criteria.andAuthEqualTo(novel.getAuth());
		List<Novel> novels = novelMapper.selectByExample(example);
		if (novels.size() != 0) {
			return novels.get(0);
		}
		return null;

	}

	/*
	 * (non-Javadoc) 异步调用
	 */
	@Async
	@Override
	public void AsynAddNovel(Novel novel) {
		/*
		 * 1插入书籍信息 2获取章节信息 3加载内容
		 */

		List<Chapter> chapters = getChapters(novel.getTitleurl(), novel.getId());
		if (chapters != null) {
			getContents(chapters);
		}

	}

	@Override
	public void addNovel(Novel novel) {
		novel.setContentid(0);
		// 拼装url改为目录地址
		String[] split = novel.getTitleurl().split("/");
		String titleUrl = chapterItem + split[split.length - 1] + ".html";
		novel.setTitleurl(titleUrl);
		novelMapper.insert(novel);
	}

	@Override
	public Novel GetNovelState(Novel novel) {
		String url = novel.getTitleurl();
		String[] urlSplit = url.split("/");
		String bookId = urlSplit[urlSplit.length-1].split("\\.")[0];
		url = contentUrl+"/book/"+bookId+"/";
		//System.out.println(url);
		String html = getHtml(url).toString();
		Document doc = Jsoup.parse(html);
		Elements select = doc.select(".synopsisArea_detail");
		if(select.size()==0){
			//查询失败加入数据库
			return novel;
		}
		Elements p = select.get(0).select("p");
		for (Element element : p) {
			String[] split = element.text().split("：");
			if("状态".equals(split[0])){
				if(!(split.length>=2)){
					return novel;
				}
				if(!split[1].equals(novel.getState())) {
					novel.setState(split[1]);
					novelMapper.updateByPrimaryKeySelective(novel);
				}
			}

		}
		return novel;
	}

	/*
	 * 30分钟执行一次任务
	 * 
	 */
	@Scheduled(cron = "0 0 0/1 * * ? ")
	@Override
	public Msg clockNovelUpdate() {
		// 第一步查询小说
		// 2根据最新章节 原地址开始解析
		System.out.println(new Date().toString()+"50分钟");
		getUpdateNveol(0);
		return null;
	}

	// 3小时任务
	@Scheduled(cron = "0 0 0/3 * * ? ")
	@Override
	public Msg clockNovelUpdate3() {
		System.out.println(new Date().toString()+"3");
		getUpdateNveol(1);
		return null;
	}

	// 6小时任务
	@Scheduled(cron = "0 0 0/6 * * ? ")
	@Override
	public Msg clockNovelUpdate6() {
		System.out.println(new Date().toString()+"6");
		getUpdateNveol(2);
		return null;
	}

	// 13.5小时任务
	@Scheduled(cron = "0 0 0/12 * * ? ")
	@Override
	public Msg clockNovelUpdate13() {
		System.out.println(new Date().toString()+"13");
		getUpdateNveol(3);
		return null;
	}

	@Scheduled(cron = " 0 0 0/23 ? * * ")
	// 每tian三小时执行一次将小说更新状态初始化为0变为30分钟查一次
	@Override
	public Msg clockNovelUpdateState() {
		System.out.println(new Date().toString()+"3点");
		// 1重置更新时间
		NovelClockExample example = new NovelClockExample();
		NovelClock novelClock = new NovelClock();
		novelClock.setClockstate(0);
		novelClockMapper.updateByExample(novelClock,example);
		// 2根据小说id查询小说是否为
		return null;
	}

	@Scheduled(cron = " 0 0 3 ? * Sun ")
	// 每周三小时执行一次将判断小说是否还是连载中
	@Override
	public Msg updatenovelClock() {
		// 判断小说状态
		NovelClockExample example = new NovelClockExample();
		List<NovelClock> selectByExample = novelClockMapper.selectByExample(example);
		List<Integer> lists = new ArrayList<>();
		for (NovelClock novelClock : selectByExample) {
			Novel nove = new Novel();
			nove.setId(novelClock.getId());
			nove.setTitleurl(novelClock.getTitleurl());
			nove = GetNovelState(nove);
			if (novelClock.getState() != novelstate) {
				lists.add(novelClock.getId());
			}
		}
		// 将完结的小说从表中移除
		NovelClockExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(lists);
		novelClockMapper.deleteByExample(example);
		// 更新novel表中小说状态

		return null;
	}

	// 查询数据
	public void getUpdateNveol(int state) {

		// state为更新时间段
		
		if (updateNovelStartAndEnd) {
			System.out.println("任务开启");
			NovelClockExample example = new NovelClockExample();
			NovelClockExample.Criteria criteria = example.createCriteria();
			criteria.andClockstateEqualTo(state);// 查询出30分钟更新的小说
			List<NovelClock> novelclocks = novelClockMapper.selectByExample(example);

			// 2根据最新章节 原地址开始解析
			for (NovelClock novelClock : novelclocks) {
				isUpdate(novelClock, state);
			}
		}
	}

	// 判断是否跟新
	public void isUpdate(NovelClock novelClock, int state) {

		String url = novelClock.getChapterurl();
		String html = getHtml(url).toString();
		Document doc = Jsoup.parse(html);
		String next = doc.select("#pt_next").attr("href");

		if (!next.contains("/")) {
			// 将其放入3小时更新队列
			novelClock.setClockstate(state+1);
			novelClockMapper.updateByPrimaryKeySelective(novelClock);
			getNewChapters(novelClock, next);
		}

	}

	public boolean getNewChapters(NovelClock novelClock, String nextUrl) {

		String idString = nextUrl.split("\\.")[0];
        int contentid = Integer.parseInt(idString);
		String[] urlSplit = novelClock.getTitleurl().split("/");
		String bookId = urlSplit[urlSplit.length-1].split("\\.")[0];
		String url = contentUrl+"/book/"+bookId+"/"+nextUrl;
		String html = getHtml(url).toString();
		Document doc = Jsoup.parse(html);
		Elements contenttext = doc.select("#chaptercontent");
		String pre = doc.select("#pt_prev").attr("href");
		String next = doc.select("#pt_next").attr("href");
		if(pre==null||next==null) {
			return false;
		}
		String title = doc.select(".title").text();

		String bodyold = contenttext.toString();
		String body = changerBody(bodyold);
        int nextid = 0;
		if (!next.contains("/")) {
			nextid = Integer.parseInt(next.split("\\.")[0]);
		}
			int preid = Integer.parseInt(pre.split("\\.")[0]);
			Content content = new Content(title, preid, nextid, 0, body);
			content.setId(contentid);
			content.setNovelid(novelClock.getId());
			content.setNum(novelClock.getNum()+1);
			contentMapper.insert(content);
			Chapter chapter = new Chapter();
			chapter.setContentid(content.getId());
			chapter.setNum(novelClock.getNum()+1);
			chapter.setChaptertext(title);
			chapter.setNovelid(novelClock.getId());
			chapter.setSource(url);
			chapterMapper.insert(chapter);
			novelClock.setNum(novelClock.getNum()+1);
			// 判断是否还有下一张
			if (!next.contains("/")) {
				getNewChapters(novelClock, next);
			} else {
				novelClock.setChapterurl(contentUrl+"/"+bookId+"/"+nextUrl);
				novelClockMapper.updateByPrimaryKeySelective(novelClock);
				Novel novel = new Novel();
				novel.setId(novelClock.getId());
				novel.setNewchapterurl(contentid);
				novel.setNewchapter(title);
				novelMapper.updateByPrimaryKey(novel);
				// 更新书架
				ReadRecord readRecord = new ReadRecord();
				readRecord.setIsupdate(1);
				readRecord.setUpdatetime(new Date());
				ReadRecordExample example = new ReadRecordExample();
				ReadRecordExample.Criteria criteria = example.createCriteria();
				criteria.andNovelidEqualTo(novel.getId());
				readRecordMapper.updateByExampleSelective(readRecord, example);

			}

		return true;
	}

	@Override
	public Msg UpdateNovelStartAndStop(Boolean state) {
		updateNovelStartAndEnd = state;
		return Msg.success();
	}

	// 线程开启内部类
	class MyThread implements Runnable {
		private List<Chapter> chapters;

		public MyThread() {
		}

		public MyThread(List<Chapter> chapters) {
			this.chapters = chapters;
		}

		@Override
		public void run() {
			getContentThread(chapters);

		}
	}
}
