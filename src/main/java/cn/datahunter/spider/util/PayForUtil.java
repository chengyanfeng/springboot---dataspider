package cn.datahunter.spider.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class PayForUtil {
   public final static String APPID="wxcef2b3836a816181";
   public final static String secret="67525a90d4eae7c7ad47c592f982c1c2";
    public static List<String> NAMELIST=new ArrayList<>();
    public static List<Object> DATALIST=new ArrayList<>();
    public static  List<String> getCategory(String category) {

        if(category.equals("usersummary")){
           List list=new ArrayList<>();
            list.add("new_user");
            list.add("cancel_user");
            list.add("user_source");
            try {
                list.add( URLEncoder.encode("时间","UTF-8"));
            }catch (Exception e){

            }
        return list;
        }else if(category.equals("upstreammsghour")){
            List list=new ArrayList<>();

            list.add("user_source");
            list.add("msg_user");
            list.add("msg_type");
            list.add("msg_count");
            list.add("ref_hour");
            try {
                list.add( URLEncoder.encode("时间","gbk"));
            }catch (Exception e){

            }



            return list;
        }else if(category.equals("interfacesummaryhour")){
            List list=new ArrayList<>();

            list.add("callback_count");
            list.add("fail_count");
            list.add("total_time_cost");
            list.add("max_time_cost");
            list.add("ref_hour");
            try {
                list.add( URLEncoder.encode("时间","UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            return list;
        }
        else if(category.equals("articlesummary")){
            List list=new ArrayList<>();

            list.add("title");//标题
            list.add("int_page_read_count");//原文页的阅读次数
            list.add("ori_page_read_count");//图文页的阅读次数
            list.add("add_to_fav_count");//收藏的次数
            list.add("int_page_read_user");//图文页（点击群发图文卡片进入的页面）的阅读人数
            list.add("msgid");//请注意：这里的msgid实际上是由msgid（图文消息id，这也就是群发接口调用后返回的msg_data_id）和index（消息次序索引）组成， 例如12003_3， 其中12003是msgid，即一次群发的消息的id； 3为index，假设该次群发的图文消息共5个文章（因为可能为多图文），3表示5个中的第3个
            list.add("ori_page_read_user");//原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
            list.add("user_source");//在获取图文阅读分时数据时才有该字段，代表用户从哪里进入来阅读该图文。0:会话;1.好友;2.朋友圈;3.腾讯微博;4.历史消息页;5.其他
            list.add("add_to_fav_user");//收藏的人数
            list.add("share_user");//分享的人数
            list.add("share_count");//分享的次数

            try {
                list.add("时间"); //日期
            } catch (Exception e) {
                e.printStackTrace();
            }


            return list;
        }else if(category.equals("userreadhour")){
            List list=new ArrayList<>();

            list.add("total_online_time");//阅读在线时长
            list.add("int_page_read_count");//原文页的阅读次数
            list.add("ori_page_read_count");//图文页的阅读次数
            list.add("add_to_fav_count");//收藏的次数
            list.add("int_page_read_user");//图文页（点击群发图文卡片进入的页面）的阅读人数
            list.add("ori_page_read_user");//原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
            list.add("user_source");//在获取图文阅读分时数据时才有该字段，代表用户从哪里进入来阅读该图文。0:会话;1.好友;2.朋友圈;3.腾讯微博;4.历史消息页;5.其他
            list.add("add_to_fav_user");//收藏的人数
            list.add("share_user");//分享的人数
            list.add("share_count");//分享的次数
            list.add("ref_hour");
            try {
                list.add("时间"); //日期
            } catch (Exception e) {
                e.printStackTrace();
            }


            return list;
        }else if(category.equals("usersharehour")){
            List list=new ArrayList<>();
            list.add("share_scene");//分享的场景1代表好友转发 2代表朋友圈 3代表腾讯微博 255代表其他
            list.add("user_source");//在获取图文阅读分时数据时才有该字段，代表用户从哪里进入来阅读该图文。0:会话;1.好友;2.朋友圈;3.腾讯微博;4.历史消息页;5.其他
            list.add("share_user");//分享的人数
            list.add("share_count");//分享的次数
            list.add("ref_hour");
            try {
                list.add("时间"); //日期
            } catch (Exception e) {
                e.printStackTrace();
            }


            return list;
        }
    return new ArrayList<>();
    }
    public  static String getCategoryName(String category){
        switch (category){
            case "usersummary": return "微信用户管理统计";
            case "upstreammsghour": return "微信消息管理统计";
            case "interfacesummaryhour":return "微信接口管理统计";
            case "articlesummary":return "微信图文群发数据统计";
            case "userreadhour": return "微信图文数据统计";
            case "usersharehour": return "微信图文分享转发数据统计";
        }
        return "";
    }
    //获取每天的日期
    public static String getDate(int i,String category){
        if(!CommonUtils.loctionFileexist("/data/dataspider/InterfaceAPI/"+getCategoryName(category)+".csv")){
        Date date=new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.set(2017, 04, 22);
        calendar.add(calendar.DAY_OF_YEAR,+i);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
        }
        else{

            Date date=new Date();
            String formatterDate = getFormatterDate(date);
            return formatterDate;


        }

    }
    //获取直到今天为止时间长度
    public static Integer getDateLong(String category){
        if(!CommonUtils.loctionFileexist("/data/dataspider/InterfaceAPI/"+getCategoryName(category)+".csv")) {
            Calendar cal = Calendar.getInstance();
            cal.set(2017, 04, 22);
            long time1 = cal.getTimeInMillis();
            cal.setTime(new Date());
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);

            return Integer.parseInt(String.valueOf(between_days));
        }else{
            return 1;
        }
    }
//格式化日期并返回2017-03-01 的string类型
    public static  String getFormatterDate(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DAY_OF_YEAR,-1);
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }


}
