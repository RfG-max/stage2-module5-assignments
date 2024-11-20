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

    private StringBuilder namesBuilder = new StringBuilder();

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
    public void listIterator(List<String> stringList) {
        if (stringList == null || stringList.isEmpty()) {
            throw new IllegalStateException("String list cannot be null or empty");
        }
        stringArrayList = new LinkedList<>(stringList);
        if (period > stringArrayList.size()){
            for (String s : stringArrayList) {
                logger.info(String.valueOf(s.hashCode()));
            }
        } else {
            for (int i = 0; i < period; i++) {
                logger.info(String.valueOf(stringArrayList.get(i).hashCode()));
            }
        }
    }

    @FullNameProcessorGeneratorAnnotation
    public String fullNameProcessorGenerator(LinkedList<String> stringList) {
        namesBuilder.setLength(0);
        for (String string : stringList) {
            namesBuilder.append(string).append(" ");
        }
        processorName = namesBuilder.toString();
        return processorName;
    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorName(File file) throws FileNotFoundException {
        try {
            informationScanner = new Scanner(file);
            namesBuilder.setLength(0);
            while (informationScanner.hasNext()) {
                namesBuilder.append(informationScanner.nextLine());
            }
            processorVersion = namesBuilder.toString();
        } catch (FileNotFoundException e) {
            logger.severe("File not found: " + e.getMessage());
        } finally{
            if (informationScanner != null) {
                informationScanner.close();
            }
        }
    }
}
