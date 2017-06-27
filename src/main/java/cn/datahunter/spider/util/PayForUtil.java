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
    return new ArrayList<>();
    }
    public  static String getCategoryName(String category){
        switch (category){
            case "usersummary": return "微信用户管理统计";
            case "upstreammsghour": return "微信消息管理统计";
            case "interfacesummaryhour":return "微信接口管理统计";
            case "articlesummary":return "图文群发数据统计";
            case "userreadhour": return "图文数据统计";
            case "usersharehour": return "图文分享转发数据统计";
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
