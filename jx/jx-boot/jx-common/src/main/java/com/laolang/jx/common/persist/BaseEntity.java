package com.laolang.jx.common.persist;

import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后修改人
     */
    private Long updateBy;

    /**
     * 最后修改时间
     */
    private LocalDateTime updateTime;

    private String remark;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;
}
