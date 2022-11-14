package utils;

import javax.swing.plaf.PanelUI;

public interface IStatusCode {
    public static int OK = 200;
    public static int CREATED = 201;
    public static int BAD_REQUEST = 400;
    public static int NOT_FOUND = 404;
    public static int METHOD_NOT_ALLOWED = 405;
    public static int REQUEST_TIMEOUT = 408;
    public static int LENGTH_REQUIRED = 411;
    public static int URI_TOO_LONG = 414;
    public static int UNSUPPORTED_MEDIA_TYPE = 415;
    public static int INTERNAL_SERVER_ERROR = 500;
    public static int NOT_IMPLEMENTED = 501;
    public static int BAD_GATEWAY = 502;
    public static int SERVICE_UNAVAILABLE = 503;
    public int getStatusCode();
}
