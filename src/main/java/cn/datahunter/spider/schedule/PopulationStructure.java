package cn.datahunter.spider.schedule;

import cn.datahunter.spider.process.PopulationStructureProcess;
import cn.datahunter.spider.util.GNPandGDPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import us.codecraft.webmagic.Spider;

/**
 * Created by Administrator on 2017/7/6 0006.
 */
public class PopulationStructure {

    @Autowired
    PopulationStructureProcess PopulationStructureProcess;


    @Scheduled(cron = "0 1 0 23 3 *", zone = "Asia/Shanghai")
    public void executeStructure() {
        GNPandGDPUtil.DATALIST.clear();
        GNPandGDPUtil.NAMELIST.clear();
        GNPandGDPUtil.ColumnName="";
        GNPandGDPUtil.ColumnName="Structure";
        String url = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=hgnd&rowcode=sj&colcode=zb&wds=%5B%5D&dfwds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22A0303%22%7D%5D&k1=1499324309464";
        Spider.create(new PopulationStructureProcess()).addUrl(url)
                .thread(1).run();
    }

    @Scheduled(cron = "0 1 0 23 3 *", zone = "Asia/Shanghai")
    public void executeIncrease() {
        GNPandGDPUtil.DATALIST.clear();
        GNPandGDPUtil.NAMELIST.clear();
        GNPandGDPUtil.ColumnName="";
        GNPandGDPUtil.ColumnName="Increase";
        String url = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=hgnd&rowcode=sj&colcode=zb&wds=%5B%5D&dfwds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22A0302%22%7D%5D&k1=1499323983643";
        Spider.create(new PopulationStructureProcess()).addUrl(url)
                .thread(1).run();
    }

    @Scheduled(cron = "0 1 0 23 3 *", zone = "Asia/Shanghai")
    public void executeStudent() {
        GNPandGDPUtil.DATALIST.clear();
        GNPandGDPUtil.NAMELIST.clear();
        GNPandGDPUtil.ColumnName="";
        GNPandGDPUtil.ColumnName="Student";
        String url = "http://data.stats.gov.cn/easyquery.htm?m=QueryData&dbcode=hgnd&rowcode=zb&colcode=sj&wds=%5B%5D&dfwds=%5B%7B%22wdcode%22%3A%22zb%22%2C%22valuecode%22%3A%22A0M0S%22%7D%5D&k1=1499325502528";
        Spider.create(new PopulationStructureProcess()).addUrl(url)
                .thread(1).run();
    }

}
