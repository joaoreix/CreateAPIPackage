package ao.selenium.bkintegration.core.feature.GB5425;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public record GB5425Request(
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
        @JacksonXmlElementWrapper(localName = "cspcflgce")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcflgce,
        @JacksonXmlElementWrapper(localName = "cspcclcp")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcclcp,
        @JacksonXmlElementWrapper(localName = "cspcwrice")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcwrice,
        @JacksonXmlElementWrapper(localName = "cspcindex")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcindex,
        @JacksonXmlElementWrapper(localName = "cspccvlr")
        @JacksonXmlProperty(localName = "item")
        List<String> cspccvlr,
        @JacksonXmlElementWrapper(localName = "cspcident")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcident,
        @JacksonXmlElementWrapper(localName = "cspcdesc")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcdesc,
        @JacksonXmlElementWrapper(localName = "cspcvalor")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcvalor,
        @JacksonXmlElementWrapper(localName = "cspcttrc")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcttrc,
        @JacksonXmlElementWrapper(localName = "cspcvsld")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcvsld,
        @JacksonXmlElementWrapper(localName = "cspcmoed")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcmoed,
        @JacksonXmlElementWrapper(localName = "cspctpsld")
        @JacksonXmlProperty(localName = "item")
        List<String> cspctpsld,
        @JacksonXmlElementWrapper(localName = "cspccps")
        @JacksonXmlProperty(localName = "item")
        List<String> cspccps,
        @JacksonXmlElementWrapper(localName = "cspcpgmcs")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcpgmcs,
        @JacksonXmlElementWrapper(localName = "cspcrefer")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcrefer,
        @JacksonXmlElementWrapper(localName = "cspcatrc")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcatrc,
        @JacksonXmlElementWrapper(localName = "cspcmasld")
        @JacksonXmlProperty(localName = "item")
        List<String> cspcmasld,
        @JacksonXmlElementWrapper(localName = "cspctcrsl")
        @JacksonXmlProperty(localName = "item")
        List<String> cspctcrsl
) {}