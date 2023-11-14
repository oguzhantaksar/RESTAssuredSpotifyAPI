package Reusables;

public enum StatusCodeMsg {
    CODE_200(200,""),
    CODE_100(201,""),
    CODE_400(400,"Missing required field: name"),
    CODE_401(401, "Invalid access token");

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private final int code;
    private final String msg;

    StatusCodeMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
