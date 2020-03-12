package hr.ecc.corepoc.demo.domain;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Size(max = 4)
    private String code;

    @NotNull
    @Size(max = 2)
    private String codeA2;

    @NotNull
    @Size(max = 3)
    private String codeA3;

    //--- set / get methods ---------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeA2() {
        return codeA2;
    }

    public void setCodeA2(String codeA2) {
        this.codeA2 = codeA2;
    }

    public String getCodeA3() {
        return codeA3;
    }

    public void setCodeA3(String codeA3) {
        this.codeA3 = codeA3;
    }
}
