package cn.luxu.springsecurityjwt.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {
    private Integer code;
    private String msg;
    private T date;
}
