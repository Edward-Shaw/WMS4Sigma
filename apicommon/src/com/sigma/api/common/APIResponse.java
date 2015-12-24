package com.sigma.api.common;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties("appPrivateKey")
public class APIResponse {

	private int code;
	private String message;
	
	private Object result;
	
	private String appPrivateKey = null;

	public APIResponse(){}
	
	/**
	 * 
	 * @param code
	 * @param body
	 * @param appPrivateKey 这个参数绝对不能为空, 必须使用真实的privateKey, 否则无法对回复进行签名
	 * @throws Exception 
	 */
	public APIResponse(int code, Object result, String appPrivateKey){
		this.code = code;
		this.result = result;
		this.appPrivateKey = appPrivateKey;
	}
	
	public APIResponse(int code, Object result){
		this.code = code;
		this.result = result;
	}
	
	public APIResponse(int code){
		this.code = code;
		this.result = null;
	}

	public Object getResult(){
		return this.result;
	}

	public String getAppPrivateKey() {
		return appPrivateKey;
	}

/*	public void setCpPrivateKey(String appPrivateKey) {
		this.appPrivateKey = appPrivateKey;
	}*/
		
	public int getCode() {
		return code;
	}

	public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/** 操作成功 */
    public static final int RC_SUCCESS = 0;
    public static final int RC_OK = RC_SUCCESS;
    
    /** 道具不存在 */
    public static final int RC_PROPERTY_NOT_EXSIST = 10001;
    /** 用户不存在 */
    public static final int RC_USER_NOT_EXSIST = 10002;

    public static final int RC_ORDER_EXPIRED = 10003;
    
    /** 该道具不能用喵币购买 */
    public static final int RC_COINS_NOT_AVAILABLE =10004;
    /** 用户没有积分和喵币 */
    public static final int RC_USER_NO_MONEY = 10005;
    /** 积分不足 */
    public static final int RC_NO_ENOUGH_POINTS = 10006;
    /** 喵币不足 */
    public static final int RC_NO_ENOUGH_COINS = 10007;
    /** 参数不足  */
    public static final int RC_NO_ENOUGH_ARGUMENTS = 10008;
    /** 该道具不可消耗  购买数不能大于1*/
    public static final int RC_PROPERTY_UNCONSUMABLE = 10009;
   
    /** 程序内部错误 */
    public static final int RC_INTERNAL_ERROR = 10099;
    /**app不存在*/
    public static final int RC_APP_NOT_EXSIST = 10010;
    /**dataKey为空*/
    public static final int RC_DATAKEY_IS_EMPTY = 10011;
    @Deprecated
    /**dataValue不能转为数字*/
    public static final int RC_DATAVALUE_NOT_NUMBER = 10012;
    /**参数不为数字    */
    public static final int RC_NOT_NUMBER = 10012; 
    @Deprecated
    /**计算后的dataValue小于0*/
    public static final int RC_DATAVALUE_LESS_ZERO = 10013;
    /**参数小于0*/
    public static final int RC_LESS_ZERO = 10013;
    /** 该道具不可消耗 并且已经购买过*/
    public static final int RC_ALREADY_PURCHASE = 10014;
    
    /** 充值喵币数不能被10整除*/
    public static final int RC_BAD_NUMBER = 10015;
    
    /**插入购买记录不成功*/
    public static final int RC_INSERT_UNSUCCESS = 10016;
    /**dataValue为空    */
    public static final int RC_DATAVALUE_IS_EMPTY = 10017;   
    /**ext用户绑定cap出错    */
    public static final int RC_EXT_BIND_ERROR = 10018;  
    /**ext用户不存在    */
    public static final int RC_EXT_USER_NOT_EXIST = 10019;
    /**传入的public key不存在    */
    public static final int RC_PUBLIC_KEY_NOT_EXIST = 10020;
    /**spid或spkey配置不正确    */
    public static final int RC_SP_ID_OR_SP_KEY_ERROR = 10021;
    /**访问出错    */
    public static final int RC_REQUEST_FAIL = 10022;
    
    /**数据库更新失败    */
    public static final int RC_UPDATE_FAIL = 10501; 
    
    /**data key项无检索结果    */
    @Deprecated public static final int RC_DATA_KEY_NO_RESULT = 10023;
    public static final int RC_DATA_KEY_NOT_EXISTS = 10023;
    
    /** 购买成功 */
    /**
     * 用RC_SUCCESS或者RC_OK代替
     */
    @Deprecated public static final int RC_PURCHASE_SUCCESS = 0;
    
    /** 更新成功*/
    /**
     * 用RC_SUCCESS或者RC_OK代替
     */
    @Deprecated public static final int RC_SAVE_SUCCESS = 0;
    
    /**
     * 请求参数不合法或者有问题
     */
    public static final int RC_BAD_REQUEST = 10100;
    
      //整理后的api response
//    /** 操作成功 */
//    public static final int RC_SUCCESS = 0;
//    public static final int RC_OK = RC_SUCCESS;
//    
//    /** 道具不存在 */
//    public static final int RC_PROPERTY_NOT_EXSIST = 10001;
//    /** 该道具不能用喵币购买 */
//    public static final int RC_COINS_NOT_AVAILABLE = 10004;
//    /** 喵币不足 */
//    public static final int RC_NO_ENOUGH_COINS = 10007;
//    /** 充值喵币数不能被10整除*/
//    public static final int RC_NOT_ALIQUOT_TEN = 10015;  
//    /** 不可消耗道具已购买*/
//    public static final int RC_PROPERTY_UNCONSUMABLE = 10009;
//    public static final int RC_ALREADY_PURCHASE = RC_PROPERTY_UNCONSUMABLE;
//    
//    /** 用户不存在 */
//    public static final int RC_USER_NOT_EXSIST = 10002;
//    /** ext用户不存在    */
//    public static final int RC_EXT_USER_NOT_EXIST = 10019;
//    /**ext用户绑定cap出错    */
//    public static final int RC_EXT_BIND_ERROR = 10018;  
//    /**spid或spkey配置不正确    */
//    public static final int RC_SP_ID_OR_SP_KEY_ERROR = 10021;
//    
//    /** 传入的public key不存在    */
//    public static final int RC_PUBLIC_KEY_NOT_EXIST = 10020;
//    /**app不存在*/
//    public static final int RC_APP_NOT_EXSIST = RC_PUBLIC_KEY_NOT_EXIST;
//    
//    /**dataKey为空*/
//    public static final int RC_DATAKEY_IS_EMPTY = 10011;
//    /**参数不为数字    */
//    public static final int RC_NOT_NUMBER = 10012; 
//    /**dataValue为空    */
//    public static final int RC_DATAVALUE_IS_EMPTY = 10017;   
//    /**data key项无检索结果    */
//    public static final int RC_DATA_KEY_NOT_EXISTS = 10201;
//    
//    /**数据库更新失败    */
//    public static final int RC_UPDATE_FAIL = 10501; 
//    /**插入购买记录不成功*/
//    public static final int RC_INSERT_UNSUCCESS = RC_UPDATE_FAIL;
//        
//    /**访问出错    */
//    public static final int RC_REQUEST_FAIL = 10200;

    
    ///messages
    private static Map<Integer, String> messages = null;
    
    public static String getMessage(int code){
    	if(messages == null){
    		messages = new HashMap<Integer, String>();
    		
    		messages.put(RC_PROPERTY_NOT_EXSIST, "道具不存在");
    	    messages.put(RC_USER_NOT_EXSIST, "用户不存在");
    	    messages.put(RC_ORDER_EXPIRED, "订单已过期");
    	    messages.put(RC_COINS_NOT_AVAILABLE, "该道具不可用喵币购买");
    	    messages.put(RC_USER_NO_MONEY, "该用户没有喵币或积分");
    	    messages.put(RC_NO_ENOUGH_POINTS, "积分不足");
    	    messages.put(RC_NO_ENOUGH_COINS, "喵币不足");
    	    messages.put(RC_NO_ENOUGH_ARGUMENTS, "参数不全");
    	    messages.put(RC_PROPERTY_UNCONSUMABLE, "该道具不可消耗 购买数不能大于1");
    	    messages.put(RC_INTERNAL_ERROR, "程序错误");
    	    messages.put(RC_APP_NOT_EXSIST, "App不存在");
    	    messages.put(RC_DATAKEY_IS_EMPTY, "dataKey为空");
    	    messages.put(RC_DATAVALUE_NOT_NUMBER, "参数值不能转为数字");
    	    messages.put(RC_NOT_NUMBER, "参数值不能转为数字");
    	    messages.put(RC_DATAVALUE_LESS_ZERO, "参数值小于0");
    	    messages.put(RC_LESS_ZERO, "参数值小于0");
    	    messages.put(RC_ALREADY_PURCHASE, "该道具不可消耗 并且已经购买过");
    	    messages.put(RC_BAD_NUMBER, "充值数不能被十整数");
    	    messages.put(RC_INSERT_UNSUCCESS, "插入购买记录不成功");
    	    messages.put(RC_DATAVALUE_IS_EMPTY, "datavalue为空");
    	    messages.put(RC_BAD_REQUEST , "请求不合法");
    	    messages.put(RC_SUCCESS, "成功");
    	    messages.put(RC_DATA_KEY_NOT_EXISTS, "data key项无检索结果");
    	    messages.put(RC_REQUEST_FAIL, "很抱歉，您的访问出错了");
    	    messages.put(RC_EXT_BIND_ERROR, " ext用户绑定cap出错");	
    	    messages.put(RC_EXT_USER_NOT_EXIST, " ext用户不存在");
    	    messages.put(RC_PUBLIC_KEY_NOT_EXIST, " 传入的public key不存在");
    	    messages.put(RC_SP_ID_OR_SP_KEY_ERROR, " spid或spkey配置不正确 ");
    	    
    	    messages.put(RC_UPDATE_FAIL , "数据库更新失败");
    	    
    	    ///以下为API请求头校验相关的消息
    	    messages.put(RC_BAD_REQUEST + 1,	"随机字符串无效");
    	    messages.put(RC_BAD_REQUEST + 2,	"时间戳无效");
    	    messages.put(RC_BAD_REQUEST + 3,	"cap-public-key或者cap-user-id无效");
    	    messages.put(RC_BAD_REQUEST + 4,	"指定的游戏不存在或密钥无效");
    	    messages.put(RC_BAD_REQUEST + 5,	"签名无效");
    	    messages.put(RC_BAD_REQUEST + 6,    "header参数不全");
    	    
            ///以下为iptv充值请求可能返回的错误信息
            messages.put(RC_BAD_REQUEST + 101,    "[上海IPTV]充值余额不可用");
            messages.put(RC_BAD_REQUEST + 102,    "[上海IPTV]UserID和UserToken验证错误");
            messages.put(RC_BAD_REQUEST + 103,    "[上海IPTV]MD5校验错误");
            messages.put(RC_BAD_REQUEST + 104,    "[上海IPTV]SP不存在");
            messages.put(RC_BAD_REQUEST + 105,    "[上海IPTV]游戏不存在");        
            messages.put(RC_BAD_REQUEST + 106,    "[上海IPTV]接口版本不可用");
            messages.put(RC_BAD_REQUEST + 107,    "[上海IPTV]参数错误");
            messages.put(RC_BAD_REQUEST + 108,    "[上海IPTV]用户充值已达充值限额");
            messages.put(RC_BAD_REQUEST + 109,    "[上海IPTV]该用户未登录过游戏大厅！");
            messages.put(RC_BAD_REQUEST + 199,    "[上海IPTV]系统内部错误");
            messages.put(RC_BAD_REQUEST + 111,    "[上海IPTV]充值请求返回值为空");
            messages.put(RC_BAD_REQUEST + 110,    "[上海IPTV]userToken不存在");
            messages.put(RC_BAD_REQUEST + 112,    "电信充值请求失败");
            messages.put(RC_BAD_REQUEST + 113,    "无法获得充值金额");
            messages.put(RC_BAD_REQUEST + 114,    "无法获得消费 gameid");
            messages.put(RC_BAD_REQUEST + 115,    "检测是否需要二次确认时发生错误");
            messages.put(RC_BAD_REQUEST + 116,    "需要二次确认");
            
            ///以下为iptv购买请求可能返回的错误信息
            messages.put(RC_BAD_REQUEST + 201,    "[上海IPTV]用户余额不足");
            messages.put(RC_BAD_REQUEST + 202,    "[上海IPTV]用户为黑名单不允许消费");
            messages.put(RC_BAD_REQUEST + 203,    "[上海IPTV]MD5校验错误");
            messages.put(RC_BAD_REQUEST + 204,    "[上海IPTV]SP不存在");
            messages.put(RC_BAD_REQUEST + 205,    "[上海IPTV]游戏不存在");        
            messages.put(RC_BAD_REQUEST + 206,    "[上海IPTV]接口版本不可用");
            messages.put(RC_BAD_REQUEST + 207,    "[上海IPTV]参数错误");
            messages.put(RC_BAD_REQUEST + 208,    "[上海IPTV]订单号已存在");
            messages.put(RC_BAD_REQUEST + 210,    "[上海IPTV]userToken不存在");
            messages.put(RC_BAD_REQUEST + 298,    "[上海IPTV]购买接口无返回");
            messages.put(RC_BAD_REQUEST + 299,    "[上海IPTV]系统内部错误");
            messages.put(RC_BAD_REQUEST + 211,    "[上海IPTV]消费请求返回值为空");
            messages.put(RC_BAD_REQUEST + 212,    "[上海IPTV]消费请求失败");
            messages.put(RC_BAD_REQUEST + 213,    "[上海IPTV]无法获得消费金额");
            messages.put(RC_BAD_REQUEST + 214,    "无法获得消费订单");
            messages.put(RC_BAD_REQUEST + 215,    "无法获得消费 gameid");
            messages.put(RC_BAD_REQUEST + 216,    "无法获得消费记录返回地址");
            
            ///以下为app data controller中获取，更新data, 获取排行榜可能返回的错误信息
            messages.put(RC_BAD_REQUEST + 301,    "所请求的key没有对应的value");
            messages.put(RC_BAD_REQUEST + 302,    "app或user不存在");
            messages.put(RC_BAD_REQUEST + 303,    "data key或data value为空");
            
            //以下为sync中，可能出现的错误信息
            messages.put(RC_BAD_REQUEST + 401,    "[天飞]当前用户不存在");
            messages.put(RC_BAD_REQUEST + 402,    "[天飞]coin或coinAll或money为空");           
            
            //以下为华数充值购买中，可能出现的错误信息
            messages.put(RC_BAD_REQUEST + 501,    "[WASU]账号不存在");
            messages.put(RC_BAD_REQUEST + 502,    "[WASU]其他错误");
            messages.put(RC_BAD_REQUEST + 503,    "[WASU]参数异常");
            messages.put(RC_BAD_REQUEST + 504,    "[WASU]达家长管理月限额");
            messages.put(RC_BAD_REQUEST + 505,    "[WASU]达家长管理日限额");
            messages.put(RC_BAD_REQUEST + 506,    "[WASU]该账户已被列入黑名单");
            messages.put(RC_BAD_REQUEST + 507,    "[WASU]充值金额为空");
            messages.put(RC_BAD_REQUEST + 508,    "[WASU]billing auth 地址不正确");
            messages.put(RC_BAD_REQUEST + 509,    "[WASU]cpCode 不正确");
            messages.put(RC_BAD_REQUEST + 510,    "[WASU]gameId不正确");
            messages.put(RC_BAD_REQUEST + 511,    "[WASU]userId不正确");
            messages.put(RC_BAD_REQUEST + 512,    "[WASU]充值金额不为整数");
            messages.put(RC_BAD_REQUEST + 512,    "[WASU]action Id 不存在");
            messages.put(RC_BAD_REQUEST + 513,    "[WASU]二次确认过程发生错误");
            messages.put(RC_BAD_REQUEST + 514,    "[WASU]SpServiceIP 不正确");
            messages.put(RC_BAD_REQUEST + 515,    "[WASU]SpServicePort 不正确");
            messages.put(RC_BAD_REQUEST + 516,    "[WASU]cpcode 不正确");
            messages.put(RC_BAD_REQUEST + 517,    "[WASU]action 不正确");
            messages.put(RC_BAD_REQUEST + 519,    "[WASU]现金支付出错");
            messages.put(RC_BAD_REQUEST + 520,    "[WASU]达到限额");
            messages.put(RC_BAD_REQUEST + 521,    "[WASU]口令错误");
            messages.put(RC_BAD_REQUEST + 522,    "[WASU]订购产品（编号）不存在");
            messages.put(RC_BAD_REQUEST + 523,    "[WASU]机顶盒停机");
            messages.put(RC_BAD_REQUEST + 524,    "[WASU]帐号不存在");
            messages.put(RC_BAD_REQUEST + 525,    "[WASU]大厅不存在");
            messages.put(RC_BAD_REQUEST + 526,    "[WASU]扣费金额(PPVID)不存在");
            messages.put(RC_BAD_REQUEST + 527,    "[WASU]充值达到日限");
            messages.put(RC_BAD_REQUEST + 528,    "[WASU]充值达到月限");
            messages.put(RC_BAD_REQUEST + 529,    "[WASU]");
            messages.put(RC_BAD_REQUEST + 530,    "[WASU]BOSS返回扣费失败");
            messages.put(RC_BAD_REQUEST + 531,    "[WASU]与BOSS连接建立失败");
            messages.put(RC_BAD_REQUEST + 532,    "[WASU]");
            messages.put(RC_BAD_REQUEST + 533,    "[WASU]扣费行为编号不存在");
            messages.put(RC_BAD_REQUEST + 534,    "[WASU]禁止消费");
            messages.put(RC_BAD_REQUEST + 535,    "[WASU]限额提醒");
            messages.put(RC_BAD_REQUEST + 536,    "[WASU]消费达家长控制月限额");
            messages.put(RC_BAD_REQUEST + 537,    "[WASU]消费达家长控制日限额");
            messages.put(RC_BAD_REQUEST + 538,    "[WASU]该账户已被列入黑名单");
           
    	}
    	
    	String message = messages.get(code);
    	
    	return message == null ? "指定的错误码不存在" : message;
    }
}
