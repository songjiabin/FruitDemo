package song.jeno.com.fruitdemo.bean;

import java.util.List;

import me.ghui.fruit.Attrs;
import me.ghui.fruit.annotations.Pick;

/**
 * author : 宋佳
 * time   : 2018/11/12
 * desc   : 语录
 * version: 1.0.0
 */

public class QuotationsBean {


    @Pick(value = "article")
    private List<QuotationsBeanItem> quotationsBeanItemList;

    public List<QuotationsBeanItem> getQuotationsBeanItemList() {
        return quotationsBeanItemList;
    }

    public void setQuotationsBeanItemList(List<QuotationsBeanItem> quotationsBeanItemList) {
        this.quotationsBeanItemList = quotationsBeanItemList;
    }

    public static class QuotationsBeanItem {
        //图片
        @Pick(value = "img", attr = Attrs.SRC)
        private String img;
        @Pick(value = "h2.entry-title > a")
        private String title;
        @Pick(value = "div.archive-content")
        private String content;
        @Pick(value = "span.entry-meta > span")
        private String time;
        @Pick(value = "h2.entry-title > a", attr = Attrs.HREF)
        private String link;
        @Pick(value = "span.cat > a")
        private String tag;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }


}
