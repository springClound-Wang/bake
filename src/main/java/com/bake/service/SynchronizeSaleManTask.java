package com.bake.service;

import com.bake.dto.Sysconfig;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.json.JSONArray;

/*
*
 * 营业员

*/

public class SynchronizeSaleManTask extends BaseTask{
    protected static Logger logger = Logger.getLogger(SynchronizeSaleManTask.class);
    @Override
    protected String getSubTimePath() {
        return "lastUpdate/getModeSaleManLastSaleId";
    }

    @Override
    protected String getSubPath() {
        return "update/updateModeSaleMan";
    }

    @Override
    protected String getTaskName() {
        return "营业员";
    }


    @Override
    protected JsonArray getSubData(String time) {
        String sql = "SELECT  sale_id,sale_name    FROM "+ Sysconfig.dbName+".t_rm_saleman";
        logger.info("营业员执行的sql为："+sql);
        return this.baseDao.findBysql(sql);
    }

    @Override
    protected void putSubPath(JSONArray dates, JsonObject data) {
        String[] str = new String[2];
        str[0] = data.get("sale_id").getAsString();
        str[1] = data.get("sale_name")==null?"":data.get("sale_name").getAsString();
        dates.put(str);
    }
}
