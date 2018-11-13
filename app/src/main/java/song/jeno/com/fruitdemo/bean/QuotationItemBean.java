package song.jeno.com.fruitdemo.bean;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.ghui.fruit.Attrs;
import me.ghui.fruit.annotations.Pick;

/**
 * author : 宋佳
 * time   : 2018/11/13
 * desc   :
 * version: 1.0.0
 */

public class QuotationItemBean {

    @Pick(value = "header.entry-header > h1")
    private String title;


    @Pick(value = "div.single-content > p")
    private String content;


    @Pick(value = "div.single-content > p > a > img", attr = Attrs.SRC)
    private String img;


    @Pick(value = "div.single-cat")
    private String time;


    @Pick(value = "div.single-cat > a")
    private String category;


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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        if (!TextUtils.isEmpty(time)) {
            time = time.split("  ")[0];
            String regEx = "\\D";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(time);
            String result = m.replaceAll("").trim();
            Character ch = result.charAt(0);
            int index = time.indexOf(ch);
            time = time.substring(index);
        }
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
