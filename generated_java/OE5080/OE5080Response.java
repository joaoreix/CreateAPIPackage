package ao.selenium.bkintegration.core.feature.OE5080;

import ao.selenium.bkintegration.core.bootstrapper.transaction.TransactionResponse;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "XMLOE5080")
public record OE5080Response(
        String eopgpgmc,
        String eopgttr,
        String eopgjobnc,
        String eopguserc,
        String eopgpgmqc,
        String eopgrcode,
        String eopgestr,
        String eopgaptr,
        String eopghcli,
        String eopgbalm,
        String eopgmoec,
        String eopgtopr,
        String eopgice01,
        String eopgvlr1,
        String eopgice02,
        String eopgmoedv,
        String eopgice03,
        String eopgmoei,
        String eopgcamb,
        String eopgcmbi,
        String eopgcmbn,
        String eopgice44,
        String eopgtemi,
        String eopgice04,
        String eopgrcli,
        String eopgice05,
        String eopgropr,
        String eopgice06,
        String eopgncli,
        String eopgice07,
        String eopgcnto,
        String eopgice08,
        String eopgbalc,
        String eopgccb,
        String eopgice09,
        String eopgdmovm,
        String eopgice43,
        String eopgdtvc,
        String eopgice10,
        String eopgdes1,
        String eopgice11,
        String eopgmoed1,
        String eopgice38,
        String eopgdmv1,
        String eopgcoc1,
        String eopgice12,
        String eopgdes2,
        String eopgice13,
        String eopgmoed2,
        String eopgice39,
        String eopgdmv2,
        String eopgcoc2,
        String eopgice14,
        String eopgbncoo,
        String eopgice15,
        String eopgccnt,
        String eopgice16,
        String eopgbande,
        String eopgice17,
        String eopgbanbe,
        String eopgice35,
        String eopgibenf,
        String eopgice18,
        String eopgiorde,
        String eopgcnta,
        String eopgice19,
        String eopgbncob,
        String eopgice20,
        String eopgdpag,
        String eopgice21,
        String eopgdgas,
        String eopgice22,
        String eopgmsgo,
        String eopgice23,
        String eopgcest,
        String eopgice24,
        String eopgpais,
        String eopgice25,
        String eopglcre,
        String eopgice26,
        String eopgaubc,
        String eopgice27,
        String eopgdoca,
        String eopgice28,
        String eopgcnot,
        String eopgice29,
        String eopgnumtn,
        String eopgice30,
        String eopgbann,
        String eopgice31,
        String eopgautr,
        String eopgice32,
        String eopgvlri1,
        String eopgice33,
        String eopgtmov,
        String eopgvlro,
        String eopgvlre,
        String eopgnauti,
        String eopgnopr,
        String eopgctv,
        String eopgconf,
        String eopgice34,
        String eopgtpnt,
        String eopgdesc,
        String eopgvtim,
        String eopgvtco,
        String eopgvtde,
        String eopgvtou,
        String eopgvtd,
        String eopgvtim2,
        String eopgvtco2,
        String eopgvtde2,
        String eopgvtou2,
        String eopgvtd2,
        String eopganlp,
        String eopgice36,
        String eopgproc,
        String eopgestca,
        String eopgbyps,
        String eopgnord,
        String eopgcope,
        String eopgndoc,
        String eopgdtvcr,
        String eopgice37,
        String eopgstrdf,
        String eopgcntd,
        String eopgice40,
        String eopgbalcd,
        String eopgccbd,
        String eopgice41,
        String eopgmoedd,
        String eopgice42,
        String eopgporde,
        String eopgice55,
        String eopgpbene,
        String eopgcido,
        String eopgice56,
        String eopgcidb,
        String eopgice57,
        String eopgmoedc,
        String eopgmoepo,
        String eopgmoepd,
        String eopgidmsg,
        String eopgspclc,
        String eopgclcod,
        String eopgice58,
        String eopgclmid,
        String eopgice59,

        // Arrays - usando wrappers personalizados,
        @JacksonXmlProperty(localName = "eopgcban")
        ArrayWrapper eopgcban,
        @JacksonXmlProperty(localName = "eopgspai")
        ArrayWrapper eopgspai,
        @JacksonXmlProperty(localName = "eopgrcele")
        ArrayWrapper eopgrcele,
        @JacksonXmlProperty(localName = "eopgrvlre")
        ArrayWrapper eopgrvlre,
        @JacksonXmlProperty(localName = "eopgricee")
        ArrayWrapper eopgricee,
        @JacksonXmlProperty(localName = "eopgvlpre")
        ArrayWrapper eopgvlpre,
        @JacksonXmlProperty(localName = "eopgdmpre")
        ArrayWrapper eopgdmpre,
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
        return this.eopgrcode;
    }

    @Override
    public List<String> getMessages() {
        return this.messages;
    }
}