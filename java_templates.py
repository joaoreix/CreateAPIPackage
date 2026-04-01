class JavaTemplates:
    @staticmethod
    def request_template(pkg, name, fields_str):
        return f"""package {pkg};

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public record {name}Request(
{fields_str}
) {{}}"""

    @staticmethod
    def response_template(pkg, name, fields_str, rcode_field):
        return f"""package {pkg};

import ao.selenium.bkintegration.core.bootstrapper.transaction.TransactionResponse;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "XML{name}")
public record {name}Response(
{fields_str},
        List<String> messages
) implements TransactionResponse {{
    public static record ArrayWrapper(
            @JacksonXmlElementWrapper(useWrapping = false)
            @JacksonXmlProperty(localName = "i")
            List<String> i
    ) {{
        public List<String> getValues() {{
            return i != null ? i : List.of();
        }}
    }}

    @Override
    public String getReturnCode() {{
        return this.{rcode_field};
    }}

    @Override
    public List<String> getMessages() {{
        return this.messages;
    }}
}}"""

    @staticmethod
    def handler_template(pkg, name, xml_build_logic, response_mapping):
        return f"""package {pkg};

import ao.selenium.bkintegration.core.bootstrapper.transaction.AbstractTransactionHandler;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class {name}Handler extends AbstractTransactionHandler<{name}Request, {name}Response> {{

    @Override
    public String getTransactionCode() {{
        return "{name}";
    }}

    @Override
    public Class<{name}Request> getRequestType() {{
        return {name}Request.class;
    }}

    @Override
    protected Class<{name}Response> getResponseType() {{
        return {name}Response.class;
    }}

    @Override
    protected String getXmlRootElementName() {{
        return "XML{name}";
    }}

    @Override
    public String buildRequestPayload({name}Request request) {{
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<XML{name}>");
{xml_build_logic}
        xmlBuilder.append("</XML{name}>");
        return xmlBuilder.toString();
    }}

    private String createArrayTag(String tagName, List<String> values) {{
        if (values == null || values.isEmpty()) return "";
        StringBuilder arrayBuilder = new StringBuilder();
        arrayBuilder.append("<").append(tagName).append(">");
        for (String value : values) {{
            arrayBuilder.append("<i>").append(value).append("</i>");
        }}
        arrayBuilder.append("</").append(tagName).append(">");
        return arrayBuilder.toString();
    }}

    @Override
    protected {name}Response createResponse({name}Response parsedXml, List<String> messages) {{
        return new {name}Response(
{response_mapping}
        );
    }}

    public List<String> getArrayValues({name}Response.ArrayWrapper wrapper) {{
        return wrapper != null ? wrapper.getValues() : List.of();
    }}
}}"""