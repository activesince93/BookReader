package active.since93.pdfreader;

import android.os.Environment;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by darshan.parikh on 18-Sep-15.
 */
public class FileOperations {

    public String read(String fname) {
        String response;
        try {
        	// set your pdf file path.
            String fpath = Environment.getExternalStorageDirectory() + "/" + fname + ".pdf";
            PdfReader reader = new PdfReader(new FileInputStream(fpath));
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            StringWriter strW = new StringWriter();
            TextExtractionStrategy strategy;
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                strW.write(strategy.getResultantText());
            }
            response = strW.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }
}