package com.novel;

import com.novel.bean.Chapter;
import com.novel.bean.Novel;
import com.novel.service.PNovelService;
import com.novel.service.implnotrain.PNovelServiceimpl;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class test {
    PNovelServiceimpl pNovelService = new PNovelServiceimpl();

    @Test
    public void testUrl(){
        String url = "https://m.qu.la/book/27015/9942230.html";
       //String html =  pNovelService.getHtml(url,0);
        Chapter chapter = new Chapter();
        chapter.setSource(url);
        chapter.setNovelid(27);
        List<Chapter> lists = new ArrayList<>();
        lists.add(chapter);
        pNovelService.getContentThread(lists);
        //System.out.println(html);
    }
    @Test
    public void testGetStatenovel(){
        Novel novel = new Novel();

        novel.setTitleurl("https://m.qu.la/booklist/27015.html");
        try {
            pNovelService.GetNovelState(novel);
        }catch (Exception e){
            System.out.println(novel.getState());
        }

    }

    @Test
    public void testDian(){
        String str = "5.html";
        String[] strsplit = str.split("\\.");
        for (String string: strsplit) {
            System.out.println(string+"string");
        }
    }
}
