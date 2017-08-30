package com.thinkgem.jeesite.modules.stock.util;

import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Author: Changjiang Qing
 * Email: qcj@sungbio.com
 * Date: 2016/10/25
 */
public class CollectionUtils {
    public static  void removeEmptyKeyOfMap(Map<String, Object> jsonObject) {
        Iterator iter = jsonObject.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            Object val = jsonObject.get(key);
            if (val == null){
                iter.remove();
                continue;
            }
            if(val instanceof String && StringUtils.isEmpty((String)val)){
                iter.remove();
            }else  if ( val instanceof  Integer && StringUtils.isEmpty(String.valueOf(val))){
                iter.remove();
            }
        }
    }
}
