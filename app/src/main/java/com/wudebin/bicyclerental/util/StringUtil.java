package com.wudebin.bicyclerental.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wudebin on 2015/4/13.
 */
public class StringUtil {

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public static boolean isPassword(String password)
    {
        if(6<=password.length()&&password.length()<=16)
        {
            return  true;
        }
        return false;
    }
}
