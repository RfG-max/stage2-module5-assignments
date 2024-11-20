package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.lang.StringBuilder;

import assignments.annotations.FullNameProcessorGeneratorAnnotation;
import assignments.annotations.ListIteratorAnnotation;
import assignments.annotations.ReadFullProcessorNameAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalProcessor {
    private String processorName;
    private Long period = 10_000_000_000_000L;
    private String processorVersion;
    private Integer valueOfCheap;
    private Scanner informationScanner;
    private List<String> stringArrayList;

    private static final Logger logger = Logger.getLogger(LocalProcessor.class.getName());

    public LocalProcessor(String processorName, Long period, String processorVersion, Integer valueOfCheap,
                          Scanner informationScanner, LinkedList<String> stringArrayList) {
        this.processorName = processorName;
        this.period = period;
        this.processorVersion = processorVersion;
        this.valueOfCheap = valueOfCheap;
        this.informationScanner = informationScanner;
        this.stringArrayList = stringArrayList;
    }

    public LocalProcessor() {
    }

    @ListIteratorAnnotation
    public void listIterator(LinkedList<String> stringList) {
        if (stringList == null || stringList.isEmpty()) {
            throw new IllegalStateException("String list cannot be null or empty");
        }

        stringArrayList = new LinkedList<>(stringList);
        try{
            for (int i = 0; i < period; i++) {
                logger.info(String.valueOf(stringArrayList.get(i).hashCode()));
            }
        } catch(IndexOutOfBoundsException e){
            logger.severe(e.getMessage());
        } catch (IllegalArgumentException e){
            logger.severe(e.getMessage());
        }
    }

    @FullNameProcessorGeneratorAnnotation
    public String fullNameProcessorGenerator(LinkedList<String> stringList) {
        StringBuilder names = new StringBuilder();
        for (String string : stringList) {
            names.append(string).append(" ");
        }
        processorName = names.toString();
        return processorName;
    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorName(File file) throws FileNotFoundException {
        try {
            informationScanner = new Scanner(file);
            StringBuilder versions = new StringBuilder();
            while (informationScanner.hasNext()) {
                versions.append(informationScanner.nextLine());
            }
            processorVersion = versions.toString();
        } catch (FileNotFoundException e) {
            logger.severe("File not found: " + e.getMessage());
        } finally{
            informationScanner.close();
        }
    }
}
