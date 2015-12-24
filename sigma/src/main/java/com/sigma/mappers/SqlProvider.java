package com.sigma.mappers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SqlProvider {
	
	public String updateDeviceRentStatus(Map<String, Object> parameters){
		String id = (String) parameters.get("id");
		String actualTime = (String) parameters.get("actualTime");
		String status = (String) parameters.get("status");
		
		String sql = "update rent set RENT_TYPE = '" + status + "'";
		if(actualTime == null || actualTime.isEmpty()){
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			actualTime = dateFormat.format(now);
		}
		
		sql += ", ACTUAL_TIME = '" + actualTime + "'";
		
		sql += " WHERE DEVICE_ID = " + id; 
		
		return sql;
	}
	
	public String selectCurrentRentLogList(Map<String, Object> parameters){
		String user = (String) parameters.get("user");
		String sql = "SELECT * FROM v_device_rent where 1=1";
		if(user != null && !user.equals("ALL") && !user.isEmpty()){
			sql += " and MANAGER = '" + user + "'";
		}
		
		sql += " order by RENT_TIME asc";
		
		return sql;
	}
	
	public String getDeviceLogList(Map<String, Object> parameters){
		String type = (String) parameters.get("type");
		String sql = "SELECT * FROM log_device where 1=1 ";
		if(type != null && !type.equals("ALL") && !type.isEmpty()){
			sql += " and LOG_TYPE = '" + type + "'";
		}
		
		sql += " order by CREATE_TIME asc";
		
		return sql;
	}
	
	public String selectDeviceListByUser(Map<String, Object> parameters){
		String status = (String) parameters.get("status");
		String user = (String) parameters.get("user");
		String serialcode = (String) parameters.get("serialcode");
		StringBuffer sql = new StringBuffer();
		sql.append(String.format("SELECT * FROM v_user_renter_device where 1=1 "));

		if(!status.equals("ALL") && !status.isEmpty()){
			if(status.compareToIgnoreCase("EXTERNAL_BORROWING") == 0){
				sql.append(String.format(" and STATUS = '%s' or STATUS = 'TEMP_OWN_RELET' ", status));
			}else{
				sql.append(String.format(" and STATUS = '%s'", status));
			}
		}
		
		if(!user.equals("ALL") && !status.isEmpty() && !user.equals("")){
			sql.append(String.format(" and MANAGER = '%s'", user));
		}
		
		if(serialcode != null && !serialcode.equals("ALL") && !serialcode.isEmpty()){
			sql.append(String.format(" and (SERIAl_NUMBER_1 LIKE '%%%s%%'", serialcode));
			sql.append(String.format(" or SERIAl_NUMBER_2 LIKE '%%%s%%')", serialcode));
		}
		
		sql.append(String.format(" order by STATUS asc"));
		
		return sql.toString();
	}
	
	public String countDevices(Map<String, Object> parameters){
		String status = (String) parameters.get("status");
		String user = (String) parameters.get("user");

		String sql = "SELECT count(*) FROM v_user_renter_device where 1=1 ";
		if(status != null && !status.equals("ALL") && !status.isEmpty()){
			sql += " and STATUS = '" + status + "'";
		}
		
		if(user != null && !user.equals("ALL") && !user.isEmpty()){
			sql += " and MANAGER_ID = '" + user + "'";
		}
		
		return sql;
	}
	
	public String getDeviceList(Map<String, Object> parameters){
		String status = (String) parameters.get("status");
		String user = (String) parameters.get("user");
		String serialcode = (String) parameters.get("serialcode");
		int page = (Integer) parameters.get("page");
		int size = (Integer) parameters.get("size");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT * FROM v_user_renter_device where 1=1 ");
		if(status.isEmpty() || status.equals("ALL")){
			sql.append(" and STATUS != 'TEMP_OWN' and STATUS != 'TEMP_OWN_RELET'");
		}
		
		if(status != null && !status.equals("ALL") && !status.isEmpty()){
			sql.append(String.format(" and STATUS = '%s'", status));
		}
		
		if(user != null && !user.equals("ALL") && !user.isEmpty()){
			sql.append(String.format(" and MANAGER_ID = '%s'", user));
		}
		
		if(serialcode != null && !serialcode.equals("ALL") && !serialcode.isEmpty()){
			sql.append(String.format(" and (SERIAl_NUMBER_1 LIKE '%%%s%%'", serialcode));
			sql.append(String.format(" or SERIAl_NUMBER_2 LIKE '%%%s%%')", serialcode));
		}
		
		sql.append(String.format(" order by STATUS desc LIMIT %d,%d", page, size));
		
		return sql.toString();
	}
	
	public String getRentLogList(Map<String, Object> parameters){
		String user = (String) parameters.get("user");
		String sql = "SELECT * FROM v_device_rent_log ";
		if(!user.equals("ALL") && !user.isEmpty()){
			sql += " WHERE MANAGER = '" + user + "'";
		}
		
		sql += " order by MODIFY_TIME asc";
		
		return sql;
	}
	
	//用于在获取排行榜时根据不同的排序方式 返回不同的sql语句
	public String getSql(Map<String, Object> parameters) {
        String dataKey = (String) parameters.get("dataKey");
        String order = (String) parameters.get("order");
        int cursor = (Integer) parameters.get("cursor");
        int size = (Integer)parameters.get("size");
        
        String sql = "select * from APP_DATA"
        		+ " where data_key='" + dataKey + "'"
	        	+ " order by CAST(data_value as SIGNED) " + order
	        	+ " limit " + cursor +"," + size;
        
        return sql;
    }
	
	/**
	 * 获取道具信息时动态返回sql语句
	 * @param parameters
	 * @return
	 */
	public String getPropertySql(Map<String, Object> parameters){
		String appId = (String) parameters.get("appId");
		String priceOrder = (String)parameters.get("priceOrder");
		String pointsOrder = (String)parameters.get("pointsOrder");
		String type = (String)parameters.get("type");
		String sql = "SELECT PROPERTY_KEY AS propertyKey, PROPERTY_POINTS AS points, "
				+ "PROPERTY_NAME AS name, PROPERTY_PRICE AS price, "
				+ "PROPERTY_DESCRIPTION AS description, "
				+ "PROPERTY_STATE AS state,"
				+ "PROPERTY_PURCHASE_TYPE AS purchaseType, "
				+ "PROPERTY_ID AS propertyId, "
				+ "PROPERTY_APP_ID AS appId "
				+ "FROM v_property_product ";
		if(!appId.equals("ALL")){
			sql += "WHERE PROPERTY_APP_ID = #{appId}";
			if(!type.isEmpty()){
				sql +=" AND PROPERTY_PURCHASE_TYPE = #{type}";
			}
		}else{
			if(!type.isEmpty()){
				sql +=" WHERE PROPERTY_PURCHASE_TYPE = #{type}";
			}
		}
		if(!priceOrder.isEmpty()){
			sql += " ORDER BY PROPERTY_PRICE " + priceOrder ;
		}else if (!pointsOrder.isEmpty()){
			sql += " ORDER BY PROPERTY_POINTS " + pointsOrder;
		}
		return sql;
	}
}

