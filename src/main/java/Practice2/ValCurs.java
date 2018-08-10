package Practice2;

import java.util.Arrays;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ValCurs")
public class ValCurs {
    @XStreamAlias("Date")
    @XStreamAsAttribute
    private String date;
    @XStreamAsAttribute
    private String name;
    @XStreamImplicit
    private Valute[] valute;

    public ValCurs() {
    }

    public ValCurs(String date, String name, Valute[] valute) {
        this.date = date;
        this.name = name;
        this.valute = valute;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Valute[] getValute() {
        return valute;
    }

    public void setValute(Valute[] valute) {
        this.valute = valute;
    }
}
