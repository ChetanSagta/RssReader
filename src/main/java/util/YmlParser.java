package util;

import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YmlParser {

    public Object getFileContent(String filename) throws IOException {

        List<Map<String, String>> data = new ArrayList<>();
        Yaml yaml = new Yaml();

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);

        return yaml.load(inputStream);

    }
}
