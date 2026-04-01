package ao.selenium.bkintegration.core.feature.OE5080;

import ao.selenium.bkintegration.core.bootstrapper.transaction.AbstractTransactionHandler;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OE5080Handler extends AbstractTransactionHandler<OE5080Request, OE5080Response> {

    @Override
    public String getTransactionCode() {
        return "OE5080";
    }

    @Override
    public Class<OE5080Request> getRequestType() {
        return OE5080Request.class;
    }

    @Override
    protected Class<OE5080Response> getResponseType() {
        return OE5080Response.class;
    }

    @Override
    protected String getXmlRootElementName() {
        return "XMLOE5080";
    }

    @Override
    public String buildRequestPayload(OE5080Request request) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<XMLOE5080>");
        xmlBuilder.append(createXmlTag("eopgpgmc",request.eopgpgmc()));
        xmlBuilder.append(createXmlTag("eopgttr",request.eopgttr()));
        xmlBuilder.append(createXmlTag("eopgjobnc",request.eopgjobnc()));
        xmlBuilder.append(createXmlTag("eopguserc",request.eopguserc()));
        xmlBuilder.append(createXmlTag("eopgpgmqc",request.eopgpgmqc()));
        xmlBuilder.append(createXmlTag("eopgrcode",request.eopgrcode()));
        xmlBuilder.append(createXmlTag("eopgestr",request.eopgestr()));
        xmlBuilder.append(createXmlTag("eopgaptr",request.eopgaptr()));
        xmlBuilder.append(createXmlTag("eopghcli",request.eopghcli()));
        xmlBuilder.append(createXmlTag("eopgbalm",request.eopgbalm()));
        xmlBuilder.append(createXmlTag("eopgmoec",request.eopgmoec()));
        xmlBuilder.append(createXmlTag("eopgtopr",request.eopgtopr()));
        xmlBuilder.append(createXmlTag("eopgice01",request.eopgice01()));
        xmlBuilder.append(createXmlTag("eopgvlr1",request.eopgvlr1()));
        xmlBuilder.append(createXmlTag("eopgice02",request.eopgice02()));
        xmlBuilder.append(createXmlTag("eopgmoedv",request.eopgmoedv()));
        xmlBuilder.append(createXmlTag("eopgice03",request.eopgice03()));
        xmlBuilder.append(createXmlTag("eopgmoei",request.eopgmoei()));
        xmlBuilder.append(createXmlTag("eopgcamb",request.eopgcamb()));
        xmlBuilder.append(createXmlTag("eopgcmbi",request.eopgcmbi()));
        xmlBuilder.append(createXmlTag("eopgcmbn",request.eopgcmbn()));
        xmlBuilder.append(createXmlTag("eopgice44",request.eopgice44()));
        xmlBuilder.append(createXmlTag("eopgtemi",request.eopgtemi()));
        xmlBuilder.append(createXmlTag("eopgice04",request.eopgice04()));
        xmlBuilder.append(createXmlTag("eopgrcli",request.eopgrcli()));
        xmlBuilder.append(createXmlTag("eopgice05",request.eopgice05()));
        xmlBuilder.append(createXmlTag("eopgropr",request.eopgropr()));
        xmlBuilder.append(createXmlTag("eopgice06",request.eopgice06()));
        xmlBuilder.append(createXmlTag("eopgncli",request.eopgncli()));
        xmlBuilder.append(createXmlTag("eopgice07",request.eopgice07()));
        xmlBuilder.append(createXmlTag("eopgcnto",request.eopgcnto()));
        xmlBuilder.append(createXmlTag("eopgice08",request.eopgice08()));
        xmlBuilder.append(createXmlTag("eopgbalc",request.eopgbalc()));
        xmlBuilder.append(createXmlTag("eopgccb",request.eopgccb()));
        xmlBuilder.append(createXmlTag("eopgice09",request.eopgice09()));
        xmlBuilder.append(createXmlTag("eopgdmovm",request.eopgdmovm()));
        xmlBuilder.append(createXmlTag("eopgice43",request.eopgice43()));
        xmlBuilder.append(createXmlTag("eopgdtvc",request.eopgdtvc()));
        xmlBuilder.append(createXmlTag("eopgice10",request.eopgice10()));
        xmlBuilder.append(createXmlTag("eopgdes1",request.eopgdes1()));
        xmlBuilder.append(createXmlTag("eopgice11",request.eopgice11()));
        xmlBuilder.append(createXmlTag("eopgmoed1",request.eopgmoed1()));
        xmlBuilder.append(createXmlTag("eopgice38",request.eopgice38()));
        xmlBuilder.append(createXmlTag("eopgdmv1",request.eopgdmv1()));
        xmlBuilder.append(createXmlTag("eopgcoc1",request.eopgcoc1()));
        xmlBuilder.append(createXmlTag("eopgice12",request.eopgice12()));
        xmlBuilder.append(createXmlTag("eopgdes2",request.eopgdes2()));
        xmlBuilder.append(createXmlTag("eopgice13",request.eopgice13()));
        xmlBuilder.append(createXmlTag("eopgmoed2",request.eopgmoed2()));
        xmlBuilder.append(createXmlTag("eopgice39",request.eopgice39()));
        xmlBuilder.append(createXmlTag("eopgdmv2",request.eopgdmv2()));
        xmlBuilder.append(createXmlTag("eopgcoc2",request.eopgcoc2()));
        xmlBuilder.append(createXmlTag("eopgice14",request.eopgice14()));
        xmlBuilder.append(createXmlTag("eopgbncoo",request.eopgbncoo()));
        xmlBuilder.append(createXmlTag("eopgice15",request.eopgice15()));
        xmlBuilder.append(createXmlTag("eopgccnt",request.eopgccnt()));
        xmlBuilder.append(createXmlTag("eopgice16",request.eopgice16()));
        xmlBuilder.append(createXmlTag("eopgbande",request.eopgbande()));
        xmlBuilder.append(createXmlTag("eopgice17",request.eopgice17()));
        xmlBuilder.append(createXmlTag("eopgbanbe",request.eopgbanbe()));
        xmlBuilder.append(createXmlTag("eopgice35",request.eopgice35()));
        xmlBuilder.append(createXmlTag("eopgibenf",request.eopgibenf()));
        xmlBuilder.append(createXmlTag("eopgice18",request.eopgice18()));
        xmlBuilder.append(createXmlTag("eopgiorde",request.eopgiorde()));
        xmlBuilder.append(createXmlTag("eopgcnta",request.eopgcnta()));
        xmlBuilder.append(createXmlTag("eopgice19",request.eopgice19()));
        xmlBuilder.append(createXmlTag("eopgbncob",request.eopgbncob()));
        xmlBuilder.append(createXmlTag("eopgice20",request.eopgice20()));
        xmlBuilder.append(createXmlTag("eopgdpag",request.eopgdpag()));
        xmlBuilder.append(createXmlTag("eopgice21",request.eopgice21()));
        xmlBuilder.append(createXmlTag("eopgdgas",request.eopgdgas()));
        xmlBuilder.append(createXmlTag("eopgice22",request.eopgice22()));
        xmlBuilder.append(createXmlTag("eopgmsgo",request.eopgmsgo()));
        xmlBuilder.append(createXmlTag("eopgice23",request.eopgice23()));
        xmlBuilder.append(createXmlTag("eopgcest",request.eopgcest()));
        xmlBuilder.append(createXmlTag("eopgice24",request.eopgice24()));
        xmlBuilder.append(createXmlTag("eopgpais",request.eopgpais()));
        xmlBuilder.append(createXmlTag("eopgice25",request.eopgice25()));
        xmlBuilder.append(createXmlTag("eopglcre",request.eopglcre()));
        xmlBuilder.append(createXmlTag("eopgice26",request.eopgice26()));
        xmlBuilder.append(createXmlTag("eopgaubc",request.eopgaubc()));
        xmlBuilder.append(createXmlTag("eopgice27",request.eopgice27()));
        xmlBuilder.append(createXmlTag("eopgdoca",request.eopgdoca()));
        xmlBuilder.append(createXmlTag("eopgice28",request.eopgice28()));
        xmlBuilder.append(createXmlTag("eopgcnot",request.eopgcnot()));
        xmlBuilder.append(createXmlTag("eopgice29",request.eopgice29()));
        xmlBuilder.append(createXmlTag("eopgnumtn",request.eopgnumtn()));
        xmlBuilder.append(createXmlTag("eopgice30",request.eopgice30()));
        xmlBuilder.append(createXmlTag("eopgbann",request.eopgbann()));
        xmlBuilder.append(createXmlTag("eopgice31",request.eopgice31()));
        xmlBuilder.append(createXmlTag("eopgautr",request.eopgautr()));
        xmlBuilder.append(createXmlTag("eopgice32",request.eopgice32()));
        xmlBuilder.append(createXmlTag("eopgvlri1",request.eopgvlri1()));
        xmlBuilder.append(createXmlTag("eopgice33",request.eopgice33()));
        xmlBuilder.append(createXmlTag("eopgtmov",request.eopgtmov()));
        xmlBuilder.append(createXmlTag("eopgvlro",request.eopgvlro()));
        xmlBuilder.append(createXmlTag("eopgvlre",request.eopgvlre()));
        xmlBuilder.append(createXmlTag("eopgnauti",request.eopgnauti()));
        xmlBuilder.append(createXmlTag("eopgnopr",request.eopgnopr()));
        xmlBuilder.append(createXmlTag("eopgctv",request.eopgctv()));
        xmlBuilder.append(createXmlTag("eopgconf",request.eopgconf()));
        xmlBuilder.append(createXmlTag("eopgice34",request.eopgice34()));
        xmlBuilder.append(createXmlTag("eopgtpnt",request.eopgtpnt()));
        xmlBuilder.append(createXmlTag("eopgdesc",request.eopgdesc()));
        xmlBuilder.append(createXmlTag("eopgvtim",request.eopgvtim()));
        xmlBuilder.append(createXmlTag("eopgvtco",request.eopgvtco()));
        xmlBuilder.append(createXmlTag("eopgvtde",request.eopgvtde()));
        xmlBuilder.append(createXmlTag("eopgvtou",request.eopgvtou()));
        xmlBuilder.append(createXmlTag("eopgvtd",request.eopgvtd()));
        xmlBuilder.append(createXmlTag("eopgvtim2",request.eopgvtim2()));
        xmlBuilder.append(createXmlTag("eopgvtco2",request.eopgvtco2()));
        xmlBuilder.append(createXmlTag("eopgvtde2",request.eopgvtde2()));
        xmlBuilder.append(createXmlTag("eopgvtou2",request.eopgvtou2()));
        xmlBuilder.append(createXmlTag("eopgvtd2",request.eopgvtd2()));
        xmlBuilder.append(createXmlTag("eopganlp",request.eopganlp()));
        xmlBuilder.append(createXmlTag("eopgice36",request.eopgice36()));
        xmlBuilder.append(createXmlTag("eopgproc",request.eopgproc()));
        xmlBuilder.append(createXmlTag("eopgestca",request.eopgestca()));
        xmlBuilder.append(createXmlTag("eopgbyps",request.eopgbyps()));
        xmlBuilder.append(createXmlTag("eopgnord",request.eopgnord()));
        xmlBuilder.append(createXmlTag("eopgcope",request.eopgcope()));
        xmlBuilder.append(createXmlTag("eopgndoc",request.eopgndoc()));
        xmlBuilder.append(createXmlTag("eopgdtvcr",request.eopgdtvcr()));
        xmlBuilder.append(createXmlTag("eopgice37",request.eopgice37()));
        xmlBuilder.append(createXmlTag("eopgstrdf",request.eopgstrdf()));
        xmlBuilder.append(createXmlTag("eopgcntd",request.eopgcntd()));
        xmlBuilder.append(createXmlTag("eopgice40",request.eopgice40()));
        xmlBuilder.append(createXmlTag("eopgbalcd",request.eopgbalcd()));
        xmlBuilder.append(createXmlTag("eopgccbd",request.eopgccbd()));
        xmlBuilder.append(createXmlTag("eopgice41",request.eopgice41()));
        xmlBuilder.append(createXmlTag("eopgmoedd",request.eopgmoedd()));
        xmlBuilder.append(createXmlTag("eopgice42",request.eopgice42()));
        xmlBuilder.append(createXmlTag("eopgporde",request.eopgporde()));
        xmlBuilder.append(createXmlTag("eopgice55",request.eopgice55()));
        xmlBuilder.append(createXmlTag("eopgpbene",request.eopgpbene()));
        xmlBuilder.append(createXmlTag("eopgcido",request.eopgcido()));
        xmlBuilder.append(createXmlTag("eopgice56",request.eopgice56()));
        xmlBuilder.append(createXmlTag("eopgcidb",request.eopgcidb()));
        xmlBuilder.append(createXmlTag("eopgice57",request.eopgice57()));
        xmlBuilder.append(createXmlTag("eopgmoedc",request.eopgmoedc()));
        xmlBuilder.append(createXmlTag("eopgmoepo",request.eopgmoepo()));
        xmlBuilder.append(createXmlTag("eopgmoepd",request.eopgmoepd()));
        xmlBuilder.append(createXmlTag("eopgidmsg",request.eopgidmsg()));
        xmlBuilder.append(createXmlTag("eopgspclc",request.eopgspclc()));
        xmlBuilder.append(createXmlTag("eopgclcod",request.eopgclcod()));
        xmlBuilder.append(createXmlTag("eopgice58",request.eopgice58()));
        xmlBuilder.append(createXmlTag("eopgclmid",request.eopgclmid()));
        xmlBuilder.append(createXmlTag("eopgice59",request.eopgice59()));

        // Tratamento especial para os arrays
        xmlBuilder.append(createArrayTag("eopgcban",request.eopgcban()));
        xmlBuilder.append(createArrayTag("eopgspai",request.eopgspai()));
        xmlBuilder.append(createArrayTag("eopgrcele",request.eopgrcele()));
        xmlBuilder.append(createArrayTag("eopgrvlre",request.eopgrvlre()));
        xmlBuilder.append(createArrayTag("eopgricee",request.eopgricee()));
        xmlBuilder.append(createArrayTag("eopgvlpre",request.eopgvlpre()));
        xmlBuilder.append(createArrayTag("eopgdmpre",request.eopgdmpre()));
        xmlBuilder.append("</XMLOE5080>");
        return xmlBuilder.toString();
    }

    private String createArrayTag(String tagName, List<String> values) {
        if (values == null || values.isEmpty()) return "";
        StringBuilder arrayBuilder = new StringBuilder();
        arrayBuilder.append("<").append(tagName).append(">");
        for (String value : values) {
            arrayBuilder.append("<i>").append(value).append("</i>");
        }
        arrayBuilder.append("</").append(tagName).append(">");
        return arrayBuilder.toString();
    }

    @Override
    protected OE5080Response createResponse(OE5080Response parsedXml, List<String> messages) {
        return new OE5080Response(
                parsedXml.eopgpgmc(),
                parsedXml.eopgttr(),
                parsedXml.eopgjobnc(),
                parsedXml.eopguserc(),
                parsedXml.eopgpgmqc(),
                parsedXml.eopgrcode(),
                parsedXml.eopgestr(),
                parsedXml.eopgaptr(),
                parsedXml.eopghcli(),
                parsedXml.eopgbalm(),
                parsedXml.eopgmoec(),
                parsedXml.eopgtopr(),
                parsedXml.eopgice01(),
                parsedXml.eopgvlr1(),
                parsedXml.eopgice02(),
                parsedXml.eopgmoedv(),
                parsedXml.eopgice03(),
                parsedXml.eopgmoei(),
                parsedXml.eopgcamb(),
                parsedXml.eopgcmbi(),
                parsedXml.eopgcmbn(),
                parsedXml.eopgice44(),
                parsedXml.eopgtemi(),
                parsedXml.eopgice04(),
                parsedXml.eopgrcli(),
                parsedXml.eopgice05(),
                parsedXml.eopgropr(),
                parsedXml.eopgice06(),
                parsedXml.eopgncli(),
                parsedXml.eopgice07(),
                parsedXml.eopgcnto(),
                parsedXml.eopgice08(),
                parsedXml.eopgbalc(),
                parsedXml.eopgccb(),
                parsedXml.eopgice09(),
                parsedXml.eopgdmovm(),
                parsedXml.eopgice43(),
                parsedXml.eopgdtvc(),
                parsedXml.eopgice10(),
                parsedXml.eopgdes1(),
                parsedXml.eopgice11(),
                parsedXml.eopgmoed1(),
                parsedXml.eopgice38(),
                parsedXml.eopgdmv1(),
                parsedXml.eopgcoc1(),
                parsedXml.eopgice12(),
                parsedXml.eopgdes2(),
                parsedXml.eopgice13(),
                parsedXml.eopgmoed2(),
                parsedXml.eopgice39(),
                parsedXml.eopgdmv2(),
                parsedXml.eopgcoc2(),
                parsedXml.eopgice14(),
                parsedXml.eopgbncoo(),
                parsedXml.eopgice15(),
                parsedXml.eopgccnt(),
                parsedXml.eopgice16(),
                parsedXml.eopgbande(),
                parsedXml.eopgice17(),
                parsedXml.eopgbanbe(),
                parsedXml.eopgice35(),
                parsedXml.eopgibenf(),
                parsedXml.eopgice18(),
                parsedXml.eopgiorde(),
                parsedXml.eopgcnta(),
                parsedXml.eopgice19(),
                parsedXml.eopgbncob(),
                parsedXml.eopgice20(),
                parsedXml.eopgdpag(),
                parsedXml.eopgice21(),
                parsedXml.eopgdgas(),
                parsedXml.eopgice22(),
                parsedXml.eopgmsgo(),
                parsedXml.eopgice23(),
                parsedXml.eopgcest(),
                parsedXml.eopgice24(),
                parsedXml.eopgpais(),
                parsedXml.eopgice25(),
                parsedXml.eopglcre(),
                parsedXml.eopgice26(),
                parsedXml.eopgaubc(),
                parsedXml.eopgice27(),
                parsedXml.eopgdoca(),
                parsedXml.eopgice28(),
                parsedXml.eopgcnot(),
                parsedXml.eopgice29(),
                parsedXml.eopgnumtn(),
                parsedXml.eopgice30(),
                parsedXml.eopgbann(),
                parsedXml.eopgice31(),
                parsedXml.eopgautr(),
                parsedXml.eopgice32(),
                parsedXml.eopgvlri1(),
                parsedXml.eopgice33(),
                parsedXml.eopgtmov(),
                parsedXml.eopgvlro(),
                parsedXml.eopgvlre(),
                parsedXml.eopgnauti(),
                parsedXml.eopgnopr(),
                parsedXml.eopgctv(),
                parsedXml.eopgconf(),
                parsedXml.eopgice34(),
                parsedXml.eopgtpnt(),
                parsedXml.eopgdesc(),
                parsedXml.eopgvtim(),
                parsedXml.eopgvtco(),
                parsedXml.eopgvtde(),
                parsedXml.eopgvtou(),
                parsedXml.eopgvtd(),
                parsedXml.eopgvtim2(),
                parsedXml.eopgvtco2(),
                parsedXml.eopgvtde2(),
                parsedXml.eopgvtou2(),
                parsedXml.eopgvtd2(),
                parsedXml.eopganlp(),
                parsedXml.eopgice36(),
                parsedXml.eopgproc(),
                parsedXml.eopgestca(),
                parsedXml.eopgbyps(),
                parsedXml.eopgnord(),
                parsedXml.eopgcope(),
                parsedXml.eopgndoc(),
                parsedXml.eopgdtvcr(),
                parsedXml.eopgice37(),
                parsedXml.eopgstrdf(),
                parsedXml.eopgcntd(),
                parsedXml.eopgice40(),
                parsedXml.eopgbalcd(),
                parsedXml.eopgccbd(),
                parsedXml.eopgice41(),
                parsedXml.eopgmoedd(),
                parsedXml.eopgice42(),
                parsedXml.eopgporde(),
                parsedXml.eopgice55(),
                parsedXml.eopgpbene(),
                parsedXml.eopgcido(),
                parsedXml.eopgice56(),
                parsedXml.eopgcidb(),
                parsedXml.eopgice57(),
                parsedXml.eopgmoedc(),
                parsedXml.eopgmoepo(),
                parsedXml.eopgmoepd(),
                parsedXml.eopgidmsg(),
                parsedXml.eopgspclc(),
                parsedXml.eopgclcod(),
                parsedXml.eopgice58(),
                parsedXml.eopgclmid(),
                parsedXml.eopgice59(),
                parsedXml.eopgcban(),
                parsedXml.eopgspai(),
                parsedXml.eopgrcele(),
                parsedXml.eopgrvlre(),
                parsedXml.eopgricee(),
                parsedXml.eopgvlpre(),
                parsedXml.eopgdmpre(),
                messages
        );
    }

    public List<String> getArrayValues(OE5080Response.ArrayWrapper wrapper) {
        return wrapper != null ? wrapper.getValues() : List.of();
    }
}