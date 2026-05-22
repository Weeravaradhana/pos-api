package com.devapp.pos.dto.response.paginate;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class PageResponseDto <T>{
    private long dataCount;
    private List<T> dataList;
}
