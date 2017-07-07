package cn.datahunter.spider.process;

import cn.datahunter.spider.util.CommonUtils;
import cn.datahunter.spider.util.GNPandGDPUtil;
import cn.datahunter.spider.util.uplaodAndURL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2017/3/14.
 */
@Component
public class GrossDomesticcproductProcess implements PageProcessor {

    private Site site =
            Site.me()
                    .setCharset("utf-8")
                    .setSleepTime(3000)
                    .setRetryTimes(3)
                    .setCycleRetryTimes(3)
                    .setTimeOut(60000)
                    .addCookie("JSESSIONID", "230C63250897C58564351909DCDE7B52")
                    .addCookie("u", "6")
                    .addHeader("Accept",
                            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .addHeader(
                            "User-Agent",
                            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
    public static String ARG = StringUtils.EMPTY;
    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        //备用list
        List<String> dataOut = new ArrayList<>();
        List<String> dataLst = new ArrayList<>();
        //List<String> nameLst = new ArrayList<>();
        //解析page
        String rawText = page.getRawText();
        JSONObject jsonObject = JSON.parseObject(rawText);
        JSONObject returnData  = jsonObject.getJSONObject("returndata");
        //地区生产总值
        Map<String, List> AgriculturalMap = AgriculturalSidelineProducts( dataLst, returnData);


        GNPandGDPUtil.NAMELIST.add(GNPandGDPUtil.ColumnName);
        List<String> datalist = AgriculturalMap.get("data");
        if(GNPandGDPUtil.timp==1){
            for(int i=0;i<datalist.size();i=i+4) {
                GNPandGDPUtil.DATALIST.add(datalist.get(0+i).replace("自治区","").replace("省","").replace("市", "") + "," +
                        datalist.get(1+i) + "," +
                        datalist.get(2+i)
                        + "," +
                        datalist.get(3+i));

            }
        }else{
            for(int i=1;i< GNPandGDPUtil.DATALIST.size()+1;i++)
            {
                String s = GNPandGDPUtil.DATALIST.get(i-1)+","+datalist.get(i*4-1);
                GNPandGDPUtil.DATALIST.add(i-1,s);
                GNPandGDPUtil.DATALIST.remove(i);}
        }
        try {
            if(GNPandGDPUtil.timp==1){
                dataOut.add(CommonUtils.removeBrackets(GNPandGDPUtil.NAMELIST.toString()));
                dataOut.addAll(GNPandGDPUtil.DATALIST);
                FileUtils.writeLines(new File("/data/dataspider/InterfaceAPI/" + GNPandGDPUtil.getGNPName(ARG) + CommonUtils.getBeforeMonth(0) + ".csv"), "UTF-8", dataOut);
                FileUtils.writeLines(new File("/data/dataspider/InterfaceAPI/" + GNPandGDPUtil.getGNPName(ARG) + ".csv"), "UTF-8", dataOut);
                uplaodAndURL.upload(GNPandGDPUtil.getGNPName(ARG) , new File("/data/dataspider/InterfaceAPI/" + GNPandGDPUtil.getGNPName(ARG) + CommonUtils.getBeforeMonth(0) + ".csv"), "mrocker", "2","gdp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //各地区生产总值
    public Map<String,List> AgriculturalSidelineProducts(List<String> dataLst,JSONObject returnData){
        Map<String,List> map=new HashMap<>();
        JSONArray dataArrname = returnData.getJSONArray("wdnodes");
        GNPandGDPUtil.ColumnName=dataArrname.getJSONObject(0).getJSONArray("nodes").getJSONObject(0).getString("cname")
        +"("+dataArrname.getJSONObject(0).getJSONArray("nodes").getJSONObject(0).getString("unit")+")";
        JSONArray nodes=dataArrname.getJSONObject(1).getJSONArray("nodes");
        JSONArray dataArr = returnData.getJSONArray("datanodes");
        //获取年度数据
        JSONArray jidu = dataArrname.getJSONObject(2).getJSONArray("nodes");

        for(int b=0;b<jidu.size();b++) {
            for (int i = 0; i < nodes.size(); i++) {
                dataLst.add(nodes.getJSONObject(i).get("cname").toString());
                dataLst.add(jidu.getJSONObject(b).getString("name").substring(0,4));
                dataLst.add(jidu.getJSONObject(b).getString("name"));

                JSONObject data = (JSONObject) dataArr.getJSONObject(i*jidu.size()+b).
                        getJSONObject("data");
                if (null != data && StringUtils.isNotBlank(data.toString())) {
                    String strdata = data.getString("strdata");
                    dataLst.add(strdata);

                }
            }


        }



        map.put("data",dataLst);
        return map;
    }
}
