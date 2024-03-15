package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 *
 * 2023-03-15 lizy 修改过
 */
public class HuxiuProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().links().regex(".*article.*").all();
        page.addTargetRequests(requests);
        // xpath还要改一下
        page.putField("title",page.getHtml().xpath("//div[@class='article__bottom-content__right fl']//h1/text()"));
        page.putField("content",page.getHtml().xpath("//div[@class='article__content']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.huxiu.com");
    }

    public static void main(String[] args) {
        Spider.create(new HuxiuProcessor()).addUrl("http://www.huxiu.com/").run();
    }

}
