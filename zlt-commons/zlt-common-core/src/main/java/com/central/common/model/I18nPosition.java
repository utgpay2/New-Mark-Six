package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author lance
 * @date 2022-01-24 21:19:58
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("i18n_position")
public class I18nPosition extends SuperEntity {
    private static final long serialVersionUID=1L;

        private String name;
        private Integer type;
        private Long pid;
    }
