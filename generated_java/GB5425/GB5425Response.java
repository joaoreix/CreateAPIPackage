package ao.selenium.bkintegration.core.feature.GB5425;

import ao.selenium.bkintegration.core.bootstrapper.transaction.TransactionResponse;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "XMLGB5425")
public record GB5425Response(
        String cspcpgmc,
        String cspcttr,
        String cspcjobnc,
        String cspcuserc,
        String cspcpgmqc,
        String cspcrcode,
        String cspcestr,
        String cspcaptr,
        String cspchcli,
        String cspcvncr,
        String cspcsttr,
        String cspcnreg,
        String cspcpnome,
        String cspcbalc,
        String cspcncli,
        String cspcice01,
        String cspcconta,
        String cspcice02,
        String cspcnumt,
        String cspcnom1t,
        String cspcindep,
        String cspcrcli,
        String cspcucodu,
        String cspctppos,
        String cspctpcon,

        // Arrays - usando wrappers personalizados,
        @JacksonXmlProperty(localName = "cspcflgce")
        ArrayWrapper cspcflgce,
        @JacksonXmlProperty(localName = "cspcclcp")
        ArrayWrapper cspcclcp,
        @JacksonXmlProperty(localName = "cspcwrice")
        ArrayWrapper cspcwrice,
        @JacksonXmlProperty(localName = "cspcindex")
        ArrayWrapper cspcindex,
        @JacksonXmlProperty(localName = "cspccvlr")
        ArrayWrapper cspccvlr,
        @JacksonXmlProperty(localName = "cspcident")
        ArrayWrapper cspcident,
        @JacksonXmlProperty(localName = "cspcdesc")
        ArrayWrapper cspcdesc,
        @JacksonXmlProperty(localName = "cspcvalor")
        ArrayWrapper cspcvalor,
        @JacksonXmlProperty(localName = "cspcttrc")
        ArrayWrapper cspcttrc,
        @JacksonXmlProperty(localName = "cspcvsld")
        ArrayWrapper cspcvsld,
        @JacksonXmlProperty(localName = "cspcmoed")
        ArrayWrapper cspcmoed,
        @JacksonXmlProperty(localName = "cspctpsld")
        ArrayWrapper cspctpsld,
        @JacksonXmlProperty(localName = "cspccps")
        ArrayWrapper cspccps,
        @JacksonXmlProperty(localName = "cspcpgmcs")
        ArrayWrapper cspcpgmcs,
        @JacksonXmlProperty(localName = "cspcrefer")
        ArrayWrapper cspcrefer,
        @JacksonXmlProperty(localName = "cspcatrc")
        ArrayWrapper cspcatrc,
        @JacksonXmlProperty(localName = "cspcmasld")
        ArrayWrapper cspcmasld,
        @JacksonXmlProperty(localName = "cspctcrsl")
        ArrayWrapper cspctcrsl,
        List<String> messages
) implements TransactionResponse {
    public static record ArrayWrapper(
            @JacksonXmlElementWrapper(useWrapping = false)
            @JacksonXmlProperty(localName = "i")
            List<String> i
    ) {
        public List<String> getValues() {
            return i != null ? i : List.of();
        }
    }

    @Override
    public String getReturnCode() {
        return this.cspcrcode;
    }

    @Override
    public List<String> getMessages() {
        return this.messages;
    }
}