package miaosha.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ValidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");//1后面跟着10位数字

    public static boolean isMobile(String src){
        if(StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }
/*
    public static void main(String[] args) {
        System.out.println(isMobile("18812341234"));
        System.out.println(isMobile("28812341234"));
    }*/
}
