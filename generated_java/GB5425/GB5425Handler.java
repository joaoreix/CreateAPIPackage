package ao.selenium.bkintegration.core.feature.GB5425;

import ao.selenium.bkintegration.core.bootstrapper.transaction.AbstractTransactionHandler;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GB5425Handler extends AbstractTransactionHandler<GB5425Request, GB5425Response> {

    @Override
    public String getTransactionCode() {
        return "GB5425";
    }

    @Override
    public Class<GB5425Request> getRequestType() {
        return GB5425Request.class;
    }

    @Override
    protected Class<GB5425Response> getResponseType() {
        return GB5425Response.class;
    }

    @Override
    protected String getXmlRootElementName() {
        return "XMLGB5425";
    }

    @Override
    public String buildRequestPayload(GB5425Request request) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<XMLGB5425>");
        xmlBuilder.append(createXmlTag("cspcpgmc",request.cspcpgmc()));
        xmlBuilder.append(createXmlTag("cspcttr",request.cspcttr()));
        xmlBuilder.append(createXmlTag("cspcjobnc",request.cspcjobnc()));
        xmlBuilder.append(createXmlTag("cspcuserc",request.cspcuserc()));
        xmlBuilder.append(createXmlTag("cspcpgmqc",request.cspcpgmqc()));
        xmlBuilder.append(createXmlTag("cspcrcode",request.cspcrcode()));
        xmlBuilder.append(createXmlTag("cspcestr",request.cspcestr()));
        xmlBuilder.append(createXmlTag("cspcaptr",request.cspcaptr()));
        xmlBuilder.append(createXmlTag("cspchcli",request.cspchcli()));
        xmlBuilder.append(createXmlTag("cspcvncr",request.cspcvncr()));
        xmlBuilder.append(createXmlTag("cspcsttr",request.cspcsttr()));
        xmlBuilder.append(createXmlTag("cspcnreg",request.cspcnreg()));
        xmlBuilder.append(createXmlTag("cspcpnome",request.cspcpnome()));
        xmlBuilder.append(createXmlTag("cspcbalc",request.cspcbalc()));
        xmlBuilder.append(createXmlTag("cspcncli",request.cspcncli()));
        xmlBuilder.append(createXmlTag("cspcice01",request.cspcice01()));
        xmlBuilder.append(createXmlTag("cspcconta",request.cspcconta()));
        xmlBuilder.append(createXmlTag("cspcice02",request.cspcice02()));
        xmlBuilder.append(createXmlTag("cspcnumt",request.cspcnumt()));
        xmlBuilder.append(createXmlTag("cspcnom1t",request.cspcnom1t()));
        xmlBuilder.append(createXmlTag("cspcindep",request.cspcindep()));
        xmlBuilder.append(createXmlTag("cspcrcli",request.cspcrcli()));
        xmlBuilder.append(createXmlTag("cspcucodu",request.cspcucodu()));
        xmlBuilder.append(createXmlTag("cspctppos",request.cspctppos()));
        xmlBuilder.append(createXmlTag("cspctpcon",request.cspctpcon()));

        // Tratamento especial para os arrays
        xmlBuilder.append(createArrayTag("cspcflgce",request.cspcflgce()));
        xmlBuilder.append(createArrayTag("cspcclcp",request.cspcclcp()));
        xmlBuilder.append(createArrayTag("cspcwrice",request.cspcwrice()));
        xmlBuilder.append(createArrayTag("cspcindex",request.cspcindex()));
        xmlBuilder.append(createArrayTag("cspccvlr",request.cspccvlr()));
        xmlBuilder.append(createArrayTag("cspcident",request.cspcident()));
        xmlBuilder.append(createArrayTag("cspcdesc",request.cspcdesc()));
        xmlBuilder.append(createArrayTag("cspcvalor",request.cspcvalor()));
        xmlBuilder.append(createArrayTag("cspcttrc",request.cspcttrc()));
        xmlBuilder.append(createArrayTag("cspcvsld",request.cspcvsld()));
        xmlBuilder.append(createArrayTag("cspcmoed",request.cspcmoed()));
        xmlBuilder.append(createArrayTag("cspctpsld",request.cspctpsld()));
        xmlBuilder.append(createArrayTag("cspccps",request.cspccps()));
        xmlBuilder.append(createArrayTag("cspcpgmcs",request.cspcpgmcs()));
        xmlBuilder.append(createArrayTag("cspcrefer",request.cspcrefer()));
        xmlBuilder.append(createArrayTag("cspcatrc",request.cspcatrc()));
        xmlBuilder.append(createArrayTag("cspcmasld",request.cspcmasld()));
        xmlBuilder.append(createArrayTag("cspctcrsl",request.cspctcrsl()));
        xmlBuilder.append("</XMLGB5425>");
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
    protected GB5425Response createResponse(GB5425Response parsedXml, List<String> messages) {
        return new GB5425Response(
                parsedXml.cspcpgmc(),
                parsedXml.cspcttr(),
                parsedXml.cspcjobnc(),
                parsedXml.cspcuserc(),
                parsedXml.cspcpgmqc(),
                parsedXml.cspcrcode(),
                parsedXml.cspcestr(),
                parsedXml.cspcaptr(),
                parsedXml.cspchcli(),
                parsedXml.cspcvncr(),
                parsedXml.cspcsttr(),
                parsedXml.cspcnreg(),
                parsedXml.cspcpnome(),
                parsedXml.cspcbalc(),
                parsedXml.cspcncli(),
                parsedXml.cspcice01(),
                parsedXml.cspcconta(),
                parsedXml.cspcice02(),
                parsedXml.cspcnumt(),
                parsedXml.cspcnom1t(),
                parsedXml.cspcindep(),
                parsedXml.cspcrcli(),
                parsedXml.cspcucodu(),
                parsedXml.cspctppos(),
                parsedXml.cspctpcon(),
                parsedXml.cspcflgce(),
                parsedXml.cspcclcp(),
                parsedXml.cspcwrice(),
                parsedXml.cspcindex(),
                parsedXml.cspccvlr(),
                parsedXml.cspcident(),
                parsedXml.cspcdesc(),
                parsedXml.cspcvalor(),
                parsedXml.cspcttrc(),
                parsedXml.cspcvsld(),
                parsedXml.cspcmoed(),
                parsedXml.cspctpsld(),
                parsedXml.cspccps(),
                parsedXml.cspcpgmcs(),
                parsedXml.cspcrefer(),
                parsedXml.cspcatrc(),
                parsedXml.cspcmasld(),
                parsedXml.cspctcrsl(),
                messages
        );
    }

    public List<String> getArrayValues(GB5425Response.ArrayWrapper wrapper) {
        return wrapper != null ? wrapper.getValues() : List.of();
    }
}