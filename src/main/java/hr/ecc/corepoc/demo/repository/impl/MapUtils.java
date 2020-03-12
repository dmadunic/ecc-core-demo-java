package hr.ecc.corepoc.demo.repository.impl;

import java.util.Map;

public class MapUtils {

    public static Long getNextLongId(Map<Long, ?> map) {
        long maxId = 0;
        for (Long id : map.keySet()) {
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId+1;
    }
}
