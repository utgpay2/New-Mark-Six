package com.central.backend.model.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

/**
 * 分页对象
 * */
@Data
public class PageResultVO<T>  implements Serializable {
    private static final long serialVersionUID = -300978954545179L;
    /** 页码，默认是第一页 */
    private Integer number = 1;
    /** 每页显示的记录数，默认是20 */
    private Integer size = 10;
    /** 总记录数 */
    private Long totalElements;
    /** 总页数 */
    private Integer totalPages;
    private List<?> content;

    public PageResultVO() {

    }

    public PageResultVO(Integer number, Integer size, Long totalElements, List<?> content) {
        this.number = number;
        this.size = size;
        this.totalElements = totalElements;
        this.setContent(content);
        Long totalPage = totalElements % size == 0 ? totalElements / size : totalElements / size + 1;
        this.setTotalPages(totalPage.intValue());
    }
    public PageResultVO(Integer number, Integer size, Long totalElements, Integer totalPage, List<?> content) {
        this.number = number;
        this.size = size;
        this.totalElements = totalElements;
        this.setContent(content);
        this.setTotalPages(totalPage);
    }
    public PageResultVO(Page<T> pageage) {
        this.number = pageage.getNumber();
        this.size = pageage.getSize();
        this.totalElements = pageage.getTotalElements();
        this.totalPages = pageage.getTotalPages();
    }
    public PageResultVO(PageResultVO<T> pageResultVO) {
        this.number = pageResultVO.getNumber();
        this.size = pageResultVO.getSize();
        this.totalElements = pageResultVO.getTotalElements();
        this.totalPages = pageResultVO.getTotalPages();
    }
}
