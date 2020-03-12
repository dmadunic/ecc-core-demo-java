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

    // ------------------------------------------------------------------------

    public Country name(String name) {
        this.name = name;
        return this;
    }

    public Country code(String code) {
        this.code = code;
        return this;
    }

    public Country codeA2(String codeA2) {
        this.codeA2 = codeA2;
        return this;
    }

    public Country codeA3(String codeA3) {
        this.codeA3 = codeA3;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        return id != null && id.equals(((Country) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", code='" + getCode() + "'" +
                ", codeA2='" + getCodeA2() + "'" +
                ", codeA3='" + getCodeA3() + "'" +
                "}";
    }
}
