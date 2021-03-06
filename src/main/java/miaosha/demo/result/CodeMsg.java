package miaosha.demo.result;

public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(100100,"服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(100101,"参数校验异常:%s");

    //登录模块
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(100201,"登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(100202,"手机号码不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(100203,"手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(100204,"手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(100204,"密码错误");
    //商品模块

    //订单模块

    //秒杀模块

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
}
