package benedict.zhang.torrentkitty.parser;

import benedict.zhang.torrentkitty.parser.impl.JSoupSearchResultParser;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.util.Optional;

public class JSoupParserTest {

    @Test
    public void test(){
//        System.out.println(loadContent());
        final var paser = new JSoupSearchResultParser();
        final var results = paser.parse(loadContent());
        results.forEach(System.out::println);
    }

    private String loadContent(){
        try(final var ios = getClass().getClassLoader().getResourceAsStream("search-gvg.html")){
            return Optional.ofNullable(ios).map(inputStream -> {
                final var fis = new BufferedInputStream(inputStream);
                final var contentBuilder = new StringBuilder();
                try{
                    while (fis.available() > 0){
                        final var data = fis.readAllBytes();
                        contentBuilder.append(new String(data));
                    }
                    return contentBuilder.toString();
                }catch (Exception e){
                    return null;
                }
            }).orElse(null);
        }catch (Exception e){
            return null;
        }
    }
}
