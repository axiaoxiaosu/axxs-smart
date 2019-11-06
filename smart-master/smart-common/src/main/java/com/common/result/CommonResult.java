package com.common.result;

import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 默认分页返回
 *
 * @author lwq
 * @data 2019/8/30
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CommonResult<T> extends Msg {

    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 每页的数量
     */
    private Integer pageSize;
    /**
     * 共多少页
     */
    private Long totalPage;
    /**
     * 数据量
     */
    private Integer total;
    /**
     * 返回数据
     */
    private List<T> data;

    public CommonResult(List<T> resultList) {
        PageInfo<T> pageInfo = new PageInfo<T>(resultList);
        this.data = pageInfo.getList();
        this.totalPage = pageInfo.getTotal();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.total = resultList.size();
    }

}