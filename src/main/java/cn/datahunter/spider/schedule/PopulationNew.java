package cn.datahunter.spider.schedule;

import cn.datahunter.spider.process.PopulationProcessnew;
import cn.datahunter.spider.util.CommonUtils;
import cn.datahunter.spider.util.GNPandGDPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;
import us.codecraft.webmagic.Spider;

/**
 * Created by Administrator on 2017/3/21.
 */
@Component
public class PopulationNew {


    @Autowired
    private static PopulationProcessnew pop;


    public void doExecute() {
        Population();
    }



    @Test

    @Scheduled(cron = "0 0 0 1 1/6 ?", zone = "Asia/Shanghai")
    public static void Population() {
        GNPandGDPUtil.DATALIST.clear();
        GNPandGDPUtil.NAMELIST.clear();
        GNPandGDPUtil.NAMELIST.add("省市");
        GNPandGDPUtil.NAMELIST.add("年份");
        GNPandGDPUtil.NAMELIST.add("季度");
        String url = "";
        char ch='A';
        String year=null;
        //判断文件是否存在，如果存在就获取最近一年的，如果不存在就获取所有的18个月的。
        if(CommonUtils.loctionFileexist("/data/dataspider/InterfaceAPI/全国地区人口统计.csv"))
        {
            year="1";
        }else
        {
            year="20";
        }
            for (int i = 1; i < 5; i++) {
            if (i <4) {
                GNPandGDPUtil.timp =i;
                url=  "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=fsnd&rowcode=reg&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22A03010"+i+"%22%7D%5D&dfwds=%5B%7B%22wdcode%22%3A%22sj%22%2C%22valuecode%22%3A%22LAST"+year+"%22%7D%5D&k1=1497243185062";
            } else {
                GNPandGDPUtil.timp =i;
                int a=8-i;
                url = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=fsnd&rowcode=reg&colcode=sj&wds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22A0308010"+a+"%22%7D%5D&dfwds=%5B%7B%22wdcode%22%3A%22sj%22%2C%22valuecode%22%3A%22LAST"+year+"%22%7D%5D&k1=1497247440079";
                };

                pop.ARG="AREA_POP";
             url = url.replace(CommonUtils.getBeforeMonth(0), CommonUtils.getBeforeMonth(0));
            //各地区人口---城镇，乡村，总人口,男，女，比例。
            Spider.create(new PopulationProcessnew()).addUrl(url)
                    .thread(1).run();
        }
    }



}
