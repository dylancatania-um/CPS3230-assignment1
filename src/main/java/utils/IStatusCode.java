package utils;

import javax.swing.plaf.PanelUI;

public interface IStatusCode {
    public static int OK = 200;
    public static int CREATED = 201;
    public static int BAD_REQUEST = 400;
    public static int UNSUPPORTED_MEDIA_TYPE = 415;
    public static int INTERNAL_SERVER_ERROR = 500;
    public static int SERVICE_UNAVAILABLE = 503;
    public int getStatusCode();
}
