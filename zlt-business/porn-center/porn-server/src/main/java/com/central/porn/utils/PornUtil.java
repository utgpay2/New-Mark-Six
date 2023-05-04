package com.central.porn.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.central.common.constant.PornConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PornUtil {

    /**
     * 关键词搜索影片
     * @param sid 站点id
     * @param keywords 搜索关键词
     * @return
     */
    public static List<Long> searchByKeywords(Long sid, String keywords) {
        List<Long> resultMovieIds = new ArrayList<>();
        List<Long> movieIds = PornConstants.LocalCache.LOCAL_MAP_SITE_MOVIE_IDS.get(sid);
        for (Long movieId : movieIds) {
            String[] movieNameArr = PornConstants.LocalCache.LOCAL_MAP_MOVIE_NAME.get(movieId);
            if(ArrayUtil.isNotEmpty(movieNameArr)){
                for (String movieName : movieNameArr) {
                    if (StrUtil.isNotBlank(movieName) && movieName.contains(keywords)) {
                        if(resultMovieIds.size()>=500){
                            return resultMovieIds;
                        }
                        resultMovieIds.add(movieId);
                        break;
                    }
                }
            }
        }
        return resultMovieIds;
    }

    /**
     * 判断bigdecimal 是否大于 0
     */
    public static boolean isDecimalBigThanZero(BigDecimal bigDecimal) {
        return bigDecimal.compareTo(BigDecimal.ZERO) >= 1;
    }

    /**
     * source >= target
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isDecimalGeThan(BigDecimal source, BigDecimal target) {
        return source.compareTo(target) >= 0;
    }
}
