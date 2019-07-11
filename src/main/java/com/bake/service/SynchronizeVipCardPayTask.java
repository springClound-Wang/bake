package com.bake.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.json.JSONArray;
/*

*
 * 会员卡支付列表


*/

public class SynchronizeVipCardPayTask extends BaseTask{
    protected static Logger logger = Logger.getLogger(SynchronizeVipCardPayTask.class);
    @Override
    protected String getSubTimePath() {
        return "lastUpdateTime/modeCardPay";
    }

    @Override
    protected String getSubPath() {
        return "update/updateModeCardPaylist";
    }

    @Override
    protected String getTaskName() {
        return "会员卡支付列表";
    }



    @Override
    protected JsonArray getSubData(String time) {
        String sql = "select flow_id,card_id,consum_amt,ope_date, branch_no from t_rm_vip_savelist where oper_des='充值卡消费'  and ope_date>'"+time+"'";


        logger.info("会员卡支付列表执行的sql为："+sql);
        return this.baseDao.findBysql(sql);
    }

    @Override
    protected void putSubPath(JSONArray dates, JsonObject data) {
        String[] str = new String[5];
        str[0] = data.get("flow_id")==null?"":data.get("flow_id").getAsString();
        str[1] = data.get("card_id")==null?"":data.get("card_id").getAsString();
        str[2] = data.get("consum_amt")==null?"":data.get("consum_amt").getAsString();
        str[3] = data.get("ope_date")==null?"":data.get("ope_date").getAsString();
        str[4] = data.get("branch_no")==null?"All":data.get("branch_no").getAsString();

        dates.put(str);
    }
}
